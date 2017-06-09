package com.tsoftlatam.tab.sources.hpbac.controllers;

import com.mongodb.*;
import com.mongodb.util.JSON;
import com.tsoftlatam.tab.readers.hpbac.HPBacRestReader;
import com.tsoftlatam.tab.readers.hpbac.ProfileReaderModel;
import com.tsoftlatam.tab.util.Conexion;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HpBacExtractorRestController {

    private String bacClientProfilesUrl = "http://localhost:8765/extractor/hpbac/getProfiles";

    private HPBacRestReader hpBacRestReader;

    @GetMapping("/results/mongodb/fillhpbacprofiles")
    public void fillHpBacProfiles(){

        BasicDBObject dbObject = new BasicDBObject();

        hpBacRestReader = Feign.builder()
                .decoder(new GsonDecoder())
                .target(HPBacRestReader.class, bacClientProfilesUrl);

        DBCollection collection = Conexion.mongoConnect("10.10.20.176",27017,"HPBACSOURCE","Profiles");

        List<ProfileReaderModel> profilesFromReader = hpBacRestReader.findAllProfiles();

        for (ProfileReaderModel profile: profilesFromReader) {
            dbObject.put("profileName",profile.getProfileName());
            collection.insert(dbObject);
        }

    }

    @GetMapping("/results/mongodb/fillhpbacrawdata")
    public void fillHpBacRawData(){
        try {
            Conexion con = new Conexion();
            String resultado = con.sendGet();
            //System.out.println(mongoHost);

            MongoClient mongo = new MongoClient( "10.10.20.176" , 27017);
            DB db = mongo.getDB("HPBACSOURCE");
            DBCollection table = db.getCollection("Metricas");

            //********************************************************************************

            //JSONParser parser = new JSONParser();
            //Object obj = parser.parse(resultado);
            JSONArray array = new JSONArray(resultado);
            JSONObject jsonObj = new JSONObject();

            //Creación de índice para no duplicar registros
            DBObject idIndex = new BasicDBObject();
            idIndex.put("id",1);

            DBObject idIndexOptions = new BasicDBObject();
            idIndexOptions.put("unique", true);

            table.createIndex(idIndex, idIndexOptions);


            List<JSONObject> list = new ArrayList<JSONObject>();

            for(int i=0; i<array.length(); i++){
                // jsonObj  = array.getJSONObject(i);
                list.add(array.getJSONObject(i));
                String c;
                c= array.getString(i).toString();
                //System.out.println(c);
                DBObject dbObject = (DBObject) JSON.parse(c);
                try {
                    table.insert(dbObject);
                }
                catch (DuplicateKeyException e)
                {
                    System.out.println(e.getMessage());
                }

                //System.out.println(jsonObj.getString("id"));
                //System.out.println(jsonObj.getString("transactionStatus"));
            }

//***************************************************************************************

            //TODO: modificar para que se inserte un array de DBObjetcs, sino no anda
            //table.insert(dbObject);

            //System.out.println("CONSULTA DE LOS DATOS");
            //System.out.println(resultado);


            DBCursor cursorDoc = table.find();
            while (cursorDoc.hasNext()) {
                System.out.println(cursorDoc.next());
            }

            System.out.println("Done");

        }catch(Exception e){
            e.printStackTrace();
        }
    }


}

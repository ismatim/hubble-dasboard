package com.tsoftlatam.tab.sources;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by david.malagueno on 30/5/2017.
 */
public class Conexion {
    public static void main(String[] args) {


        try {
            Conexion con = new Conexion();
            String resultado = con.sendGet();

            MongoClient mongo = new MongoClient( "10.10.20.176" , 27017 );
            DB db = mongo.getDB("HPBACSOURCE");
            DBCollection table = db.getCollection("Metricas");

          //********************************************************************************

            //JSONParser parser = new JSONParser();
            //Object obj = parser.parse(resultado);
            JSONArray array = new JSONArray(resultado);
            JSONObject jsonObj = new JSONObject();

            List<JSONObject> list = new ArrayList<JSONObject>();

            for(int i=0; i<array.length(); i++){
               // jsonObj  = array.getJSONObject(i);
                list.add(array.getJSONObject(i));
                String c;
                c= array.getString(i).toString();
                //System.out.println(c);
                DBObject dbObject1 = (DBObject) JSON.parse(c);
                table.insert(dbObject1);
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

    private String sendGet() throws Exception  {
        //TODO: Sacar por parametro
        String url = "http://localhost:8765/res";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}

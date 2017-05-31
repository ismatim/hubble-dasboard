package com.tsoftlatam.tab.sources;

import com.mongodb.*;
import com.mongodb.util.JSON;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by david.malagueno on 30/5/2017.
 */
public class Conexion {
    public static void main(String[] args) {

        try {
            Conexion con = new Conexion();
            String resultado = con.sendGet();

            MongoClient mongo = new MongoClient( "10.10.20.176" , 27017 );

            DB db = mongo.getDB("testdb1");

            DBCollection table = db.getCollection("Metricas");

            DBObject dbObject = (DBObject) JSON.parse(resultado);

            //TODO: modificar para que se inserte un array de DBObjetcs, sino no anda
            //table.insert(dbObject);

            DBCursor cursorDoc = table.find();
            while (cursorDoc.hasNext()) {
                System.out.println(cursorDoc.next());
            }

            System.out.println("Done");

        }catch(Exception e){
            e.printStackTrace();
        }





        /*
        BasicDBObject document = new BasicDBObject();
        document.put("name", "mkyong");
        document.put("age", 30);
        document.put("createdDate", new Date());
        table.insert(document);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "mkyong");

        DBCursor cursor = table.find(searchQuery);

        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }

        System.out.println(db.getCollectionNames());
        */
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

package com.tsoftlatam.tab.util;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conexion {

    public String sendGet() throws Exception  {
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

    public static DBCollection mongoConnect(String host, int port,String db,String collection){

        MongoClient mongo = new MongoClient( host , port);
        DB database = mongo.getDB(db);
        DBCollection table = database.getCollection(collection);

        return table;

    }
}

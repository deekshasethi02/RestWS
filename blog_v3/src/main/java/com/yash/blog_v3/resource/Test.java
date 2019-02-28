package com.yash.blog_v3.resource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {
    
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try {         
            URL oracle = new URL("http://10.26.9.180:8080/restuserdemo/webapi/users"); // URL to Parse
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {               
                JSONArray a = (JSONArray) parser.parse(inputLine);
                
                // Loop through each item
                for (Object o : a) {
                    JSONObject jsonObject = (JSONObject) o;

                    Long id = (Long) jsonObject.get("id");
                    System.out.println("ID : " + id);

                    String firstName = (String) jsonObject.get("firstName");
                    System.out.println("First name : " + firstName);

                    System.out.println("\n");
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }   
    }   
}
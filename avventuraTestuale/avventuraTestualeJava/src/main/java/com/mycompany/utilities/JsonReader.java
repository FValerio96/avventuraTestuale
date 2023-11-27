/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.utilities;

import com.mycompany.gameObjects.Room;
import com.mycompany.manager.GameManager;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.apache.commons.io.IOUtils;

/**
 * @author PPiC
 */
public class JsonReader {

    private static String roomPath = "src\\main\\java\\com\\mycompany\\resources\\gameObjectsJson\\room.json";
  
    //main per il testing
    public static void main(String[] args) throws JSONException, IOException {
        
    }
 
    //funzione di caricamento dei dati delle stanza ad inizio gioco
    public static void roomsInit() throws JSONException, IOException {
        JSONArray rooms = readFile(roomPath);
        Map<Integer, Room> roomsMap = new HashMap<>();

        for (int i = 0; i < rooms.length(); i++) {
            JSONObject room = rooms.getJSONObject(i);
            int id = room.getInt("id");
            String name = room.getString("name");
            String description = room.getString("description");
            int north = room.getInt("north");
            int south = room.getInt("south");
            int east = room.getInt("east");
            int west = room.getInt("west");
            Room roomObj = new Room(id, name, description);
            roomObj.addDirections(north, east, west, south);
            roomsMap.put(id, roomObj);        }
        //passo il set di stanze create al gameManager
        GameManager.loadRooms(roomsMap);
    }

    public static JSONArray readFile(String path) throws JSONException, IOException {
        File f = new File(path);
        JSONArray jsonArray = null;
        if (f.exists()) {
            InputStream is = new FileInputStream(f);
            String jsonTxt = IOUtils.toString(is, "UTF-8");
            jsonArray = new JSONArray(jsonTxt);
        }
        return (jsonArray);
    }

}

    /*
    public static void test () throws JSONException, IOException {
        JSONArray jsonArray = readFile(roomPath);
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                // Ottenere l'oggetto JSON corrente
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Stampa delle informazioni dell'oggetto
                System.out.println("Elemento " + (i + 1) + ":");
                System.out.println("ID: " + jsonObject.getInt("id"));
                System.out.println("Name: " + jsonObject.getString("name"));
                System.out.println("Description: " + jsonObject.getString("description"));
                System.out.println("North: " + jsonObject.getInt("north"));
                System.out.println("South: " + jsonObject.getInt("south"));
                System.out.println("East: " + jsonObject.getInt("east"));
                System.out.println("West: " + jsonObject.getInt("west"));
                System.out.println("-------------");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
*/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.utilities;

import com.mycompany.gameObjects.Room;
import com.mycompany.gameObjects.Persona;
import com.mycompany.gameObjects.Stuff;
import com.mycompany.manager.GameManager;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.apache.commons.io.IOUtils;

public class JsonReader {

    private static final String roomPath = "src\\main\\java\\com\\mycompany\\resources\\gameObjectsJson\\room.json";
    private static final String npcsPath = "src\\main\\java\\com\\mycompany\\resources\\gameObjectsJson\\persona.json";
    private static final String stuffsPath = "src\\main\\java\\com\\mycompany\\resources\\gameObjectsJson\\stuff.json";
    
    //main per il testing
    public static void main(String[] args) throws JSONException, IOException {

    }

    /*
    I metodi init leggono da un json le informazioni, caricano in una
    struttura d'appoggio le stesse e tramite metodi statitici messi a 
    disposizione dal GameManager gli passano le strutture caricate.
    */
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
            roomsMap.put(id, roomObj);
        }
        //passo il set di stanze create al gameManager
        GameManager.loadRooms(roomsMap);
    }

    public static void npcsInit() throws JSONException, IOException {
        JSONArray npcs = readFile(npcsPath);
        Map<Integer, Persona> npcsMap = new HashMap<>();

        for (int i = 0; i < npcs.length(); i++) {
            JSONObject npc = npcs.getJSONObject(i);
            int id = npc.getInt("id");
            String name = npc.getString("name");
            String toSay = npc.getString("toSay");
            int Room = npc.getInt("room");
            Persona persona = new Persona(toSay, id, Room, name);
            npcsMap.put(id, persona);
        }
        //passo il set di stanze create al gameManager
        GameManager.loadPersonas(npcsMap);
    }
    
    public static void stuffInit() throws JSONException, IOException {
        JSONArray stuffs = readFile(stuffsPath);
        Map<Integer, Stuff> stuffsMap = new HashMap<>();
        
        for(int i = 0; i < stuffs.length(); i++) {
            JSONObject stuff = stuffs.getJSONObject(i);
            String name = stuff.getString("name");
            int id = stuff.getInt("id");
            int room = stuff.getInt("room");
            boolean takable = stuff.getBoolean("takable");
            String description = stuff.getString("description");
            Stuff obj = new Stuff(takable, description, id, room, name);
            stuffsMap.put(id, obj);
        }
        GameManager.loadStuff(stuffsMap);
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

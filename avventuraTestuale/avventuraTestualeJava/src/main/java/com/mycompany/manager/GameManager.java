/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.manager;

import com.mycompany.avventuratestualejava.Parser;
import com.mycompany.gameObjects.Room;
import com.mycompany.utilities.JsonReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author PPiC
 */
public class GameManager {

    private static Room currentRoom;
    private static Map<Integer, Room> rooms;
    //static Set<Direction> directions;
    //static Set<Npc> npcs;
    //static Set<Item> items;
    private static boolean isGameRunning = false;
    private static String input;

    public static void main(String[] args) throws JSONException, IOException {
        JsonReader.roomsInit();
        printRooms();
    }

    public static void launcher() {
        Parser parser = new Parser();
        Scanner scan = new Scanner(System.in);
        try {
            JsonReader.roomsInit();
            //aggiungi caricamento degli altri json
        } catch (JSONException | IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, "Errore durante l'inizializzazione delle stanze: {0}", ex.getMessage());
        }
        while (true) {
            System.out.println("cosa vuoi fare?");
            GameManager.input = scan.nextLine();
            parser.parserGame(input);
        }

    }

    public static void printRooms() {
        System.out.println("Lista delle stanze:");
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            int roomId = entry.getKey();
            Room room = entry.getValue();
            System.out.println("ID: " + roomId + ", Nome: " + room.getName() + ", Descrizione: " + room.getDescription());
        }
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(Room currentRoom) {
        GameManager.currentRoom = currentRoom;
    }

    public static Map<Integer, Room> getRooms() {
        return rooms;
    }

    public static void setRooms(Map<Integer, Room> rooms) {
        GameManager.rooms = rooms;
    }

    public static boolean isIsGameRunning() {
        return isGameRunning;
    }

    public static void setIsGameRunning(boolean isGameRunning) {
        GameManager.isGameRunning = isGameRunning;
    }

    public static void loadRooms(Map<Integer, Room> rooms) {
        //read from a json file rooms and put them in the set
        GameManager.rooms = rooms;
    }
}

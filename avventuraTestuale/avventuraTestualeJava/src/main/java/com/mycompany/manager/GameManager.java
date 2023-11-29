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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author PPiC
 */
public class GameManager {

    private static int currentRoom;
    private static Map<Integer, Room> rooms;
    static Set<String> directions = Set.of("nord", "sud", "est", "ovest");
    //static Set<Npc> npcs;
    //static Set<Item> items;
    private static boolean isGameRunning = false;
    private static String input;
    private static String comando;  

    public static void main(String[] args) throws JSONException, IOException {
        launcher();
    }

    public static void launcher() {
        Parser parser = new Parser();
        Scanner scan = new Scanner(System.in);
        try {
            JsonReader.roomsInit();
            printRooms();
            //inizializzazione gioco diviso in nuova o carica partita
            //TODO: INSERISCI CARICA PARTITA
            nuovaPartita();
            
            //aggiungi caricamento degli altri jsonObjects
        } catch (JSONException | IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, "Errore durante l'inizializzazione delle stanze: {0}", ex.getMessage());
        }

        while (true) {
            System.out.println("cosa vuoi fare capitano?");
            GameManager.input = scan.nextLine();
            comando = parser.parserGame(input);
            System.out.println(comando);
            comandoManager(comando);
        }
    }
    
    public static void comandoManager(String comando) {
        if(directions.contains(comando)){
            cambioStanza(comando);
            System.out.println(rooms.get(currentRoom).getDescription());         
        }
        
    }
    
    public static void cambioStanza(String direzione) {
        int temp = currentRoom;
        switch(direzione) {
            case "nord":
                currentRoom = rooms.get(currentRoom).getNord();
                break;
            case "sud":
                currentRoom = rooms.get(currentRoom).getSud();
                break;
            case "est": 
                currentRoom = rooms.get(currentRoom).getEst();
                break;
            case "ovest":
                currentRoom = rooms.get(currentRoom).getOvest();
                break;              
        }
        //gestione caso fuori mappa
        if(currentRoom == 0) {
            System.out.println("questa strada non mi porterà da nessuna parte.. \n resterò qui: \n");
            currentRoom = temp;
        }
    }
    

    public static void nuovaPartita(){
        //inizio gioco nella prima stanza.
        currentRoom = 1;
        System.out.println(rooms.get(currentRoom).getDescription());
        
    }

    public static void printRooms() {
        System.out.println("Lista delle stanze:");
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            int roomId = entry.getKey();
            Room room = entry.getValue();
            System.out.println("ID: " + roomId + ", Nome: " + room.getName() + ", Descrizione: " + room.getDescription() +
                    "nord:" + room.getNord() + "est: " + room.getEst() + "ovest: " + room.getOvest() + "sud: " + room.getSud());
        }
    }

    public static int getCurrentRoom() {
        return currentRoom;
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

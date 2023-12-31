/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.manager;

import com.mycompany.fileManager.FileManager;
import com.mycompany.parser.Parser;
import com.mycompany.gameObjects.Room;
import com.mycompany.gameObjects.Npcs;
import com.mycompany.gameObjects.Stuff;
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
 */
public class GameManager {

    private static int currentRoom;
    private static Map<Integer, Room> rooms;
    //N.B. npcs e stuffs hanno chiave la room in cui si trovano
    private static Map<Integer, Npcs> npcs;
    private static Map<Integer, Stuff> stuffs;
    private static final Set<String> directions = Set.of("nord", "sud",
            "est", "ovest");
    private static String input;

    public static void main(String[] args) throws JSONException, IOException {
        FileManager.fileCheck();
        launcher();
    }

    public static void launcher() {
        Parser parser = new Parser();
        Scanner scan = new Scanner(System.in);
        try {
            JsonReader.roomsInit();
            JsonReader.npcsInit();
            JsonReader.stuffInit();
            //inizializzazione gioco diviso in nuova o carica partita
            //TODO: INSERISCI CARICA PARTITA
            nuovaPartita();
            DBManager.DBStart();
            DBManager.getRoomDescriptionFromDB();
            //DBSTART
        } catch (JSONException | IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE,
                    "Errore durante la letture di un json",
                    ex.getMessage());
        }
        while (true) {
            System.out.println("\n cosa vuoi fare capitano?");
            GameManager.input = scan.nextLine();
            parser.parserGame(input);
        }
    }

    public static void comandoManager(String comando) {
        if (directions.contains(comando)) {
            cambioStanza(comando);
            System.out.println(rooms.get(currentRoom).getDescription());
        } else {

        }

    }

    public static void cambioStanza(String direzione) {
        int temp = currentRoom;
        switch (direzione) {
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
        if (currentRoom == 0) {
            System.out.println("questa strada non mi porterà da nessuna "
                    + "parte.. \n resterò qui. ");
            currentRoom = temp;
        } else {
            DBManager.getRoomDescriptionFromDB();
        }
    }

    //elenca gli npc e/o ogegetti presenti nella room
// Elenca gli npc e/o oggetti presenti nella room
    public static String getOsserva(int room) {
        StringBuilder oss = new StringBuilder();
        int is = 0;

        if (stuffs.containsKey(room)) {
            oss.append("c'è un ").append(stuffs.get(room).getName()).append("\n");
            is++;
        }

        if (npcs.containsKey(room)) {
            oss.append("vedo ").append(npcs.get(room).getName()).append("\n");
            is++;
        }

        if (is == 0) {
            oss.append("maledizione non c'è niente e nessuno qui !");
        }

        return oss.toString();
    }

    private static void nuovaPartita() {
        //inizio gioco nella prima stanza.
        currentRoom = 1;
        

    }

    public static void printRooms() {
        System.out.println("Lista delle stanze:");
        rooms.forEach((roomId, room)
                -> System.out.println("ID: " + room.getId() + ", Nome: " + room.getName()
                        + ", Descrizione: " + room.getDescription() + ", nord:"
                        + room.getNord() + ", est: " + room.getEst() + ", ovest: "
                        + room.getOvest() + ", sud: " + room.getSud()));
    }

    public static void printPersonas() {
        System.out.println("Lista degli npcs:");
        for (Map.Entry<Integer, Npcs> entry : npcs.entrySet()) {
            int personaID = entry.getKey();
            Npcs persona = entry.getValue();
            System.out.println("ID: " + persona.getId() + ", Nome: "
                    + persona.getName() + ", to say: " + persona.getToSay()
                    + " room: " + persona.getRoom());
        }
    }

    public static void printstuffs() {
        System.out.println("Lista degli stuffs:");
        for (Map.Entry<Integer, Stuff> entry : stuffs.entrySet()) {
            int stuffID = entry.getKey();
            Stuff stuff = entry.getValue();
            System.out.println("ID: " + stuffID + ", Nome: "
                    + stuff.getName() + ", descrizione: "
                    + stuff.getDescription() + " room: "
                    + stuff.getRoom() + " takable: "
                    + stuff.getTakable() + " inventory: "
                    + stuff.getInventory());

        }
    }

    public static void printInventory() {
        System.out.println("INVENTARIO: \n");
        boolean empty = true;
        for (Map.Entry<Integer, Stuff> entry : stuffs.entrySet()) {
            int stuffID = entry.getKey();
            Stuff stuff = entry.getValue();
            if (stuff.getInventory()) {
                empty = false;
                System.out.println("Nome: " + stuff.getName()
                        + ", descrizione: " + stuff.getDescription());
            }

        }
        if (empty) {
            System.out.println("Inventario vuoto.. ");
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

    public static void loadRooms(Map<Integer, Room> rooms) {
        GameManager.rooms = rooms;
    }

    public static void loadStuff(Map<Integer, Stuff> stuff) {
        GameManager.stuffs = stuff;
    }

    public static void loadPersonas(Map<Integer, Npcs> persona) {
        GameManager.npcs = persona;
    }

    public static String getNpcNameInRoom() {
        Npcs npc = npcs.get(currentRoom);
        return npc != null ? npc.getName() : "null";
    }
    
    public static boolean getNpcAliveInRoom() {
        return npcs.get(currentRoom).isAlive();
    }

    public static void setDeadNpcInRoom(){
        npcs.get(currentRoom).setDead();
    }
    public static String getStuffNameInRoom() {
        Stuff stuff = stuffs.get(currentRoom);
        return stuff != null ? stuff.getName() : "null";
    }

    public static String getNpcToSayInRoom() {
        return npcs.get(currentRoom).getToSay();
    }

    public static void raccogli() {
        stuffs.get(currentRoom).eseguiAzione();
    }
}

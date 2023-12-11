/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.manager;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileManager {

    private static final String RISORSE_FILE = "./src/main/java/com/mycompany/resources/gameObjectsJson";
    private static final String roomPath = "./src/main/java/com/mycompany/resources/gameObjectsJson/room.json";
    private static final String npcsPath = "./src/main/java/com/mycompany/resources/gameObjectsJson/Persona.json";
    private static final String stuffsPath = "./src/main/java/com/mycompany/resources/gameObjectsJson/stuff.json";

    private static ArrayList<String> toDownload = new ArrayList();
    private static ArrayList<String> toCheck = new ArrayList();

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String args[]) {
        System.out.println("ciao");
        fileCheck();
    }

    private static void directoryCreator() {
        File directory = new File(RISORSE_FILE);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    /**
     * Checking for the presence of files in the game local repo, in case of
     * absence they are downloaded from the server.
     *
     * @return map<filename, path>
     */
    public static Map fileCheck() {

        directoryCreator();

        File room = new File(roomPath);
        File npc = new File(npcsPath);
        File stuff = new File(stuffsPath);
        
        if (!room.exists()) {
            toDownload.add(roomPath);
            System.out.println("file > osserva not found" + toDownload.get(0));
        }
        if (!npc.exists()) {
            toDownload.add(npcsPath);
            System.out.println("file > osserva not found" + toDownload.get(1));
        }
        if (!stuff.exists()) {
            toDownload.add(stuffsPath);
            System.out.println("file > osserva not found" + toDownload.get(2));
        }

        if (toDownload.size() > 0) {
            System.out.println("rilevati " + toDownload.size() + " file mancanti o corrotti , ripristino in corso...");
            for (int i = 0; i < toDownload.size(); i++) {
                try (Socket socket = new Socket("localhost", 5000)) {
                    dataInputStream = new DataInputStream(socket.getInputStream());
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                    String filePath = toDownload.get(i);
                    out.println(filePath);
                    System.out.println("Richiesto downlaod >" + filePath);
                    receiveFile(filePath, dataInputStream);
                    System.out.println("scaricati " + i + "file");

                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //connection to server

        }

        //creation Map <nameFile, path>
        Map filePahts = new HashMap();
        filePahts.put(room.getName(), room.getPath());
        filePahts.put(npc.getName(), npc.getPath());
        filePahts.put(stuff.getName(), stuff.getPath());
        

        return filePahts;
    }

    private static void receiveFile(String filePath, DataInputStream dataInputStream) throws Exception {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        // read file size
        long size = dataInputStream.readLong();
        byte[] buffer = new byte[4 * 1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            // read upto file size
            size -= bytes;
        }
        //fileOutputStream.close();

    }

    public static ArrayList<String> getToDownlaod() {
        System.out.println("dimensione array toDownload -->" + toDownload.size());
        return toDownload;
    }
}

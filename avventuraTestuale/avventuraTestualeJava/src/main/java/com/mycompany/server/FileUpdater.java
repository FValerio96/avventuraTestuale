/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server;

import com.mycompany.fileManager.FileManager;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flava
 */
public class FileUpdater {

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    private static final String ROOM_PATH = "./src/main/java/com/mycompany/server/resources/room.json";
    private static final String NPCS_PATH = "./src/main/java/com/mycompany/server/resources/Persona.json";
    private static final String STUFFS_PATH = "./src/main/java/com/mycompany/server/resources/stuff.json"; 

    public static void main(String[] args) {
        try ( ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("listening to port:5000");
            //resto in attesa di richiesta 
            Socket clientSocket;
            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println(clientSocket + " connected.");
                dataInputStream = new DataInputStream(clientSocket.getInputStream());
                dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //reading the client request
                String filePath = in.readLine();
                System.out.println("****** Sending file ******* \n");
                sendFile(filePath, clientSocket, dataOutputStream);

                //dataInputStream.close();
                //dataOutputStream.close();
                //clientSocket.close();
                //System.out.println(clientSocket + " disconnected.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendFile(String toDownlaod, Socket clientSocket, DataOutputStream dataOutputStream) throws IOException {
        try {
            String serverPathFile = getServerPath(getNameFileByPath(toDownlaod));
            System.out.println("going to download > " + serverPathFile);
            Thread t = new RequestThread(clientSocket, serverPathFile, dataOutputStream);
            t.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * cause the client send the missing path, we have to know only the name of
     * file, to download it from the server local path
     *
     * @param path
     * @return a string with the name of file
     */
    private static String getNameFileByPath(String path) {
        StringTokenizer tokenizer = new StringTokenizer(path, "/");
        List<String> tokenList = new ArrayList<>();

        //append tokens in list 
        while (tokenizer.hasMoreTokens()) {
            tokenList.add(tokenizer.nextToken());

        }

        return tokenList.get(tokenList.size() - 1);
    }

    private static List<String> getPathList() {
        List<String> pathList = new ArrayList<>();
        pathList.add(ROOM_PATH);
        pathList.add(STUFFS_PATH);
        pathList.add(NPCS_PATH);
        
        return pathList;
    }

    /**
     * @param fileName search the corrispondent server path
     */
    private static String getServerPath(String fileName) {
        List<String> pathList = getPathList();
        System.out.println("searching for " + fileName);
        for (int i = 0; i < pathList.size(); i++) {
            if (pathList.get(i).contains(fileName)) {
                System.out.println("send this> " + pathList.get(i));
                return pathList.get(i);
            }
        }

        return "-1";
    }

}

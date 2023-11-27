/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.avventuratestualejava;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import java.util.HashMap;

/**
 *
 * @author flava
 */
public class Loader {

    @SuppressWarnings("unchecked")
    //carica il dizionario dalla directory, poi con Map.class lo deserializza
    //come oggetto Map
    public static Map<String, List<String>> loadDictionary(String dictionary) {
        try {
            return new Gson().fromJson(new FileReader(
                    "src\\main\\java\\com\\mycompany\\resources\\dictionary\\" + dictionary + ".json"),
                    Map.class);
        } catch (IOException e) {
            System.out.println("non ho ritornato niente");
            return null;
        }

    }

    public static void main(String[] args) {
        Map<String, List<String>> noObjAction = new HashMap<>();
        noObjAction = Loader.loadDictionary("noObjAction");
        printMap(noObjAction);
    }

    public static void printMap(Map<String, List<String>> map) {
        if (map == null) {
            System.out.println("La mappa è null.");
            return;
        }

        if (map.isEmpty()) {
            System.out.println("La mappa è vuota.");
            return;
        }

        System.out.println("Contenuti della mappa:");
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String chiave = entry.getKey();
            List<String> listaDiValori = entry.getValue();

            System.out.println("Chiave: " + chiave);

            if (listaDiValori != null && !listaDiValori.isEmpty()) {
                System.out.println("  Valori:");
                for (String valore : listaDiValori) {
                    System.out.println("    " + valore);
                }
            } else {
                System.out.println("  Lista di valori è vuota o null.");
            }
        }
    }
}

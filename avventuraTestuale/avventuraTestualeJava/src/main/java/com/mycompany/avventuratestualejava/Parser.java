package com.mycompany.avventuratestualejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private ArrayList<String> stopWord;
    private Map<String, List<String>> directions;
    private Map<String, List<String>> noObjAction;
    private Map<String, List<String>> ObjAction;

    public Parser() {
        this.stopWord = Loader.loadList("stopWords");
        this.directions = Loader.loadDictionary("directions");
        this.noObjAction = Loader.loadDictionary("noObjAction");
    }

    //main for testing
    public static void main(String[] args) {
        Parser p = new Parser();
        p.parserGame("vai a north");
    }

    public String parserGame(String input) {
        convertiInMinuscolo(input);
        ArrayList<String> inputList = ottieniListaParole(input);
        rimuoviStopword(inputList);
        for (String parola : inputList) {
            System.out.println(parola + "\n");
        }
        return checkWords(inputList);
    }

    public Map<String, List<String>> getNoObjAction() {
        return noObjAction;
    }

    public void setNoObjAction(Map<String, List<String>> noObjAction) {
        this.noObjAction = noObjAction;
    }

    public Map<String, List<String>> getObjAction() {
        return ObjAction;
    }

    public void setObjAction(Map<String, List<String>> ObjAction) {
        this.ObjAction = ObjAction;
    }

    private void convertiInMinuscolo(String input) {
        input.toLowerCase();
    }

    private ArrayList<String> ottieniListaParole(String frase) {
        String[] arrayParole = frase.split("\\s+");
        ArrayList<String> listaParole = new ArrayList<>(Arrays.asList(arrayParole));
        return listaParole;
    }

    private void rimuoviStopword(ArrayList<String> listaParole) {
        listaParole.removeAll(stopWord);
    }

    public String checkWords(List<String> inputWords) {
        for (Map.Entry<String, List<String>> entry : directions.entrySet()) {
            String directionKey = entry.getKey();
            List<String> directionValues = entry.getValue();

            for (String inputWord : inputWords) {
                if (directionValues.contains(inputWord)) {
                    //delete me dopo aver testato tutto il codice
                    //System.out.println("direzione " + directionKey);
                    return directionKey;
                }
            }
        }
        return "ma come parli pirata?";
    }

    
}

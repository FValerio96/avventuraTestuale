package com.mycompany.avventuratestualejava;

import static com.mycompany.manager.GameManager.cambioStanza;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    public static void noObjActionContains() {

    }

    //main for testing
    public static void main(String[] args) {
        Parser p = new Parser();
    }

    public void parserGame(String input) {
        convertiInMinuscolo(input);
        ArrayList<String> inputList = ottieniListaParole(input);
        rimuoviStopword(inputList);
        for (String parola : inputList) {
            System.out.println(parola + "\n");
        }
        checkWords(inputList);
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

    public void checkWords(List<String> inputWords) {
        /*
        presa una chiave e la lista di valori associata, controllo se l'input
        e' una dei sinonimi della chiave, restituisco la chiave.
         */
        for (Map.Entry<String, List<String>> entry : directions.entrySet()) {
            String directionKey = entry.getKey();
            List<String> directionValues = entry.getValue();

            for (String inputWord : inputWords) {
                if (directionValues.contains(inputWord)) {
                    cambioStanza(inputWord);
                }
            }
        }
        //return "ma come parli pirata?";
    }

}

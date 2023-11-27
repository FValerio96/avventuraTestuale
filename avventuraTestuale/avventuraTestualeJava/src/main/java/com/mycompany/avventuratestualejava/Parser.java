package com.mycompany.avventuratestualejava;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private Map<String, List<String>> stopWord;
    private Map<String, List<String>> noObjAction;
    private Map<String, List<String>> ObjAction;
    private String directionPattern = "(nord)|(sud)|(est)|(ovest)";

    public Parser() {
        this.stopWord = Loader.loadDictionary("stopWord");
        this.noObjAction = Loader.loadDictionary("noObjAction");
    }

    //main for testing
    public static void main (String[] args) {
        Parser p = new Parser();
        p.parserGame("Ciao osserva tutto");
    }
    public void parserGame(String input) {
        convertiInMinuscolo(input);
        List<String> inputList = ottieniListaParole(input);
        rimuoviStopword(inputList, stopWord);
        for(String parola : inputList) {
            System.out.println("word" + parola + "\n");
        }
        
        //System.out.println();
        //System.out.println(checkWords(inputList));
        
    }

    public Map<String, List<String>> getStopWord() {
        return stopWord;
    }

    public void setStopWord(Map<String, List<String>> stopWord) {
        this.stopWord = stopWord;
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

    public String getDirectionPattern() {
        return directionPattern;
    }

    public void setDirectionPattern(String directionPattern) {
        this.directionPattern = directionPattern;
    }

    private void convertiInMinuscolo(String input) {
          input.toLowerCase();
    }

    private List<String> ottieniListaParole(String frase) {
        String[] arrayParole = frase.split("\\s+");
        List<String> listaParole = Arrays.asList(arrayParole);
        return listaParole;
    }

    private void rimuoviStopword(List<String> listaParole,
            Map<String, List<String>> stopWord) {
        for (List<String> stopWordsList : stopWord.values()) {
            listaParole.removeAll(stopWordsList);
        }
    }

    public String checkWords(List<String> inputWords) {
        
        return "ritenta";
    }
}

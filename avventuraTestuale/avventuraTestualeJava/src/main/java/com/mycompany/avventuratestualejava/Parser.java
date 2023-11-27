package com.mycompany.avventuratestualejava;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author
 */
/**
 * idea: presa la frase, si procede con una rimozione di stopwords ed una
 * conversione al minuscolo. A quel punto: 1. controllo se è uno spostamento 2.
 * controllo se ci sono parole nella frase sufficienti a compiere un' azione,
 * come "osserva" 3. altrimenti vedo se sono in una frase composta "usa +
 * oggetto"
 *
 */
public class Parser {

    //to do usa una file di stopword, le carichi in una lista e confronti
    private static Map<String, List<String>> stopWord;
    //azione che non necessità di un contesto, come "osserva"
    private static Map<String, List<String>> noObjAction;
    //azione che necessità di un soggetto come "usa + oggetto da usare"
    private static Map<String, List<String>> ObjAction;
    private static String directionPattern = "(nord)|(sud)|(est)|(ovest)";

    public Parser() {
        this.stopWord = Loader.loadDictionary("stopWord");
        this.noObjAction = Loader.loadDictionary("noObjAction");
    }

    //metodo del parser per riconoscere il comando e (TODO) lo lancia
    public void parserGame(String input) {
        convertiInMinuscolo(input);
        List<String> inputList = ottieniListaParole(input);
        rimuoviStopword(inputList, stopWord);
        System.out.println(checkWords(inputList));
    }

    
    public static Map<String, List<String>> getStopWord() {
        return stopWord;
    }

    public static void setStopWord(Map<String, List<String>> stopWord) {
        Parser.stopWord = stopWord;
    }

    public static Map<String, List<String>> getNoObjAction() {
        return noObjAction;
    }

    public static void setNoObjAction(Map<String, List<String>> noObjAction) {
        Parser.noObjAction = noObjAction;
    }

    public static Map<String, List<String>> getObjAction() {
        return ObjAction;
    }

    public static void setObjAction(Map<String, List<String>> ObjAction) {
        Parser.ObjAction = ObjAction;
    }

    public static String getDirectionPattern() {
        return directionPattern;
    }

    public static void setDirectionPattern(String directionPattern) {
        Parser.directionPattern = directionPattern;
    }

    /*
    public static void main(String[] args) {
        Parser parser = new Parser();
        List<String> phrases = new ArrayList<>();
        phrases.add("pls");
        phrases.add("guarda");
        parser.parserGame(phrases);
    } */

    private static String convertiInMinuscolo(String input) {
        return input.toLowerCase();
    }

    private static List<String> ottieniListaParole(String frase) {
        String[] arrayParole = frase.split("\\s+");
        List<String> listaParole = Arrays.asList(arrayParole);
        return listaParole;
    }

    private static void rimuoviStopword(List<String> listaParole,
            Map<String, List<String>> stopWord) {
        for (List<String> stopWordsList : stopWord.values()) {
            listaParole.removeAll(stopWordsList);
        }
    }

    //metodo che ricerca le keywords nella frase in input.
    public String checkWords(List<String> inputWords) {
        // Controlla se nella lista c'è una delle directionPattern
        for (String word : inputWords) {
            Pattern pattern = Pattern.compile(directionPattern);
            Matcher matcher = pattern.matcher(word.toLowerCase());
            if (matcher.matches()) {
                return word; // Ritorna la stringa corrispondente alla direzione
            }
        }

        // Controlla se la lista contiene una parola presente in noObjAction
        for (Map.Entry<String, List<String>> entry : noObjAction.entrySet()) {
            List<String> actionWords = entry.getValue();
            for (String word : inputWords) {
                if (actionWords.contains(word.toLowerCase())) {
                    return entry.getKey(); // Ritorna la chiave associata alla parola trovata in noObjAction
                }
            }
        }

        return "ritenta";
    }

}

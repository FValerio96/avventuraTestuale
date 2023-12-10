package com.mycompany.avventuratestualejava;

import com.mycompany.manager.GameManager;
import static com.mycompany.manager.GameManager.cambioStanza;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Parser {

    private ArrayList<String> stopWord;
    private Map<String, List<String>> directions;
    private Map<String, List<String>> noObjAction;
    private Map<String, List<String>> ObjAction;

    public Parser() {
        this.stopWord = Loader.loadList("stopWords");
        this.directions = Loader.loadDictionary("directions");
        this.noObjAction = Loader.loadDictionary("noObjAction");
        this.ObjAction = Loader.loadDictionary("objAction");
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
        boolean find = false;
        //check spostamento direzione
        for (Map.Entry<String, List<String>> entry : directions.entrySet()) {
            String directionKey = entry.getKey();
            List<String> directionValues = entry.getValue();

            for (String inputWord : inputWords) {
                if (directionValues.contains(inputWord)) {
                    cambioStanza(inputWord);
                    find = true;
                    return;
                }
            }
        }
        //check noObjAction
        for (Map.Entry<String, List<String>> entry : noObjAction.entrySet()) {
            String noObjActionKey = entry.getKey();
            List<String> noObjActionValues = entry.getValue();

            for (String inputWord : inputWords) {
                if (noObjActionValues.contains(inputWord)) {
                    GameManager.osserva();
                    find = true;
                    return;
                }
            }
        }

        //check se è una objAction
        for (Map.Entry<String, List<String>> entry : ObjAction.entrySet()) {
            String objActionKey = entry.getKey();
            List<String> objActionValues = entry.getValue();
            
            for(String inputWord : inputWords) 
                if(objActionValues.contains(inputWord)) {
                    //l'azione passata è la chiave in modo che il metodo al 
                    //parsing basti riconoscere la parola e non i suoi sinonimi.
                    objActionParsing(objActionKey, inputWords);
                    find = true;
                    return;
                    
                }
                
            }
            
        System.out.println("ma come parli pirata?");
    }
    
    /*il metodo controlla preso il comando se il character (o figli) 
      presenti nella stanza sono nella frase
    */
    private void objActionParsing(String action , List<String> inputWords){
        Scanner scan = new Scanner(System.in);
        if (action.equals("parla")) {
                if(inputWords.contains(GameManager.getNpcNameInRoom())){
                    System.out.println(GameManager.getNpcToSayInRoom());              
                } else {
                    System.out.println("vedo " + GameManager.getNpcNameInRoom()
                            + " vuoi parlare con lui? \n digita s per il si"
                                    + "altrimenti sarà no.");
                    if(scan.nextLine().equals("s")) {
                        System.out.println(GameManager.getNpcToSayInRoom()); 
                    }          
                }
        }
    }

}

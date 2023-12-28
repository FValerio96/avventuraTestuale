/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.manager;

import static com.mycompany.manager.GameManager.printRooms;
import com.mycompany.utilities.JsonReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author PPiC
 */
public class Engine {

    public static void main(String[] args) {
        FileManager.fileCheck();
        GameManager.launcher();
    }

}

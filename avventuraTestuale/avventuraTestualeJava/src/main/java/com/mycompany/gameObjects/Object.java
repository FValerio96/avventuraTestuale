/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gameObjects;

/**
 *
 * @author flava
 */
public class Object extends Character {
    boolean takable;
    boolean usable;

    public Object(String id, String frase, Room room) {
        super(id, frase, room);
    }

    @Override
    public void eseguiAzione() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

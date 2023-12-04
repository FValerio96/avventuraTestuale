/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gameObjects;

/**
 *
 * 
 */
public class Object extends Character {
    private boolean takable;
    private boolean usable;
    private final String description;

    public Object(boolean takable, boolean usable, String description, int id, int room, String name) {
        super(id, room, name);
        this.takable = takable;
        this.usable = usable;
        this.description = description;
    }

    public static void main (String args[]) {
        
    } 

    

    @Override
    public void eseguiAzione() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

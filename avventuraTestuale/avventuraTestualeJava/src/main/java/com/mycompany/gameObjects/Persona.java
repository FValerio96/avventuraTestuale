/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gameObjects;

/**
 *
 * @author flava
 */
public class Persona extends Character{
    int health;
    //Object object
    
     public Persona(String id, String frase, Room room, int health) {
        super(id, frase, room);
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    @Override
    public void eseguiAzione() {
    /*
        if(object is not null)
            give the object
            otherwise
        sout    non ho niente per te.
        */    
    }
    
}

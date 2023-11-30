/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gameObjects;

public abstract class Character {
    private final String id;
    private final String frase;
    private final Room room;

    public Character(String id, String frase, Room room) {
        this.id = id;
        this.frase = frase;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public String getFrase() {
        return frase;
    }

    public Room getRoom() {
        return room;
    }
    
    public abstract void eseguiAzione();
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gameObjects;

/**
 *
 *
 */
public class Persona extends Character {

    boolean alive = true;
    String toSay;
    //Object object

    public Persona(String toSay, int id, int room, String name) {
        super(id, room, name);
        this.toSay = toSay;
    }

    public String getToSay() {
        return toSay;
    }

    @Override
    public void eseguiAzione() {
        alive = false;
    }
    

    public static void main(String args[]) {
        Persona Gianni = new Persona("mi chiamo gianni", 1, 1,"Gianni");
        System.out.println(Gianni.getToSay());
    }

}

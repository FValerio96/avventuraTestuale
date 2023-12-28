/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gameObjects;

/**
 *
 *
 */
public class Npcs extends Character {
    boolean alive = true;
    String toSay;
    public boolean isAlive;

    public boolean isAlive() {
        return alive;
    }
    
    public void setDead() {
        alive = false;
    }
    

    public Npcs(String toSay, int id, int room, String name) {
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
        Npcs Gianni = new Npcs("mi chiamo gianni", 1, 1,"Gianni");
        System.out.println(Gianni.getToSay());
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.avventuratestualejava.action;

/**
 * @author PPiC
 */
public class Directions extends Noun{

    @Override
    public boolean execute(String verb) {
        System.out.println("mi sono spostato a " + verb);
        return true;
    }
    
}

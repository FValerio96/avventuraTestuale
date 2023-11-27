/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.avventuratestualejava.action;

import java.util.Set;

/**
 *
 * @author PPiC
 */
public abstract class Noun {
    protected String name;
    protected Set<String> aliases;
    protected int id;
    protected String Description;

    public String getDescription() {
        return Description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getAliases() {
        return aliases;
    }

    abstract public boolean execute(String verb);
}

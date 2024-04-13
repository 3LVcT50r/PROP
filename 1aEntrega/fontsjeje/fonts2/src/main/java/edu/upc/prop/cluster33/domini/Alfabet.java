package edu.upc.prop.cluster33.domini;

import java.util.TreeMap;

public class Alfabet {
    private String nom;
    private String alfabet;
    public Alfabet() {
        alfabet = "";
    }

    public Alfabet(String name, String alfa) {
        nom = name;
        alfabet = alfa;
    }

    public String getAlfabet(){
        return alfabet;
    }

    public String getNom() {
        return nom;
    }

    public int getMida() {
        //Capturar excepción alfabeto vacío.
        return alfabet.length();
    }

    public int getIndex(char c) {
        //Capturar excepcion alfabeto vacio.
        return alfabet.indexOf(c);
    }

    public char getCharAtIndex(int i) {
        return alfabet.charAt(i);
    }

    public void determinaAlfabet(Character c, TreeMap<String, String> magatzemAlfabets) {
        for (String key : magatzemAlfabets.keySet()) {
            String a = magatzemAlfabets.get(key);
            if (a.contains(""+c)) {
                alfabet = a;
                nom = key;
            }
        }
    }
}


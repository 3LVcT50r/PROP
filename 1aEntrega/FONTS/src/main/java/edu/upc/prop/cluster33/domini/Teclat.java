package edu.upc.prop.cluster33.domini;

import java.util.*;

public class Teclat {
    private String nom;
    private char[][] layout;
    private Algorisme algorisme;
    private Date dataCreacio;

    private Frequencies frequencies;

    //CREADORA
    /*
    public Teclat(String nom) {
        this.nom = nom;
        this.algorisme = null;
        this.dataCreacio = new Date();//(new Date().getYear(), new Date().getMonth(), new Date().getDay());
    }
    */

    public Teclat(String nom, char[][] layout, Algorisme nomAlgorisme, Frequencies frequencies) {
        this.nom = nom;
        this.algorisme = nomAlgorisme;
        this.layout = layout;
        this.frequencies = frequencies;
        this.dataCreacio = new Date();
    }

    //CONSULTORA
    public String getNom() {
        return this.nom;
    }

    public Frequencies getFrequencia(){
        return this.frequencies;
    }

    public Vector<String> getInfo() {
        Vector<String> info = new Vector<>();
        info.add(nom);
        info.add(this.algorisme.getNom());
        info.add(dataCreacio.toString());
        info.add(this.frequencies.getNomAlfabet());
        for (char[] fila : layout)
            info.add(new String(fila));
        return info;
    }

    public Algorisme getAlgorisme(){
        return this.algorisme;
    }

    //MODIFICADORA
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAlgorisme(Algorisme algorisme) {
        this.algorisme = algorisme;
    }

    public void addFrequencia(Frequencies frequencies) {
        this.frequencies = frequencies;
    }

    public void setLayout(char[][] layout) {
        this.layout = layout;
    }

    //ESCRITURA
    public void printData() {
        System.out.println(dataCreacio);
    }

    public Teclat shallowCopy(){
        return new Teclat(this.nom, this.layout, this.algorisme, this.frequencies);
    }
}

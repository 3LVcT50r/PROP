package edu.upc.prop.cluster33.domini;

import edu.upc.prop.cluster33.utils.TMa;

import java.util.HashMap;

public class Usuari {

    //ATRIBUTOS
    private String username;
    private String password;
    private TMa maBona;
    boolean admin;

    private HashMap<Integer, Teclat> teclats;

    //CONSTRUCTORA
    public Usuari() {
        this.username = "";
        this.password = "";
        this.maBona = TMa.DRETA;
        this.admin = false;
    }

    public Usuari(String username, String password, TMa maBona, boolean admin) {
        this.username = username;
        this.password = password;
        this.maBona = maBona;
        this.teclats = new HashMap<Integer, Teclat>();
        this.admin = admin;
    }

    public Usuari(Usuari Us) {
        this.username = Us.username;
        this.password = Us.password;
        this.maBona = Us.maBona.copia();
        this.teclats = new HashMap<Integer, Teclat>(Us.teclats);
        this.admin = Us.admin;
    }

    //CONSULTORA
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public TMa getMaBona() {
        return this.maBona;
    }

    public boolean isAdmin() {
        return admin;
    }

    public HashMap<Integer, Teclat> getTeclats() {
        return this.teclats;
    }

    public boolean isTeclat(Integer id) {
        return this.teclats.containsKey(id);
    }

    //MODIFICADORAS
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMaBona(TMa maBona) {
        this.maBona = maBona;
    }

    public void addTeclat(Integer id, Teclat teclat) {
        this.teclats.put(id,teclat);
    }

    public void eliminarTeclat(Integer id) {
        this.teclats.remove(id);
    }

    public void setAdmin(boolean b) {
        this.admin = b;
    }
}

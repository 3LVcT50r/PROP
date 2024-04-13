package edu.upc.prop.cluster33.domini;

public class TextPublic extends Text{
    private String username;

    public TextPublic(String contingut) {
        super(contingut);
    }

    public TextPublic(String titol, String contingut) {
        super(titol, contingut);
    }

    public TextPublic(String titol, String contingut, String username){
        super(titol, contingut);
        this.username = username;
    }

    public TextPublic(Text tx) {
        super(tx);
    }

    //CONSULTORAS
    public String getUsuariUsername() {
        return this.username;
    }

    //MODIFICADORAS
    public void setUsername(String nom) {
        this.username = nom;
    }

}

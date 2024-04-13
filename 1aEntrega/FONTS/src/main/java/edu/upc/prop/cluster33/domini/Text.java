package edu.upc.prop.cluster33.domini;

public class Text {
    private String titol;
    private String contingut;


    //CONSTRUCTORAS

    public Text(String contingut) {
        this.contingut = contingut;
        this.titol = "";
    }

    public Text(String titol, String contingut) {
        this.titol = titol;
        this.contingut = contingut;
    }

    public Text(Text tx) {
        this.titol = tx.titol;
        this.contingut = tx.contingut;
    }

    //CONSULTORAS
    public String getNom() {
        return this.titol;
    }

    public String llegirContingut() {
        return this.contingut;
    }

    //MODIFICADORAS
    public void setNom(String titol) {
        this.titol = titol;
    }

    public void setContingut(String text) {
        this.contingut = text;
    }
}

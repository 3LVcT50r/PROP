package edu.upc.prop.cluster33.excepcions;

public abstract class Excepcio extends Exception{

    private static final long serialVersionUID = 1L;


    public Excepcio() {
        super();
    }

    public Excepcio(String s) {
        super(s);
    }
}
package edu.upc.prop.cluster33.excepcions;

public class ExcepcioPasswordNoPassaFiltre extends Excepcio{

    public ExcepcioPasswordNoPassaFiltre() {
        super("El password introduit no ha passat el filtre de seguretat. Sisplau, seleccioni un altre");
    }
}

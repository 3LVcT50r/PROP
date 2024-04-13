package edu.upc.prop.cluster33.excepcions;

public class ExcepcioUsuariEsAdmin extends Excepcio{

    public ExcepcioUsuariEsAdmin() {
        super("L'usuari que s'esta intentant eliminar es un administrador");
    }
}

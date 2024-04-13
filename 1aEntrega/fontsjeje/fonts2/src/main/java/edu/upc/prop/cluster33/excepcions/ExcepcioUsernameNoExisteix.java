package edu.upc.prop.cluster33.excepcions;

public class ExcepcioUsernameNoExisteix extends Excepcio {

    public ExcepcioUsernameNoExisteix() {
        super("Username no detectat");
    }

    public ExcepcioUsernameNoExisteix(String username) {

        super(String.format("No existeix cap usuari amb username %s al sistema", username));
    }
}

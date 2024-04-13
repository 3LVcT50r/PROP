package edu.upc.prop.cluster33.excepcions;



public class ExcepcioUsernameJaExistent extends Excepcio {

    public ExcepcioUsernameJaExistent() {
        super();
    }

    public ExcepcioUsernameJaExistent(String username) {
        super(String.format("Un altre usuari amb username %s ja existeix. Sisplau seleccioni un altre", username));
    }


}
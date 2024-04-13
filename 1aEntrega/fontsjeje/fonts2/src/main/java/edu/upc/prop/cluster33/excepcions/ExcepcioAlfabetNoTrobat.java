package edu.upc.prop.cluster33.excepcions;

public class ExcepcioAlfabetNoTrobat extends Excepcio{
    public ExcepcioAlfabetNoTrobat() {
        super("No s'ha pogut determinar a quin alfabet pertany el text/llista de freqüencies proporcionat. És possible que no" +
                "existeixi al sistema o bé s'hagi escrit malament.");
    }
}

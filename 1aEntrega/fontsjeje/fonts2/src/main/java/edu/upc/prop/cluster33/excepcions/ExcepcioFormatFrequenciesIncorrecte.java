package edu.upc.prop.cluster33.excepcions;
//Excepcio
public class ExcepcioFormatFrequenciesIncorrecte extends Excepcio {
    public ExcepcioFormatFrequenciesIncorrecte() {
        super("El format de les parelles 'paraula-freqüència' que ha proporcionat no és correcte." +
                " Si us plau, segueixi el format 'paraula-frequencia' una sota l'altra i finalitzant la cadena" +
                " de parelles per ;");
    }
}

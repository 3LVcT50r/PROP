package edu.upc.prop.cluster33.domini;

import java.text.Normalizer;
import java.util.*;
import edu.upc.prop.cluster33.excepcions.*;

public class Frequencies {
    private TreeMap<String, Integer> llistaFrequencies;
    private Alfabet alfabet;
    private int numero_paraules;

    public Frequencies() {
        llistaFrequencies = new TreeMap<>();
        alfabet = new Alfabet();
        numero_paraules = 0;
    }
    public TreeMap<String, Integer> getLlistaFrequencies() {
        return llistaFrequencies;
    }

    public int getNumero_paraules() {
        return numero_paraules;
    }

    public Alfabet getAlfabet() {
        return alfabet;
    }

    public String getNomAlfabet() {
        return alfabet.getNom();
    }

    public void genera(String s, Alfabet[] llistaAlfabets) throws ExcepcioFrequencies/*ExcepcioTextBuit, ExcepcioMesDeUnAlfabetAlhora, ExcepcioAlfabetNoTrobat*/{
        String prohibits = " |/!¡¿?@#$%&*+.,:_=)([]{}ªº<>1234567890ao։ \n";
        String text = s.toUpperCase();
        text = Normalizer.normalize(text, Normalizer.Form.NFKD);
        text = text.replaceAll("\\p{M}", "");
        int mida = text.length();
        if (mida == 0) throw new ExcepcioFrequencies("El text/llistat de frequencies proporcionat no té contingut (està buit).");
        int j = 0;
        boolean firstCharacterFound = false;
        llistaFrequencies = new TreeMap<>();
        char ch = ' ';
        while(j < mida) {
            ch = text.charAt(j);
            if (Character.isLetter(ch)) {
                if (!firstCharacterFound) {
                    firstCharacterFound = true;
                    int it = 0;
                    boolean alfabetTrobat = false;
                    while (!alfabetTrobat && it < llistaAlfabets.length) {
                        if (llistaAlfabets[it].getAlfabet().contains(""+ch)) {
                            alfabetTrobat = true;
                            alfabet = llistaAlfabets[it];
                        }
                        ++it;
                        if (it == llistaAlfabets.length && !alfabetTrobat) throw new ExcepcioFrequencies("L'alfabet del text/llistat de frequencies no ha sigut reconegut: no existeix al sistema.");
                    }
                } else {
                    if (!alfabet.getAlfabet().contains(""+ch)) throw new ExcepcioFrequencies("El text/llistat de frequencies proporcionat conté caràcters de més d'un alfabet alhora.");
                }
            }
            ++j;
        }
        if (alfabet.getNom().equals("Cirilic")) {
            /*text = Normalizer.normalize(text, Normalizer.Form.NFKD);
            text = text.replaceAll("\\p{M}", "");*/
            text = s.toUpperCase();
        }
        String paraulaProces = "";
        for (int i = 0; i < mida; ++i) {
            Character actual = text.charAt(i);
            if (!prohibits.contains(""+actual)) {
                paraulaProces += actual;
            }
            if (prohibits.contains(""+actual) || (i == mida-1)) {
                String key = paraulaProces;
                paraulaProces = "";
                if (!key.isEmpty()) {
                    if (llistaFrequencies.containsKey(key)) {
                        Integer oldv = llistaFrequencies.get(key);
                        llistaFrequencies.replace(key, oldv, oldv+1);
                    } else {
                        llistaFrequencies.put(key, 1);
                    }
                }
            }
        }
        numero_paraules = llistaFrequencies.size();
    }
    public void llegir(String t, Alfabet[] llistaAlfabets) throws ExcepcioFrequencies/*ExcepcioFormatFrequenciesIncorrecte, ExcepcioTextBuit, ExcepcioMesDeUnAlfabetAlhora, ExcepcioAlfabetNoTrobat*/{
        String s = t.toUpperCase();
        s = Normalizer.normalize(s, Normalizer.Form.NFKD);
        s = s.replaceAll("\\p{M}", "");
        if (s.isEmpty()) throw new ExcepcioFrequencies("El text/llistat de frequencies proporcionat no té contingut (està buit).");
        int mida = s.length();
        int j = 0;
        boolean firstCharacterFound = false;
        char ch = ' ';
        while(j < mida) {
            ch = s.charAt(j);
            if (Character.isLetter(ch)) {
                if (!firstCharacterFound) {
                    firstCharacterFound = true;
                    int it = 0;
                    boolean alfabetTrobat = false;
                    while (!alfabetTrobat && it < llistaAlfabets.length) {
                        if (llistaAlfabets[it].getAlfabet().contains(""+ch)) {
                            alfabetTrobat = true;
                            alfabet = llistaAlfabets[it];
                        }
                        ++it;
                        if (it == llistaAlfabets.length && !alfabetTrobat) throw new ExcepcioFrequencies("L'alfabet del text/llistat de frequencies no ha sigut reconegut: no existeix al sistema.");
                    }
                } else {
                    if (!alfabet.getAlfabet().contains(""+ch)) throw new ExcepcioFrequencies("El text/llistat de frequencies proporcionat conté caràcters de més d'un alfabet alhora.");
                }
            }
            ++j;
        }
        if (alfabet.getNom().equals("Cirilic")) s = t.toUpperCase();
        /*Character ch = s.charAt(0);
        alfabet.determinaAlfabet(ch);*/
        llistaFrequencies = new TreeMap<>();
        String prohibits = " |/!¡¿?@#$%&*+.,:_=)([]{}ªº<>";
        Boolean guioPassat = false;
        boolean startedWriting = false;
        String paraulaActual = "";
        String valorActual = "";
        for (int i = 0; i < s.length(); ++i) {
            Character actual = s.charAt(i);
            if (!startedWriting) {
                if (Character.isLetter(actual)) startedWriting = true;
                paraulaActual += actual;
            } else {
                if (prohibits.contains(""+actual)) {
                    numero_paraules = 0;
                    throw new ExcepcioFrequencies("El format del llistat de frequencies proporcionat es incorrecte.");
                }
                if (actual != '-' && !guioPassat) {
                    paraulaActual += actual;
                }
                else if (actual == '-') guioPassat = true;
                else if (actual != '\n' && actual != ';') {
                    valorActual += actual;
                }
                else {
                    int valor = Integer.parseInt(valorActual);
                    if (!llistaFrequencies.containsKey(paraulaActual) && !paraulaActual.isEmpty()) {
                        llistaFrequencies.put(paraulaActual, valor);
                    }
                    paraulaActual = "";
                    valorActual = "";
                    guioPassat = false;
                }
            }
        }
        numero_paraules = llistaFrequencies.size();
    }
}
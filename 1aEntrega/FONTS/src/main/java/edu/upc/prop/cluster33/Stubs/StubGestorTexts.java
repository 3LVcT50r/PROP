package edu.upc.prop.cluster33.Stubs;

import edu.upc.prop.cluster33.domini.*;
import edu.upc.prop.cluster33.excepcions.*;

import java.util.HashMap;

public class StubGestorTexts {
    private HashMap<Integer, TextPublic> TextsPublics;
    private HashMap<Integer, TextPredefinit> TextsPredefinits;

    public StubGestorTexts() {
        TextsPublics = new HashMap<>();
        TextsPredefinits = new HashMap<>();
    }

    public HashMap<Integer, TextPublic> getTextsPublics() {
        return TextsPublics;
    }

    public TextPublic getTextPublic(Integer idTextPublic) throws ExcepcioIdNoValid {
        if (!TextsPublics.containsKey(idTextPublic)) throw new ExcepcioIdNoValid();
        return TextsPublics.get(idTextPublic);
    }

    public TextPredefinit getTextPredefinit(Integer idTextPredefinit) throws ExcepcioIdNoValid {
        if (!TextsPredefinits.containsKey(idTextPredefinit)) throw new ExcepcioIdNoValid();
        return TextsPredefinits.get(idTextPredefinit);
    }

    public HashMap<Integer, TextPredefinit> getTextsPredefinits() {
        return TextsPredefinits;
    }

    public HashMap<Integer, Text> getTextsPublicsUsuari(String username) {
        HashMap<Integer, Text> textsPublicsUsuari = new HashMap<>();
        for (Integer i : TextsPublics.keySet()) {
            if (username.equals(TextsPublics.get(i).getUsuariUsername())) {
                textsPublicsUsuari.put(i, TextsPublics.get(i));
            }
        }
        return textsPublicsUsuari;
    }

    public void crearTextPublic(TextPublic text) throws ExcepcioNomTextPublicJaExisteix {
        for(int i : TextsPublics.keySet()) {
            if(TextsPublics.get(i).getNom().equals(text.getNom())) throw new ExcepcioNomTextPublicJaExisteix();
        }
        Integer primerIdDisponible = 1;
        while(TextsPublics.containsKey(primerIdDisponible)) ++primerIdDisponible;
        TextsPublics.put(primerIdDisponible, text);
    }

    public void eliminarTextPublic(Integer idText) throws ExcepcioIdNoValid{
        if (!TextsPublics.containsKey(idText)) throw new ExcepcioIdNoValid();
        TextsPublics.remove(idText);
    }

    public void modificarTextPublic(Integer idText, TextPublic nouText) throws ExcepcioIdNoValid, ExcepcioNomTextPublicJaExisteix{
        if (!TextsPublics.containsKey(idText)) throw new ExcepcioIdNoValid();
        for (int i : TextsPublics.keySet()) {
            if (TextsPublics.get(i).getNom().equals(nouText.getNom())) throw new ExcepcioNomTextPublicJaExisteix();
        }
        TextsPublics.remove(idText);
        TextsPublics.put(idText, nouText);
    }

    public Text getTextPublicUsuari(Integer idText) throws ExcepcioIdNoValid{
        if (!TextsPublics.containsKey(idText)) throw new ExcepcioIdNoValid();
        return TextsPublics.get(idText);
    }

    public void crearTextPredefinit(TextPredefinit text) throws ExcepcioNomTextPredefinitJaExisteix{
        for (int i : TextsPredefinits.keySet()) {
            if (TextsPredefinits.get(i).getNom().equals(text.getNom())) throw new ExcepcioNomTextPredefinitJaExisteix();
        }
        Integer primerIdDisponible = 1;
        while(TextsPredefinits.containsKey(primerIdDisponible)) ++primerIdDisponible;
        TextsPredefinits.put(primerIdDisponible, text);
    }

    public void eliminarTextPredefinit(Integer idText) throws ExcepcioIdNoValid{
        if (!TextsPredefinits.containsKey(idText)) throw new ExcepcioIdNoValid();
        TextsPredefinits.remove(idText);
    }

    public void modificarTextPredefinit(Integer idText, TextPredefinit text) throws ExcepcioIdNoValid, ExcepcioNomTextPredefinitJaExisteix {
        if (!TextsPredefinits.containsKey(idText)) throw new ExcepcioIdNoValid();
        for (int i : TextsPredefinits.keySet()) {
            if (TextsPredefinits.get(i).getNom().equals(text.getNom())) throw new ExcepcioNomTextPredefinitJaExisteix();
        }
        TextsPredefinits.remove(idText);
        TextsPredefinits.put(idText, text);
    }
}

package edu.upc.prop.cluster33.Stubs;

import edu.upc.prop.cluster33.domini.Teclat;
import edu.upc.prop.cluster33.domini.Usuari;
import edu.upc.prop.cluster33.excepcions.*;
import edu.upc.prop.cluster33.utils.TMa;

import java.util.Collection;
import java.util.HashMap;

public class StubGestorUsuaris {

    private HashMap<String, Usuari> usuaris;

    public StubGestorUsuaris() {
        usuaris = new HashMap<>();
        Usuari udefault = new Usuari("UsuariDefault", "UsuariDefault", TMa.ESQUERRA, true);
        usuaris.put("UsuariDefault", udefault);
    }

    public Usuari getUsuari(String username) throws ExcepcioUsernameNoExisteix {
        if (!usuaris.containsKey(username)) throw new ExcepcioUsernameNoExisteix();
        return usuaris.get(username);
    }

    public boolean existeixUsuari(String username) {
        return usuaris.containsKey(username);
    }

    public void crearUsuari(Usuari user) throws ExcepcioUsernameJaExistent{
        if (usuaris.containsKey(user.getUsername())) throw new ExcepcioUsernameJaExistent();
        String key = user.getUsername();
        usuaris.put(key, user);
    }

    public void eliminarUsuari(String username) throws ExcepcioUsernameNoExisteix{
        if (!usuaris.containsKey(username)) throw new ExcepcioUsernameNoExisteix();
        usuaris.remove(username);
    }

    public void removeTeclat(Usuari user, Integer idTeclat) throws ExcepcioIdNoValid, ExcepcioUsernameNoExisteix {
        if (!usuaris.containsKey(user.getUsername())) throw new ExcepcioUsernameNoExisteix();
        String key = user.getUsername();
        Usuari userDatabase = usuaris.get(key);
        HashMap<Integer, Teclat> llistaTeclats = userDatabase.getTeclats();
        if (!llistaTeclats.containsKey(idTeclat)) throw new ExcepcioIdNoValid();
        llistaTeclats.remove(idTeclat);
    }

    public void canviarPassword(String username, String newPassword) throws ExcepcioUsernameNoExisteix{
        if (!usuaris.containsKey(username)) throw new ExcepcioUsernameNoExisteix();
        Usuari userDatabase = usuaris.get(username);
        userDatabase.setPassword(newPassword);
    }

    public void canviarMaBona(String username, TMa tipusMa) throws ExcepcioUsernameNoExisteix{
        if (!usuaris.containsKey(username)) throw new ExcepcioUsernameNoExisteix();
        Usuari userDatabase = usuaris.get(username);
        userDatabase.setMaBona(tipusMa);
    }

    public void setAdmin(String username) throws ExcepcioUsernameNoExisteix{
        if (!usuaris.containsKey(username)) throw new ExcepcioUsernameNoExisteix();
        Usuari userDatabase = usuaris.get(username);
        userDatabase.setAdmin(true);
    }

    public HashMap<String, Usuari> getUsuaris() {return usuaris;}

    public void crearTeclat(String username, Teclat teclat) throws ExcepcioUsernameNoExisteix, ExcepcioNomTeclatJaExisteix {
        if (!usuaris.containsKey(username)) throw new ExcepcioUsernameNoExisteix();
        Usuari user = usuaris.get(username);
        HashMap<Integer, Teclat> teclats = user.getTeclats();
        Collection<Teclat> teclatsDatabase = teclats.values();
        for (Teclat t : teclatsDatabase) {
            if (t.getNom().equals(teclat.getNom())) throw new ExcepcioNomTeclatJaExisteix();
        }
        Integer keySenseUtilitzar = 1;
        while(teclats.containsKey(keySenseUtilitzar)) {
            ++keySenseUtilitzar;
        }
        teclats.put(keySenseUtilitzar, teclat);
    }

    public void modificarTeclat(String username, Integer idTeclat, Teclat nouTeclat) throws ExcepcioIdNoValid, ExcepcioErrorDurantModificacio {
        if (!usuaris.containsKey(username)) throw new ExcepcioErrorDurantModificacio();
        Usuari user = usuaris.get(username);
        HashMap<Integer, Teclat> teclats = user.getTeclats();
        if (!teclats.containsKey(idTeclat)) throw new ExcepcioIdNoValid();
        teclats.remove(idTeclat);
        teclats.put(idTeclat, nouTeclat);
    }

    public void modificarTeclatNom(String username, Integer idTeclat, String nouNom) throws ExcepcioIdNoValid, ExcepcioNomTeclatJaExisteix, ExcepcioErrorDurantModificacio {
        if (!usuaris.containsKey(username)) throw new ExcepcioErrorDurantModificacio();
        Usuari user = usuaris.get(username);
        HashMap<Integer, Teclat> teclats = user.getTeclats();
        if (!teclats.containsKey(idTeclat)) throw new ExcepcioIdNoValid();
        Teclat tec = teclats.get(idTeclat);
        Collection<Teclat> teclatsDatabase = teclats.values();
        for (Teclat t : teclatsDatabase) {
            if (t.getNom().equals(nouNom)) throw new ExcepcioNomTeclatJaExisteix();
        }
        tec.setNom(nouNom);
    }
}

package edu.upc.prop.cluster33.domini;

import edu.upc.prop.cluster33.Stubs.StubGestorAlfabets;
import edu.upc.prop.cluster33.Stubs.StubGestorAlgorismes;
import edu.upc.prop.cluster33.Stubs.StubGestorTexts;
import edu.upc.prop.cluster33.Stubs.StubGestorUsuaris;
import edu.upc.prop.cluster33.excepcions.*;
import edu.upc.prop.cluster33.utils.TMa;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ControladorCapaDomini {

    private StubGestorUsuaris gestorUsuaris;
    private StubGestorTexts gestorTexts;
    private StubGestorAlgorismes gestorAlgorismes;
    private StubGestorAlfabets gestorAlfabets;

    private Usuari usuari;

    //creadora
    public ControladorCapaDomini() {
        gestorUsuaris = new StubGestorUsuaris ();
        gestorTexts = new StubGestorTexts ();
        gestorAlgorismes = new StubGestorAlgorismes ();
        gestorAlfabets = new StubGestorAlfabets();

    }

    // LOGIN REGISTRE LOGOUT
    // guarda l'usuari amb username username a l'usuari
    public void login(String username, String password) throws ExcepcioUsernameNoExisteix, ExcepcioPasswordIncorrecte {
        Usuari user = gestorUsuaris.getUsuari(username);
        if (user == null) throw new ExcepcioUsernameNoExisteix();
        String pass = user.getPassword();
        if (pass != null && pass.equals(password)) {
            usuari = user;
        }
        else throw new ExcepcioPasswordIncorrecte();
    }

    // registra l'usuari al sistema
    public void registre(String username, String password, String maBona, boolean admin) throws ExcepcioUsernameJaExistent, ExcepcioPasswordNoPassaFiltre, ExcepcioFormatMaIncorrecte, ExcepcioErrorDurantLaCreacio {
        if (gestorUsuaris.existeixUsuari(username)) throw new ExcepcioUsernameJaExistent();
        if (!filtrePassword(password)) throw new ExcepcioPasswordNoPassaFiltre(); //format contrasenya no correcte

        //creem usuari
        TMa tipusMa = TMa.DRETA ;
        if (maBona.equals("Esquerra")) tipusMa = TMa.ESQUERRA;
        else if(!maBona.equals("Dreta")) throw new ExcepcioFormatMaIncorrecte();
        Usuari user = new Usuari(username, password, tipusMa, admin);

        try {
            gestorUsuaris.crearUsuari(user);
        }
        catch(Exception e){
            throw new ExcepcioErrorDurantLaCreacio();
        }
        usuari = user;
    }

    //filtre del password, retorna true si la contrasenya passa el filtre
    private boolean filtrePassword(String password){
        return password.length() >= 3;
    }

    // canvia el password de l'usuari
    public void canviarPassword(String oldpassword, String newPassword) throws ExcepcioPasswordIncorrecte, ExcepcioPasswordNoPassaFiltre,ExcepcioErrorDurantModificacio {
        if(!oldpassword.equals(usuari.getPassword()))
            throw new ExcepcioPasswordIncorrecte();

        if (!filtrePassword(newPassword))
            throw new ExcepcioPasswordNoPassaFiltre();
        try {
            gestorUsuaris.canviarPassword(usuari.getUsername(), newPassword);
        }
        catch (Exception e) {
            throw new ExcepcioErrorDurantModificacio();
        }
        usuari.setPassword(newPassword);
    }

    // canvia la ma bona
    public void canviarMaBona() throws ExcepcioErrorDurantModificacio, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        TMa maNova = TMa.ESQUERRA;;
        if ( usuari.getMaBona() == TMa.ESQUERRA){
            maNova = TMa.DRETA;
        }
        try {
            gestorUsuaris.canviarMaBona(usuari.getUsername(), maNova);
        }
        catch (Exception e) {
            throw new ExcepcioErrorDurantModificacio();
        }
        usuari.setMaBona(maNova);
    }

    // el usuari actual passa a ser null
    public void logout() {
        usuari = null;
    }

    //elimina el usuari
    public void eliminarPropiUsuari(String username,String password) throws ExcepcioUsernamePasswordIncorrectes, ExcepcioIntentEsborrarAdmin, ExcepcioErrorDurantEsborrat {
        if (!username.equals(usuari.getUsername()) || !password.equals(usuari.getPassword()))
            throw new ExcepcioUsernamePasswordIncorrectes();
        if (usuari.isAdmin())
            throw new ExcepcioIntentEsborrarAdmin();
        try {
            gestorUsuaris.eliminarUsuari(usuari.getUsername());
        }
        catch (Exception e) {
            throw new ExcepcioErrorDurantEsborrat();
        }
        usuari = null;
    }

    // retorna la ma bona de l'usuari loguejat
    public String getMaBona() throws ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        return (usuari.getMaBona() == TMa.DRETA) ? "Dreta" : "Esquerra";
    }

    //retorna true si l'usuari es admin
    public boolean isAdmin() throws ExcepcioNoUsuariLogin{
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        return usuari.isAdmin();
    }

    //GESTIO DE TECLATS

    //Retorna un hashmap amb el id i el nom de cada teclat, l'usuari podra seleccionar el teclat per aquest id
    public HashMap<Integer,String> getTeclats() throws ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        HashMap<Integer,String> teclatsOut = new HashMap<>();
        HashMap<Integer,Teclat> teclats = usuari.getTeclats();
        for(Map.Entry<Integer, Teclat> entry : teclats.entrySet())
            teclatsOut.put(entry.getKey(), entry.getValue().getNom());
        return teclatsOut;
    }

    //retorna un vector de strings amb les propietats del teclat
    public Vector<String> getTeclat(Integer id) throws ExcepcioIdNoValid, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        HashMap<Integer,Teclat> teclats = usuari.getTeclats();
        Teclat teclat = teclats.get(id);
        if(teclat == null) throw new ExcepcioIdNoValid();
        return teclat.getInfo();
    }

    //retorna el nom de l'algorisme
    public String getAlgorismeTeclat(Integer idTeclat) throws ExcepcioIdNoValid, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        HashMap<Integer,Teclat> teclats = usuari.getTeclats();
        Teclat teclat = teclats.get(idTeclat);
        if(teclat == null) throw new ExcepcioIdNoValid();
        return teclat.getInfo().elementAt(1);
    }

    //retorna el nom del teclat
    public String getNomTeclat(Integer idTeclat) throws ExcepcioIdNoValid, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        HashMap<Integer,Teclat> teclats = usuari.getTeclats();
        Teclat teclat = teclats.get(idTeclat);
        if(teclat == null) throw new ExcepcioIdNoValid();
        return teclat.getNom();
    }

    //elimina correctament el teclat
    public void eliminarTeclat(Integer idTeclat) throws ExcepcioErrorDurantEsborrat, ExcepcioIdNoValid, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        HashMap<Integer,Teclat> teclats = usuari.getTeclats();
        try {
            gestorUsuaris.removeTeclat(usuari, idTeclat);
        }
        catch(ExcepcioIdNoValid e) {
            throw new ExcepcioIdNoValid();
        }
        catch (Exception e){
            throw new ExcepcioErrorDurantEsborrat();
        }
        teclats.remove(idTeclat);
    }

    // FUNCIONS AUXILIARS GET PER A PRESENTACIO PER A CREAR UN TECLAT

    // retorna un map amb claus int i els noms dels algorismes disponibles
    public HashMap<Integer,String> getAlgorismes(){
        HashMap<Integer,String> algorismesOut = new HashMap<>();
        HashMap<Integer,Algorisme> algorismes = gestorAlgorismes.getAlgorismes();
        for(Map.Entry<Integer, Algorisme> entry : algorismes.entrySet())
            algorismesOut.put(entry.getKey(), entry.getValue().getNom());
        return algorismesOut;
    }

    // retorna un map amb claus int i els noms dels texts publics disponibles
    public HashMap<Integer,String> getTextsPublics(){
        HashMap<Integer,String> textsOut = new HashMap<>();
        HashMap<Integer,TextPublic> textsPublics = gestorTexts.getTextsPublics();
        for(Map.Entry<Integer, TextPublic> entry : textsPublics.entrySet())
            textsOut.put(entry.getKey(), entry.getValue().getNom());
        return textsOut;
    }

    // retorna un map amb claus int i els noms dels texts predefinits disponibles
    public HashMap<Integer,String> getTextsPredefinits(){
        HashMap<Integer,String> textsOut = new HashMap<>();
        HashMap<Integer,TextPredefinit> textsPredefinits = gestorTexts.getTextsPredefinits();
        for(Map.Entry<Integer, TextPredefinit> entry : textsPredefinits.entrySet())
            textsOut.put(entry.getKey(), entry.getValue().getNom());
        return textsOut;
    }


    //CREAR TECLATS

    //genera les frequencies i crida a crear teclat
    public void crearTeclatFrequencies(Integer algorismeId, String frequenciesString, String nom) throws ExcepcioIdNoValid, ExcepcioErrorDurantLaCreacio,  ExcepcioNomTeclatJaExisteix, ExcepcioFrequencies {
        Frequencies frequencies = new Frequencies();
        Alfabet[] alfabets = gestorAlfabets.getAlfabets();
        frequencies.llegir(frequenciesString, alfabets);
        crearTeclat(nom, algorismeId, frequencies);
    }

    //genera les frequencies i crida a crear teclat
    public void crearTeclatFitxerTextUsuari(Integer algorismeId, String contingutText, String nom) throws ExcepcioIdNoValid, ExcepcioErrorDurantLaCreacio,  ExcepcioNomTeclatJaExisteix, ExcepcioFrequencies{

        Frequencies frequencies = new Frequencies();
        Alfabet[] alfabets = gestorAlfabets.getAlfabets();

        frequencies.genera(contingutText, alfabets);
        crearTeclat(nom, algorismeId, frequencies);

    }

    //genera les frequencies a partir del text public i crida a crear teclat
    public void crearTeclatFitxerTextPublic(Integer algorismeId, Integer idTextPublic, String nom) throws ExcepcioIdNoValid, ExcepcioErrorDurantLaCreacio, ExcepcioNomTeclatJaExisteix, ExcepcioFrequencies{

        Text text = gestorTexts.getTextPublic(idTextPublic);

        String contentsFitxer;
        contentsFitxer = text.llegirContingut();

        Frequencies frequencies = new Frequencies();
        Alfabet[] alfabets = gestorAlfabets.getAlfabets();

        frequencies.genera(contentsFitxer, alfabets);
        crearTeclat(nom, algorismeId, frequencies);

    }

    //genera les frequencies a partir del text predefinit i crida a crear teclat
    public void crearTeclatFitxerTextPredefinit(Integer algorismeId, Integer idTextPredefinit, String nom) throws ExcepcioIdNoValid, ExcepcioErrorDurantLaCreacio, ExcepcioNomTeclatJaExisteix, ExcepcioFrequencies{
        Text text = gestorTexts.getTextPredefinit(idTextPredefinit);

        String contentsFitxer;
        contentsFitxer = text.llegirContingut();

        Frequencies frequencies = new Frequencies();
        Alfabet[] alfabets = gestorAlfabets.getAlfabets();

        frequencies.genera(contentsFitxer, alfabets);
        crearTeclat(nom, algorismeId, frequencies);
    }

    //Crea el teclat
    private void crearTeclat(String nom, Integer algorismeId, Frequencies frequencies) throws ExcepcioErrorDurantLaCreacio, ExcepcioIdNoValid, ExcepcioNomTeclatJaExisteix{

        Algorisme algorisme = gestorAlgorismes.getAlgorisme(algorismeId);
        algorisme.init();

        int columnes = 7;
        int files = 6;
        char[][] layout = algorisme.generarLayout(frequencies, columnes, files);

        Teclat teclat = new Teclat(nom, layout, algorisme, frequencies);

        try {
            gestorUsuaris.crearTeclat(usuari.getUsername(), teclat);
        }
        catch(ExcepcioNomTeclatJaExisteix e) {
            throw new ExcepcioNomTeclatJaExisteix();
        }
        catch (Exception e){
            throw new ExcepcioErrorDurantLaCreacio();
        }

        try {
            usuari = gestorUsuaris.getUsuari(usuari.getUsername());
        }
        catch(ExcepcioUsernameNoExisteix e){
            throw new ExcepcioErrorDurantLaCreacio();
        }

    }

    //MODIFICAR TECLATS

    // retorna 1 si ha canviat el nom, retorna 2 si el nom ja exisgteix
    public void modificarTeclatNom(Integer idTeclat, String nouNom) throws ExcepcioIdNoValid, ExcepcioNomTeclatJaExisteix, ExcepcioErrorDurantModificacio, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        HashMap<Integer,Teclat> teclats = usuari.getTeclats();
        Teclat teclat = teclats.get(idTeclat);
        Teclat nouTeclat = teclat.shallowCopy();
        nouTeclat.setNom(nouNom);

        gestorUsuaris.modificarTeclatNom(usuari.getUsername(), idTeclat, nouNom);

        teclats.put(idTeclat, nouTeclat);

    }

    // retorna 1 si ha modificat elo teclat, retorna 2 si el nom de l'algorisme no es correcte
    public void modificarTeclatAlgorisme(Integer idTeclat, Integer idNouAlgorsime) throws ExcepcioIdNoValid, ExcepcioErrorDurantModificacio, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        HashMap<Integer,Teclat> teclats = usuari.getTeclats();
        Teclat teclat = teclats.get(idTeclat);

        Teclat nouTeclat = teclat.shallowCopy();
        Algorisme algorisme = gestorAlgorismes.getAlgorisme(idNouAlgorsime);

        int columnes = 7;
        int files = 6;
        char[][] layout = algorisme.generarLayout(nouTeclat.getFrequencia(), columnes, files);
        nouTeclat.setLayout(layout);
        try {
            gestorUsuaris.modificarTeclat(usuari.getUsername(), idTeclat, nouTeclat);
        }
        catch (Exception e) {
            throw new ExcepcioErrorDurantModificacio();
        }
        teclats.put(idTeclat, nouTeclat);

    }

    // retorna 1 si ha modificat el teclat, retorna 2 si el teclat no utilitza cap algorisme existent, retorna 3 si no s'ha pogut eliminar el teclat, retorna 4 si no s'ha pogut tornar a crear el teclat
    public void modificarTeclatFrequencies(Integer idTeclat, String frequenciesString) throws ExcepcioFrequencies, ExcepcioIdNoValid, ExcepcioErrorDurantModificacio, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        HashMap<Integer,Teclat> teclats = usuari.getTeclats();
        Teclat teclat = teclats.get(idTeclat);
        if (teclat == null) throw new ExcepcioIdNoValid();

        Teclat nouTeclat = teclat.shallowCopy();
        Algorisme algorisme = nouTeclat.getAlgorisme();

        Alfabet[] alfabets = gestorAlfabets.getAlfabets();
        Frequencies frequencies = new Frequencies();
        frequencies.llegir(frequenciesString, alfabets);

        int columnes = 7;
        int files = 6;
        char[][] layout = algorisme.generarLayout(frequencies, columnes, files);
        nouTeclat.setLayout(layout);
        try {
            gestorUsuaris.modificarTeclat(usuari.getUsername(), idTeclat, nouTeclat);
        }
        catch (Exception e) {
            throw new ExcepcioErrorDurantModificacio();
        }
        teclats.put(idTeclat, nouTeclat);
    }


    // retorna 1 si ha modificat el teclat, retorna 2 si el teclat no utilitza cap algorisme existent, retorna 3 si no s'ha pogut eliminar el teclat, retorna 4 si no s'ha pogut tornar a crear el teclat
    public void modificarTeclatFrequenciesFitxerTextUsuari(Integer idTeclat, String contingutsFitxer) throws ExcepcioFrequencies, ExcepcioIdNoValid, ExcepcioErrorDurantModificacio, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        HashMap<Integer,Teclat> teclats = usuari.getTeclats();
        Teclat teclat = teclats.get(idTeclat);
        if (teclat == null) throw new ExcepcioIdNoValid();

        Teclat nouTeclat = teclat.shallowCopy();
        Algorisme algorisme = nouTeclat.getAlgorisme();

        Alfabet[] alfabets = gestorAlfabets.getAlfabets();
        Frequencies frequencies = new Frequencies();
        frequencies.genera(contingutsFitxer, alfabets);

        int columnes = 7;
        int files = 6;
        char[][] layout = algorisme.generarLayout(frequencies, columnes, files);
        nouTeclat.setLayout(layout);
        try {
            gestorUsuaris.modificarTeclat(usuari.getUsername(), idTeclat, nouTeclat);
        }
        catch (Exception e) {
            throw new ExcepcioErrorDurantModificacio();
        }
        teclats.put(idTeclat, nouTeclat);
    }

    // retorna 1 si ha modificat el teclat, retorna 2 si el teclat no utilitza cap algorisme existent, retorna 3 si no s'ha pogut eliminar el teclat, retorna 4 si no s'ha pogut tornar a crear el teclat
    public void modificarTeclatFrequenciesFitxerTextPublic(Integer idTeclat, Integer idText) throws ExcepcioFrequencies, ExcepcioIdNoValid, ExcepcioErrorDurantModificacio, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        HashMap<Integer,Teclat> teclats = usuari.getTeclats();
        Teclat teclat = teclats.get(idTeclat);
        if (teclat == null) throw new ExcepcioIdNoValid();

        Text text = gestorTexts.getTextPublic(idText);
        String contingutsFitxer =  text.llegirContingut();

        Teclat nouTeclat = teclat.shallowCopy();
        Algorisme algorisme = nouTeclat.getAlgorisme();

        Alfabet[] alfabets = gestorAlfabets.getAlfabets();
        Frequencies frequencies = new Frequencies();
        frequencies.genera(contingutsFitxer, alfabets);

        int columnes = 7;
        int files = 6;
        char[][] layout = algorisme.generarLayout(frequencies, columnes, files);
        nouTeclat.setLayout(layout);


        try {
            gestorUsuaris.modificarTeclat(usuari.getUsername(), idTeclat, nouTeclat);
        }
        catch (Exception e) {
            throw new ExcepcioErrorDurantModificacio();
        }
        teclats.put(idTeclat, nouTeclat);
    }

    // retorna 1 si ha modificat el teclat, retorna 2 si el teclat no utilitza cap algorisme existent, retorna 3 si no s'ha pogut eliminar el teclat, retorna 4 si no s'ha pogut tornar a crear el teclat
    public void modificarTeclatFrequenciesFitxerTextPredefinit(Integer idTeclat, Integer idText) throws ExcepcioFrequencies, ExcepcioIdNoValid, ExcepcioErrorDurantModificacio, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        HashMap<Integer,Teclat> teclats = usuari.getTeclats();
        Teclat teclat = teclats.get(idTeclat);
        if (teclat == null) throw new ExcepcioIdNoValid();

        Text text = gestorTexts.getTextPredefinit(idText);
        String contingutsFitxer =  text.llegirContingut();

        Teclat nouTeclat = teclat.shallowCopy();
        Algorisme algorisme = nouTeclat.getAlgorisme();

        Alfabet[] alfabets = gestorAlfabets.getAlfabets();
        Frequencies frequencies = new Frequencies();
        frequencies.genera(contingutsFitxer, alfabets);

        int columnes = 7;
        int files = 6;
        char[][] layout = algorisme.generarLayout(frequencies, columnes, files);
        nouTeclat.setLayout(layout);


        try {
            gestorUsuaris.modificarTeclat(usuari.getUsername(), idTeclat, nouTeclat);
        }
        catch (Exception e) {
            throw new ExcepcioErrorDurantModificacio();
        }
        teclats.put(idTeclat, nouTeclat);
    }


    //GESTIONAR TEXTOS PUBLICS DE L'USUARI

    // retorna un map amb claus mapejades als textspublics int i els noms dels texts publics de l'usuari
    public HashMap<Integer,String> getTextsPublicsUsuari() throws ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        HashMap<Integer,String> textsOut = new HashMap<>();
        HashMap<Integer,Text> textsPublicsUsuari = gestorTexts.getTextsPublicsUsuari(usuari.getUsername());
        for (Map.Entry<Integer, Text> entry : textsPublicsUsuari.entrySet()){
            String title = entry.getValue().getNom();
            textsOut.put(entry.getKey(), title);
        }
        return textsOut;
    }

    //retorna un 1 si s'ha creat el fitxer public
    public void afegirTextPublic(String nom, String contingutFitxer) throws ExcepcioErrorDurantLaCreacio, ExcepcioNomTextPublicJaExisteix, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        TextPublic text = new TextPublic(nom, contingutFitxer, usuari.getUsername());
        try {
            gestorTexts.crearTextPublic(text);
        }
        catch(ExcepcioNomTextPublicJaExisteix e){
            throw new ExcepcioNomTextPublicJaExisteix();
        }
        catch(Exception e){
            throw new ExcepcioErrorDurantLaCreacio();
        }

    }

    // S'elimina el text public
    public void eliminarTextPublic(Integer idTextPublicUsuari) throws ExcepcioIdNoValid, ExcepcioErrorDurantEsborrat{
        try {
            gestorTexts.eliminarTextPublic(idTextPublicUsuari);
        }
        catch(ExcepcioIdNoValid e) {
            throw new ExcepcioIdNoValid();
        }
        catch(Exception e){
            throw new ExcepcioErrorDurantEsborrat();
        }
    }

    // retorna 1 si s'ha modificat correctament
    public void modificarTextPublicNom(Integer idTextPublicUsuari, String nom) throws ExcepcioIdNoValid, ExcepcioErrorDurantModificacio, ExcepcioNomTextPublicJaExisteix {

        TextPublic textPublicUsuari = gestorTexts.getTextPublic(idTextPublicUsuari);
        textPublicUsuari.setNom(nom);

        try {
            gestorTexts.modificarTextPublic(idTextPublicUsuari, textPublicUsuari);
        }
        catch(ExcepcioNomTextPublicJaExisteix e){
            throw new ExcepcioNomTextPublicJaExisteix();
        }
        catch(Exception e){
            throw new ExcepcioErrorDurantModificacio();
        }
    }

    public String getTextPublic(Integer idTextPublicUsuari) throws ExcepcioIdNoValid{

        TextPublic textPublicUsuari = gestorTexts.getTextPublic(idTextPublicUsuari);
        return textPublicUsuari.llegirContingut();

    }


//**********************************A PARTIR D AQUI TAMBE*********************************************************************

    //USUARI ADMIN

    //Gestio d'usuaris

    //retorna un map amb calu id int i els usernames dels usuaris
    public String[] getUsuaris() throws ExcepcioUsuariNoEsAdmin, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        String[] usuarisOut;
        if(usuari.isAdmin()) {
            HashMap<String,Usuari> usuaris = gestorUsuaris.getUsuaris();
            usuarisOut = new String[usuaris.size()-1];
            int i = 0;
            for (Map.Entry<String, Usuari> entry : usuaris.entrySet()) {
                if(!entry.getKey().equals("UsuariDefault")){
                    usuarisOut[i] = entry.getKey();
                    ++i;
                }
            }
        }
        else throw new ExcepcioUsuariNoEsAdmin();
        return usuarisOut;
    }

    //transforma a l'usuari a admin
    public void setAdmin(String username) throws ExcepcioErrorDurantModificacio, ExcepcioUsernameNoExisteix, ExcepcioUsuariNoEsAdmin, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        if(usuari.isAdmin()){
            try {
                gestorUsuaris.setAdmin(username);
            }
            catch (ExcepcioUsernameNoExisteix e) {
                throw new ExcepcioUsernameNoExisteix();
            }
            catch (Exception e) {
                throw new ExcepcioErrorDurantModificacio();
            }
        }
        else throw new ExcepcioUsuariNoEsAdmin();

    }

    //elimina usuati
    public void eliminarUsuari(String username) throws ExcepcioUsernameNoExisteix, ExcepcioErrorDurantEsborrat, ExcepcioUsuariNoEsAdmin, ExcepcioNoUsuariLogin {
        if (usuari == null) throw new ExcepcioNoUsuariLogin();
        if(usuari.isAdmin()){
            try {
                gestorUsuaris.eliminarUsuari(username);
            }
            catch (ExcepcioUsernameNoExisteix e) {
                throw new ExcepcioUsernameNoExisteix();
            }
            catch (Exception e) {
                throw new ExcepcioErrorDurantEsborrat();
            }

        }
        else throw new ExcepcioUsuariNoEsAdmin();
    }

//*******************************************************************************************************


    //Gestio texts predefinits

    //retorna un 1 si s'ha creat el fitxer predefinit
    public void afegirTextPredefinit(String nom, String contingutFitxer) throws ExcepcioErrorDurantLaCreacio, ExcepcioNomTextPredefinitJaExisteix{
        TextPredefinit text = new TextPredefinit(nom, contingutFitxer);
        try {
            gestorTexts.crearTextPredefinit(text);
        }
        catch(ExcepcioNomTextPredefinitJaExisteix e){
            throw new ExcepcioNomTextPredefinitJaExisteix();
        }
        catch(Exception e){
            throw new ExcepcioErrorDurantLaCreacio();
        }
    }

    // retorna 1 si s'ha eliminat correctament
    public void eliminarTextPredefinit(Integer idTextPredefinit) throws ExcepcioIdNoValid, ExcepcioErrorDurantEsborrat{
        try {
            gestorTexts.eliminarTextPredefinit(idTextPredefinit);
        }
        catch(ExcepcioIdNoValid e) {
            throw new ExcepcioIdNoValid();
        }
        catch(Exception e){
            throw new ExcepcioErrorDurantEsborrat();
        }
    }

    // retorna 1 si s'ha modificat correctament
    public void modificarTextPredefinitNom(Integer idTextPredefinit, String nom) throws ExcepcioIdNoValid, ExcepcioErrorDurantModificacio,ExcepcioNomTextPredefinitJaExisteix{
        TextPredefinit textPredefinit = gestorTexts.getTextPredefinit(idTextPredefinit);
        textPredefinit.setNom(nom);
        try {
            gestorTexts.modificarTextPredefinit(idTextPredefinit, textPredefinit);
        }
        catch(ExcepcioNomTextPredefinitJaExisteix e){
            throw new ExcepcioNomTextPredefinitJaExisteix();
        }
        catch(Exception e){
            throw new ExcepcioErrorDurantModificacio();
        }

    }

    public String getTextPredefinit(Integer idTextPredefinit) throws ExcepcioIdNoValid {
        TextPredefinit textPredefinit = gestorTexts.getTextPredefinit(idTextPredefinit);
        return textPredefinit.llegirContingut();
    }


}

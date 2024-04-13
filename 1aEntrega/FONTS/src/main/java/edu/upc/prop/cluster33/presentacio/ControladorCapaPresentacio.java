package edu.upc.prop.cluster33.presentacio;

import edu.upc.prop.cluster33.domini.ControladorCapaDomini;
import edu.upc.prop.cluster33.excepcions.*;

import java.util.HashMap;
import java.util.Vector;


public class ControladorCapaPresentacio {
/*
    //a la primera entrega, una única vista
    private ViewTerminal vt;
    private ControladorCapaDomini controladorDomini;

    public ControladorCapaPresentacio() throws Exception{
        controladorDomini = new ControladorCapaDomini();
        vt = new ViewTerminal(this);

        vt.iniTerminal();

    }

    public void iniciaInstancia(String username, String password) throws ExcepcioUsernameNoExisteix, ExcepcioPasswordIncorrecte {
         controladorDomini.login(username, password);
    }

    public void enregistraUsuari(String username, String password, String maBona, boolean isAdmin) throws ExcepcioUsernameJaExistent, ExcepcioPasswordNoPassaFiltre, ExcepcioFormatMaIncorrecte {
         controladorDomini.registre(username, password, maBona, isAdmin);
    }

    public void logout() throws ExcepcioErrorGuardantDades{
        controladorDomini.logout() ;
    }

    public void canviaPassword(String passAntic, String passNou) throws ExcepcioPasswordIncorrecte, ExcepcioPasswordNoPassaFiltre{
        controladorDomini.canviarPassword(passAntic, passNou);
    }

    public String getMaBona(){
        return controladorDomini.getMaBona();
    }

    public void canviaMaBona(){
        controladorDomini.canviarMaBona();
    }

    public void esborraUsuari(String username, String password) throws ExcepcioUsernamePasswordIncorrectes, ExcepcioIntentEsborrarAdmin, ExcepcioErrorDurantEsborrat{
        controladorDomini.eliminarPropiUsuari(username, password);
    }

    public HashMap<Integer,String> getTeclats(){
        return controladorDomini.getTeclats();
    }

    public Vector<String> getTeclat(int id) throws ExcepcioIdNoValid{
        return controladorDomini.getTeclat(id);
    }

    public HashMap<Integer,String> getAlgorismes(){
        return controladorDomini.getAlgorismes();
    }
    public String getAlgorismeEnUs(int id) throws ExcepcioIdNoValid{
        return controladorDomini.getAlgorismeTeclat(id);
    }

    public void modificarTeclatAlgorisme(int idTeclat, int idAlgorisme) throws ExcepcioIdNoValid{
        controladorDomini.modificarTeclatAlgorisme(idTeclat, idAlgorisme);
    }
    public String getNomTeclat(int id) throws ExcepcioIdNoValid{
        return controladorDomini.getNomTeclat(id);
    }
    public void modificarTeclatNom(int id, String nouNom){
        controladorDomini.modificarTeclatNom(id, nouNom);
    }

    public void eliminarTeclat(int id) throws ExcepcioIdNoValid{
        controladorDomini.eliminarTeclat(id);
    }

    public void crearTeclatInputManual(int idAlgorisme, String frequencies, String nomTeclat) throws ExcepcioIdNoValid, ExcepcioNomTeclatJaExisteix, ExcepcioMesDeUnAlfabetAlhora{
        controladorDomini.crearTeclatInputManual(idAlgorisme, frequencies, nomTeclat);
    }
    public void crearTeclatFitxerTextUsuari(int idAlgorisme, String text, String nomTeclat) throws ExcepcioIdNoValid, ExcepcioNomTeclatJaExisteix, ExcepcioMesDeUnAlfabetAlhora{
        controladorDomini.crearTeclatFitxerTextUsuari(idAlgorisme, text, nomTeclat);
    }

    public HashMap<Integer,String> getTextsPublics(){
        return controladorDomini.getTextsPublics();
    }
    public HashMap<Integer,String> getTextsPredefinits(){
        return controladorDomini.getTextsPredefinits();
    }

    public void crearTeclatFitxerTextPublic(int idAlgorisme, int idTextPublic, String nomTeclat) throws ExcepcioIdNoValid, ExcepcioNomTeclatJaExisteix, ExcepcioMesDeUnAlfabetAlhora{
        controladorDomini.crearTeclatFitxerTextPublic(idAlgorisme, idTextPublic, nomTeclat);
    }

    public void crearTeclatFitxerTextPredefinit (int idAlgorisme, int idTextPredefinit, String nomTeclat) throws ExcepcioIdNoValid, ExcepcioNomTeclatJaExisteix, ExcepcioMesDeUnAlfabetAlhora{
    controladorDomini.crearTeclatFitxerTextPredefinit(idAlgorisme, idTextPredefinit, nomTeclat);
    }

    public String getTextPublic(int id) throws ExcepcioIdNoValid{
        return controladorDomini.getTextPublic(id);
    }

    public String getTextPredefinit(int id) throws ExcepcioIdNoValid{
        return controladorDomini.getTextPredefinit(id);
    }



    public void eliminarTextPublic(int id) throws ExcepcioIdNoValid{
        controladorDomini.eliminarTextPublic(id);
    }

    public void eliminarTextPredefinit(int id) throws ExcepcioIdNoValid{
        controladorDomini.eliminarTextPredefinit(id);
    }

    public void afegirTextPublic(String nomTextPublic, String text) throws ExcepcioNomTextPublicJaExisteix{
        controladorDomini.afegirTextPublic(nomTextPublic, text);
    }

    public void modificarTeclatFrequenciesInputManual(int idTeclat, String frequencies) throws ExcepcioIdNoValid{
        controladorDomini.modificarTeclatFrequenciesInputManual(idTeclat, frequencies);
    }

    public void modificarTeclatFrequenciesFitxerTextUsuari(int idTeclat, String text) throws ExcepcioIdNoValid, ExcepcioMesDeUnAlfabetAlhora{
        controladorDomini.modificarTeclatFrequenciesFitxerTextUsuari(idTeclat, text);
    }

    public void modificarTeclatFrequenciesFitxerTextPublic(int idTeclat, int idTextPublic) throws ExcepcioIdNoValid, ExcepcioNomTeclatJaExisteix, ExcepcioMesDeUnAlfabetAlhora{
        controladorDomini.modificarTeclatFrequenciesFitxerTextPublic(idTeclat, idTextPublic);
    }

    public void modificarTeclatFrequenciesFitxerTextPredefinit(int idTeclat, int idTextPredefinit) throws ExcepcioIdNoValid, ExcepcioNomTeclatJaExisteix, ExcepcioMesDeUnAlfabetAlhora{
        controladorDomini.modificarTeclatFrequenciesFitxerTextPredefinit(idTeclat, idTextPredefinit);
    }

    public boolean isAdmin(){
        return controladorDomini.isAdmin();
    }

    public HashMap<Integer, String> getUsuaris(){
        return controladorDomini.getUsuaris();
    }

    public void setAdmin(int id) throws ExcepcioIdNoValid{
        controladorDomini.setAdmin(id);
    }

    public void eliminaAdminUsuari(int id) throws ExcepcioIdNoValid, ExcepcioUsuariEsAdmin{
        controladorDomini.eliminarUsuari(id);
    }
    */

}
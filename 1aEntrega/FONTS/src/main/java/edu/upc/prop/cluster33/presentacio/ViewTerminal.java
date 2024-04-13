package edu.upc.prop.cluster33.presentacio;
import edu.upc.prop.cluster33.excepcions.*;
import edu.upc.prop.cluster33.utils.inout;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class ViewTerminal {

    private ControladorCapaPresentacio controladorPresentacio;
    private inout io;

    public ViewTerminal(ControladorCapaPresentacio cp){
        controladorPresentacio = cp;
        io = new inout();
    }
/*
    public void loginAmbExit(String username) throws Exception{
        io.writeln(String.format("Usuari %s ha fet login correctament.", username));
        mostraMenu();
        esperaSeleccioMenu();
    }

    public void mostraOpcionsErrorLogin() throws Exception{
        io.writeln("\nSi vol cancelar, introdueixi un 1 en el terminal");
        io.writeln("\nSi vol tornar a introduir les credencials, introdueixi un 2 en el terminal");
    }

    public void esperaSeleccioErrorLogin() throws Exception{
        int op = io.readint();
        if(op == 1) mostraOpcionsLogin();
        else if(op == 2) ferLogin();
        else {
            io.writeln("\nERROR: Ha introduit una opcio no valida");
            io.writeln("Sisplau, introdueixi una de les opcions disponibles al terminal");
            esperaSeleccioErrorLogin();
        }
    }

    public void errorImpossible() throws Exception{
        io.writeln("\nHA SUCCEIT UN ERROR IMPOSSIBLE");
        //nose que posar, que exploti el programa o algo
    }

    public void ferLogin() throws Exception{
        io.writeln("Ha seleccionat fer login");
        io.writeln("\nIntrodueixi el seu username:");
        String username = io.readword();
        io.writeln("Introdueixi el seu password");
        String password = io.readword();
        try{
            controladorPresentacio.iniciaInstancia(username, password);
        }
        catch(ExcepcioUsernameNoExisteix e1){
            io.writeln(String.format("\nERROR: %s", e1));
            mostraOpcionsErrorLogin();
            esperaSeleccioErrorLogin();
        }
        catch(ExcepcioPasswordIncorrecte e1){
            io.writeln(String.format("\nERROR: %s", e1));
            mostraOpcionsErrorLogin();
            esperaSeleccioErrorLogin();
        }
       loginAmbExit(username);
    }

    public void usuariCreatAmbExit(String username) throws Exception{
        io.writeln("\nUsuari creat amb exit!!\n");
        loginAmbExit(username);
    }

    public void nouUsuari() throws Exception{
        io.writeln("\nCreacio d'un nou usuari:");
        io.writeln("Introdueixi el username:");
        String username = io.readword();
        io.writeln("Introdueixi el seu password:");
        String password = io.readword();
        io.writeln("Introdueixi la seva ma bona (en el format Dreta/Esquerra):");
        String maBona = io.readword();
        try{
            controladorPresentacio.enregistraUsuari(username, password, maBona, false);
        }
        catch (ExcepcioUsernameJaExistent e1){
            io.writeln(String.format("\nERROR: %s", e1));
            nouUsuari();
        }
        catch (ExcepcioPasswordNoPassaFiltre e1){
            io.writeln(String.format("\nERROR: %s", e1));
            nouUsuari();
        }
        catch (ExcepcioFormatMaIncorrecte e1){
            io.writeln(String.format("\nERROR: %s", e1));
            nouUsuari();
        }
        usuariCreatAmbExit(username);
    }

    private void mostraOpcionsLogin() throws Exception{
        io.writeln("Opcions disponibles:\n");
        io.writeln("1. Iniciar sessio amb usuari existent");
        io.writeln("2. Crear usuari nou");
        io.writeln("3. Sortir del sistema");
    }

    private void sortirSistema() throws Exception{
        io.writeln("\nFINS AVIAT!!");
        System.exit(0);
    }

    private void esperaSeleccioLogin() throws Exception{
        io.writeln("\nEscull una de les opcions indicant el numero al terminal");
        int op = io.readint();
        if(op == 1) ferLogin();
        else if(op == 2) nouUsuari();
        else if(op == 3) sortirSistema();
        else{
            io.writeln("\nERROR: L'entrada no correspon amb cap de les opcions\n");
            esperaSeleccioLogin();
        }
    }

    public void escullAltresFuncions() throws Exception{
        io.writeln("\nSi vol dur a terme alguna altre funcionalitat del sistema, entri 's' al terminal. Si no, introdueixi qualsevol cosa i es fara logout del seu usuari \n");
        String resposta = io.readword();
        if(resposta.equals("s")) {
            mostraMenu();
            esperaSeleccioMenu();
        }
        //si no vol fer res mes, es com si es fa un logout
        escollit15();
    }

    public void mostraMenu() throws Exception{
        io.writeln("Opcions disponibles:\n");
        io.writeln("0. Mes informacio sobre les opcions disponibles");
        io.writeln("1. Gestionar teclats");
        io.writeln("2. Crear teclat");
        io.writeln("3. Gestionar usuari");
        io.writeln("4. Gestionar texts publics");
        io.writeln("5. Penjar text public");
        if(controladorPresentacio.isAdmin()) {
            io.writeln("6. Gestionar texts predefinits");
            io.writeln("7. Gestionar usuaris");
        }
        io.writeln("15.Logout");
    }

    public void esperaSeleccioMenu() throws Exception{
        io.writeln("\nEscull una funcionalitat del menu a dur a terme, indicant el numero corresponent a la terminal");
        int funcionalitat = io.readint();
        if(funcionalitat == 0) mesInformacioMenu();
        else if(funcionalitat == 1) escollit1();
        else if(funcionalitat == 2) escollit2();
        else if(funcionalitat == 3) escollit3();
        else if(funcionalitat == 4) escollit4();
        else if(funcionalitat == 5) escollit5();
        else if(funcionalitat == 6 && controladorPresentacio.isAdmin()) escollit6();
        else if(funcionalitat == 7 && controladorPresentacio.isAdmin()) escollit7();
        else if(funcionalitat == 15) escollit15();
        else {
            io.writeln("\nERROR: L'entrada no correspon amb cap de les opcions proporcionades pel sistema");
            esperaSeleccioMenu();
        }
    }

    public void mesInformacioMenu() throws Exception{
        io.writeln("Informacio adicional a les funcionalitats del sistema:");
        io.writeln("");
        escullAltresFuncions();
    }

    public void escollit1() throws Exception{
        io.writeln("\nHa escollit l'opcio 1, gestionar els seus teclats");
        io.writeln("A continuacio se li mostren tots els seus teclats, amb un numero identificador i el seu nom");
        HashMap<Integer, String> teclats = controladorPresentacio.getTeclats();
        mostraTeclats(teclats);
        io.writeln("\nEscull teclat introduint el seu identificador al terminal\n");
        int id = io.readint();
        mostraOpcionsTeclat();
        esperaSeleccioTeclat(id);
        escullAltresFuncions();
    }

    private void mostraTeclats(HashMap<Integer, String> teclats) throws Exception{
        for(Map.Entry<Integer, String> entry : teclats.entrySet()) {
            io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
        }
    }

    public void mostraOpcionsTeclat() throws Exception{
        io.writeln("Opcions disponibles:\n");
        io.writeln("0. Mes informacio");
        io.writeln("1. Veure teclat");
        io.writeln("2. Modificar teclat"); //modificar nom, input, estrategia
        io.writeln("3. Eliminar teclat");
    }

    public void esperaSeleccioTeclat(int id) throws Exception{
        io.writeln("\nEscull una funcionalitat del menu a dur a terme, indicant el numero corresponent a la terminal");
        int funcionalitat = io.readint();
        if(funcionalitat == 0) mesInformacioTeclat();
        else if(funcionalitat == 1) visualitzaTeclat(id);
        else if(funcionalitat == 2) modificaTeclat(id);
        else if(funcionalitat == 3) eliminaTeclat(id);

        else {
            io.writeln("\nERROR: L'entrada no correspon amb cap de les opcions proporcionades pel sistema");
            esperaSeleccioMenu();
        }
    }

    public void mesInformacioTeclat() throws Exception{
        io.writeln("Informacio adicional de les funcionalitats sobre teclats");

        escullAltresFuncions();
    }

    public void visualitzaTeclat(int id) throws Exception{
        io.writeln(String.format("\nHa seleccionat, visualitzar el teclat amb id %d", id));

        try{
            Vector<String> infoTeclat = controladorPresentacio.getTeclat(id);
        }
        catch(ExcepcioIdNoValid e1){
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }

        //PRESENTAR INFO DEL TECLAT CONSULTAR COM ARRIBA LA INFO

        escullAltresFuncions();
    }

    public void modificaTeclat(int id) throws Exception{
        io.writeln(String.format("\nHa seleccionat, modificar el teclat amb id %d", id));
        mostraMenuModificacioTeclat();
        esperaSeleccioModificacioTeclat(id);
        escullAltresFuncions();
    }

    private void mostraMenuModificacioTeclat() throws Exception{
        io.writeln("Opcions disponibles:\n");
        io.writeln("1. Modificar nom del teclat");
        io.writeln("2. Modificar input del teclat");
        io.writeln("3.Modificar estrategia del teclat");
    }

    private void esperaSeleccioModificacioTeclat(int id) throws Exception{
        io.writeln("\nEsculli una funcionalitat del menu a dur a terme, indicant el numero corresponent a la terminal");
        int funcionalitat = io.readint();
        if(funcionalitat == 1) canviaNomTeclat(id);
        else if(funcionalitat == 2) canviaInputTeclat(id);
        else if(funcionalitat == 3) canviaEstrategiaTeclat(id);
        else {
            io.writeln("\nERROR: L'entrada no correspon amb cap de les opcions proporcionades pel sistema");
            esperaSeleccioMenu();
        }
    }

    public void canviaNomTeclat(int id) throws Exception{
        io.writeln("\nHa escollit la opcio 1, canviar el nom del teclat");
        String nomTeclatActual = "";
        try{
            nomTeclatActual = controladorPresentacio.getNomTeclat(id);
        }
        catch (ExcepcioIdNoValid e1){
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }
        io.writeln(String.format("El nom actual del teclat identificat amb id %d es %s. Si el vol canviar, introdueixi 's' al terminal. Si no, introdueixi qualsevol cosa", id, nomTeclatActual));
        String op = io.readword();
        if(op.equals("s")){
            io.writeln("Introdueixi el nou nom:");
            String nomTeclatNou = io.readword();
            controladorPresentacio.modificarTeclatNom(id, nomTeclatNou);
        }
        escullAltresFuncions();
    }

    public void canviaInputTeclat(int id) throws Exception{
        io.writeln("\nHa escollit la opcio 2, canviar l'input del teclat");
        mostraOpcionsInput();
        esperaSeleccioModificacioInput(id);
        escullAltresFuncions();
    }

    private void esperaSeleccioModificacioInput(int id) throws Exception{
        io.writeln("\nEsculli una funcionalitat del menu a dur a terme, indicant el numero corresponent a la terminal");
        int funcionalitat = io.readint();
        if(funcionalitat == 1) canviarTeclatManual(id);
        else if(funcionalitat == 2) canviarTeclatFrequencies(id);
        else if(funcionalitat == 3) canviarTeclatText(id);
        else if(funcionalitat == 4) canviarTeclatTextPublic(id);
        else if(funcionalitat == 5) canviarTeclatTextPredefinit(id);
        else {
            io.writeln("\nERROR: L'entrada no correspon amb cap de les opcions proporcionades pel sistema");
            esperaSeleccioMenu();
        }
    }

    private void canviarTeclatManual(int idTeclat) throws Exception{
        io.writeln("Introdueixi per terminal la entrada desitjada seguint el format indicat a continuacio:");
        io.writeln("paraula1-frequencia1");
        io.writeln("paraula2-frequencia2");
        io.writeln("paraula3-frequencia3;");
        io.writeln("Per acabar d'introduir, afegir un ';' al final de la frequencia final (paraulaN-frequenciaN;");
        String frequencies = "";
        char c = io.read();
        while(c != ';'){
            frequencies= frequencies + c;
            c = io.read();
        }
        frequencies = frequencies + ';';
        try{
            controladorPresentacio.modificarTeclatFrequenciesInputManual(idTeclat, frequencies);
        }
        catch (ExcepcioIdNoValid e1){
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }

        io.writeln("S'ha modificat el teclat");
        escullAltresFuncions();
    }

    private void canviarTeclatFrequencies(int idTeclat) throws Exception{
        io.writeln("Introdueixi per terminal la entrada desitjada seguint el format indicat a continuacio:");
        io.writeln("paraula1-frequencia1");
        io.writeln("paraula2-frequencia2");
        io.writeln("paraula3-frequencia3;");
        io.writeln("Per acabar d'introduir, afegir un ';' al final de la frequencia final (paraulaN-frequenciaN;");
        String frequencies = "";
        char c = io.read();
        while(c != ';'){
            frequencies= frequencies + c;
            c = io.read();
        }
        frequencies = frequencies + ';';
        try{
            controladorPresentacio.modificarTeclatFrequenciesInputManual(idTeclat, frequencies);
        }
        catch (ExcepcioIdNoValid e1){
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }

        io.writeln("S'ha modificat el teclat");
        escullAltresFuncions();
    }

    private void canviarTeclatText(int idTeclat) throws Exception{
        io.writeln("Introdueixi el text a partir del qual vol modificar l'input del seu teclat");
        io.writeln("Quan hagi acabat d'introduir el text, faci Ctrl+D per confirmar");

        Scanner scanner = new Scanner(System.in);
        StringBuilder inputText = new StringBuilder();
        while (scanner.hasNextLine()) {
            inputText.append(scanner.nextLine()).append("\n");
        }
        String text = inputText.toString();


        try {
            controladorPresentacio.modificarTeclatFrequenciesFitxerTextUsuari(idTeclat, text);
        }
        catch (ExcepcioIdNoValid e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        } catch (ExcepcioMesDeUnAlfabetAlhora e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }
        io.writeln("S'ha modificat el teclat");
        escullAltresFuncions();
    }

    private void canviarTeclatTextPublic(int idTeclat) throws Exception{
        HashMap<Integer, String> textsPublics = controladorPresentacio.getTextsPublics();
        io.writeln("Aquests son els texts publics disponibles al sistema:");
        for(Map.Entry<Integer, String> entry : textsPublics.entrySet()) {
            io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
        }
        io.writeln("\nSisplau, introdueixi el id del text public que vol utilitzar al terminal");
        int idTextPublic = io.readint();
        try{
            controladorPresentacio.modificarTeclatFrequenciesFitxerTextPublic(idTeclat, idTextPublic);
        }
        catch (ExcepcioIdNoValid e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        } catch (ExcepcioNomTeclatJaExisteix e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }
        catch (ExcepcioMesDeUnAlfabetAlhora e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }
        io.writeln("S'ha modificat el teclat");
        escullAltresFuncions();
    }

    private void canviarTeclatTextPredefinit(int idTeclat) throws Exception{
        HashMap<Integer, String> textsPredefinits = controladorPresentacio.getTextsPredefinits();
        io.writeln("Aquests son els texts predefinits disponibles al sistema:");
        for(Map.Entry<Integer, String> entry : textsPredefinits.entrySet()) {
            io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
        }
        io.writeln("\nSisplau, introdueixi el id del text predefinit que vol usar per terminal");
        int idTextPredefinit = io.readint();
        try{
            controladorPresentacio.modificarTeclatFrequenciesFitxerTextPredefinit(idTeclat, idTextPredefinit);
        }
        catch (ExcepcioIdNoValid e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        } catch (ExcepcioNomTeclatJaExisteix e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }
        catch (ExcepcioMesDeUnAlfabetAlhora e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }
        io.writeln("S'ha modificat el teclat");
        escullAltresFuncions();
    }

    public void canviaEstrategiaTeclat(int id) throws Exception {
        io.writeln("\nHa escollit la opcio 3, canviar la estrategia de crecio del teclat");
        HashMap<Integer, String> algorismes = controladorPresentacio.getAlgorismes();
        String algoritmeEnUs = "";
        try{
            algoritmeEnUs = controladorPresentacio.getAlgorismeEnUs(id);
        }
        catch (ExcepcioIdNoValid e1){
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }

        io.writeln(String.format("L'algorisme de creació del teclat amb id %d es %s", id, algoritmeEnUs));
        io.writeln("Els algorismes de creacio disponibles son:");
        for(Map.Entry<Integer, String> entry : algorismes.entrySet()) {
            io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
        }
        io.writeln("\nSisplau, introdueixi el id del algorisme al que vol canviar per terminal");
        int idAlgoritme = io.readint();
        try{
            controladorPresentacio.modificarTeclatAlgorisme(id, idAlgoritme);
        }
        catch (ExcepcioIdNoValid e1){
            io.writeln(String.format("\nERROR: %s", e1));
            canviaEstrategiaTeclat(id);
        }
        escullAltresFuncions();
    }

    public void eliminaTeclat(int id) throws Exception{
        io.writeln(String.format("\nHa seleccionat, eliminar el teclat amb id %d", id));
        io.writeln("Introdueixi 's' al terminal per confirmar l'esborrat del teclat");
        String op = io.readword();
        if(op.equals("s")){
            try{
                controladorPresentacio.eliminarTeclat(id);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nERROR: %s", e1));
                escullAltresFuncions();
            }
            io.writeln(String.format("S'ha eliminat el teclat amb id %d", id));
        }
        escullAltresFuncions();
    }

    public void escollit2() throws Exception{
        io.writeln("He escollit l'opcio 2, crear un teclat nou");
        io.writeln("Introdueix el nom del teclat:");
        String nomTeclat = io.readword();
        io.writeln("Algorismes disponibles:");
        HashMap<Integer, String> algorismes = controladorPresentacio.getAlgorismes();
        for(Map.Entry<Integer, String> entry : algorismes.entrySet()) {
            io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
        }
        io.writeln("Introdueixi el id del algorisme de creacio del teclat:");
        int idAlgorisme = io.readint();
        mostraOpcionsInput();
        esperaSeleccioInput(nomTeclat, idAlgorisme);
        escullAltresFuncions();
    }

    public void mostraOpcionsInput() throws Exception{
        io.writeln("Opcions disponibles:\n");
        io.writeln("1. Entrada frequencies per terminal");
        io.writeln("2. Entrada fitxer frequencies");
        io.writeln("3. Entrada fitxer text");
        io.writeln("4. Entrada fitxer text public");
        io.writeln("5. Entrada fitxer text predefinit");
    }

    public void esperaSeleccioInput(String nomTeclat, int idAlgorisme) throws Exception{
        io.writeln("\nEsculli una funcionalitat del menu a dur a terme, indicant el numero corresponent a la terminal");
        int funcionalitat = io.readint();
        if(funcionalitat == 1) crearTeclatManual(nomTeclat, idAlgorisme);
        else if(funcionalitat == 2) crearTeclatFrequencies(nomTeclat, idAlgorisme);
        else if(funcionalitat == 3) crearTeclatText(nomTeclat, idAlgorisme);
        else if(funcionalitat == 4) crearTeclatTextPublic(nomTeclat, idAlgorisme);
        else if(funcionalitat == 5) crearTeclatTextPredefinit(nomTeclat, idAlgorisme);
        else {
            io.writeln("\nERROR: L'entrada no correspon amb cap de les opcions proporcionades pel sistema");
            esperaSeleccioMenu();
        }
    }

    private void crearTeclatManual(String nomTeclat, int idAlgorisme) throws Exception{
        io.writeln("Introdueixi per terminal la entrada desitjada seguint el format indicat a continuacio:");
        io.writeln("paraula1-frequencia1");
        io.writeln("paraula2-frequencia2");
        io.writeln("paraula3-frequencia3;");
        io.writeln("Per acabar d'introduir, afegir un ';' al final de la frequencia final (paraulaN-frequenciaN;");
        String frequencies = "";
        char c = io.read();
        while(c != ';'){
            frequencies= frequencies + c;
            c = io.read();
        }
        frequencies = frequencies + ';';
        try{
            controladorPresentacio.crearTeclatInputManual(idAlgorisme, frequencies, nomTeclat);
        }
        catch (ExcepcioIdNoValid e1){
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        }
        catch (ExcepcioNomTeclatJaExisteix e1){
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        }
        io.writeln(String.format("S'ha creat el teclat amb nom %s", nomTeclat));
        escullAltresFuncions();
    }

    private void crearTeclatFrequencies(String nomTeclat, int idAlgorisme) throws Exception{
        //DE MOMENT COPIAT ADALT
        io.writeln("Introdueixi per terminal la entrada desitjada seguint el format indicat a continuacio:");
        io.writeln("paraula1-frequencia1");
        io.writeln("paraula2-frequencia2");
        io.writeln("paraula3-frequencia3;");
        io.writeln("Per acabar d'introduir, afegir un ';' al final de la frequencia final (paraulaN-frequenciaN;");
        String frequencies = "";
        char c = io.read();
        while(c != ';'){
            frequencies= frequencies + c;
            c = io.read();
        }
        frequencies = frequencies + ';';
        try{
            controladorPresentacio.crearTeclatInputManual(idAlgorisme, frequencies, nomTeclat);
        }
        catch (ExcepcioIdNoValid e1){
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        }
        catch (ExcepcioNomTeclatJaExisteix e1){
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        }
        catch (ExcepcioMesDeUnAlfabetAlhora e1){
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        }
        io.writeln(String.format("S'ha creat el teclat amb nom %s", nomTeclat));
        escullAltresFuncions();
    }

    private void crearTeclatText(String nomTeclat, int idAlgorisme) throws Exception {
        io.writeln("Introdueixi el text a partir del qual vol que es generi l'input del seu teclat");
        io.writeln("Quan hagi acabat d'introduir el text, faci Ctrl+D per confirmar");

        Scanner scanner = new Scanner(System.in);
        StringBuilder inputText = new StringBuilder();
        while (scanner.hasNextLine()) {
            inputText.append(scanner.nextLine()).append("\n");
        }
        String text = inputText.toString();


        try {
            controladorPresentacio.crearTeclatFitxerTextUsuari(idAlgorisme, text, nomTeclat);
        }
        catch (ExcepcioIdNoValid e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        } catch (ExcepcioNomTeclatJaExisteix e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        } catch (ExcepcioMesDeUnAlfabetAlhora e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        }
        io.writeln(String.format("S'ha creat el teclat amb nom %s", nomTeclat));
        escullAltresFuncions();
    }

    private void crearTeclatTextPublic(String nomTeclat, int idAlgorisme) throws Exception{
        HashMap<Integer, String> textsPublics = controladorPresentacio.getTextsPublics();
        io.writeln("Aquests son els texts publics disponibles al sistema:");
        for(Map.Entry<Integer, String> entry : textsPublics.entrySet()) {
            io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
        }
        io.writeln("\nSisplau, introdueixi el id del text public que vol utilitzar al terminal");
        int idTextPublic = io.readint();
        try{
            controladorPresentacio.crearTeclatFitxerTextPublic(idAlgorisme, idTextPublic, nomTeclat);
        }
        catch (ExcepcioIdNoValid e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        } catch (ExcepcioNomTeclatJaExisteix e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        }
        catch (ExcepcioMesDeUnAlfabetAlhora e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        }
        io.writeln(String.format("S'ha creat el teclat amb nom %s", nomTeclat));
        escullAltresFuncions();
    }

    private void crearTeclatTextPredefinit(String nomTeclat, int idAlgorisme) throws Exception{
        HashMap<Integer, String> textsPredefinits = controladorPresentacio.getTextsPredefinits();
        io.writeln("Aquests son els texts predefinits disponibles al sistema:");
        for(Map.Entry<Integer, String> entry : textsPredefinits.entrySet()) {
            io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
        }
        io.writeln("\nSisplau, introdueixi el id del text predefinit que vol usar per terminal");
        int idTextPredefinit = io.readint();
        try{
            controladorPresentacio.crearTeclatFitxerTextPredefinit(idAlgorisme, idTextPredefinit, nomTeclat);
        }
        catch (ExcepcioIdNoValid e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        } catch (ExcepcioNomTeclatJaExisteix e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        }
        catch (ExcepcioMesDeUnAlfabetAlhora e1) {
            io.writeln(String.format("\nERROR: %s", e1));
            esperaSeleccioInput(nomTeclat, idAlgorisme);
        }
        io.writeln(String.format("S'ha creat el teclat amb nom %s", nomTeclat));
        escullAltresFuncions();
    }

    public void escollit3() throws Exception{
        io.writeln("He escollit l'opcio 3, gestionar l'usuari");
        mostraMenuGestioUsuari();
        esperaSeleccioMenuGestioUsuari();
    }

    public void mostraMenuGestioUsuari() throws Exception{
        io.writeln("Opcions disponibles:\n");
        io.writeln("1. Canviar password");
        io.writeln("2. Canviar ma bona");
        io.writeln("3. Eliminar usuari");
    }

    public void esperaSeleccioMenuGestioUsuari() throws Exception{
        io.writeln("\nEscull una funcionalitat del menu a dur a terme, indicant el numero corresponent a la terminal");
        int funcionalitat = io.readint();
        if(funcionalitat == 1) canviaPassword();
        else if(funcionalitat == 2) canviaMaBona();
        else if(funcionalitat == 3) eliminaUsuari();
        else {
            io.writeln("\nERROR: L'entrada no correspon amb cap de les opcions proporcionades pel sistema");
            esperaSeleccioMenuGestioUsuari();
        }
    }

    public void canviaPassword() throws Exception{
        io.writeln("Ha seleccionat l'opcio 1, canviar password");
        io.writeln("\nSisplau introdueixi el seu password actual:");
        String passwordAntic = io.readword();
        io.writeln("\nSisplau, introdueixi el seu nou password:");
        String passwordNou = io.readword();

        try{
            controladorPresentacio.canviaPassword(passwordAntic, passwordNou);
        }
        catch (ExcepcioPasswordIncorrecte e1){
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }
        catch (ExcepcioPasswordNoPassaFiltre e1){
            io.write(String.format("\nERROR; %s", e1));
            escullAltresFuncions();
        }
        io.writeln("\nEl canvi de password s'ha realitzat correctament\n");
        escullAltresFuncions();
    }

    public void canviaMaBona() throws Exception{
        io.writeln("Ha seleccionat l'opcio 2, canviar la ma bona");
        String maBonaActual = controladorPresentacio.getMaBona();
        io.writeln("\nLa seva ma bona actual es: ");
        io.writeln(maBonaActual);
        io.writeln("\nSi vol canviar de ma bona, introdueixi 's' al terminal, sino introdueixi qualsevol altre cosa\n");
        String op = io.readword();
        if(op.equals("s")) {
            controladorPresentacio.canviaMaBona();
        }
        escullAltresFuncions();
    }

    public void eliminaUsuari() throws Exception{
        io.writeln("\nHa seleccionat l'opcio 3, eliminar l'usuari");
        io.writeln("\nESTA SEGUR QUE VOL ELIMINAR EL SEU USUARI, I PER TANT, TOTA LA SEVA INFORMACIO GUARDADA AL SISTEMA?");
        io.writeln("\nSi esta segur, introdueixi 's' al terminal, sino introdueixi qualsevol altre cosa\n");
        String resp = io.readword();
        if(resp.equals("s")) {
            io.writeln("\nIntrodueixi el seu username i el seu password per continuar amb l'esborrat del seu usuari");
            io.writeln("\nUsername: ");
            String username = io.readword();
            io.writeln("\nPassword: ");
            String password = io.readword();

            try {
                controladorPresentacio.esborraUsuari(username, password);
            } catch (ExcepcioUsernamePasswordIncorrectes e1) {
                io.writeln(String.format("ERROR: %s", e1));
                escullAltresFuncions();
            } catch (ExcepcioIntentEsborrarAdmin e1) {
                io.writeln(String.format("ERROR: %s", e1));
                escullAltresFuncions();
            } catch (ExcepcioErrorDurantEsborrat e1) {
                io.writeln(String.format("ERROR: %s", e1));
                escullAltresFuncions();
            }
            io.writeln("\nS'ha esborrat l'usuari correctament");
            mostraOpcionsLogin();
            esperaSeleccioLogin();
        }
    }

    private void escollit4() throws Exception{
        io.writeln("\nHa escollit l'opcio 4, gestionar els seus textos publics");
        io.writeln("A continuacio se li mostren tots els seus textos publics, amb un numero identificador i el seu nom");
        HashMap<Integer, String> textosPublics = controladorPresentacio.getTextsPublics();
        mostraTextos(textosPublics);
        io.writeln("\nEscull text introduint el seu identificador al terminal\n");
        int id = io.readint();
        mostraOpcionsTextosPublics();
        esperaSeleccioTextosPublics(id);

        escullAltresFuncions();
    }

    private void mostraTextos(HashMap<Integer, String> textos) throws Exception{
        for(Map.Entry<Integer, String> entry : textos.entrySet()) {
            io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
        }
    }

    public void mostraOpcionsTextosPublics() throws Exception{
        io.writeln("Opcions disponibles:\n");
        io.writeln("1. Veure text public");
        io.writeln("2. Eliminar text public");
    }

    public void esperaSeleccioTextosPublics(int id) throws Exception{
        io.writeln("\nEsculli una funcionalitat del menu a dur a terme, indicant el numero corresponent a la terminal");
        int funcionalitat = io.readint();
        if(funcionalitat == 1) veureTextPublic(id);
        else if(funcionalitat == 2) eliminarTextPublic(id);
        else {
            io.writeln("\nERROR: L'entrada no correspon amb cap de les opcions proporcionades pel sistema");
            esperaSeleccioMenu();
        }
    }

    private void veureTextPublic(int id) throws Exception{
        String textPublic = "";
        try{
            textPublic = controladorPresentacio.getTextPublic(id);
        }
        catch (ExcepcioIdNoValid e1){
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }
        io.writeln(textPublic);
        escullAltresFuncions();
    }

    private void eliminarTextPublic(int id) throws Exception{
        io.writeln(String.format("Si vol eliminar el text public amb identificador %d, introdueixi 's' al terminal, sino introdueixi qualsevol altre cosa", id));
        String op = io.readword();
        if(op.equals("s")){
            try{
                controladorPresentacio.eliminarTextPublic(id);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nERROR: %s", e1));
                escullAltresFuncions();
            }
            io.writeln(String.format("S'ha eliminat el text public amb identificador %d", id));
        }
        escullAltresFuncions();
    }

    private void escollit5() throws Exception{
        io.writeln("\nHa escollit l'opcio 5, penjar un nou text public");
        io.writeln("Introdueixi el nom del nou text public");
        String nomTextPublic = io.readword();

        io.writeln("Introdueixi el text public que vol penjar");
        io.writeln("Quan hagi acabat d'introduir el text, faci Ctrl+D per confirmar");

        Scanner scanner = new Scanner(System.in);
        StringBuilder inputText = new StringBuilder();
        while (scanner.hasNextLine()) {
            inputText.append(scanner.nextLine()).append("\n");
        }
        String text = inputText.toString();

        try{
           controladorPresentacio.afegirTextPublic(nomTextPublic, text);
        }
        catch(ExcepcioNomTextPublicJaExisteix e1){
            io.writeln(String.format("\nERROR: %s", e1));
           escullAltresFuncions();
        }
        io.writeln("S'ha penjat el text amb exit");
        escullAltresFuncions();
    }

    private void escollit6() throws Exception{
        io.writeln("\nHa escollit l'opcio 6, gestionar texts predefinits");
        io.writeln("A continuacio se li mostren tots els seus textos predefinits, amb un numero identificador i el seu nom");
        HashMap<Integer, String> textosPredefinits = controladorPresentacio.getTextsPredefinits();
        mostraTextos(textosPredefinits);
        io.writeln("\nEscull text introduint el seu identificador al terminal\n");
        int id = io.readint();
        mostraOpcionsTextosPredefinits();
        esperaSeleccioTextosPredefinits(id);

        escullAltresFuncions();
    }

    public void mostraOpcionsTextosPredefinits() throws Exception{
        io.writeln("Opcions disponibles:\n");
        io.writeln("1. Veure text predefinit");
        io.writeln("2. Crear nou text predefinit ");
        io.writeln("2. Eliminar text predefinit");
    }

    public void esperaSeleccioTextosPredefinits(int id) throws Exception{
        io.writeln("\nEsculli una funcionalitat del menu a dur a terme, indicant el numero corresponent a la terminal");
        int funcionalitat = io.readint();
        if(funcionalitat == 1) veureTextPredefinit(id);
        if(funcionalitat == 2) crearTextPredefinit();
        else if(funcionalitat == 3) eliminarTextPredefinit(id);
        else {
            io.writeln("\nERROR: L'entrada no correspon amb cap de les opcions proporcionades pel sistema");
            esperaSeleccioMenu();
        }
    }

    private void veureTextPredefinit(int id) throws Exception{
        String textPredefinit = "";
        try{
            textPredefinit = controladorPresentacio.getTextPredefinit(id);
        }
        catch (ExcepcioIdNoValid e1){
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }
        io.writeln(textPredefinit);
        escullAltresFuncions();
    }

    private void crearTextPredefinit() throws Exception{

        io.writeln("Introdueixi el nom del nou text predefinit");
        String nomTextPredefinit = io.readword();

        io.writeln("Introdueixi el text predefinit que vol penjar");
        io.writeln("Quan hagi acabat d'introduir el text, faci Ctrl+D per confirmar");

        Scanner scanner = new Scanner(System.in);
        StringBuilder inputText = new StringBuilder();
        while (scanner.hasNextLine()) {
            inputText.append(scanner.nextLine()).append("\n");
        }
        String text = inputText.toString();

        try{
            controladorPresentacio.afegirTextPublic(nomTextPredefinit, text);
        }
        catch(ExcepcioNomTextPublicJaExisteix e1){
            io.writeln(String.format("\nERROR: %s", e1));
            escullAltresFuncions();
        }
        io.writeln("S'ha penjat el text amb exit");
        escullAltresFuncions();
    }

    private void eliminarTextPredefinit(int id) throws Exception{
        io.writeln(String.format("Si vol eliminar el text predefinit amb identificador %d, introdueixi 's' al terminal, sino introdueixi qualsevol altre cosa", id));
        String op = io.readword();
        if(op.equals("s")){
            try{
                controladorPresentacio.eliminarTextPredefinit(id);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nERROR: %s", e1));
                escullAltresFuncions();
            }
            io.writeln(String.format("S'ha eliminat el text predefinit amb identificador %d", id));
        }
        escullAltresFuncions();
    }

    private void escollit7() throws Exception{
        io.writeln("\nHa escollit l'opcio 7, gestionar usuaris");
        io.writeln("A continuacio se li mostren tots els usuaris , amb un numero identificador i el seu username");
        HashMap<Integer, String> usuaris = controladorPresentacio.getUsuaris();
        mostraTeclats(usuaris); //es un hasmap igual
        io.writeln("\nEscull usuari introduint el seu identificador al terminal\n");
        int id = io.readint();
        mostraOpcionsGestioAdmin();
        esperaSeleccioGestioAdmin(id);
        escullAltresFuncions();
    }

    private void mostraOpcionsGestioAdmin() throws Exception{
        io.writeln("Opcions disponibles:\n");
        io.writeln("1. Promocionar a admin a un usuari");
        io.writeln("2. Eliminar un usuari");
    }

    private void esperaSeleccioGestioAdmin(int id) throws Exception{
        io.writeln("\nEsculli una funcionalitat del menu a dur a terme, indicant el numero corresponent a la terminal");
        int funcionalitat = io.readint();
        if(funcionalitat == 1){
            try{
                controladorPresentacio.setAdmin(id);
            }
            catch(ExcepcioIdNoValid e1){
                io.writeln(String.format("\nERROR: %s", e1));
                escullAltresFuncions();
            }
        }
        if(funcionalitat == 2){
            try{
                controladorPresentacio.eliminaAdminUsuari(id);
            }
            catch(ExcepcioIdNoValid e1){
                io.writeln(String.format("\nERROR: %s", e1));
                escullAltresFuncions();
            }
            catch(ExcepcioUsuariEsAdmin e1){
                io.writeln(String.format("\nERROR: %s", e1));
                escullAltresFuncions();
            }

        }

        else {
            io.writeln("\nERROR: L'entrada no correspon amb cap de les opcions proporcionades pel sistema");
            esperaSeleccioMenu();
        }
        escullAltresFuncions();
    }

    public void escollit15() throws Exception{
        io.writeln("S'esta fent el logout");
        try {
            controladorPresentacio.logout();
        }
       catch (ExcepcioErrorGuardantDades e1){
           io.writeln(String.format("ERROR: %s", e1));
           escullAltresFuncions();
       }
        io.writeln("\nCanvis guardats correctament. Fins la proxima!!");
        mostraOpcionsLogin();
        esperaSeleccioLogin();
    }

    public void iniTerminal() throws Exception{
        io.writeln("\n ####### SISTEMA GESTOR DE TECLATS ####### \n");
        mostraOpcionsLogin();
        esperaSeleccioLogin();

        mostraMenu();
    }

 */
}
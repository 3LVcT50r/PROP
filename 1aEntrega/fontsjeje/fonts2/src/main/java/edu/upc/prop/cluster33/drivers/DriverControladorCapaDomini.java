package edu.upc.prop.cluster33.drivers;

import edu.upc.prop.cluster33.domini.ControladorCapaDomini;
import edu.upc.prop.cluster33.excepcions.*;
import edu.upc.prop.cluster33.utils.inout;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class DriverControladorCapaDomini {
    private ControladorCapaDomini controladorCapaDomini;
    private static inout io;

    private static void printIns() {
        System.out.println("Selecciona una de les funcionalitats:");
        System.out.println("0. Tornar a escriure les instruccions");
        System.out.println("1. Test Get Tots Els Teclats");
        System.out.println("2. Test Get Teclat");
        System.out.println("3. Test Get Algoritme Teclat");
        System.out.println("4. Test Get Nom Teclat");
        System.out.println("5. Test Esborrar Teclat");
        System.out.println("6. Test Get All Algorismes");
        System.out.println("7. Test Get All Texts Publics");
        System.out.println("8. Test Get All Texts Predefinits");
        System.out.println("9. Test Crear Teclat Input Manual");
        System.out.println("10. Test Crear Teclat Fitxer Frequencies");
        System.out.println("11. Test Crear Teclat Fitxer Text Del Usuari");
        System.out.println("12. Test Crear Teclat Fitxer Text Public");
        System.out.println("13. Test Crear Teclat Fitxer Text Predefinit");
        System.out.println("14. Test Canviar Nom Teclat");
        System.out.println("15. Test Canviar Algorisme Teclat");
        System.out.println("16. Test Canviar A Input Manual");
        System.out.println("17. Test Canviar A Input Fitxer Frequencies");
        System.out.println("18. Test Canviar A Input Fitxer Text usuari");
        System.out.println("19. Test Canviar A Input Fitxer Text Public");
        System.out.println("20. Test Canviar A Input Fitxer Text Predefinit");
        System.out.println("21. Test Get Textos Publics Usuari");
        System.out.println("22. Test Afegir Text Public");
        System.out.println("23. Test Eliminar Text Public");
        System.out.println("24. Test Canviar Nom Text Public");
        System.out.println("25. Test Get Text Public");
        System.out.println("26. Test Afegir Text Predefinit");
        System.out.println("27. Test Eliminar Text Predefinit");
        System.out.println("28. Test Canviar Nom Text Predefinit");
        System.out.println("29. Test Get Text Predefinit");

        System.out.println("99. Finalitzar driver");

        System.out.println("SI NO ES POSA UN NUMERO, EL DRIVER PETA");

    }

    public static void main(String[] args) throws Exception {
        DriverControladorCapaDomini dcd = new DriverControladorCapaDomini();
        System.out.println("Estas provant el driver de la classe ControladorCapaDomini\n");
        System.out.println("Abans de provar la classe anem a crear un nou ControladorCapaDomini");
        dcd.TestCreadoraControladorCapaDomini();

        Scanner sc = new Scanner(System.in);
        int func = 0;
        printIns();

        while (func != -1) {

            func = sc.nextInt();

            switch (func) {
                case 0:
                    printIns();
                    break;
                case 1:
                    dcd.TestGetAllTeclats();
                    break;
                case 2:
                    dcd.TestGetTeclat();
                    break;
                case 3:
                    dcd.TestGetAlgorismeTeclat();
                    break;
                case 4:
                    dcd.TestGetNomTeclat();
                    break;
                case 5:
                    dcd.TestEsborrarTeclat();
                    break;
                case 6:
                    dcd.TestGetAlgoritmes();
                    break;
                case 7:
                    dcd.TestGetTextsPublics();
                    break;
                case 8:
                    dcd.TestGetTextsPredefinits();
                    break;
                case 9:
                    dcd.TestCrearTextInputManual();
                    break;
                case 10:
                    dcd.TestCrearTeclatFitxerTextFrequencies();
                    break;
                case 11:
                    dcd.TestCrearTeclatFitxerTextUsuari();
                    break;
                case 12:
                    dcd.TestCrearTeclatFitxerTextPublic();
                    break;
                case 13:
                    dcd.TestCrearTeclatFitxerTextPredefinit();
                    break;
                case 14:
                    dcd.TestCanviarNomTeclat();
                    break;
                case 15:
                    dcd.TestCanviarAlgorismeTeclat();
                    break;
                case 16:
                    dcd.TestCanviarInputManual();
                    break;
                case 17:
                    dcd.TestCanviarInputFitxerFreq();
                    break;
                case 18:
                    dcd.TestCanviarInputFitxerTextUsuari();
                    break;
                case 19:
                    dcd.TestCanviarInputFitxerTextPublic();
                    break;
                case 20:
                    dcd.TestCanviarInputFitxerTextPredefinit();
                    break;
                case 21:
                    dcd.TestGetTextosPublicsUsuari();
                    break;
                case 22:
                    dcd.TestAfegirNouTextPublic();
                    break;
                case 23:
                    dcd.TestEliminarTextPublic();
                    break;
                case 24:
                    dcd.TestCanviarNomTextPublic();
                    break;
                case 25:
                    dcd.TestGetTextPublic();
                    break;
                case 26:
                    dcd.TestAfegirNouTextPredefinit();
                    break;
                case 27:
                    dcd.TestEliminarTextPredefinit();
                    break;
                case 28:
                    dcd.TestCanviarNomTextPredefinit();
                    break;
                case 29:
                    dcd.TestGetTextPredefinit();
                    break;
                case 99:
                    System.out.println("Finalitzant driver");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Funció no trobada");
            }

            System.out.println("=============================================================");
        }
    }


    private void TestCreadoraControladorCapaDomini() throws Exception{

        controladorCapaDomini = new ControladorCapaDomini();
        io = new inout();
        try{
            //login usuari fantasma per poder realitzar totes les funcionalitats principals sense dependre d'un login
            controladorCapaDomini.login("UsuariDefault", "UsuariDefault");
        }
        catch(Exception e1){
            io.writeln(("ERRO R: Aixoooo no pot passar mai"));
            return;
        }
        System.out.println("S'ha creat una nova instancia de ControladorCapaDomini");
    }


    private boolean TestGetAllTeclats() throws Exception{
        HashMap<Integer, String> teclats = controladorCapaDomini.getTeclats();
        if(teclats.isEmpty()) io.writeln("No te cap teclat al sistema. Sisplau seleccioni la opcio per crear un teclat");
        else{
            io.writeln("A continuacio es mostren tots els teclats, identificats per un identificador, seguit del nom del teclat");
            for(Map.Entry<Integer, String> entry : teclats.entrySet()) {
                io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
            }
        }
        return !teclats.isEmpty();
    }

    private void TestGetTeclat() throws Exception{
        HashMap<Integer, String> teclats = controladorCapaDomini.getTeclats();
        if(teclats.isEmpty()) io.writeln("No te cap teclat al sistema. Sisplau seleccioni la opcio per crear un teclat");
        else{
            io.writeln("A continuacio es mostren tots els teclats, identificats per un identificador, seguit del nom del teclat");
            for(Map.Entry<Integer, String> entry : teclats.entrySet()) {
                io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
            }
            io.writeln("Introdueixi el id del teclat que vol visualitzar:");
            int id = io.readint();
            Vector<String> infoTeclat;
            try{
                infoTeclat = controladorCapaDomini.getTeclat(id);
            }
            catch(ExcepcioIdNoValid e1){
                io.writeln(String.format("Ha ocorregut un error %s", e1));
                return;
            }
            //MOSTRA INFO TECLAT
            io.writeln(String.format("Nom del teclat: %s", infoTeclat.get(0)));
            io.writeln(String.format("Nom del algorisme amb el que s'ha generat: %s", infoTeclat.get(1)));
            io.writeln(String.format("Data de creacio del teclat: %s", infoTeclat.get(2)));
            io.writeln(String.format("Nom del alfabet: %s", infoTeclat.get(3)));
            io.writeln("Layout teclat:");
            for(int i = 4; i < infoTeclat.size(); ++i){
                io.writeln(infoTeclat.get(i));
            }
        }

    }

    private void TestGetAlgorismeTeclat() throws Exception{
        HashMap<Integer, String> teclats = controladorCapaDomini.getTeclats();
        if(teclats.isEmpty()) io.writeln("No te cap teclat al sistema. Sisplau seleccioni la opcio per crear un teclat");
        else{
            io.writeln("A continuacio es mostren tots els teclats, identificats per un identificador, seguit del nom del teclat");
            for(Map.Entry<Integer, String> entry : teclats.entrySet()) {
                io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
            }
            io.writeln("Introdueixi el id del teclat del que vol consultar el algorisme:");
            int id = io.readint();
            String algorismeTeclat;
            try{
                algorismeTeclat = controladorCapaDomini.getAlgorismeTeclat(id);
            }
            catch(ExcepcioIdNoValid e1){
                io.writeln(String.format("Ha ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("L'algorimse que usa el teclat identificat amb id %d, es %s", id, algorismeTeclat));
        }

    }


    private void TestGetNomTeclat() throws Exception{
        HashMap<Integer, String> teclats = controladorCapaDomini.getTeclats();
        if(teclats.isEmpty()) io.writeln("No te cap teclat al sistema. Sisplau seleccioni la opcio per crear un teclat");
        else {
            io.writeln("A continuacio es mostren tots els teclats, identificats per un identificador, seguit del nom del teclat");
            for(Map.Entry<Integer, String> entry : teclats.entrySet()) {
                io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
            }
            io.writeln("Introdueixi el id del teclat del que vol consultar el nom:");
            int id = io.readint();
            String nomTeclat;
            try{
                nomTeclat = controladorCapaDomini.getAlgorismeTeclat(id);
            }
            catch(ExcepcioIdNoValid e1){
                io.writeln(String.format("Ha ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("L'algorimse que usa el teclat identificat amb id %d, es %s", id, nomTeclat));
        }

    }

    private void TestEsborrarTeclat() throws Exception{
        HashMap<Integer, String> teclats = controladorCapaDomini.getTeclats();
        if(teclats.isEmpty()) io.writeln("No te cap teclat al sistema. Sisplau seleccioni la opcio per crear un teclat");
        else{
            io.writeln("A continuacio es mostren tots els teclats, identificats per un identificador, seguit del nom del teclat");
            for(Map.Entry<Integer, String> entry : teclats.entrySet()) {
                io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
            }
            io.writeln("Introdueixi el id del teclat que vol esborrar:");
            int id = io.readint();

            try{
                controladorCapaDomini.eliminarTeclat(id);
            }
            catch (ExcepcioErrorDurantEsborrat e1){
                io.writeln(String.format("Ha ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("Ha ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("S'ha esborrat el teclat amb id %d", id));
        }
    }

    private void TestGetAlgoritmes() throws Exception{
        HashMap<Integer, String> algorismes = controladorCapaDomini.getAlgorismes();
        io.writeln("A continuacio es mostren tots els algorismes, identificats per un identificador, seguit del nom del algorisme");
        for(Map.Entry<Integer, String> entry : algorismes.entrySet()) {
            io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
        }
    }

    private boolean TestGetTextsPublics() throws Exception{
        HashMap<Integer, String> textsPublics = controladorCapaDomini.getTextsPublics();
        if(textsPublics.isEmpty()) io.writeln("No hi ha cap text public. Sisplau seleccioni la opcio per crear un text public");
        else{
            io.writeln("A continuacio es mostren tots els texts publics, identificats per un identificador, seguit del nom del text");
            for(Map.Entry<Integer, String> entry : textsPublics.entrySet()) {
                io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
            }
        }
        return !textsPublics.isEmpty();
    }

    private boolean TestGetTextsPredefinits() throws Exception{
        HashMap<Integer, String> textsPredefinits = controladorCapaDomini.getTextsPredefinits();
        if(textsPredefinits.isEmpty()) io.writeln("No hi ha cap text predefinit. Sisplau seleccioni la opcio per crear un text predefinit");
        else{
            io.writeln("A continuacio es mostren tots els texts predefinits, identificats per un identificador, seguit del nom del text");
            for(Map.Entry<Integer, String> entry : textsPredefinits.entrySet()) {
                io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
            }
        }
        return !textsPredefinits.isEmpty();
    }

    private void TestCrearTextInputManual() throws Exception{
        TestGetAlgoritmes();
        io.writeln("Introdueixi el id del algorisme que vol usar per crear el teclat:");
        int idAlgorisme = io.readint();
        io.writeln("Introdueixi el nom que vol donar al teclat:");
        String nomTeclat = io.readword();

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
            controladorCapaDomini.crearTeclatFrequencies(idAlgorisme, frequencies, nomTeclat);
        }
        catch (ExcepcioIdNoValid e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        catch (ExcepcioNomTeclatJaExisteix e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        catch (ExcepcioErrorDurantLaCreacio e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        catch (ExcepcioFrequencies e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        io.writeln(String.format("S'ha creat el teclat amb nom %s", nomTeclat));
    }

    //FUNCIO GENERICA PER PASSAR DE UN PATH A UNA STRING AMB EL SEU CONTINGUT
    private static String readFile(String filePath) {
        String content = "";
        try{
            Path path = Path.of(filePath);
            byte[] bytes = Files.readAllBytes(path);
            content = new String(bytes, StandardCharsets.UTF_8);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return content;
    }

    private void TestCrearTeclatFitxerTextFrequencies() throws Exception {
        TestGetAlgoritmes();
        io.writeln("Introdueixi el id del algorisme que vol usar per crear el teclat:");
        int idAlgorisme = io.readint();
        io.writeln("Introdueixi el nom que vol donar al teclat:");
        String nomTeclat = io.readword();
        io.writeln("El path ha de ser relatiu desde el executable del driver (Si es desde IntelliJ, el path ha de començar desde src/...)");
        io.writeln("Escriu el path del fitxer de input:");
        String path = io.readword();
        String text = readFile(path);

        try{
            controladorCapaDomini.crearTeclatFrequencies(idAlgorisme, text, nomTeclat);
        }
        catch (ExcepcioIdNoValid e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        catch (ExcepcioNomTeclatJaExisteix e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        catch (ExcepcioErrorDurantLaCreacio e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        catch (ExcepcioFrequencies e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        io.writeln(String.format("S'ha creat el teclat amb nom %s", nomTeclat));

    }

    private void TestCrearTeclatFitxerTextUsuari() throws Exception{
        //fa la mateixa funcionalitat que si el fitxer fos els parells de frequencies
        //passa el contingut del fitxer del path donat a un string
        //nomes cambia la funcio cridada a la capa de domini
        TestGetAlgoritmes();
        io.writeln("Introdueixi el id del algorisme que vol usar per crear el teclat:");
        int idAlgorisme = io.readint();
        io.writeln("Introdueixi el nom que vol donar al teclat:");
        String nomTeclat = io.readword();
        io.writeln("El path ha de ser relatiu desde el executable del driver (Si es desde IntelliJ, el path ha de començar desde src/...)");
        io.writeln("Escriu el path del fitxer de input:");
        String path = io.readword();
        String text = readFile(path);

        try{
            controladorCapaDomini.crearTeclatFitxerTextUsuari(idAlgorisme, text, nomTeclat);
        }
        catch (ExcepcioIdNoValid e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        catch (ExcepcioNomTeclatJaExisteix e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        catch (ExcepcioErrorDurantLaCreacio e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        catch (ExcepcioFrequencies e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        io.writeln(String.format("S'ha creat el teclat amb nom %s", nomTeclat));
    }

    private void TestCrearTeclatFitxerTextPublic() throws Exception{
        if(TestGetTextsPublics()){
            io.writeln("Introdueixi el id del text public que vol usar per crear el teclat:");
            int idTextPublic = io.readint();
            TestGetAlgoritmes();
            io.writeln("Introdueixi el id del algorisme que vol usar per crear el teclat:");
            int idAlgorisme = io.readint();
            io.writeln("Introdueixi el nom que vol donar al teclat:");
            String nomTeclat = io.readword();

            try{
                controladorCapaDomini.crearTeclatFitxerTextPublic(idAlgorisme, idTextPublic, nomTeclat);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioErrorDurantLaCreacio e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioFrequencies e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioNomTeclatJaExisteix e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("S'ha creat el teclat amb nom %s", nomTeclat));
        }
    }

    private void TestCrearTeclatFitxerTextPredefinit() throws Exception{
        if(TestGetTextsPredefinits()){
            io.writeln("Introdueixi el id del text predefinit que vol usar per crear el teclat:");
            int idTextPredefinit = io.readint();
            TestGetAlgoritmes();
            io.writeln("Introdueixi el id del algorisme que vol usar per crear el teclat:");
            int idAlgorisme = io.readint();
            io.writeln("Introdueixi el nom que vol donar al teclat:");
            String nomTeclat = io.readword();


            try{
                controladorCapaDomini.crearTeclatFitxerTextPredefinit(idAlgorisme, idTextPredefinit, nomTeclat);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioErrorDurantLaCreacio e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioFrequencies e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioNomTeclatJaExisteix e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("S'ha creat el teclat amb nom %s", nomTeclat));
        }

    }

    private void TestCanviarNomTeclat() throws Exception{
        if(TestGetAllTeclats()){
            io.writeln("Introdueixi el id del teclat al que vol canviar el nom:");
            int idTeclat = io.readint();
            io.writeln("Introdueixi el nou nom que li vol donar al teclat");
            String nouNomTeclat = io.readword();
            try{
                controladorCapaDomini.modificarTeclatNom(idTeclat, nouNomTeclat);
            }
            catch (ExcepcioNomTeclatJaExisteix e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioErrorDurantModificacio e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("S'ha canviat el nom del teclat amb id %d a %s", idTeclat, nouNomTeclat));
        }
    }

    private void TestCanviarAlgorismeTeclat() throws Exception{
        if(TestGetAllTeclats()){
            io.writeln("Introdueixi el id del teclat al que vol canviar l'algorisme de creacio:");
            int idTeclat = io.readint();
            TestGetAlgoritmes();
            io.writeln("Introdueixi el id del algorisme que vol usar:");
            int idAlgorismeNou= io.readint();
            try{
                controladorCapaDomini.modificarTeclatAlgorisme(idTeclat, idAlgorismeNou);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioErrorDurantModificacio e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("S'ha canviat l'algorisme del teclat amb id %d", idTeclat));
        }
    }

    private void TestCanviarInputManual() throws Exception{
        if(TestGetAllTeclats()){
            io.writeln("Introdueixi el id del teclat al que vol canviar l'input:");
            int idTeclat = io.readint();
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
                controladorCapaDomini.modificarTeclatFrequencies(idTeclat, frequencies);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioFrequencies e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioErrorDurantModificacio e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("S'ha modificat l'input del teclat amb id %d", idTeclat));
        }
    }

    private void TestCanviarInputFitxerFreq() throws Exception{
        if(TestGetAllTeclats()){
            io.writeln("Introdueixi el id del teclat al que vol canviar l'input:");
            int idTeclat = io.readint();
            io.writeln("El path ha de ser relatiu desde el executable del driver (Si es desde IntelliJ, el path ha de començar desde src/...)");
            io.writeln("Escriu el path del fitxer de input:");
            String path = io.readword();
            String text = readFile(path);
            try{
                controladorCapaDomini.modificarTeclatFrequencies(idTeclat, text);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioFrequencies e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioErrorDurantModificacio e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("S'ha modificat l'input del teclat amb id %d", idTeclat));
        }
    }

    private void TestCanviarInputFitxerTextUsuari() throws Exception{
        if(TestGetAllTeclats()){
            io.writeln("Introdueixi el id del teclat al que vol canviar l'input:");
            int idTeclat = io.readint();
            io.writeln("El path ha de ser relatiu desde el executable del driver (Si es desde IntelliJ, el path ha de començar desde src/...)");
            io.writeln("Escriu el path del fitxer de input:");
            String path = io.readword();
            String text = readFile(path);
            try{
                controladorCapaDomini.modificarTeclatFrequenciesFitxerTextUsuari(idTeclat, text);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioFrequencies e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioErrorDurantModificacio e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("S'ha modificat l'input del teclat amb id %d", idTeclat));
        }
    }

    private void TestCanviarInputFitxerTextPublic() throws Exception{
        if(TestGetAllTeclats()){
            io.writeln("Introdueixi el id del teclat al que vol canviar l'input:");
            int idTeclat = io.readint();
            TestGetTextsPublics();
            io.writeln("Introdueixi el id del text publi cque vol usar com a input:");
            int idTextPublic = io.readint();
            try{
                controladorCapaDomini.modificarTeclatFrequenciesFitxerTextPublic(idTeclat, idTextPublic);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioFrequencies e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioErrorDurantModificacio e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("S'ha modificat l'input del teclat amb id %d", idTeclat));
        }
    }

    private void TestCanviarInputFitxerTextPredefinit() throws Exception{
        if(TestGetAllTeclats()){
            io.writeln("Introdueixi el id del teclat al que vol canviar l'input:");
            int idTeclat = io.readint();
            TestGetTextsPredefinits();
            io.writeln("Introdueixi el id del text predefinit que vol usar com a input:");
            int idTextPredefinit = io.readint();
            try{
                controladorCapaDomini.modificarTeclatFrequenciesFitxerTextPredefinit(idTeclat, idTextPredefinit);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioFrequencies e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioErrorDurantModificacio e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("S'ha modificat l'input del teclat amb id %d", idTeclat));
        }
    }

    private boolean TestGetTextosPublicsUsuari() throws Exception{
        HashMap<Integer, String> textosPublicsUsuari = controladorCapaDomini.getTextsPublicsUsuari();
        if(textosPublicsUsuari.isEmpty()) io.writeln("L'usuari no te cap text public penjat");
        else{
            io.writeln("A continuacio es mostren tots els textos publics del usuari, identificats per un identificador, seguit del nom del text");
            for(Map.Entry<Integer, String> entry : textosPublicsUsuari.entrySet()) {
                io.writeln(String.format("\nId: %s Nom: %s", entry.getKey().toString(), entry.getValue()));
            }
        }
       return !textosPublicsUsuari.isEmpty();
    }

    private void TestAfegirNouTextPublic() throws Exception{
        io.writeln("Introdueix el nom que vols donarli al nou text public:");
        String nomtext = io.readword();
        io.writeln("El path ha de ser relatiu desde el executable del driver (Si es desde IntelliJ, el path ha de començar desde src/...)");
        io.writeln("Escriu el path del fitxer de input:");
        String path = io.readword();
        String text = readFile(path);
        try{
            controladorCapaDomini.afegirTextPublic(nomtext, text);
        }
        catch(ExcepcioErrorDurantLaCreacio e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        catch (ExcepcioNomTextPublicJaExisteix e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        io.writeln(String.format("S'ha creat el nou text public amb nom %s", nomtext));
    }

    private void TestEliminarTextPublic() throws Exception{
        if(TestGetTextosPublicsUsuari()){
            io.writeln("Introdueixi el id del text public que vol eliminar:");
            int idTextPublic = io.readint();
            try {
                controladorCapaDomini.eliminarTextPublic(idTextPublic);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioErrorDurantEsborrat e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("s'ha eliminat correctament el text public amb id %d", idTextPublic));
        }
    }

    private void TestCanviarNomTextPublic() throws Exception{
        if(TestGetTextosPublicsUsuari()){
            io.writeln("Introdueixi el id del text public al que vol canviarli el nom:");
            int idTextPublic = io.readint();
            io.writeln("Introdueixi el nou nom que vol posarli al text public:");
            String nouNomText = io.readword();
            try{
                controladorCapaDomini.modificarTextPublicNom(idTextPublic, nouNomText);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioErrorDurantModificacio e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioNomTextPublicJaExisteix e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("S'ha canviat el nom del text public amb id %d", idTextPublic));
        }
    }

    private void TestGetTextPublic() throws Exception{
        if(TestGetTextosPublicsUsuari()){
            io.writeln("Introdueixi el id del text public al que vol visualitzar:");
            int idTextPublic = io.readint();
            String textPublic = "";
            try{
                textPublic = controladorCapaDomini.getTextPublic(idTextPublic);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(textPublic);
        }
    }

    private void TestAfegirNouTextPredefinit() throws Exception{
        io.writeln("Introdueix el nom que vols donarli al nou text predefinit:");
        String nomtext = io.readword();
        io.writeln("El path ha de ser relatiu desde el executable del driver (Si es desde IntelliJ, el path ha de començar desde src/...)");
        io.writeln("Escriu el path del fitxer de input:");
        String path = io.readword();
        String text = readFile(path);
        try{
            controladorCapaDomini.afegirTextPredefinit(nomtext, text);
        }
        catch (ExcepcioErrorDurantLaCreacio e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        catch (ExcepcioNomTextPredefinitJaExisteix e1){
            io.writeln(String.format("\nHa ocorregut un error %s", e1));
            return;
        }
        io.writeln(String.format("S'ha creat el nou text predefinit amb nom %s", nomtext));
    }

    private void TestEliminarTextPredefinit() throws Exception{
        if(TestGetTextsPredefinits()){
            io.writeln("Introdueixi el id del text predefinit que vol eliminar:");
            int idTextPredefinit = io.readint();
            try {
                controladorCapaDomini.eliminarTextPredefinit(idTextPredefinit);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioErrorDurantEsborrat e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("s'ha eliminat correctament el text predefinit amb id %d", idTextPredefinit));
        }
    }

    private void TestCanviarNomTextPredefinit() throws Exception{
        if(TestGetTextsPredefinits()){
            io.writeln("Introdueixi el id del text predefinit al que vol canviarli el nom:");
            int idTextPredefinit = io.readint();
            io.writeln("Introdueixi el nou nom que vol posarli al text predefinit:");
            String nouNomText = io.readword();
            try{
                controladorCapaDomini.modificarTextPredefinitNom(idTextPredefinit, nouNomText);
            }
            catch (ExcepcioIdNoValid e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioErrorDurantModificacio e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            catch (ExcepcioNomTextPredefinitJaExisteix e1){
                io.writeln(String.format("\nHa ocorregut un error %s", e1));
                return;
            }
            io.writeln(String.format("S'ha canviat el nom del text predefinit amb id %d", idTextPredefinit));
        }
    }

    private void TestGetTextPredefinit() throws Exception {
        if(TestGetTextsPredefinits()){
            io.writeln("Introdueixi el id del text predefinit que vol visualitzar:");
            int idTextPredefinit = io.readint();
            String textPredefinit = "";
            try {
                textPredefinit = controladorCapaDomini.getTextPredefinit(idTextPredefinit);
            } catch (ExcepcioIdNoValid e1) {
                io.writeln(String.format("\nHa ocorregut un error: %s", e1));
                return;
            }
            io.writeln(textPredefinit);
        }
    }
}

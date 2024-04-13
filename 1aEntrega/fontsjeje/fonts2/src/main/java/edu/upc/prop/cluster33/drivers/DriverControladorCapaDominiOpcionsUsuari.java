package edu.upc.prop.cluster33.drivers;

import edu.upc.prop.cluster33.domini.ControladorCapaDomini;
import edu.upc.prop.cluster33.excepcions.*;
import edu.upc.prop.cluster33.utils.inout;


import java.util.Scanner;


public class DriverControladorCapaDominiOpcionsUsuari {

    private ControladorCapaDomini controladorCapaDomini;
    private inout io;
    private boolean login;

    private void TestCreadoraControladorCapaDomini() throws ExcepcioPasswordNoPassaFiltre, ExcepcioUsernameJaExistent, ExcepcioErrorDurantLaCreacio, ExcepcioFormatMaIncorrecte {
        controladorCapaDomini = new ControladorCapaDomini();
        controladorCapaDomini.registre("admin", "admin", "Dreta", true);
        controladorCapaDomini.logout();
        io = new inout();
        login = false;

        System.out.println("S'ha creat una nova instancia de ControladorCapaDomini");
    }

    private void TestLogin() throws Exception{

        //logout per evitar dos usuaris
        controladorCapaDomini.logout();

        io.writeln("Introdueixi el username:");
        String username = io.readword();
        io.writeln("Introdueixi el password:");
        String password = io.readword();
        try{
            controladorCapaDomini.login(username, password);
        }
        catch(ExcepcioUsernameNoExisteix e1){
            io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
            return;
        }
        catch(ExcepcioPasswordIncorrecte e1){
            io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
            return;
        }

        io.writeln("S'ha fet login correctament!!");
        login = true;
    }

    private void TestRegistre() throws Exception{

        //logout per evitar dos usuaris
        controladorCapaDomini.logout();

        io.writeln("Introdueixi el username:");
        String username = io.readword();
        io.writeln("Introdueixi el password:");
        String password = io.readword();
        io.writeln("Introdueixi la seva ma bona. Format Esquerra/Dreta");
        String maBona = io.readword();
        try{
            controladorCapaDomini.registre(username, password, maBona, false);
        }
        catch(ExcepcioUsernameJaExistent e1){
            io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
            return;
        }
        catch(ExcepcioPasswordNoPassaFiltre e1){
            io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
            return;
        }
        catch(ExcepcioFormatMaIncorrecte e1){
            io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
            return;
        }
        catch (ExcepcioErrorDurantLaCreacio e1){
            io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
            return;
        }
        io.writeln("S'ha registrat correctament l'usuari");
        login = true;
    }


    private void TestCambiarContrasenya() throws Exception{
        if(login){
            io.writeln("Introdueix el teu password actual:");
            String passActual = io.readword();
            io.writeln("Introdueix el teu nou password:");
            String passNou = io.readword();
            try{
                controladorCapaDomini.canviarPassword(passActual, passNou);
            }
            catch(ExcepcioPasswordIncorrecte e1){
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }
            catch(ExcepcioPasswordNoPassaFiltre e1){
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }
            catch(ExcepcioErrorDurantModificacio e1){
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }
            io.writeln("S'ha canviat el password correctament");
        }
        else{
            io.writeln("Per fer aquesta funcionalitat, primer ha de realitzar un login o un registre");
        }

    }

    private void TestCambiarMaBona() throws Exception{
        if(login){
            try {
                controladorCapaDomini.canviarMaBona();
            }
            catch (ExcepcioNoUsuariLogin e1) {
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }
            io.writeln("S'ha canviat la ma bona del usuari");
        }
        else{
            io.writeln("Per fer aquesta funcionalitat, primer ha de realitzar un login o un registre");
        }
    }

    private void TestLogout() throws Exception{
        if(login){
            controladorCapaDomini.logout();
            io.writeln("S'ha fet logout correctament");
            login = false;
        }
        else{
            io.writeln("No es pot fer logout si no s'ha fet login abans");
        }

    }

    private void TestEliminarPropiUsuari() throws Exception{
        if(login){
            io.writeln("Introdueix el teu username");
            String username = io.readword();
            io.writeln("Introdueix el teu password:");
            String password = io.readword();
            try{
                controladorCapaDomini.eliminarPropiUsuari(username, password);
            }
            catch(ExcepcioUsernamePasswordIncorrectes e1){
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }
            catch(ExcepcioIntentEsborrarAdmin e1){
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }
            catch(ExcepcioErrorDurantEsborrat e1){
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }
            io.writeln("S'ha esborrat l'usuari correctament");
        }
        else{
            io.writeln("Per fer aquesta funcionalitat, primer ha de realitzar un login o un registre");
        }

    }

    private void TestGetMaBona() throws Exception {
        if(login){
            String maBona;
            try {
                maBona = controladorCapaDomini.getMaBona();
            }
            catch (ExcepcioNoUsuariLogin e1) {
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }

            io.writeln(String.format("La teva ma bona es %s", maBona));
        }
        else{
            io.writeln("Per fer aquesta funcionalitat, primer ha de realitzar un login o un registre");
        }

    }

    private void TestIsAdmin() throws Exception {
        if(login){
            boolean isAdmin;
            try {
                isAdmin = controladorCapaDomini.isAdmin();
            }
            catch (ExcepcioNoUsuariLogin e1) {
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }
            if(isAdmin) io.writeln("L'usuari es admin");
            else io.writeln("L'usuari no es admin");
        }
        else {
            io.writeln("Per fer aquesta funcionalitat, primer ha de realitzar un login o un registre");
        }

    }


    //tests nomes pels admins

    private void TestGetUsuaris() throws Exception{
        if(login){
            String[] usuaris;
            try{
                usuaris = controladorCapaDomini.getUsuaris();
            }
            catch(ExcepcioUsuariNoEsAdmin e1){
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }
            io.writeln("A continuacio es mostren tots els usuaris registrats al sistema, identificats per un id seguit del seu username");
            for (String s : usuaris) {
                io.writeln(String.format("\nUsername: %s", s));
            }
        }
        else{
            io.writeln("Per fer aquesta funcionalitat, primer ha de realitzar un login o un registre en mode administrador");
        }

    }

    private void TestEliminaUsuari() throws Exception{
        if(login){
            String[] usuaris;
            try{
                usuaris = controladorCapaDomini.getUsuaris();
            }
            catch(ExcepcioUsuariNoEsAdmin e1){
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }
            io.writeln("A continuacio es mostren tots els usernames dels usuaris registrats al sistema");
            for (String s : usuaris) {
                io.writeln(String.format("\nUsername: %s", s));
            }
            io.writeln("Introdueixi el username del usuari que vol borrar:");
            String username = io.readword();
            try{
                controladorCapaDomini.eliminarUsuari(username);
            }
            catch (ExcepcioUsernameNoExisteix e1){
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }
            io.writeln(String.format("s'ha eliminat l'usuari identificat amb id %s", username));
        }
        else{
            io.writeln("Per fer aquesta funcionalitat, primer ha de realitzar un login o un registre en mode administrador");
        }

    }

    private void TestSetAdmin() throws Exception{
        if(login){
            String[] usuaris;
            try{
                usuaris = controladorCapaDomini.getUsuaris();
            }
            catch(ExcepcioUsuariNoEsAdmin e1){
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }
            io.writeln("A continuacio es mostren tots els usernames dels usuaris registrats al sistema");
            for (String s : usuaris) {
                io.writeln(String.format("\nUsername: %s", s));
            }
            io.writeln("Introdueixi el username del usuari al que vol donar privilegis d'administrador:");
            String username = io.readword();
            try{
                controladorCapaDomini.setAdmin(username);
            }
            catch (ExcepcioUsernameNoExisteix e1){
                io.writeln(String.format("Ha ocorregut un error: %s", e1.getMessage()));
                return;
            }

            io.writeln(String.format("S'ha donat privilegis d'administrador al usuari amb username %s", username));
        }
        else{
            io.writeln("Per fer aquesta funcionalitat, primer ha de realitzar un login o un registre en mode administrador");
        }

    }

    private static void printIns() {
        System.out.println("Selecciona una de les funcionalitats:");
        System.out.println("0. Tornar a escriure les instruccions");
        System.out.println("1. Test Login");
        System.out.println("2. Test Registrar Usuari");
        System.out.println("3. Test Canviar Password");
        System.out.println("4. Test Canviar Ma Bona");
        System.out.println("5. Test Logout");
        System.out.println("6. Test Elimina Usuari");
        System.out.println("7. Test Obte Ma Bona");
        System.out.println("8. Test Es Admin");
        System.out.println("99. Finalitzar driver");

        System.out.println("\nLes seguents funcionalitats nomes es poden executar si el usuari que ha fet login es administrador del sistema");
        System.out.println("9. Test Get Tots Els Usuaris");
        System.out.println("10. Test Donar Permisos Admin");
        System.out.println("11. Test Eliminar usuari Del Sistema");

        System.out.println("SI NO ES POSA UN NUMERO, EL DRIVER PETA");
    }

    public static void main(String[] args) throws Exception {
        DriverControladorCapaDominiOpcionsUsuari dcd = new DriverControladorCapaDominiOpcionsUsuari();
        System.out.println("Estas provant el driver de les funcionalitats relacionades amb els Usuaris de la classe ControladorCapaDomini\n");
        System.out.println("Abans de provar la classe anem a crear un nou ControladorCapaDomini");
        dcd.TestCreadoraControladorCapaDomini();

        System.out.println("Al ser funcionalitats relacionades amb un usuari, per poder realitzar correctament la resta de funcionalitats, primer fes un login o registra un nou usuari");
        System.out.println("Si fa login amb username 'admin' i password 'admin', fara login com a administrador del sistema");
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
                    dcd.TestLogin();
                    break;
                case 2:
                    dcd.TestRegistre();
                    break;
                case 3:
                    dcd.TestCambiarContrasenya();
                    break;
                case 4:
                    dcd.TestCambiarMaBona();
                    break;
                case 5:
                    dcd.TestLogout();
                    break;
                case 6:
                    dcd.TestEliminarPropiUsuari();
                    break;
                case 7:
                    dcd.TestGetMaBona();
                    break;
                case 8:
                    dcd.TestIsAdmin();
                    break;
                case 9:
                    dcd.TestGetUsuaris();
                    break;
                case 10:
                    dcd.TestSetAdmin();
                    break;
                case 11:
                    dcd.TestEliminaUsuari();
                    break;
                case 99:
                    System.out.println("Finaitzant driver");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Funció no trobada");
            }
            System.out.println("=============================================================");
        }
    }
}

package edu.upc.prop.cluster33;

import edu.upc.prop.cluster33.drivers.DriverControladorCapaDomini;
import edu.upc.prop.cluster33.drivers.DriverControladorCapaDominiOpcionsUsuari;
import com.google.gson.Gson;
import edu.upc.prop.cluster33.utils.inout;

public class Main {
  public static void main(String[] args) throws Exception {
    System.out.println("BENVINGUT AL MAIN");
    new Gson();
    System.out.println("Aquest main es pensat per executar-lo amb el gradlew run");
    System.out.println("Proporciona les diferents opcions per executar el programa");
    System.out.println("El ViewTerminal i els Drivers");
    System.out.println("1- ViewTerminal");
    System.out.println("2- ContorladorDominiUsuari");
    System.out.println("3- ControladorDomini");

    inout io = new inout();
    int func = 0;
    String[] a = new String[0];
    while (func != -1) {
      func = io.readint();
      switch (func) {
        case 1:
          break;
        case 2:
          DriverControladorCapaDominiOpcionsUsuari d = new DriverControladorCapaDominiOpcionsUsuari();
          d.main(a);
          break;
        case 3:
          DriverControladorCapaDomini.main(a);
          break;
      }
    }
  }
}
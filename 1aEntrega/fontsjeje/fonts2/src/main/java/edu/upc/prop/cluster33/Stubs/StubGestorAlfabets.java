package edu.upc.prop.cluster33.Stubs;

import edu.upc.prop.cluster33.domini.Alfabet;

import java.util.HashMap;

public class StubGestorAlfabets {
    private Alfabet[] alfabets;

    public StubGestorAlfabets() {
        //Creem els alfabets:
        alfabets = new Alfabet[5];
        alfabets[0] = new Alfabet("Llati", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        alfabets[1] = new Alfabet("Grec", "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ");
        alfabets[2] = new Alfabet("Cirilic", "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ");
        alfabets[3] = new Alfabet("Armeni", "ԱԲԳԴԵԶԷԸԹԺԻԼԽԾԿՀՁՂՃՄՅՆՇՈՉՊՋՌՍՎՏՐՑՒՓՔՕՖ");
        alfabets[4] = new Alfabet("Georgia", "აბგდევზთიკლმნოპჟრსტჳუფქღყშჩცძწჭხჴჯჰ");
    }

    public Alfabet[] getAlfabets() {return alfabets;}
}

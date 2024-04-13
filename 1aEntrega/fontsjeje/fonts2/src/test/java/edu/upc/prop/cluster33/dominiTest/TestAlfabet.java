package edu.upc.prop.cluster33.dominiTest;

import org.junit.Test;
import edu.upc.prop.cluster33.domini.*;

import static org.junit.Assert.assertEquals;

public class TestAlfabet {
    @Test
    public void TestGetAlfabet() {
        Alfabet prova = new Alfabet("a", "abc");
        assertEquals("abc", prova.getAlfabet());
    }

    @Test
    public void TestGetNom() {
        Alfabet prova = new Alfabet("a", "abc");
        assertEquals("a", prova.getNom());
    }

    @Test
    public void TestGetMida() {
        Alfabet prova = new Alfabet("a", "abc");
        assertEquals(3, prova.getMida());
    }

    @Test
    public void TestGetIndex() {
        Alfabet prova = new Alfabet("a", "abc");
        assertEquals(0, prova.getIndex('a'));
        assertEquals(2, prova.getIndex('c'));
    }
}

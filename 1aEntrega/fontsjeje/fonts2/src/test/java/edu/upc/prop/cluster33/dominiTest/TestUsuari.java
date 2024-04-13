package edu.upc.prop.cluster33.dominiTest;

import edu.upc.prop.cluster33.domini.*;
import edu.upc.prop.cluster33.utils.TMa;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.w3c.dom.Text;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(value= MockitoJUnitRunner.class)
public class TestUsuari {

    @Mock
    Teclat mockTeclat1;
    @Mock
    Teclat mockTeclat2;
    @Mock
    Teclat mockTeclat3;
    @Mock
    Teclat mockTeclat4;
    @Mock
    TextPublic mockText1;
    @Mock
    TextPublic mockText2;
    @Mock
    TextPublic mockText3;
    @Mock
    TextPublic mockText4;

    @Test
    public void TestGetUsarname() {
        Usuari prova = new Usuari("a","b", TMa.DRETA, false);
        assertEquals("a", prova.getUsername());
    }

    @Test
    public void TestGetPassword() {
        Usuari prova = new Usuari("a","b", TMa.DRETA, false);
        assertEquals("b", prova.getPassword());
    }

    @Test
    public void TestGetMaBona() {
        Usuari prova = new Usuari("a","b", TMa.DRETA, false);
        assertEquals(TMa.DRETA, prova.getMaBona());
    }

    @Test
    public void TestIsAdmin() {
        Usuari prova = new Usuari("a","b", TMa.DRETA, false);
        assertFalse(prova.isAdmin());
    }

    @Test
    public void TestSetUsername() {
        Usuari prova = new Usuari("a","b", TMa.DRETA, false);
        assertEquals("a", prova.getUsername());
        prova.setUsername("b");
        assertEquals("b", prova.getUsername());
        prova.setUsername("");
        assertEquals("", prova.getUsername());
    }

    @Test
    public void TestSetPassword() {
        Usuari prova = new Usuari("a","b", TMa.DRETA, false);
        assertEquals("b", prova.getPassword());
        prova.setPassword("a");
        assertEquals("a", prova.getPassword());
        prova.setPassword("");
        assertEquals("", prova.getPassword());
        prova.setPassword("qwertyuiopñlkjnhvfdsw45678o");
        assertEquals("qwertyuiopñlkjnhvfdsw45678o", prova.getPassword());
    }

    @Test
    public void TestSetMaBona() {
        Usuari prova = new Usuari("a","b", TMa.DRETA, false);
        assertEquals(TMa.DRETA, prova.getMaBona());
        prova.setMaBona(TMa.ESQUERRA);
        assertEquals(TMa.ESQUERRA, prova.getMaBona());
        prova.setMaBona(TMa.ESQUERRA);
        assertEquals(TMa.ESQUERRA, prova.getMaBona());
        prova.setMaBona(TMa.DRETA);
        assertEquals(TMa.DRETA, prova.getMaBona());
    }

    @Test
    public void TestSetAdmin() {
        Usuari prova = new Usuari("a","b", TMa.DRETA, false);
        assertFalse(prova.isAdmin());
        prova.setAdmin(false);
        assertFalse(prova.isAdmin());
        prova.setAdmin(true);
        assertTrue(prova.isAdmin());
    }

    @Test
    public void TestInteraccioTeclat() {
        when(mockTeclat1.getNom()).thenReturn("Nom1");
        when(mockTeclat2.getNom()).thenReturn("Nom2");
        when(mockTeclat3.getNom()).thenReturn("Nom3");
        when(mockTeclat4.getNom()).thenReturn("Nom4");

        Usuari prova = new Usuari("a","b", TMa.DRETA, false);
        prova.addTeclat(1, mockTeclat1);
        assertEquals("Nom1", prova.getTeclats().get(1).getNom());
        prova.addTeclat(3, mockTeclat3);
        prova.addTeclat(2, mockTeclat2);
        prova.addTeclat(4, mockTeclat4);
        assertEquals("Nom1", prova.getTeclats().get(1).getNom());
        assertEquals("Nom4", prova.getTeclats().get(4).getNom());
        assertEquals("Nom2", prova.getTeclats().get(2).getNom());
        assertEquals("Nom3", prova.getTeclats().get(3).getNom());
    }

    @Test
    public void TestGetTeclats() {
        when(mockTeclat1.getNom()).thenReturn("Nom1");
        when(mockTeclat2.getNom()).thenReturn("Nom2");
        when(mockTeclat3.getNom()).thenReturn("Nom3");
        when(mockTeclat4.getNom()).thenReturn("Nom4");

        Usuari prova = new Usuari("a", "b", TMa.DRETA, false);
        prova.addTeclat(1, mockTeclat1);
        prova.addTeclat(3, mockTeclat3);
        prova.addTeclat(2, mockTeclat2);
        prova.addTeclat(4, mockTeclat4);

        HashMap<Integer, Teclat> map = prova.getTeclats();

        assertEquals("Nom1", map.get(1).getNom());
        assertEquals("Nom2", map.get(2).getNom());
        assertEquals("Nom3", map.get(3).getNom());
        assertEquals("Nom4", map.get(4).getNom());
    }

    @Test
    public void TestIsTeclat() {
        Usuari prova = new Usuari("a", "b", TMa.DRETA, false);
        prova.addTeclat(1, mockTeclat1);
        prova.addTeclat(3, mockTeclat3);
        prova.addTeclat(2, mockTeclat2);
        prova.addTeclat(4, mockTeclat4);

        assertTrue(prova.isTeclat(1));
        assertTrue(prova.isTeclat(2));
        assertTrue(prova.isTeclat(3));
        assertTrue(prova.isTeclat(4));
        assertFalse(prova.isTeclat(5));
    }

    @Test
    public void TestEliminarTeclat() {
        Usuari prova = new Usuari("a", "b", TMa.DRETA, false);
        prova.addTeclat(1, mockTeclat1);
        prova.addTeclat(3, mockTeclat3);
        prova.addTeclat(2, mockTeclat2);
        prova.addTeclat(4, mockTeclat4);

        prova.eliminarTeclat(3);

        HashMap<Integer, Teclat> map = prova.getTeclats();
        assertEquals(3, map.size());

        assertTrue(prova.isTeclat(1));
        assertTrue(prova.isTeclat(2));
        assertFalse(prova.isTeclat(3));
        assertTrue(prova.isTeclat(4));
    }
}

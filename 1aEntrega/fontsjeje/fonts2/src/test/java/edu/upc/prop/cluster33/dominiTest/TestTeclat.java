package edu.upc.prop.cluster33.dominiTest;

import org.junit.runner.RunWith;
import edu.upc.prop.cluster33.domini.*;
import org.mockito.Mock;
import org.junit.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

@RunWith(value= MockitoJUnitRunner.class)
public class TestTeclat {

    @Mock
    Algorisme mockAlgorisme;
    @Mock
    Frequencies mockFrequencies;

    char[][] a = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'}
    };

    @Test
    public void TestGetNom() {
        Teclat prova = new Teclat("a", a, mockAlgorisme, mockFrequencies);
        assertEquals("a", prova.getNom());
    }

    @Test
    public void TestSetNom() {
        Teclat prova = new Teclat("a", a, mockAlgorisme, mockFrequencies);
        prova.setNom("b");
        assertEquals("b", prova.getNom());
    }

    @Test
    public void TestInteraccioFrequencies() {
        when(mockFrequencies.getNomAlfabet()).thenReturn("Nom1");
        Teclat prova = new Teclat("a", a, mockAlgorisme, mockFrequencies);
        prova.addFrequencia(mockFrequencies);
        assertEquals("Nom1", prova.getFrequencia().getNomAlfabet());
    }

    @Test
    public void TestInteraccioAlgorisme() {
        when(mockAlgorisme.getNom()).thenReturn("Nom1");

        Teclat prova = new Teclat("a", a, mockAlgorisme, mockFrequencies);
        prova.setAlgorisme(mockAlgorisme);
        assertEquals(mockAlgorisme, prova.getAlgorisme());
        assertEquals("Nom1", prova.getAlgorisme().getNom());
    }

    @Test
    public void TestGetInfo() {
        when(mockAlgorisme.getNom()).thenReturn("Nom1");
        when(mockFrequencies.getNomAlfabet()).thenReturn("Alfabet");

        char[][] a = {
                {'a', 'b', 'c'},
                {'d', 'e', 'f'}
        };

        Teclat prova = new Teclat("a", a, mockAlgorisme, mockFrequencies);

        assertEquals("a", prova.getInfo().get(0));
        assertEquals("Nom1", prova.getInfo().get(1));
        assertEquals("Alfabet", prova.getInfo().get(3));
        assertEquals("abc", prova.getInfo().get(4));
        assertEquals("def", prova.getInfo().get(5));
    }

    @Test
    public void TestSetLayout() {
        char[][] b = {
                {'a', 'b', 'c'},
                {'d', 'e', 'z'}
        };

        Teclat prova = new Teclat("a", a, mockAlgorisme, mockFrequencies);
        //prova.setLayout(a);
        assertEquals("abc", prova.getInfo().get(4));
        assertEquals("def", prova.getInfo().get(5));
        prova.setLayout(b);
        assertEquals("dez", prova.getInfo().get(5));
    }
}
package edu.upc.prop.cluster33.dominiTest;

import edu.upc.prop.cluster33.domini.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(value= MockitoJUnitRunner.class)
public class TestText {

    @Test
    public void TestGetNom() {
        Text prova = new Text("a", "b");
        assertEquals("a", prova.getNom());
    }

    @Test
    public void TestLlegirContigut() {
        Text prova = new Text("b");
        assertEquals("b", prova.llegirContingut());
    }

    @Test
    public void TestSetNom() {
        Text prova = new Text("a", "b");
        prova.setNom("c");
        assertEquals("c", prova.getNom());
        prova.setNom("");
        assertEquals("", prova.getNom());
    }

    @Test
    public void TestSetContingut() {
        Text prova = new Text("b");
        prova.setContingut("c");
        assertEquals("c", prova.llegirContingut());
        prova.setContingut("");
        assertEquals("", prova.llegirContingut());
        prova.setContingut("gsydfuhijo duyfhijaoks guhijasfk. duhfijopk" +
                "dhfsjiopkgwfhujieok . dgyfhujiwef. ywefhuji678." +
                "dfgwhuejiokf");
        assertEquals("gsydfuhijo duyfhijaoks guhijasfk. duhfijopk" +
                "dhfsjiopkgwfhujieok . dgyfhujiwef. ywefhuji678." +
                "dfgwhuejiokf", prova.llegirContingut());
    }
}

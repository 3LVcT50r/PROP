package edu.upc.prop.cluster33.dominiTest;

import org.junit.Test;
import edu.upc.prop.cluster33.domini.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(value= MockitoJUnitRunner.class)
public class TestTextPublic {

    @Test
    public void TestInteraccioUsuariUsername() {
        TextPublic prova = new TextPublic("a");
        assertNull(prova.getUsuariUsername());
        prova.setUsername("a");
        assertEquals("a", prova.getUsuariUsername());
    }
}

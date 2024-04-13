package edu.upc.prop.cluster33.dominiTest;

import com.sun.source.tree.Tree;
import edu.upc.prop.cluster33.domini.Alfabet;
import edu.upc.prop.cluster33.domini.Frequencies;
import edu.upc.prop.cluster33.excepcions.ExcepcioFrequencies;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.TreeMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestFrequencies {
    @Mock
    Alfabet mockLlati;
    @Mock
    Alfabet mockGrec;
    @Mock
    Alfabet mockCirilic;
    @Mock
    Alfabet mockArmeni;
    @Mock
    Alfabet mockGeorgia;

    @Before
    public void inicializacion() {
        MockitoAnnotations.initMocks(this);
    }

    // FUNCIÓ GENERA:
    @Test
    public void testGeneraLlati() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockLlati};
        when(mockLlati.getAlfabet()).thenReturn("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        when(mockLlati.getNom()).thenReturn("Llati");
        freq.genera("En la mañana, María se levanta temprano" +
                " y sueña con un día lleno de alegría.", llistaAlfabets);
        TreeMap<String, Integer> expected = new TreeMap<>();
        expected.put("EN", 1);
        expected.put("LA", 1);
        expected.put("MARIA", 1);
        expected.put("SE", 1);
        expected.put("LEVANTA", 1);
        expected.put("TEMPRANO", 1);
        expected.put("Y", 1);
        expected.put("SUENA", 1);
        expected.put("CON", 1);
        expected.put("UN", 1);
        expected.put("DIA", 1);
        expected.put("LLENO", 1);
        expected.put("DE", 1);
        expected.put("ALEGRIA", 1);
        expected.put("MANANA", 1);
        assertEquals(expected, freq.getLlistaFrequencies());
        assertEquals(expected.size(), freq.getNumero_paraules());
        assertEquals("Llati", freq.getNomAlfabet());
    }

    @Test
    public void testGeneraGrec() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockGrec};
        when(mockGrec.getAlfabet()).thenReturn("ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ");
        when(mockGrec.getNom()).thenReturn("Grec");
        freq.genera("Καλημέρα! Είναι μια όμορφη μέρα.\n" +
                "Το ηλιοβασίλεμα στην Ελλάδα είναι εκπληκτικό.", llistaAlfabets);
        TreeMap<String, Integer> expected = new TreeMap<>();
        expected.put("ΚΑΛΗΜΕΡΑ", 1);
        expected.put("ΕΙΝΑΙ", 2);
        expected.put("ΜΙΑ", 1);
        expected.put("ΟΜΟΡΦΗ", 1);
        expected.put("ΜΕΡΑ", 1);
        expected.put("ΤΟ", 1);
        expected.put("ΗΛΙΟΒΑΣΙΛΕΜΑ", 1);
        expected.put("ΣΤΗΝ", 1);
        expected.put("ΕΛΛΑΔΑ", 1);
        expected.put("ΕΚΠΛΗΚΤΙΚΟ", 1);
        assertEquals(expected, freq.getLlistaFrequencies());
        assertEquals(expected.size(), freq.getNumero_paraules());
        assertEquals("Grec", freq.getNomAlfabet());
    }

    @Test
    public void testGeneraCirilic() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockCirilic};
        when(mockCirilic.getAlfabet()).thenReturn("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ");
        when(mockCirilic.getNom()).thenReturn("Cirilic");
        freq.genera("Привет! Как дела?\n" +
                "Мне нравится изучать новые языки.", llistaAlfabets);
        TreeMap<String, Integer> expected = new TreeMap<>();
        expected.put("ПРИВЕТ", 1);
        expected.put("КАК", 1);
        expected.put("ДЕЛА", 1);
        expected.put("МНЕ", 1);
        expected.put("НРАВИТСЯ", 1);
        expected.put("ИЗУЧАТЬ", 1);
        expected.put("НОВЫЕ", 1);
        expected.put("ЯЗЫКИ", 1);
        assertEquals(expected, freq.getLlistaFrequencies());
        assertEquals(expected.size(), freq.getNumero_paraules());
        assertEquals("Cirilic", freq.getNomAlfabet());
    }

    @Test
    public void testGeneraArmeni() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockArmeni};
        when(mockArmeni.getAlfabet()).thenReturn("ԱԲԳԴԵԶԷԸԹԺԻԼԽԾԿՀՁՂՃՄՅՆՇՈՉՊՋՌՍՎՏՐՑՒՓՔՕՖ");
        when(mockArmeni.getNom()).thenReturn("Armeni");
        freq.genera("Բարեւ! Իսկություն ունեմ։\n" +
                "Մենք կարող ենք հայերեն խոսել։", llistaAlfabets);
        TreeMap<String, Integer> expected = new TreeMap<>();
        expected.put("ԲԱՐԵՒ", 1);
        expected.put("ԻՍԿՈՒԹՅՈՒՆ", 1);
        expected.put("ՄԵՆՔ", 1);
        expected.put("ԿԱՐՈՂ", 1);
        expected.put("ՈՒՆԵՄ", 1);

        expected.put("ԵՆՔ", 1);
        expected.put("ՀԱՅԵՐԵՆ", 1);
        expected.put("ԽՈՍԵԼ", 1);
        assertEquals(expected, freq.getLlistaFrequencies());
        assertEquals(expected.size(), freq.getNumero_paraules());
        assertEquals("Armeni", freq.getNomAlfabet());
    }

    @Test
    public void testGeneraGeorgia() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockGeorgia};
        when(mockGeorgia.getAlfabet()).thenReturn("აბგდევზთიკლმნოპჟრსტჳუფქღყშჩცძწჭხჴჯჰ");
        when(mockGeorgia.getNom()).thenReturn("Georgia");
        freq.genera("გამარჯობა! როგორ ხარ?\n" +
                "მიყვარხარ ქართული ენის შესწორება.", llistaAlfabets);
        TreeMap<String, Integer> expected = new TreeMap<>();
        expected.put("გამარჯობა", 1);
        expected.put("როგორ", 1);
        expected.put("ხარ", 1);
        expected.put("მიყვარხარ", 1);
        expected.put("ქართული", 1);
        expected.put("ენის", 1);
        expected.put("შესწორება", 1);
        assertEquals(expected, freq.getLlistaFrequencies());
        assertEquals(expected.size(), freq.getNumero_paraules());
        assertEquals("Georgia", freq.getNomAlfabet());
    }

    //FUNCIÓ LLEGIR:
    @Test
    public void testLlegirLlati() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockLlati};
        when(mockLlati.getAlfabet()).thenReturn("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        when(mockLlati.getNom()).thenReturn("Llati");
        freq.llegir("Kiitos-12\n" +
                "Terve-45\n" +
                "Hyvä-66\n" +
                "Kissa-7\n" +
                "Järvi-21;", llistaAlfabets);
        TreeMap<String, Integer> expected = new TreeMap<>();
        expected.put("KIITOS", 12);
        expected.put("TERVE", 45);
        expected.put("HYVA", 66);
        expected.put("KISSA", 7);
        expected.put("JARVI", 21);
        assertEquals(expected, freq.getLlistaFrequencies());
        assertEquals(expected.size(), freq.getNumero_paraules());
        assertEquals("Llati", freq.getNomAlfabet());
    }

    @Test
    public void testLlegirGrec() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockGrec};
        when(mockGrec.getAlfabet()).thenReturn("ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ");
        when(mockGrec.getNom()).thenReturn("Grec");
        freq.llegir("Καλημέρα-14\n" +
                "Ευχαριστώ-43\n" +
                "Αγάπη-22\n" +
                "Θάλασσα-11\n" +
                "Σχολείο-3;", llistaAlfabets);
        TreeMap<String, Integer> expected = new TreeMap<>();
        expected.put("ΚΑΛΗΜΕΡΑ", 14);
        expected.put("ΕΥΧΑΡΙΣΤΩ", 43);
        expected.put("ΑΓΑΠΗ", 22);
        expected.put("ΘΑΛΑΣΣΑ", 11);
        expected.put("ΣΧΟΛΕΙΟ", 3);
        assertEquals(expected, freq.getLlistaFrequencies());
        assertEquals(expected.size(), freq.getNumero_paraules());
        assertEquals("Grec", freq.getNomAlfabet());
    }

    @Test
    public void testLlegirCirilic() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockCirilic};
        when(mockCirilic.getAlfabet()).thenReturn("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ");
        when(mockCirilic.getNom()).thenReturn("Cirilic");
        freq.llegir("Здравейте-3\n" +
                "Ето-4\n" +
                "едно-5\n" +
                "изречение-6\n" +
                "български-7;", llistaAlfabets);
        TreeMap<String, Integer> expected = new TreeMap<>();
        expected.put("ЗДРАВЕЙТЕ", 3);
        expected.put("ЕТО", 4);
        expected.put("ЕДНО", 5);
        expected.put("ИЗРЕЧЕНИЕ", 6);
        expected.put("БЪЛГАРСКИ", 7);
        assertEquals(expected, freq.getLlistaFrequencies());
        assertEquals(expected.size(), freq.getNumero_paraules());
        assertEquals("Cirilic", freq.getNomAlfabet());
    }

    @Test
    public void testLlegirArmeni() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockArmeni};
        when(mockArmeni.getAlfabet()).thenReturn("ԱԲԳԴԵԶԷԸԹԺԻԼԽԾԿՀՁՂՃՄՅՆՇՈՉՊՋՌՍՎՏՐՑՒՓՔՕՖ");
        when(mockArmeni.getNom()).thenReturn("Armeni");
        freq.llegir("Շնորհակալություն-1\n" +
                "Բարեւ-2\n" +
                "Լավ-3\n" +
                "Շատ-4\n" +
                "Աղ-5;", llistaAlfabets);
        TreeMap<String, Integer> expected = new TreeMap<>();
        expected.put("ՇՆՈՐՀԱԿԱԼՈՒԹՅՈՒՆ", 1);
        expected.put("ԲԱՐԵՒ", 2);
        expected.put("ԼԱՎ", 3);
        expected.put("ՇԱՏ", 4);
        expected.put("ԱՂ", 5);
        assertEquals(expected, freq.getLlistaFrequencies());
        assertEquals(expected.size(), freq.getNumero_paraules());
        assertEquals("Armeni", freq.getNomAlfabet());
    }

    @Test
    public void testLlegirGeorgia() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockGeorgia};
        when(mockGeorgia.getAlfabet()).thenReturn("აბგდევზთიკლმნოპჟრსტჳუფქღყშჩცძწჭხჴჯჰ");
        when(mockGeorgia.getNom()).thenReturn("Georgia");
        freq.llegir("მადლობა-9\n" +
                "გამარჯობა-8\n" +
                "კარგი-7\n" +
                "კაცი-6\n" +
                "წყალი-5;", llistaAlfabets);
        TreeMap<String, Integer> expected = new TreeMap<>();
        expected.put("მადლობა", 9);
        expected.put("გამარჯობა", 8);
        expected.put("კარგი", 7);
        expected.put("კაცი", 6);
        expected.put("წყალი", 5);
        assertEquals(expected, freq.getLlistaFrequencies());
        assertEquals(expected.size(), freq.getNumero_paraules());
        assertEquals("Georgia", freq.getNomAlfabet());
    }

    //TEST CASOS D'EXCEPCIÓ:
    //Les excepcions relatives al text buit, més d'un alfabet alhora i alfabet no trobat les testejarem només a la
    //funció genera, ja que la forma de comprovar-les és comú a les dues funcions genera i llegeix.

    @Test(expected = ExcepcioFrequencies.class)
    public void testExcepcioTextBuit() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockLlati};
        when(mockLlati.getAlfabet()).thenReturn("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        when(mockLlati.getNom()).thenReturn("Llati");
        freq.genera("", llistaAlfabets);
    }

    @Test(expected = ExcepcioFrequencies.class)
    public void testExcepcioMesDunAlfabet() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockLlati};
        when(mockLlati.getAlfabet()).thenReturn("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        when(mockLlati.getNom()).thenReturn("Llati");
        freq.genera("I like картофель!", llistaAlfabets);
    }

    @Test(expected = ExcepcioFrequencies.class)
    public void testExcepcioAlfabetNoTrobat() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockLlati};
        when(mockLlati.getAlfabet()).thenReturn("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        when(mockLlati.getNom()).thenReturn("Llati");
        freq.genera("მადლობა", llistaAlfabets);
    }
    //Ara, provarem el format incorrecte de la llista de freqüencies:
    @Test(expected = ExcepcioFrequencies.class)
    public void testExcepcioFormatIncorrecte() throws ExcepcioFrequencies {
        Frequencies freq = new Frequencies();
        Alfabet[] llistaAlfabets = {mockLlati};
        when(mockLlati.getAlfabet()).thenReturn("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        when(mockLlati.getNom()).thenReturn("Llati");
        freq.llegir("Lets-23\n" +
                "Nascar/12;", llistaAlfabets);
    }
}

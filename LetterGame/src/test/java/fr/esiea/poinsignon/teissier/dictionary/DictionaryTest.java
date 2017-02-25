package fr.esiea.poinsignon.teissier.dictionary;

import org.junit.Before;
import org.junit.Test;
import fr.esiea.poinsignon.teissier.dictionary.FrenchDictionary;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test sample for Dictionary.
 */
public class DictionaryTest {
    
    protected FrenchDictionary dico;

    @Before
    public void setup() {
        //TODO
		dico = new FrenchDictionary();
    }

    @Test
    public void removeAccentsTest() {

    	assertFalse(dico.exists("Blabli"));
    	assertTrue(dico.exists("avion"));
    	assertTrue(dico.exists("Voiture"));
    	assertTrue(dico.exists("Vôiture"));
    	assertTrue(dico.exists("AbBé"));
    	assertTrue(dico.exists("abBe"));
    	assertTrue(dico.exists("âbbé"));
    	assertTrue(dico.exists("abbe"));
    
    }

    @Test
	public void isAFrenchDictionary() {
    	assertTrue(dico.lang.matches("french"));
		String testLongWord = dico.getLongestWordFor("ambul");
		assertTrue(dico.exists(testLongWord));
	}
}

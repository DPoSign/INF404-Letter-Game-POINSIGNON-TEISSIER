package fr.esiea.unique.binome.name.dictionary;

import org.junit.Before;
<<<<<<< HEAD
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
=======

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
>>>>>>> ea886a0041089b49bff47fad00a340bf50b0e9b1

/**
 * Unit test sample for Dictionary.
 */
public class DictionaryTest {

    private IDictionary dictionary;

    @Before
    public void setup() {
        //TODO
    }

<<<<<<< HEAD
    @Test
=======
>>>>>>> ea886a0041089b49bff47fad00a340bf50b0e9b1
    public void testIsWord() {
        assertTrue(dictionary.isWord("maman"));
        assertFalse(dictionary.isWord("namam"));
    }
}

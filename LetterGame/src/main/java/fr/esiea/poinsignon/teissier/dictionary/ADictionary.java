package fr.esiea.poinsignon.teissier.dictionary;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashSet;
import java.util.Set;

import fr.esiea.poinsignon.teissier.util.Util;


/**
 * Abstract dictionary class
 * 
 * @author Daniel Poinsignon
 */
public abstract class ADictionary {
	protected String lang;
	protected Set<String> dico;
	
	
	/**
	 * Create a new dictionary based on a text file
	 * 
	 * @param language The language (or type) of the dictionary
	 */
	protected ADictionary(String language) {
		lang = language;
		dico = new HashSet<String>();
		String name = "/dico." + lang + ".txt";
		InputStream in = Util.openResource(name);
		fillDictionary(in);
	}
	
	/**
	 * Check if a given word exists
	 * 
	 * @param word The word to be tested
	 * 
	 * @return True if the word is found, false otherwise
	 */
	public boolean exists(String word) {
		if (word == null || word.isEmpty())
			return false;
		
		return dico.contains(removeAccents(word).toLowerCase());
	}

	
	/**
	 * Fill the dictionary with the given input stream
	 * 
	 * @param in The input stream to read from
	 */
	private void fillDictionary(InputStream in) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while ((line = reader.readLine()) != null)
				dico.add(line);
		}
		catch (Exception e) {
			System.out.println("Error while reading the '" + lang + "' dictionary: " + e.getMessage());
			System.exit(0);
		}
	}
	
	/**
	 * Remove accents from a given string
	 * 
	 * @param word The string to remove the accents from
	 * 
	 * @return Return the normalized string
	 * 
	 * @see https://www.drillio.com/en/2011/java-remove-accent-diacritic/
	 */
	protected String removeAccents(String word) {
		if (word == null || word.isEmpty())
			return "";
		
		return Normalizer.normalize(word, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
}

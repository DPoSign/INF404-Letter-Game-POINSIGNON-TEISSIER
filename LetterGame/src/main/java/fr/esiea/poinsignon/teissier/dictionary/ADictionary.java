package fr.esiea.poinsignon.teissier.dictionary;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

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
	 * Find the longest word in the dictionary with the given letters
	 * 
	 * @param word
	 * 
	 * @return
	 */
	public String getLongestWordFor(String word) {
		String wordFixed = removeAccents(word).toLowerCase();

		// Get all substrings, ordered by decreasing length
		for (String subword : Util.getAllSubstrings(wordFixed, 2)) {
			int len = subword.length();
			//System.out.println("Testing subword: " + subword);
			for (String test : dico) {
				if (test.length() == len)
				{
					//System.out.println("same length: " + test + " // " + Util.hasSameLetters(subword, test));
				}
				if (test.length() == len && Util.hasSameLetters(subword, test))
				{
					//System.out.println("Subwords: " + wordFixed + " // " + Util.getAllSubstrings(wordFixed, 2));
					return test;
				}
			}
		}
		
		//System.out.println("Subwords: " + wordFixed + " // " + Util.getAllSubstrings(wordFixed, 2));
		
		return "";
	}
	
	/**
	 * Find all the words that can be an extension of the given word
	 * 
	 * @param word
	 * 
	 * @return
	 */
	public Vector<String> getPossibleExtensionsFor(String word) {
		Vector<String> res = new Vector<String>();
		
		// Don't check dictionary in this worst-case scenario
		if (word == null || word.length() < 2)
			return res;
		
		String wordFixed = removeAccents(word).toLowerCase();
		int len = wordFixed.length();
		for (String test : dico)
			if (test.length() > len && Util.hasSameLetters(wordFixed, test))
				res.add(test);
		
		return res;
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
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null)
				dico.add(removeAccents(line).toLowerCase());
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
		/*String original = "aáeéiíoóöőuúüű AÁEÉIÍOÓÖŐUÚÜŰ";
		for (int i = 0; i < original.length(); i++) {
		    // we will report on each separate character, to show you how this works
		    String text = original.substring(i, i + 1);
		    // normalizing
		    String decomposed = Normalizer.normalize(text, Form.NFD);
		    // removing diacritics
		    String removed = decomposed.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	   
		}*/
		
		if (word == null || word.isEmpty())
			return "";
		
		return Normalizer.normalize(word, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
}

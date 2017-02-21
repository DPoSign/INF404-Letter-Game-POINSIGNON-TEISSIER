package fr.esiea.poinsignon.teissier.util;

import java.io.InputStream;
import java.util.Vector;

/**
 * Utils
 * 
 * @author Daniel Poinsignon
 */
public abstract class Util {
	/**
	 * Check if "available" has all the letters needed by the word
	 * 
	 * @param word
	 * @param available
	 * 
	 * @return True if all needed letters are present
	 */
	public static boolean hasSameLetters(String word, Vector<String> available) {
		Vector<String> copy = new Vector<String>(available);
		int idx;

		for (char c : word.toCharArray()) {
			if ((idx = copy.indexOf("" + c)) == -1)
				return false;
			
			copy.remove(idx);
		}
		
		return true;
	}
	
	/**
	 * Subtract characters from an origin string and return the remaining characters from origin
	 * 
	 * @param origin
	 * @param subtract
	 * 
	 * @return
	 */
	public static String remainingChars(String origin, String subtract) {
		String res = new String(origin);
		for (char c : subtract.toCharArray())
			res = res.replaceFirst("" + c, "");
		
		return res;
	}
	
	/**
	 * Add the characters in the given word in the given vector of characters
	 * 
	 * @param list
	 * @param word
	 * 
	 * @return
	 */
	public static Vector<String> addCharsToVector(Vector<String> list, String word) {
		Vector<String> res = new Vector<String>(list);
		for (char c : word.toCharArray())
			res.add("" + c);
		
		return res;
	}
	
	/**
	 * Open a resource and return it as an input stream
	 * 
	 * @param name The resource to open
	 * 
	 * @return The input stream
	 * 
	 * @throws Exception If resource cannot be read
	 */
	public static InputStream openResource(String name) {
		InputStream in = null;
		
		try {
			in = Util.class.getResourceAsStream(name);
			if (in == null)
				throw new Exception();
		}
		catch (Exception e) {
			throw new RuntimeException("Could not find the resource: '" + name + "'");
		}
		
		return in;
	}
}


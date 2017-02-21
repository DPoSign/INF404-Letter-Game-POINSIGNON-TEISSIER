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


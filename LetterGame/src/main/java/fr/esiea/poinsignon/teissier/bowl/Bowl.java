package fr.esiea.poinsignon.teissier.bowl;

import java.util.Vector;

import fr.esiea.poinsignon.teissier.bag.Bag;


/**
 * 
 * @author Daniel Poinsignon
 */
public class Bowl {
	protected static Bowl instance;
	
	protected Vector<String> letters = new Vector<String>();
	
	
	/**
	 * Singleton
	 */
	protected Bowl() {
		
	}
	
	
	
	
	/**
	 * Add the given letters to the bowl (mainly used for testing purposes)
	 * 
	 * @param word
	 */
	public void addLetters(String word) {
		for (char c : word.toUpperCase().toCharArray())
			letters.add("" + c);
	}
	
	/**
	 * Remove from the bowl the letters contained in this word
	 * 
	 * @param word
	 */
	public void useLetters(String word) {
		int idx;
		for (char c : word.toUpperCase().toCharArray()) {
			if ((idx = letters.indexOf("" + c)) != -1)
				letters.remove(idx);
		}
	}
	
	/**
	 * Pick letters
	 * @param bag
	 * @param amount
	 */
	public void pickLetters(Bag bag, int amount) {
		for (int i = 0; i < amount; i++)
			letters.add(bag.pickLetter());
	}
	
	/**
	 * Pick a single letter and return it
	 * 
	 * @param bag
	 * @return
	 */
	public String pickLetter(Bag bag) {
		String letter = bag.pickLetter();
		letters.add(letter);
		
		return letter;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public final Vector<String> getLetters() {
		return letters;
	}
	
	/**
	 * 
	 * @return
	 */
	public final String getLettersImploded() {
		String ret = "";
		for (String letter : letters)
			ret += (ret.isEmpty() ? "" : ", ") + letter;
		
		return ret;
	}
	
	
	/**
	 * Singleton design pattern
	 * 
	 * @return
	 */
	public static final Bowl getInstance() {
		if (instance == null)
			instance = new Bowl();
		
		return instance;
	}
}

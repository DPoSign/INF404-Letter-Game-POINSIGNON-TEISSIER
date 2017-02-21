package fr.esiea.poinsignon.teissier.move;

import fr.esiea.poinsignon.teissier.bowl.Bowl;
import fr.esiea.poinsignon.teissier.game.AGame;
import fr.esiea.poinsignon.teissier.util.Util;


/**
 * Move that allows a player to only use letters from the bowl
 * 
 * @author Daniel Poinsignon
 */
public class FromBowl extends AMove {
	/**
	 * The player wants to play the given option of this move
	 * 
	 * @param game
	 * @param idx
	 */
	public void playOption(AGame game, int idx) {
		if (options.size() < (idx + 1) || idx < 0)
			throw new RuntimeException("Should never happen");
		
		game.getBowl().useLetters((String)options.elementAt(idx));
	}
	
	/**
	 * Try to perform the move, and keep possible solutions at hand
	 * 
	 * @param game
	 * @param word
	 * 
	 * @return True if the move is possible
	 */
	public boolean attempt(AGame game, String word) {
		Bowl bowl = game.getBowl();
		
		if (Util.hasSameLetters(word, bowl.getLetters())) {
			options.add(word);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Show on the console the different options that are available with this move
	 */
	public void showAvailableOptions(int idx) {
		if (options.isEmpty())
			return;
		
		showOptionIndex(idx);
		System.out.println("Use only letters from bowl");
	}
	
	/**
	 * Show on the console the given option that is available with this move
	 * 
	 * @param idxGlobal
	 * @param idxOpt
	 */
	public void showAvailableOption(int idxGlobal, int idxOpt) {
		if (options.isEmpty())
			return;
		
		showOptionIndex(idxGlobal);
		System.out.println("Use only letters from bowl");
	}
}

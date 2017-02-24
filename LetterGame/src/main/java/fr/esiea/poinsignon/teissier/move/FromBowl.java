package fr.esiea.poinsignon.teissier.move;

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
	 * @param word
	 * @param idx
	 */
	public void playOption(AGame game, String word, int idx) {
		assertOption(idx);
		
		game.getBowl().useLetters((String)options.elementAt(idx));
		System.out.println("Using only letters from the bowl");
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
		if (Util.hasSameLetters(word, game.getBowl().getLetters()))
			options.add(word);
		
		return !options.isEmpty();
	}
	
	/**
	 * Show on the console the given option that is available with this move
	 * 
	 * @param idxGlobal
	 * @param idxOpt
	 */
	public void showAvailableOption(int idxGlobal, int idxOpt) {
		assertOption(idxOpt);
		
		showOptionIndex(idxGlobal);
		System.out.println("Use only letters from bowl");
	}
}

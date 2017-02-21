package fr.esiea.poinsignon.teissier.move;

import fr.esiea.poinsignon.teissier.bowl.Bowl;
import fr.esiea.poinsignon.teissier.game.AGame;
import fr.esiea.poinsignon.teissier.player.APlayer;


/**
 * Move that allows a player to create a new word by completing one of his own words
 * 
 * @author Daniel Poinsignon
 */
public class CompleteOwnWord extends AMove {
	/**
	 * The player wants to play the given option of this move
	 * 
	 * @param game
	 * @param idx
	 */
	public void playOption(AGame game, int idx) {
		
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
		
		for (APlayer opponent : game.getOpponents())
			for (String oppWord : opponent.getWords())
				System.out.println(oppWord + " + " + bowl.getLetters() + " = " + (oppWord + bowl.getLetters()));
		
		return false;
	}
	
	/**
	 * Show on the console the different options that are available with this move
	 */
	public void showAvailableOptions(int idx) {
		if (options.isEmpty())
			return;
		
		showOptionIndex(idx);
		System.out.println("Use own word");
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
		System.out.println("Use steal " + idxOpt);
	}
}

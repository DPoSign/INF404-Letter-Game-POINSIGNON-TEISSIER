package fr.esiea.poinsignon.teissier.move;

import java.util.Vector;

import fr.esiea.poinsignon.teissier.game.AGame;
import fr.esiea.poinsignon.teissier.player.APlayer;
import fr.esiea.poinsignon.teissier.util.Util;


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
	 * @param word
	 * @param idx
	 */
	public void playOption(AGame game, String word, int idx) {
		assertOption(idx);
		
		String ownWord = (String)options.elementAt(idx);
		String remaining = Util.remainingChars(word, ownWord);
		game.getBowl().useLetters(remaining);
		System.out.println("Extending own word '" + ownWord + "' and using letters '" + remaining + "' from the bowl");
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
		Vector<String> bowlLetters = game.getBowl().getLetters();
		
		APlayer self = game.getCurrentPlayer();
		for (String ownWord : self.getWords())
			if (word.length() > ownWord.length() && Util.hasSameLetters(ownWord, word) && Util.hasSameLetters(word, Util.addCharsToVector(bowlLetters, ownWord)))
				options.add(ownWord);
		
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
		System.out.println("Extend own word '" + (String)options.elementAt(idxOpt) + "'");
	}
}

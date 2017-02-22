package fr.esiea.poinsignon.teissier.move;

import java.util.Vector;

import fr.esiea.poinsignon.teissier.game.AGame;
import fr.esiea.poinsignon.teissier.player.APlayer;
import fr.esiea.poinsignon.teissier.util.Pair;
import fr.esiea.poinsignon.teissier.util.Util;


/**
 * Move that allows a player to steal a word from an opponent
 * 
 * @author Cannelle Teissier
 */
public class StealFromOpponent extends AMove {
	/**
	 * The player wants to play the given option of this move
	 * 
	 * @param game
	 * @param word
	 * @param idx
	 */
	public void playOption(AGame game, String word, int idx) {
		assertOption(idx);
		
		try {
			@SuppressWarnings("unchecked")
			Pair<APlayer, String> pair = (Pair<APlayer, String>)options.elementAt(idx);
			
			String remaining = Util.remainingChars(word, pair.getSecond());
			game.getBowl().useLetters(remaining);
			pair.getFirst().removeWord(pair.getSecond());
			System.out.println("Stealing word '" + pair.getSecond() + "' from player '" + pair.getFirst().getName() + "' and using letters '" + remaining + "' from the bowl");
		}
		catch (ClassCastException e) {}
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
		
		for (APlayer opponent : game.getOpponents())
			for (String oppWord : opponent.getWords())
				if (word.length() > oppWord.length() && Util.hasSameLetters(oppWord, word) && Util.hasSameLetters(word, Util.addCharsToVector(bowlLetters, oppWord)))
					options.add(new Pair<APlayer, String>(opponent, oppWord));
		
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
		
		try {
			@SuppressWarnings("unchecked")
			Pair<APlayer, String> pair = (Pair<APlayer, String>)options.elementAt(idxOpt);
			showOptionIndex(idxGlobal);
			System.out.println("Steal word '" + pair.getSecond() + "' from player '" + pair.getFirst().getName() + "'");
		}
		catch (ClassCastException e) {}
	}
}

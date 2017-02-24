package fr.esiea.poinsignon.teissier.player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import fr.esiea.poinsignon.teissier.game.AGame;
import fr.esiea.poinsignon.teissier.move.AMove;
import fr.esiea.poinsignon.teissier.move.FromBowl;
import fr.esiea.poinsignon.teissier.move.StealFromOpponent;
import fr.esiea.poinsignon.teissier.util.Pair;
import fr.esiea.poinsignon.teissier.util.Util;

/**
 * IA player (bot)
 * 
 * @author Daniel Poinsignon
 */
public class Bot extends APlayer {
	protected static int counter = 0;
	
	
	public Bot(String name, String letter) {
		super(name, letter);
		counter++;
		this.name = "BOT_" + counter;
	}
	
	
	
	
	/**
	 * Check whether this player type needs the console to view things
	 * 
	 * @return
	 */
	public boolean needsConsole() {
		return false;
	}
	
	/**
	 * Choose a move option to execute
	 * 
	 * @param moves
	 */
	public int chooseMoveOption(HashMap<Integer, Pair<AMove, Integer>> options) {
		// First StealFromOpponent...
		for (Map.Entry<Integer, Pair<AMove, Integer>> entry : options.entrySet())
			if (entry.getValue().getFirst() instanceof StealFromOpponent)
				return entry.getKey().intValue();

		// ... then FromBowl
		for (Map.Entry<Integer, Pair<AMove, Integer>> entry : options.entrySet())
			if (entry.getValue().getFirst() instanceof FromBowl)
				return entry.getKey().intValue();
		
		// ... otherwise return random action
		return new Random().nextInt(options.size());
	}
	

	/**
	 * Play our turn
	 * 
	 * @param game
	 * 
	 * @return The word we try to put
	 */
	public String play(AGame game) {
		String res;
		
		// First we try to steal words from an opponent
		res = findWordFromOpponents(game);
		if (res != null && !res.isEmpty())
			return res;
		
		// Next we try to pick most of the letters from the bowl
		res = game.getBowl().findLongestWord(game.getDictionary());
		if (res != null && !res.isEmpty())
			return res;
		
		// Finally we try to extend one of our words
		return findWordFromSelf(game);
	}
	
	
	/**
	 * Try to find a word that allows us to steal a word from an opponent
	 * 
	 * @param game
	 * 
	 * @return
	 */
	protected final String findWordFromOpponents(AGame game) {
		int maxScore = -1;
		String res = "";
		
		for (APlayer opponent : game.getOpponents()) {
			String word = getLongestPossibleExtensionFromPlayer(game, opponent);
			// Steal a word from the opponent that has the biggest score
			if (!word.isEmpty() && opponent.getScore() > maxScore) {
				maxScore = opponent.getScore();
				res = word;
			}
		}
		
		return res;
	}
	
	/**
	 * Get the longest word that we can achieve using one of our words
	 * 
	 * @param game
	 * 
	 * @return
	 */
	protected final String findWordFromSelf(AGame game) {
		return getLongestPossibleExtensionFromPlayer(game, this);
	}
	
	/**
	 * Get the longest extension from any of the words of the given player
	 * 
	 * @param game
	 * @param player
	 * 
	 * @return
	 */
	protected final String getLongestPossibleExtensionFromPlayer(AGame game, APlayer player) {
		Vector<String> bowlLetters = game.getBowl().getLetters();
		String res = "";
		
		for (String word : player.getWords())
			for (String possibleWord : game.getDictionary().getPossibleExtensionsFor(word))
				if (possibleWord.length() > res.length() && Util.hasSameLetters(Util.remainingChars(possibleWord, word), bowlLetters))
					res = possibleWord;
		
		return res;
	}
}

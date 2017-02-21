package fr.esiea.poinsignon.teissier.game;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import fr.esiea.poinsignon.teissier.bag.Bag;
import fr.esiea.poinsignon.teissier.bowl.Bowl;
import fr.esiea.poinsignon.teissier.dictionary.FrenchDictionary;
import fr.esiea.poinsignon.teissier.move.AMove;
import fr.esiea.poinsignon.teissier.player.APlayer;
import fr.esiea.poinsignon.teissier.player.PlayOrderComparator;
import fr.esiea.poinsignon.teissier.player.ScoreComparator;
import fr.esiea.poinsignon.teissier.util.Pair;



/**
 * Abstract game class
 * 
 * @author Daniel Poinsignon
 */
public abstract class AGame {
	protected static AGame instance = null;
	protected static final int WIN_SCORE = 2;
	
	protected FrenchDictionary dico = new FrenchDictionary();
	protected Bag bag = Bag.getInstance();
	protected Bowl bowl = Bowl.getInstance();
	protected Vector<APlayer> players = new Vector<APlayer>();
	protected int nbPlayers = 0;
	protected int curPlayerIdx = -1;
	protected APlayer curPlayer;
	protected final String[] moves = {"FromBowl", "StealFromOpponent", "CompleteOwnWord"};
	
	
	
	/**
	 * 
	 */
	public final void init() {
		// Debug
		System.out.println("Blabli exists ? " + dico.exists("Blabli"));
		System.out.println("voiture exists ? " + dico.exists("voiture"));
		System.out.println("Voiture exists ? " + dico.exists("Voiture"));
		System.out.println("Vôiture exists ? " + dico.exists("Vôiture"));
		
		waitForPlayers();
		checkNbPlayers();
		determinePlayerOrder();
		
		// Debug
		for (APlayer player : players)
			System.out.println("Player " + player.getName());
		
		play();
		showScores();
	}
	
	
	
	
	/**
	 * Check that there are enough players
	 */
	protected final void checkNbPlayers() {
		nbPlayers = players.size();
		if (nbPlayers < 2) {
			System.out.println("There must be at least 2 players for this game");
			System.exit(0);
		}
	}
	
	/**
	 * Play the game
	 */
	protected final void play() {
		String word;
		
		while (true) {
			getNextPlayer();
			bowl.pickLetters(bag, 2);
			showBoardStatus(true);
			word = curPlayer.play().trim().toUpperCase();
			if (assertWordExists(word)) {
				tryToPlaceWord(word);
				if (curPlayer.getScore() >= WIN_SCORE)
					break;
			}
		}
	}
	
	
	
	
	/**
	 * Get the bowl
	 * 
	 * @return
	 */
	public final Bowl getBowl() {
		return bowl;
	}
	
	/**
	 * Show scores when game is over
	 */
	protected final void showScores() {
		System.out.println("");
		System.out.println("Player " + curPlayer.getName() + " has won!");
		System.out.println("");
		System.out.println("Scores:");
		Collections.sort(players, new ScoreComparator());
		for (int i = 0, len = players.size(); i < len; i++)
			System.out.println((i + 1) + ". " + players.elementAt(i).getName() + " : " + players.elementAt(i).getScore());
	}
	
	/**
	 * Show the words players have played, and the letters in the bowl
	 * 
	 * @param showCurrentPlayer
	 */
	protected final void showBoardStatus(boolean showCurrentPlayer) {
		System.out.println("");
		if (showCurrentPlayer)
			System.out.println("= " + curPlayer.getName() + "'s turn!\n========================================");
		System.out.println("Words already in game:");
		for (APlayer player : players)
			System.out.println("   * " + player.getName() + ": " + player.getWordsImploded());
		
		System.out.println("Letters in the bowl:");
		System.out.println(bowl.getLettersImploded());
	}
	
	/**
	 * Check if a word can be placed
	 * 
	 * @param word
	 * @return
	 */
	protected final void tryToPlaceWord(String word) {
		HashMap<Integer, Pair<AMove, Integer>> options = getPossibleMoveOptions(word);
		if (options.isEmpty()) {
			System.out.println("Cannot place the word '" + word + "'");
			return;
		}
		
		System.out.println("**** OPTIONS");
		showAllOptions(options, true);
		
		showAllOptions(options, false);
		if (options.size() == 1)
			playOption(options.get(1));
		else
			playOption(options.get(curPlayer.chooseMoveOption(options)));
		
		curPlayer.addWord(word);
		bowl.pickLetter(bag);
	}
	
	
	
	/**
	 * Show all options on the console (only if current player needs the console, and if there is more than 1 option)
	 * 
	 * @param options
	 * @param force
	 */
	protected final void showAllOptions(HashMap<Integer, Pair<AMove, Integer>> options, boolean force) {
		if (!force && (options.size() < 2 || !curPlayer.needsConsole()))
			return;
		
		for (Map.Entry<Integer, Pair<AMove, Integer>> entry : options.entrySet())
			entry.getValue().getFirst().showAvailableOption(entry.getKey(), entry.getValue().getSecond());
	}
	
	/**
	 * Play the given option
	 * 
	 * @param option
	 */
	protected final void playOption(Pair<AMove, Integer> option) {
		option.getFirst().playOption(this, option.getSecond().intValue());
	}
	
	
	/**
	 * Get the possible move techniques and their options
	 * 
	 * @param word
	 * 
	 * @return
	 */
	protected final HashMap<Integer, Pair<AMove, Integer>> getPossibleMoveOptions(String word) {
		HashMap<Integer, Pair<AMove, Integer>> options = new HashMap<Integer, Pair<AMove, Integer>>();
		int cur = 1;
		for (String method : moves) {
			AMove move = AMove.factory(method);
			if (move.attempt(this, word)) {
				for (int i = 0, len = move.getNbOptions(); i < len; i++) {
					options.put(cur, new Pair<AMove, Integer>(move, i));
					cur++;
				}
			}
		}
		
		return options;
	}
	
	
	/**
	 * Make sure a word exists in our dictionary
	 * 
	 * @param word
	 * 
	 * @return
	 */
	protected final boolean assertWordExists(String word) {
		if (word == null || word.isEmpty()) {
			System.out.println("Skipping turn");
			return false;
		}
		
		if (!dico.exists(word)) {
			System.out.println("This word does not seem to exist");
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Get the next player to play
	 * 
	 * @return
	 */
	protected final APlayer getNextPlayer() {
		curPlayerIdx = (curPlayerIdx + 1) % nbPlayers;
		curPlayer = players.elementAt(curPlayerIdx);
		
		return curPlayer;
	}
	
	/**
	 * Get the current player
	 * 
	 * @return
	 */
	public final APlayer getCurrentPlayer() {
		return curPlayer;
	}
	
	/**
	 * Get the opponents of the current player
	 * 
	 * @return
	 */
	public final Vector<APlayer> getOpponents() {
		Vector<APlayer> opponents = new Vector<APlayer>(players);
		opponents.remove(curPlayer);
		
		return opponents;
	}
	
	
	/**
	 * Wait for all players to be connected
	 */
	protected abstract void waitForPlayers();
	
	
	/**
	 * Sort players according to the letter they picked at the beginning of the game
	 */
	private final void determinePlayerOrder() {
		Collections.sort(players, new PlayOrderComparator());
	}
	
	
	/**
	 * Check whether the name can be used for a new player
	 * 
	 * @param name
	 * 
	 * @return
	 */
	protected final boolean checkName(String name) {
		if (name == null || name.isEmpty())
			return false;
		
		for (APlayer player : players)
			if (player.hasName(name)) {
				System.out.println("This name is already taken");
				return false;
			}
		
		return true;
	}




	/**
	 * Factory design pattern + singleton
	 * 
	 * @param type
	 * @return
	 */
	public static final AGame factory(String type) {
		if (instance != null)
			return instance;
		
		try {
			Class<?> gameClass = Class.forName("fr.esiea.poinsignon.teissier.game." + type);
			
			if (gameClass != AGame.class && AGame.class.isAssignableFrom(gameClass)) {
				instance = (AGame)gameClass.newInstance();
				return instance;
			}
		}
		catch (Exception e) {
		}
		
		throw new RuntimeException(type + " is not an available game mode");
	}
}

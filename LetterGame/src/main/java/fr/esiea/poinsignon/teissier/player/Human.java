package fr.esiea.poinsignon.teissier.player;

import java.util.HashMap;

import fr.esiea.poinsignon.teissier.move.AMove;
import fr.esiea.poinsignon.teissier.util.Pair;

/**
 * Human player
 * 
 * @author Daniel Poinsignon
 */
public class Human extends APlayer {
	public Human(String name, String letter) {
		super(name, letter);
	}
	
	
	
	
	/**
	 * Check whether this player type needs the console to view things
	 * 
	 * @return
	 */
	public boolean needsConsole() {
		return true;
	}
	
	/**
	 * Choose a move option to execute
	 * 
	 * @param moves
	 */
	public int chooseMoveOption(HashMap<Integer, Pair<AMove, Integer>> options) {
		do {
			System.out.print("Choose option: ");
			String choice = System.console().readLine();
			if (choice == null || (choice = choice.trim()).isEmpty())
				continue;
			try {
				Integer idx = Integer.valueOf(choice);
				if (options.containsKey(idx))
					return idx.intValue();
			}
			catch (Exception e) {}
		} while (true);
	}
	

	/**
	 * Play our turn
	 * 
	 * @return The word we try to put
	 */
	public String play() {
		System.out.print("Please enter a word (empty to skip): ");

		return System.console().readLine();
	}
}

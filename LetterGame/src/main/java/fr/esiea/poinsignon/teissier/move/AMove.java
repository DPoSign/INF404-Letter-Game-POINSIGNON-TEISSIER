package fr.esiea.poinsignon.teissier.move;

import java.util.Vector;

import fr.esiea.poinsignon.teissier.game.AGame;


/**
 * Abstract move
 * 
 * @author Daniel Poinsignon
 */
public abstract class AMove {
	/**
	 * Options are stored by each move implementation with various data inside
	 */
	protected Vector<Object> options = new Vector<Object>();
	
	
	
	/**
	 * The player wants to play the given option of this move
	 * 
	 * @param game
	 * @param idx
	 */
	abstract public void playOption(AGame game, int idx);
	
	/**
	 * Try to perform the move, and keep possible solutions at hand
	 * 
	 * @param game
	 * @param word
	 * 
	 * @return True if the move is possible
	 */
	abstract public boolean attempt(AGame game, String word);
	
	/**
	 * Show on the console the different options that are available with this move
	 * 
	 * @param idx
	 */
	abstract public void showAvailableOptions(int idx);
	
	/**
	 * Show on the console the given option that is available with this move
	 * 
	 * @param idxGlobal
	 * @param idxOpt
	 */
	abstract public void showAvailableOption(int idxGlobal, int idxOpt);

	
	/**
	 * Show the option index at the beginning of the line
	 * 
	 * @param idx
	 */
	protected final void showOptionIndex(int idx) {
		System.out.print(idx + ". ");
	}
	
	
	/**
	 * Get the number of options for this move (for example we could steal a word from 2 different opponents)
	 * 
	 * @return
	 */
	public final int getNbOptions() {
		return options.size();
	}
	
	
	
	/**
	 * Factory design pattern
	 * 
	 * @param type
	 * @return
	 */
	public static final AMove factory(String type) {
		// Here we are using Reflection, but we could also use either a switch or even an enum somewhere
		try {
			Class<?> moveClass = Class.forName("fr.esiea.poinsignon.teissier.move." + type);
			
			if (moveClass != AMove.class && AMove.class.isAssignableFrom(moveClass))
				return (AMove)moveClass.newInstance();
		}
		catch (Exception e) {
		}
		
		throw new RuntimeException(type + " is not an available move");
	}
}

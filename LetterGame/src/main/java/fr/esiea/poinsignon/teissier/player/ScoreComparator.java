package fr.esiea.poinsignon.teissier.player;

import java.util.Comparator;



/**
 * Comparator for sorting players by their score
 * 
 * @author Daniel Poinsignon
 */
public class ScoreComparator implements Comparator<APlayer> {
	/**
	 * @param o1
	 * @param o2
	 * @return
	 */
	public int compare(APlayer o1, APlayer o2) {
		return o2.getScore() - o1.getScore();
	}
}

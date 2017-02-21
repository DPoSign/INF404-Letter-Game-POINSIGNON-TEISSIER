package fr.esiea.poinsignon.teissier.player;

import java.util.Comparator;



/**
 * Comparator for sorting players by their play order
 * 
 * @author Daniel Poinsignon
 */
public class PlayOrderComparator implements Comparator<APlayer> {
	/**
	 * @param o1
	 * @param o2
	 * @return
	 */
	public int compare(APlayer o1, APlayer o2) {
		return o1.getLetter().compareToIgnoreCase(o2.getLetter());
	}
}

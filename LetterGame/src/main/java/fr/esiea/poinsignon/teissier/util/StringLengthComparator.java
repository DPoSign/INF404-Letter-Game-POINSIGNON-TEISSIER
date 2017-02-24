package fr.esiea.poinsignon.teissier.util;

import java.util.Comparator;



/**
 * Comparator for sorting players by their score
 * 
 * @author Daniel Poinsignon
 */
public class StringLengthComparator implements Comparator<String> {
	/**
	 * @param o1
	 * @param o2
	 * @return
	 */
	public int compare(String o1, String o2) {
		return Integer.compare(o1.length(), o2.length());
	}
}

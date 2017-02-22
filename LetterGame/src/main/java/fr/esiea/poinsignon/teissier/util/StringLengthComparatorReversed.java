package fr.esiea.poinsignon.teissier.util;

import java.util.Comparator;



/**
 * Comparator for sorting players by their score
 * 
 * @author Cannelle Teissier
 */
public class StringLengthComparatorReversed implements Comparator<String> {
	/**
	 * @param o1
	 * @param o2
	 * @return
	 */
	public int compare(String o1, String o2) {
		return Integer.compare(o2.length(), o1.length());
	}
}

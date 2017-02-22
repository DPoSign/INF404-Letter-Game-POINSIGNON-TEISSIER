package fr.esiea.poinsignon.teissier.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * Utils
 * 
 * @author Cannelle Teissier
 */
public abstract class Util {
 /**
  * Get all substrings for the given string
  * 
  * @param str
  * @param minLen
  * 
  * @return
  */
 public static ArrayList < String > getAllSubstrings(String str, int minLen) {
  Set < String > perm = new HashSet < String > ();
  String tmp;

  for (int i = 0; i < str.length(); i++) {
   for (int k = 0; k < i; k++)
    if ((tmp = str.substring(k, i) + str.substring(i + 1)).length() >= minLen)
     perm.add(tmp);
   for (int j = i + 1; j <= str.length(); j++)
    if ((tmp = str.substring(i, j)).length() >= minLen)
     perm.add(tmp);
  }

  ArrayList < String > arr = new ArrayList < String > (perm);
  arr.sort(new StringLengthComparatorReversed());

  return arr;
 }

 /**
  * Find all permutations of a string
  * 
  * @param str
  * @return
  * 
  * @see http://javarevisited.blogspot.fr/2015/08/how-to-find-all-permutations-of-string-java-example.html
  */
 public static Set < String > findPermutations(String str) {
  Set < String > perm = new HashSet < String > ();

  findPermutations("", str, perm);

  return perm;
 }

 /**
  * Find permutations of a string
  * 
  * @param pivot
  * @param str
  * @param list
  * 
  * @return
  * 
  * @see http://javarevisited.blogspot.fr/2015/08/how-to-find-all-permutations-of-string-java-example.html
  */
 protected static Set < String > findPermutations(String pivot, String str, Set < String > list) {
  if (str.isEmpty())
   return list;

  for (int i = 0, len = str.length(); i < len; i++) {
   list.add(pivot + str);
   findPermutations(pivot + str.charAt(i), str.substring(0, i) + str.substring(i + 1, len), list);
  }

  return list;
 }

 /**
  * Check if "available" has all the letters needed by the word
  * 
  * @param word
  * @param available
  * 
  * @return True if all needed letters are present
  */
 public static boolean hasSameLetters(String word, String available) {
  Vector < String > res = new Vector < String > ();
  for (char c: available.toCharArray())
   res.add("" + c);

  return hasSameLetters(word, res);
 }

 /**
  * Check if "available" has all the letters needed by the word
  * 
  * @param word
  * @param available
  * 
  * @return True if all needed letters are present
  */
 public static boolean hasSameLetters(String word, Vector < String > available) {
  Vector < String > copy = new Vector < String > (available);
  int idx;

  for (char c: word.toCharArray()) {
   if ((idx = copy.indexOf("" + c)) == -1)
    return false;

   copy.remove(idx);
  }

  return true;
 }

 /**
  * Subtract characters from an origin string and return the remaining characters from origin
  * 
  * @param origin
  * @param subtract
  * 
  * @return
  */
 public static String remainingChars(String origin, String subtract) {
  String res = new String(origin);
  for (char c: subtract.toCharArray())
   res = res.replaceFirst("" + c, "");

  return res;
 }

 /**
  * Add the characters in the given word in the given vector of characters
  * 
  * @param list
  * @param word
  * 
  * @return
  */
 public static Vector < String > addCharsToVector(Vector < String > list, String word) {
  Vector < String > res = new Vector < String > (list);
  for (char c: word.toCharArray())
   res.add("" + c);

  return res;
 }

 /**
  * Open a resource and return it as an input stream
  * 
  * @param name The resource to open
  * 
  * @return The input stream
  * 
  * @throws Exception If resource cannot be read
  */
 public static InputStream openResource(String name) {
  InputStream in = null;

  try { in = Util.class.getResourceAsStream(name);
   if ( in == null)
    throw new Exception();
  } catch (Exception e) {
   throw new RuntimeException("Could not find the resource: '" + name + "'");
  }

  return in;
 }
}
package fr.esiea.poinsignon.teissier.bag;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

import fr.esiea.poinsignon.teissier.util.Util;


/**
 * Bag from which we pick letters
 * 
 * @author Daniel Poinsignon
 */
public class Bag {
 protected static Bag instance;

 protected NavigableMap < Double, String > letters = new TreeMap < Double, String > ();
 protected Random random;
 protected double totalWeight = 0;


 /**
  * Singleton
  */
 protected Bag() {
  random = new Random();
  initLetters();
 }


 /**
  * Pick a letter from the bag
  * 
  * @return A letter
  */
 public String pickLetter() {
  double rand = random.nextDouble() * totalWeight;

  return letters.ceilingEntry(rand).getValue().toUpperCase();
 }

 /**
  * Init letters with their associate weight
  * 
  * @see https://fr.wikipedia.org/wiki/Fr%C3%A9quence_d'apparition_des_lettres_en_fran%C3%A7ais
  * @see http://stackoverflow.com/questions/6409652/random-weighted-selection-in-java
  */
 protected void initLetters() {
  BufferedReader reader = new BufferedReader(new InputStreamReader(Util.openResource("/letters.txt")));
  String line;
  String[] parts;
  try {
   while ((line = reader.readLine()) != null) {
    parts = line.split(":");
    if (parts.length != 2)
     continue;
    // FIXME : sanity check
    totalWeight += Double.parseDouble(parts[1]);
    letters.put(totalWeight, parts[0]);
   }
  } catch (Exception e) {
   throw new RuntimeException("Error while reading the letters resource: " + e.getMessage());
  }
 }


 /**
  * Singleton design pattern
  * 
  * @return
  */
 public static final Bag getInstance() {
  if (instance == null)
   instance = new Bag();

  return instance;
 }
}
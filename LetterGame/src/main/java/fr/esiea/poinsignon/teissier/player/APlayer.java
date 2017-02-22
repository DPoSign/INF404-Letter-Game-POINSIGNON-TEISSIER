package fr.esiea.poinsignon.teissier.player;

import java.util.HashMap;
import java.util.Vector;
import fr.esiea.poinsignon.teissier.game.AGame;
import fr.esiea.poinsignon.teissier.move.AMove;
import fr.esiea.poinsignon.teissier.util.Pair;

/**
 * Abstract player
 * 
 * @author Daniel Poinsignon
 */
public abstract class APlayer {
 protected String name;
 protected String letter;
 protected Vector < String > words = new Vector < String > ();


 public APlayer(String name, String letter) {
  this.name = name;
  this.letter = letter;
 }



 /**
  * Add a word for this player
  * 
  * @param word
  */
 public final void addWord(String word) {
  words.add(word);
 }

 /**
  * Remove the given word from this player
  * 
  * @param word
  */
 public final void removeWord(String word) {
  words.remove(word);
 }


 /**
  * Check whether this player type needs the console to view things
  * 
  * @return
  */
 abstract public boolean needsConsole();

 /**
  * Play our turn
  * 
  * @param game
  * 
  * @return The word we try to put
  */
 abstract public String play(AGame game);

 /**
  * Choose a move option to execute
  * 
  * @param moves
  */
 abstract public int chooseMoveOption(HashMap < Integer, Pair < AMove, Integer >> options);



 /**
  * Get the score
  * 
  * @return
  */
 public final int getScore() {
  return words.size();
 }

 /**
  * Get the words placed by this player
  * 
  * @return
  */
 public final Vector < String > getWords() {
  return words;
 }

 /**
  * Get the words placed, but imploded as a comma-separated string
  * 
  * @return
  */
 public final String getWordsImploded() {
  String ret = "";
  for (String word: words)
   ret += (ret.isEmpty() ? "" : ", ") + word;

  return ret;
 }


 /**
  * Get this player name
  * 
  * @return
  */
 public final String getName() {
  return name;
 }

 /**
  * Get the beginning letter (for determining order of play)
  * 
  * @return
  */
 public final String getLetter() {
  return letter;
 }

 /**
  * Check if we have the given name (case insensitive)
  * 
  * @param test
  * 
  * @return
  */
 public final boolean hasName(String test) {
  return name.equalsIgnoreCase(test);
 }
}
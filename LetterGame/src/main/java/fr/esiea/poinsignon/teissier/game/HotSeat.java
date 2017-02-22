package fr.esiea.poinsignon.teissier.game;

import fr.esiea.poinsignon.teissier.player.APlayer;
import fr.esiea.poinsignon.teissier.player.Bot;
import fr.esiea.poinsignon.teissier.player.Human;

/**
 * HotSeat game
 * 
 * @author Cannelle Teissier
 */
public class HotSeat extends AGame {
 /**
  * Wait for all players to be connected.
  * In HotSeat, we only have local player, no client/server architecture
  */
 protected void waitForPlayers() {
  String name;

  do {
   System.out.print("New player enter your name ('BOT' for IA, otherwise leave empty): ");
   name = System.console().readLine();
   name = (name == null ? "" : name.trim());
   if (checkName(name))
    addHotSeatPlayer(name);
   System.out.println("");
  } while (!name.isEmpty());
 }


 /**
  * Add a new hotseat player
  * 
  * @param name
  */
 protected void addHotSeatPlayer(String name) {
  APlayer player;
  if (name.compareToIgnoreCase("bot") == 0)
   player = new Bot("bot", bowl.pickLetter(bag));
  else
   player = new Human(name, bowl.pickLetter(bag));
  players.add(player);
  System.out.println(player.getName() + " entered the game and picked the letter " + player.getLetter());
 }
}
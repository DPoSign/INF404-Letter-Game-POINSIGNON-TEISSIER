package fr.esiea.poinsignon.teissier.game;

import fr.esiea.poinsignon.teissier.player.Human;

/**
 * HotSeat game
 * 
 * @author Daniel Poinsignon
 */
public class HotSeat extends AGame {
	/**
	 * Wait for all players to be connected.
	 * In HotSeat, we only have local player, no client/server architecture
	 */
	protected void waitForPlayers() {
		String name;
		
		do {
			System.out.print("New player enter your name (otherwise leave empty): ");
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
		players.add(new Human(name, bowl.pickLetter(bag)));
		System.out.println(name + " entered the game and picked the letter " + players.lastElement().getLetter());
	}
}

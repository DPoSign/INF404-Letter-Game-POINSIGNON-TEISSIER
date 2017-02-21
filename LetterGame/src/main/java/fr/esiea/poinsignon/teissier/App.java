package fr.esiea.poinsignon.teissier;

import fr.esiea.poinsignon.teissier.game.AGame;
import fr.esiea.poinsignon.teissier.game.HotSeat;


/**
 * Main class
 * 
 * @author Daniel Poinsignon
 * 
 * @see http://findbugs.sourceforge.net/manual/eclipse.html
 */
class App {
	public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.

        HotSeat game = (HotSeat)AGame.factory("HotSeat");
        game.init();
    }
}

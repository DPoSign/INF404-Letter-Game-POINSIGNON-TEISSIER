package fr.esiea.poinsignon.teissier;

import fr.esiea.poinsignon.teissier.game.AGame;
import fr.esiea.poinsignon.teissier.game.HotSeat;
import fr.esiea.poinsignon.teissier.util.Util;


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

        // FIXME gérer l'IA
        // FIXME gérer les mots composés
        // FIXME tests unitaires
        
        HotSeat game = (HotSeat)AGame.factory("HotSeat");
        game.init();
    }
}

/*

Pour compiler ce fichier, il faut faire appel au compilateur Java : javac.
Vous pouvez compiler le fichier avec la commande suivante : javac Main.java.

Si tout se passe bien, vous devriez obtenir un fichier nommé Main.class. Il
contient le code source précédent sous forme de bytecode. Pour lancer le fichier, il faut
cette fois passer par l’intermédiaire de la machine virtuelle Java, avec la
commande suivante java Main.

*/


import interface_package.*;
import game_package.*;

public class Menu{ //extends Player{

    // interface_package.Display display = new interface_package.Display(); // Pour essayer de raccourcir le nom..


    public static void main(String[] args){
      int width = 6;
      int height = 7;
      String[] input_players;
      input_players = interface_package.Display.displayMenu();

      Game game = new Game(input_players, width, height);

      // System.out.println("Player 1 est "+game.player1.getName());
      // System.out.println("Player 2 est "+game.player2.getName());

      game.historyGame();

      game.play();

    }

}

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

public class Menu{

    // interface_package.Display display = new interface_package.Display(); // Pour essayer de raccourcir le nom..


    public static void main(String[] args){
      int width = 6;
      int height = 7;
      String[] input_players;
      int[] score = {0,0};

      input_players = Display.displayMenu();

      Game game = new Game(input_players, width, height, score);

      game.writePlayers();

      while(game.getScore(0) != 3 && game.getScore(1) != 3){
        // System.out.println(">>>>Score "+game.getScore(0)+" - "+game.getScore(1));
        game.writeBuffer("Manche commence");
        game.play();
        game.writeBuffer("Score "+game.getScore(0)+" - "+game.getScore(1));
        game_package.Grid.resetGrid(game.getGrid().getValues());
      }

      System.out.println("Partie finie");
    }

}

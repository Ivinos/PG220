/*

Pour compiler ce fichier, il faut faire appel au compilateur Java : javac.
Vous pouvez compiler le fichier avec la commande suivante : javac Main.java.

Si tout se passe bien, vous devriez obtenir un fichier nommé Main.class. Il
contient le code source précédent sous forme de bytecode. Pour lancer le fichier, il faut
cette fois passer par l’intermédiaire de la machine virtuelle Java, avec la
commande suivante java Main.

*/


// import interfacePackage.*;
import gamePackage.*;
// import java.io.File;
// import java.io.FileWriter;

public class Menu{

    public static void main(String[] args){
      int numberPlayers = 2;
      int width = 6;
      int height = 7;
      int rounds = 3;

      String[] inputPlayers;
      int[] score = {0,0};
      
      int[] parameters;

      parameters = interfacePackage.Display.parametersMenu(numberPlayers, width, height, rounds);
      numberPlayers = parameters[0];
      width = parameters[1];
      height = parameters[2];
      rounds = parameters[3];

      // inputPlayers = Display.displayMenu();
      inputPlayers = gamePackage.Game.selectPlayers();

      Game game = new Game(inputPlayers, numberPlayers, width, height, score, rounds);

      // game.writePlayers();

      while(game.getScore(0) != rounds && game.getScore(1) != rounds){
        gamePackage.WriteInLog.writeBuffer("Manche commence");
        game.play();
        gamePackage.WriteInLog.writeBuffer("Score "+game.getScore(0)+" - "+game.getScore(1));
        gamePackage.Grid.resetGrid(game.getGrid());
      }

      System.out.println("Partie finie");
    }

}

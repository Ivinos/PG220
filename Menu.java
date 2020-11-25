/*

Pour compiler ce fichier, il faut faire appel au compilateur Java : javac.
Vous pouvez compiler le fichier avec la commande suivante : javac Main.java.

Si tout se passe bien, vous devriez obtenir un fichier nommé Main.class. Il
contient le code source précédent sous forme de bytecode. Pour lancer le fichier, il faut
cette fois passer par l’intermédiaire de la machine virtuelle Java, avec la
commande suivante java Main.

*/

import java.io.File;
import java.io.FileWriter;
import interface_package.*;
import game_package.*;

public class Menu{ //extends Player{

    // interface_package.Display display = new interface_package.Display(); // Pour essayer de raccourcir le nom..

    public static void history_game(){
      String filename = "log.txt";

      try{
        File file_to_create = new File(filename);

        file_to_create.delete();

        if (file_to_create.createNewFile())
          System.out.println("\nFile "+filename+" created\n");

        else{
          System.out.println("\nFile "+filename+" already exists");
          System.exit(0);
        }

        FileWriter file_to_write = new FileWriter(filename);
        // file_to_write.write("Joueur 1 est "+game_package.Game.getPlayer1().getName());
        file_to_write.write("Joueur 1 est ...");
        file_to_write.write("\nJoueur 2 est ...");
        file_to_write.write("\nManche commence");
        file_to_write.close();
      }
      catch (Exception e){
        System.err.println(e);
      }
    }


    public static void main(String[] args){
      int width = 6;
      int height = 7;
      String[] input_players;
      input_players = interface_package.Display.display_menu();

      Game game = new Game(input_players, width, height);

      // Player p1 = new game_package.Game.getPlayer1();
      // String name_p1 = new game_package.Player.getName();
      // System.out.println("\nPlayer 1 est ");
      history_game();

      game.play();

    }

}

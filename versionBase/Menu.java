import gamePackage.interfacePackage.WriteInLog;
import gamePackage.interfacePackage.CheckInput;
import gamePackage.Game;
import java.util.Scanner;

public class Menu{

    private static WriteInLog write; // Pour utiliser les méthodes de WriteInLog
    private static CheckInput check; // Pour utiliser les méthodes de CheckInput
  
    public static void main(String[] args){
      int numberPlayers = 2;
      int width = 7;
      int height = 6;
      int rounds = 3;
      int tokens = 4;
      int[] score;
      String[] inputPlayers;
      
      score = new int[numberPlayers];

      // Choix du pseudo + type
      inputPlayers = selectPlayers(numberPlayers);
      if (inputPlayers[0].equals("sortir")){
        System.exit(0);
      }

      // Initialisation du jeu
      Game game = new Game(inputPlayers, numberPlayers, width, height, score, rounds, tokens);

      // Début du jeu
      while(game.checkRoundsBestOf() == 0){
        write.writeBuffer("Manche commence");
        game.play();
        write.writeBuffer(game.writeScore());
      }

      write.writeBuffer("Partie finie");
    }

  // Création du fichier log.txt
  private static String[] selectPlayers(int numberPlayers){
    Scanner scanner = new Scanner(System.in);
    String s = new String("Joueur ");
    String res[] = new String[2*numberPlayers];
    int cntPlayer;

    write.createLog();

    for (int i = 0; i<2*numberPlayers; i +=2){
      String buf[] = new String[2];
      String name = new String();
      String type = new String();

      cntPlayer = (i/2)+1;

      System.out.println(s+cntPlayer+"?"); // Joueur i?

      buf = check.checkNameType(scanner.nextLine(), cntPlayer);
      if (buf[0].equals("sortir")){
        return buf;
      }
      type = buf[0];
      name = buf[1];

      write.writeBuffer("Joueur "+cntPlayer+" est "+type+" "+name);
      res[i] = type;
      res[i+1] = name;
    }

    return res; // type1 name1 type2 name2 ..
  }
    
}

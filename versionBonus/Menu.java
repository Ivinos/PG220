import gamePackage.interfacePackage.WriteInLog;
import gamePackage.interfacePackage.CheckInput;
import gamePackage.interfacePackage.BonusMenu;
import gamePackage.Game;
import java.util.Scanner;

public class Menu{

    private static WriteInLog write; // Pour utiliser les méthodes de WriteInLog
    private static CheckInput check; // Pour utiliser les méthodes de CheckInput
    private static BonusMenu menu;   // Pour utiliser les méthodes de BonusMenu (gère les paramètres)
  
    public static void main(String[] args){
      int numberPlayers = 2; // Nombre de joueur
      int width = 7;  // Largeur de la grille
      int height = 6; // Hauteur de la grille
      int rounds = 3; // Nombre de manche à jouer
      int tokens = 4; // Nombre de jeton à aligner pour gagner
      int[] score;    // Score des joueur (liste des score de chacun dans l'ordre des joueurs)
      String[] inputPlayers;

       // Afichage du menu
       int[] parameters = new int[5];

       parameters = menu.parametersMenu(numberPlayers, width, height, rounds, tokens);
       numberPlayers = parameters[0];
       width = parameters[1];
       height = parameters[2];
       rounds = parameters[3];
       tokens = parameters[4];
      
      score = new int[numberPlayers];

      // Choix du pseudo + type
      inputPlayers = selectPlayers(numberPlayers);
      if (inputPlayers[0].equals("sortir")){
        System.exit(0);
      }

      // Initialisation du jeu
      Game game = new Game(inputPlayers, numberPlayers, width, height, score, rounds, tokens);
      System.out.println("");

      // Début du jeu
      while(game.checkRoundsBestOf() == 0){
        write.writeBuffer("Manche commence");
        game.play();
        write.writeBuffer(game.writeScore());
      }

      System.out.println("Partie finie");
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

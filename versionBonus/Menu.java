import gamePackage.interfacePackage.WriteInLog;
import gamePackage.interfacePackage.CheckInput;
import gamePackage.interfacePackage.BonusMenu;
import gamePackage.Game;
import java.util.Scanner;

public class Menu{

    private static WriteInLog write; // Pour utiliser les méthodes de WriteInLog
    private static CheckInput check; // Pour utiliser les méthodes de CheckInput
    private static BonusMenu menu; // Pour utiliser les méthodes de BonusMenu (gère les paramètres)
  
    public static void main(String[] args){
      int numberPlayers = 2;
      int width = 7;
      int height = 6;
      int rounds = 3;
      int tokens = 4;
      int[] score;
      String[] inputPlayers;
      String[] symbols = {". ", "X ", "O ", "V ", "T ", "Y ", "@ "}; // Symboles des joueurs (index 0 = personne)

      
      String[] A = {"1","2","3","4","5","6","7","8","9"};
      String[] AA = menu.getStringFromTo(A,2,5);
      for (int i = 0; i<AA.length; i++)
          System.out.print(AA[i]);
      System.out.println("\n");

      // Afichage du menu
      int[] parameters = new int[5];
      String[] parametersString = new String[parameters.length+symbols.length];

      parametersString = menu.parametersMenu(numberPlayers, width, height, rounds, tokens, symbols);
      parameters = menu.convertStringToInt(menu.getStringFromTo(parametersString,0,4));
      numberPlayers = parameters[0];
      width = parameters[1];
      height = parameters[2];
      rounds = parameters[3];
      tokens = parameters[4];
      symbols = menu.getStringFromTo(parametersString,5,parametersString.length-1);

      // for (int i = 0; i<symbols.length; i++)
      //     System.out.print(symbols[i]);
      // System.out.println("FINI");

      score = new int[numberPlayers];

      // Choix du pseudo + type
      inputPlayers = selectPlayers(numberPlayers);
      if (inputPlayers[0].equals("sortir")){
        System.exit(0);
      }

      // Initialisation du jeu
      Game game = new Game(inputPlayers, numberPlayers, width, height, score, rounds, tokens, symbols);
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

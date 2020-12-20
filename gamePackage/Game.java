package gamePackage;

import gamePackage.interfacePackage.Display;
import java.io.Console;
import java.util.ArrayList; // Pour tableau de Player dynamique

public class Game{

    // Attributs
    public ArrayList<Player> players;
    public Grid grid;
    public int[] score;
    public int rounds;
    public int tokens;
    public int numberPlayers;

    public static Display disp;
    public static WriteInLog write;
    public static gamePackage.CheckInput check;

    // Constructeur
    public Game(String[] inputPlayers, int numberPlayers, int width, int height, int[] score, int rounds, int tokens){
      int name;
      int type;
      int numeroPlayer;
      this.players = new ArrayList<Player>(); // On initialise la collection (elle est actuellement vide)

      for (int i = 0; i<2*numberPlayers; i+=2) {
          type = i;
          name = i + 1;
          numeroPlayer = (i / 2) + 1;

          if (inputPlayers[type].equals("humain"))
              this.players.add(new Human(inputPlayers[name], numeroPlayer));
          else if (inputPlayers[type].equals("ia:monkey"))
              this.players.add(new Monkey(inputPlayers[name], numeroPlayer));
          else if (inputPlayers[type].equals("ia:pro"))
              this.players.add(new Pro(inputPlayers[name], numeroPlayer));
          else // ia normale
              this.players.add(new Ia(inputPlayers[name], numeroPlayer));

          this.grid = new Grid(width, height);
          this.score = score;
          this.rounds = rounds;
          this.tokens = tokens;
          this.numberPlayers = numberPlayers;
      }
    }

    // Getteurs + setters
    public Grid getGrid(){
        return grid;
    }

    public Player getPlayer(int who){
       return players.get(who-1);
    }

    public int getScore(int i){
        return score[i];
    }

    public void setScore(int i){
        score[i] += 1;
    }

    public int getRounds(int i){
      return rounds;
    }

    public void setRounds(int i){
        rounds = i;
    }

    public int getNumberPlayers(){
      return numberPlayers;
    }

    public void setNumberPlayers(int i){
      numberPlayers = i;
    }

    public int getTokens(){
      return tokens;
    }
    
    public void setTokens(int i){
      tokens = i;
    }

    public static int checkRounds(Game game){
      int res = 0;
      for (int i = 0; i<game.numberPlayers; i++){
        if (game.score[i] == game.rounds)
          res = 1;
      }
      return res;
    }

    public static String writeScore(Game game){
      String res;
      res = "Score "+game.score[0];

      for (int i = 1; i<game.numberPlayers; i++){
        res = res+" - "+game.score[i];
      }
      return res;
    }

    // public static String setColor(String buffer, String color, int attr) {
    //   String reset = "\u001B[0m";
    //   String bold = "\u001B[1m";

    //   if (attr == 0)
    //     return color + buffer + reset;
    //   return bold + color + buffer + reset;
    // }

    // public void gameParameters(){
    //   String[] symbols = {".", "X", "O", "V", "T", "Y", "@"}; // à changer plus tard surement parce que pas très beau
    //   String[] colors = {"\u001B[37m","\u001B[31m","\u001B[34m","\u001B[32m","\u001B[35m","\u001B[33m","\u001B[36m"};


    //   System.out.println(setColor("\n[JEU]","\u001B[33m",1));
    //   System.out.println("Victoire : "+this.rounds+" manches à remporter");
    //   System.out.println("Manche : "+this.tokens+" jetons à aligner pour remporter 1 manche");
    //   System.out.println(writeScore(this));

    //   System.out.println(setColor("\n[GRILLE]","\u001B[33m",1));
    //   System.out.println("Hauteur : "+this.grid.height+" lignes");
    //   System.out.println("Largeur : "+this.grid.width+" colonnes");

    //   System.out.println(setColor("\n[JOUEURS]","\u001B[33m",1));
    //   System.out.println("Nombre de joueurs : "+this.numberPlayers);
    //   for (int i = 1; i<=this.numberPlayers; i++)
    //     System.out.println("Joueur "+i+" est "+this.getPlayer(i).getName()+" de type "+this.getPlayer(i).getType()+" (symbole : "+setColor(symbols[i],colors[i],0)+")");
    //   System.out.println("");     
    // }

    // Création du fichier log.txt 
    public static String[] selectPlayers(int numberPlayers){
      Console console = System.console();
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

        buf = check.checkPlayers(console.readLine(),cntPlayer);
        type = check.checkType(buf[0],cntPlayer);
        name = buf[1];

        write.writeBuffer("Joueur "+cntPlayer+" est "+name);
        res[i] = type;
        res[i+1] = name;
        }

      return res; // type1 name1 type2 name2 ..
    }

    // Retourne le numéro du joueur qui joue
    public int whichPlayer(int i){
      int res = i%this.numberPlayers;

      if (res == 0)
          res = this.numberPlayers;
      // En gros 2%2 = 0 sauf que c'est le dernier joueur
      // Et rajouter plus 1 au res ne marche pas car sinon le p1 ne joue pas en premier
      return res;
    }

    public int nullTab(int[] tab, int len){
      int bool = 0;
      for (int i=0; i<len; i++){
        if (tab[i] == 1)
            bool = 1;
      }
      return bool;
    }


    // Actions pendant le jeu
    public void play(){
      int i = 1; // i = numéro de tour (le joueur 1 commence à jouer)
      int who; // numéro de joueur
      int position = -3; // -3 correspond à un move non valide (colonne pleine)
      int[] wins = new int[this.numberPlayers];
      int equality = 0;

      disp.displayGrid(getGrid());

      while(nullTab(wins, this.numberPlayers) == 0 && equality == 0){
        who = whichPlayer(i);
        position = -3;

        while(position == -3){

            position = this.getPlayer(who).choice(grid.values, grid.width, grid.height, this.tokens);

            if (position == -1) // commande "sortir"
                System.exit(0);

            if (position == -2) {
                disp.gameParameters(this);
                position = -3; // On remet en "attente le choix du joueur"
            }
            else if (position == -1) { // commande "sortir"
                System.exit(0);
            }
            else{
                if (getPlayer(who).getType().equals("humain")) // pour un affichage bien (oui oui)
                  //System.out.println("");

                position = grid.updateGrid(position, who);
            }
        }

        if (position == -1) // commande "sortir"
          System.exit(0);

        disp.displayGrid(getGrid());
        
        if(grid.victoryCheck(this.tokens,position-1) == 1){ // -1 car en java, l'indexe commence à 0
          write.writeBuffer("Joueur "+who+" gagne");
          setScore(who-1); 
          wins[who-1] = 1; 
        }

        else if(grid.equalityCheck() == 1){
          equality = 1;
          write.writeBuffer("Egalite");
        }

      i++;
      }
    }

}
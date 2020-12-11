package gamePackage;

import java.io.Console;
import java.util.ArrayList; // Pour tableau de Player dynamique

public class Game{

    // Attributs
    //public Player[] players;
    public ArrayList<Player> players;
    public Grid grid;
    public int[] score;
    public int rounds;
    public int numberPlayers;

    // Constructeur
    public Game(String[] inputPlayers, int numberPlayers, int width, int height, int[] score, int rounds){
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
              //this.players[numeroPlayer] = new Human(inputPlayers[name], numeroPlayer);
          else if (inputPlayers[type].equals("ia:monkey"))
              this.players.add(new Monkey(inputPlayers[name], numeroPlayer));
              //this.players[numeroPlayer] = new Monkey(inputPlayers[name], numeroPlayer);
          else if (inputPlayers[type].equals("ia:pro"))
              this.players.add(new Pro(inputPlayers[name], numeroPlayer));
              //this.players[numeroPlayer] = new Pro(inputPlayers[name], numeroPlayer);
          else // ia normal
              this.players.add(new Ia(inputPlayers[name], numeroPlayer));
              //this.players[numeroPlayer] = new Ia(inputPlayers[name], numeroPlayer);

          this.grid = new Grid(width, height);
          this.score = score;
          this.rounds = rounds;
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

    public void gameParameters(){
      System.out.println("\n[JEU]");
      System.out.println("Victoire : "+this.rounds+" manches à remporter");
      System.out.println("Manche : 4 jetons à aligner");
      System.out.println("Score : "+ this.getScore(0)+" - "+ this.getScore(1));

      System.out.println("\n[GRILLE]");
      System.out.println("Hauteur : "+this.grid.height+" lignes");
      System.out.println("Largeur : "+this.grid.width+" colonnes");

      System.out.println("\n[JOUEURS]");
      System.out.println("Nombre de joueurs : "+this.numberPlayers);

      for (int i = 0; i<this.numberPlayers; i++){

        System.out.print("Joueur "+i+" est "+this.getPlayer(i).getName()+" de type "+this.getPlayer(i).getType()); // Pour l'instant pas ok mais trkl (on va bientôt faire une liste de joeur)

//        if (numeroPlayer %2 == 1)
//          System.out.println(" (symbole : croix rouge)");
//        else
//          System.out.println(" (symbole : rond bleue)");
      }
      System.out.println("");     
    }

    // Create file log.txt + check pseudo/type of players
    public static String[] selectPlayers(int numberPlayers){
      Console console = System.console();
      String s = new String("Joueur ");
      String res[] = new String[2*numberPlayers];
      int cntPlayer;

      WriteInLog.createLog();

      for (int i = 0; i<2*numberPlayers; i +=2){
        String buf[] = new String[2];
        String name = new String();
        String type = new String();

        cntPlayer = (i/2)+1;

        System.out.println(s+cntPlayer+"?"); // Joueur i?

        buf = CheckInput.checkPlayers(console.readLine(),cntPlayer);
        type = CheckInput.checkType(buf[0],cntPlayer);
        name = buf[1];

        WriteInLog.writeBuffer("Joueur "+cntPlayer+" est "+name);
        res[i] = type;
        res[i+1] = name;
        }

      return res; // type1 name1 type2 name2 ..
    }

    // Return which player is playing
    public int whichPlayer(int i){
      int res = i%this.numberPlayers;

      if (res == 0)
          res = this.numberPlayers;
      // En gros 2%2 = 0 sauf que c'est le dernier joueur
      // Et rajouter plus 1 au res ne marche pas car sinon le p1 ne joue pas en premier (réflechi..)
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


    // Actions during the game
    public void play(){
      int i = 1; // player 1 starts playing, i = numéro de tour
      int who; // numéro de joueur
      int position = -3; // -3 correspond à un move non valide (colonne pleine)
      int[] wins = new int[this.numberPlayers]; // Initialiser à 0 par défaut
      int equality = 0;
      // int win1 = 0, win2 = 0, equality = 0;

      interfacePackage.Display.displayGrid(getGrid());

      while(nullTab(wins, this.numberPlayers) == 0 && equality == 0){
        who = whichPlayer(i);
        position = -3;

        // position = getPlayer(i).choice(grid);

        while(position == -3){

            position = this.getPlayer(who).choice(grid.values, grid.width, grid.height);

//            if (who == 1) // si le joueur 1 doit jouer
//                position = getPlayer1().choice(grid.values, grid.width, grid.height);
//
//            else // si le joueur 2 doit jouer
//                position = getPlayer2().choice(grid.values, grid.width, grid.height);

            if (position == -1) // command "sortir"
                System.exit(0);

            if (position == -2) {
                this.gameParameters();
                position = -3; // On remet en "attente le choix du joueur"
            }
            else if (position == -1) { // command "sortir"
                System.exit(0);
            }else{
                System.out.println("");
                position = grid.updateGrid(position, who);
            }
        }

        if (position == -1) // command "sortir"
          System.exit(0);

        interfacePackage.Display.displayGrid(getGrid());

        if(grid.victoryCheck(position-1) == 1){ // -1 because in java, index starts at 0
          WriteInLog.writeBuffer("Joeur "+who+" gagne");
          setScore(who-1); // Le joueur 1 à l'index 0 ect
          wins[who-1] = 1; // same
        }

        else if(grid.equalityCheck() == 1){
          equality = 1;
          WriteInLog.writeBuffer("Egalite");
        }

      i++;
      }
    }

}
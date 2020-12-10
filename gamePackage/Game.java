package gamePackage;

import java.io.Console;

public class Game{

    // Attributs
    public Player player1;
    public Player player2;
    // public Player players[];
    public Grid grid;
    public int[] score;
    public int rounds;
    public int numberPlayers;

    // Constructeur
    public Game(String[] inputPlayers, int numberPlayers, int width, int height, int[] score, int rounds){
      int name;
      int type;

      for (int i = 0; i<numberPlayers-1; i++){
        type = 2*i;
        name = 2*i + 1;
        
        // if (inputPlayers[type].equals("humain")){
        //   this.players[i] = new Human(inputPlayers[name],i+1);
        // }
        // else{
        //   this.players[i] = new Ia(inputPlayers[name],i+1);
        // }

        if (inputPlayers[type].equals("humain"))
          this.player1 = new Human(inputPlayers[name],i);
        else if (inputPlayers[type].equals("ia:monkey"))
          this.player1 = new Monkey(inputPlayers[name],i);
        else if (inputPlayers[type].equals("ia:pro"))
            this.player1 = new Pro(inputPlayers[name],i);
        else
            this.player1 = new Ia(inputPlayers[name],i);
  
        if (inputPlayers[type+2].equals("humain"))
          this.player2 = new Human(inputPlayers[name+2],i+2);
        else if (inputPlayers[type+2].equals("ia:monkey"))
            this.player2 = new Monkey(inputPlayers[name+2],i+2);
        else if (inputPlayers[type+2].equals("ia:pro"))
            this.player2 = new Pro(inputPlayers[name+2],i+2);
        else
            this.player2 = new Ia(inputPlayers[name+2],i+2);
      }
      this.grid = new Grid(width, height);
      this.score = score;
      this.rounds = rounds;
      this.numberPlayers = numberPlayers;
    }

    // Getteurs + setters
    public Grid getGrid(){
        return grid;
    }

    // public Player getPlayer(int who){
    //   return players[who-1];
    // }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
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

    public int getNumberPlayers(int i){
      return numberPlayers;
    }

    public void setNumberPlayers(int i){
      numberPlayers = i;
    }

    public void gameParameters(){
      int numeroPlayer;
      int cntPlayers = 2;

      System.out.println("\n[JEU]");
      System.out.println("Victoire : "+this.rounds+" manches à remporter");
      System.out.println("Manche : 4 jetons à aligner");
      System.out.println("Score : "+ this.getScore(0)+" - "+ this.getScore(1));

      System.out.println("\n[GRILLE]");
      System.out.println("Hauteur : "+this.grid.height+" lignes");
      System.out.println("Largeur : "+this.grid.width+" colonnes");

      System.out.println("\n[JOUEURS]");
      System.out.println("Nombre de joueurs : "+cntPlayers);
      for (int i = 0; i<2*cntPlayers; i += 2){
        numeroPlayer = (i/2)+1;
        System.out.print("Joueur "+numeroPlayer+" est "+this.getPlayer1().getName()+" de type "+this.getPlayer1().getType()); // Pour l'instant pas ok mais trkl (on va bientôt faire une liste de joeur)

        if (numeroPlayer %2 == 1)
          System.out.println(" (symbole : croix rouge)");
        else
          System.out.println(" (symbole : rond bleue)");
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
    public static int whichPlayer(int i){
      int res = i%2;

      if (res == 0)
        return 2;
      else
        return 1;
    }


    // Actions during the game
    public void play(){
      int i = 1; // player 1 starts playing, i = numéro de tour
      int who; // numéro de joueur
      int position = -3; // -3 correspond à un move non valide (colonne pleine)
      int win1 = 0, win2 = 0, equality = 0;

      interfacePackage.Display.displayGrid(getGrid());

      while(win1 == 0 && win2 == 0 && equality == 0){
        who = whichPlayer(i);
        position = -3;

        // position = getPlayer(i).choice(grid);

        while(position == -3){
            if (who == 1) // si le joueur 1 doit jouer
                position = getPlayer1().choice(grid.values, grid.width, grid.height);

            else // si le joueur 2 doit jouer
                position = getPlayer2().choice(grid.values, grid.width, grid.height);

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
          if(who == 1){
            WriteInLog.writeBuffer("Joueur 1 gagne");
            setScore(0);
            win1 = 1;
          }
          else{
            WriteInLog.writeBuffer("Joueur 2 gagne");
            setScore(1);
            win2 = 1;
          }
        }

        else if(grid.equalityCheck() == 1){
          equality = 1;
          WriteInLog.writeBuffer("Egalite");
        }

      i++;
      }
    }

}
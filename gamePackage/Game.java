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

        if (inputPlayers[type].equals("humain")){
          this.player1 = new Human(inputPlayers[name],i);
        }
        else{
          this.player1 = new Ia(inputPlayers[name],i);
        }
  
        if (inputPlayers[type+2].equals("humain")){
          this.player2 = new Human(inputPlayers[name+2],i+2);
        }
        else{
          this.player2 = new Ia(inputPlayers[name+2],i+2);
        }

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


    // Create file log.txt + check pseudo/type of players
    public static String[] selectPlayers(int numberPlayers){
      Console console = System.console();
      String s = new String("Joueur ");
      String res[] = new String[2*numberPlayers];
      int cntPlayer;

      WriteInLog.createLog();

      for (int i = 0; i<numberPlayers; i++){
        String buf[] = new String[2];
        String name = new String();
        String type = new String();

        cntPlayer = i+1;
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
      int i = 1; // player 1 starts playing
      int position = 0;
      int win1 = 0, win2 = 0, equality = 0;

      interfacePackage.Display.displayGrid(getGrid());

      while(win1 == 0 && win2 == 0 && equality == 0){
        i = whichPlayer(i);

        // position = getPlayer(i).choice(grid);

        if (i == 1) // si le joueur 1 doit jouer
         position = getPlayer1().choice(grid);

        else // si le joueur 2 doit jouer
         position = getPlayer2().choice(grid);

        if (position == -1){ // command "sortir"
          System.exit(0);
        }
        
        System.out.println("");
        //position = updateGrid(getGrid(), position, i);
          position = grid.updateGrid(position, i, getPlayer1(), getPlayer2());

        if (position == -1){ // command "sortir"
          System.exit(0);
        }

        interfacePackage.Display.displayGrid(getGrid());

        if(grid.victoryCheck(position-1) == 1){ // -1 because in java, index starts at 0
          if(i == 1){
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
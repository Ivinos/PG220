package gamePackage;

import java.util.ArrayList;
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

      for (int i = 0; i<2*numberPlayers; i +=2){
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

    // Update the grid after a move
    public int updateGrid(Grid grid, int position, int player){
      int validMove = 0;
      int width = grid.width;
      int height = grid.height;

      int numeroPlayer = whichPlayer(player);

      while (validMove == 0){
        for(int k = 0; k<height; k++){
            if (grid.values[height-1-k][position-1] == 0){ // if cell is empty
                if (player == 1)
                    grid.values[height-1-k][position-1] = -1;
                else
                    grid.values[height-1-k][position-1] = 1;

                int ord = position-1+1;
                validMove = 1;
                // System.out.println("Joueur "+numeroPlayer+" joue en position "+ord);
                WriteInLog.writeBuffer("Joueur "+numeroPlayer+" joue "+ord);
                getGrid().values = grid.values;
                return position;
            }
        }
        WriteInLog.writeBuffer("Erreur colonne pleine "+position);
        System.out.print("Erreur : colonne "+position+" pleine. Choisis un nombre entre 1 et "+width+" : ");

        // position = getPlayer(numeroPlayer).choice(grid);

        if (numeroPlayer == 1){
          position = getPlayer1().choice(grid);
        }
        else
          position = getPlayer2().choice(grid);

        System.out.println("");
      
      if (position == -1)
        return position;
      }

      getGrid().values = grid.values;
      return position;
    }


    // Check if there is equality
    int equalityCheck(Grid grid){
      int width = grid.width;
      int cpt = 0;

      for (int i = 0; i<width; i++){
        if (grid.values[0][i] != 0)
          cpt++;
      }
      if (cpt == width)
        return 1;

      return 0;
    }

    // Check if the last move make a win
    int victoryCheck(Grid grid, int lastColumn){
        int width = grid.width;
        int height = grid.height;
        int lastLine = 0; // Recherche de la coordonné i du dernier coup
        
        while(grid.values[lastLine][lastColumn] == 0){
            lastLine++;
        }

        int vertical[] = new int[height];
        int horizontal[] = new int[width];
        int lenDiag = 1;
        int lenAntiDiag = 1;
        ArrayList<Integer> diagonalTmp = new ArrayList<Integer>();
        ArrayList<Integer> antidiagonalTmp = new ArrayList<Integer>();

        diagonalTmp.add(grid.values[lastLine][lastColumn]); // the move has been added
        antidiagonalTmp.add(grid.values[lastLine][lastColumn]); // idem

        // Filling diagonal
        int tmpLine = lastLine; int tmpColumn = lastColumn;
        while((tmpLine-- > 0) && (tmpColumn++ < width-1)){ // check if the next cell is possible
            diagonalTmp.add(grid.values[tmpLine][tmpColumn]);
            lenDiag++;
        }
        tmpLine = lastLine; tmpColumn = lastColumn; // reinitialization
        while((tmpLine++ < height-1) && (tmpColumn-- > 0)){
            diagonalTmp.add(0, grid.values[tmpLine][tmpColumn]);
            lenDiag++;
        }

        // Filling antidiagonal
        tmpLine = lastLine; tmpColumn = lastColumn; // reinitialization
        while((tmpLine++ < height-1) && (tmpColumn++ < width-1)){
            antidiagonalTmp.add(grid.values[tmpLine][tmpColumn]);
            lenAntiDiag++;
        }
        tmpLine = lastLine; tmpColumn = lastColumn; // reinitialization
        while((tmpLine-- > 0) && (tmpColumn-- > 0)){
            antidiagonalTmp.add(0, grid.values[tmpLine][tmpColumn]);
            lenAntiDiag++;
        }

        // Filling vertically and horizontally
        for(int i = 0; i<height; i++){
            vertical[i] = grid.values[i][lastColumn];
        }
        for(int i = 0; i<width; i++){
            horizontal[i] = grid.values[lastLine][i];
        }

        // diag and antidiag are now transformed into a list
        int diagonal[] = new int[lenDiag];
        int antiDiagonal[] = new int[lenAntiDiag];
        for (int i = 0; i<lenDiag; i++){
            diagonal[i] = diagonalTmp.get(i);
        }
        for (int i = 0; i<lenAntiDiag; i++){
            antiDiagonal[i] = antidiagonalTmp.get(i);
        }

        // check of each list
        if(arrayCheck(vertical, height) == 1){
            return 1; // victory
        } else if(arrayCheck(horizontal, width) == 1){
            return 1; // victory
        } else if(arrayCheck(diagonal, lenDiag) == 1){
            return 1; // victory
        } else if(arrayCheck(antiDiagonal, lenAntiDiag) == 1){
            return 1; // victory
        }
        return 0; // No victory
    }

    // Print an array
    void printArray(int[] array, int length){
        for (int i=0; i<length; i++){
            System.out.print(array[i]);
        }
        System.out.print("\n");
    }

    // Check if there is a victory
    int arrayCheck(int[] array, int length){
        int count = 0;
        int max = length - 4;

        for(int i = 0; i <= max; i++){
          for(int j = 0; j<4; j++){
            count += array[i+j];
          
            if ((count == -4) || (count == 4)){
              return 1; // victory
            }
          }
          count = 0;
        }
        return 0; // no victory
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
        position = updateGrid(getGrid(), position, i);

        if (position == -1){ // command "sortir"
          System.exit(0);
        }

        interfacePackage.Display.displayGrid(getGrid());

        if(victoryCheck(grid, position-1) == 1){ // -1 because in java, index starts at 0
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

        else if(equalityCheck(grid) == 1){
          equality = 1;
          WriteInLog.writeBuffer("Egalite");
        }

       i++;
      }
    }


}
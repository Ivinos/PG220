package gamePackage;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;


public class Game{

    // Attributs
    public Player player1;
    public Player player2;
    public Grid grid;
    public int[] score;

    // Constructeur
    public Game(String[] inputPlayers, int width, int height, int[] score){
      if (inputPlayers[0].equals("humain")){
        this.player1 = new Human(inputPlayers[1],1);
      }
      else{
        this.player1 = new Ia(inputPlayers[1],1);
      }

      if (inputPlayers[2].equals("humain")){
        this.player2 = new Human(inputPlayers[3],2);
      }
      else{
        this.player2 = new Ia(inputPlayers[3],2);
      }

      this.grid = new Grid(width, height);

      this.score = score;
    }

    // Getteurs
    public Grid getGrid(){
        return grid;
    }

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



    // Return which player is playing
    public static int whichPlayer(int i){
      int res = i%2;
      if (res == 0)
        return 2;
      else
        return 1;
    }




    public void writePlayers(){
      String filename = "log.txt";

      try{
        File fileToCreate = new File(filename);

        fileToCreate.delete();

        // if (fileToCreate.createNewFile())
        //   System.out.println("\nFile "+filename+" created\n");
        //
        // else{
        //   System.out.println("\nFile "+filename+" already exists");
        //   System.exit(0);
        // }

        FileWriter fileToWrite = new FileWriter(filename);
        fileToWrite.write("Joueur 1 est "+player1.getName());
        fileToWrite.write("\nJoueur 2 est "+player2.getName());
        fileToWrite.close();
      }
      catch (Exception e){
        System.err.println(e);
      }
    }

    //Write in log.txt the move of the player
    public static void writeMove(int player, int position){
      try{
        String filename = "log.txt";
        FileWriter fileToWrite = new FileWriter(filename, true);
        fileToWrite.write("\nJoueur "+player+" joue "+position);
        fileToWrite.close();
      }
      catch (Exception e){
        System.err.println(e);
      }
    }

    // //Write in log.txt the victory of the player
    // public static void writeVictory(String win){
    //   try{
    //     String filename = "log.txt";
    //     FileWriter fileToWrite = new FileWriter(filename, true);
    //     if (win.equals("win1"))
    //       fileToWrite.write("\nJoueur 1 gagne");
    //     else
    //       fileToWrite.write("\nJoueur 2 gagne");
    //     fileToWrite.close();
    //   }
    //   catch (Exception e){
    //     System.err.println(e);
    //   }
    // }

    //Write in log.txt the string
    public static void writeBuffer(String buffer){
      try{
        String filename = "log.txt";
        FileWriter fileToWrite = new FileWriter(filename, true);

        fileToWrite.write("\n"+buffer);
        fileToWrite.close();
      }

      catch (Exception e){
        System.err.println(e);
      }
    }


    // Update the grid after a turn
    public int[][] updateGrid(int[][] grid, int position, int player){
      int validMove = 0;

      player = whichPlayer(player);            // define which is player's turn (player 1 or player 2 ?)

      while (validMove == 0){
        for(int k = 0; k<6; k++){
            if (grid[5-k][position-1] == 0){ // if cell [position][k] is empty
                if (player == 1)
                    grid[5-k][position-1] = -1;
                else
                    grid[5-k][position-1] = 1;

                // int abs = 5-k+1;
                int ord = position-1+1;
                validMove = 1;
                System.out.println("Player "+player+" joue en position "+ord+"\n");
                writeMove(player, ord);
                return grid;
            }
        }
        writeBuffer("Erreur colonne pleine "+position);
        System.out.println("Erreur : colonne "+position+" pleine. Choisis un nombre entre 1 et 7 : ");

        if (player == 1){
          position = getPlayer1().choice(); //Integer.parseInt(console.readLine());
        }
        else
          position = getPlayer2().choice(); //getRandomNumber(1,7);
        System.out.println("");
      }
      return grid;
    }


    // Vérifie s'il y a égalité
    int equalityCheck(Grid grid){
      // int width = grid.width;
      int height = grid.height;
      int cpt = 0;
      for (int i = 0; i<height; i++){
        if (grid.values[0][i] != 0)
          cpt++;
      }
      if (cpt == 7)
        return 1;

      return 0;
    }

    // Vérifie si le dernier coup permet la victoire ou non
    int victoryCheck(Grid grid, int lastColumn){ // i -> width, j -> height
        int width = grid.width;
        int height = grid.height;
        int lastLine = 0; // Recherche de la coordonné i du dernier coup
        while(grid.values[lastLine][lastColumn] == 0){
            lastLine++;
        }

        int vertical[] = new int[width];
        int horizontal[] = new int[height];
        int lenDiag = 1;
        int lenAntiDiag = 1;
        ArrayList<Integer> diagonalTmp = new ArrayList<Integer>();
        ArrayList<Integer> antidiagonalTmp = new ArrayList<Integer>();

        diagonalTmp.add(grid.values[lastLine][lastColumn]); // On ajoute la position jouée
        antidiagonalTmp.add(grid.values[lastLine][lastColumn]); // idem

        // On remplit diagonale
        int tmpLine = lastLine; int tmpColumn = lastColumn;
        while((tmpLine-- > 0) && (tmpColumn++ < height-1)){ // Je regarde si la case suivante est possible
            diagonalTmp.add(grid.values[tmpLine][tmpColumn]);
            lenDiag++;
        }
        tmpLine = lastLine; tmpColumn = lastColumn; // On les réinitialise
        while((tmpLine++ < width-1) && (tmpColumn-- > 0)){
            diagonalTmp.add(0, grid.values[tmpLine][tmpColumn]);
            lenDiag++;
        }

        // On remplit l'anti diagonale
        tmpLine = lastLine; tmpColumn = lastColumn; // On les réinitialise
        while((tmpLine++ < width-1) && (tmpColumn++ < height-1)){
            antidiagonalTmp.add(grid.values[tmpLine][tmpColumn]);
            lenAntiDiag++;
        }
        tmpLine = lastLine; tmpColumn = lastColumn; // On les réinitialise
        while((tmpLine-- > 0) && (tmpColumn-- > 0)){
            antidiagonalTmp.add(0, grid.values[tmpLine][tmpColumn]);
            lenAntiDiag++;
        }

        // On remplit vertical et horizontal
        for(int i = 0; i<width; i++){
            vertical[i] = grid.values[i][lastColumn];
        }
        for(int i = 0; i<height; i++){
            horizontal[i] = grid.values[lastLine][i];
        }

        // On remet diag et antiDiag sous forme de vraie liste
        int diagonal[] = new int[lenDiag];
        int antiDiagonal[] = new int[lenAntiDiag];
        for (int i = 0; i<lenDiag; i++){
            diagonal[i] = diagonalTmp.get(i);
        }
        for (int i = 0; i<lenAntiDiag; i++){
            antiDiagonal[i] = antidiagonalTmp.get(i);
        }

        // Vérifier ici les listes qu'on donnent à checker
//        printArray(vertical, width);
//        printArray(horizontal, height);
//        printArray(diagonal, lenDiag);
//        printArray(antiDiagonal, lenAntiDiag);

        // On vérifie chaque liste
        if(arrayCheck(vertical, width) == 1){
            return 1; // Victoire
        } else if(arrayCheck(horizontal, height) == 1){
            return 1; // Victoire
        } else if(arrayCheck(diagonal, lenDiag) == 1){
            return 1; // Victoire
        } else if(arrayCheck(antiDiagonal, lenAntiDiag) == 1){
            return 1; // Victoire
        }
        return 0; // Pas encore de victoire
    }

    void printArray(int[] array, int length){
        for (int i=0; i<length; i++){
            System.out.print(array[i]);
        }
        System.out.print("\n");
    }

    int arrayCheck(int[] array, int length){
        int count = 0;
        int max = length - 4;

        for(int i = 0; i<=max; i++){
            for(int j = 0; j<4; j++){
                count += array[i+j];
//                System.out.println("i : " + i + " j : " + j + " count : " + count);
            }
            if ((count == -4) || (count == 4)){
                return 1; // Victoire
            }
            count = 0; // On réinitialise count
        }
        return 0; // Pas encore de victoire
    }


    public static String checkBuffer(String buf){
      String[] parameter = {"0","1","2","3","4","5","6","7","8","9"};
      String[] res;

      // System.out.println(parameter+" de taille "+parameter.length());
      for (String i :parameter){
        // System.out.println(i);
        res = buf.split(i);
        if (res[0].length() != buf.length()){ // buffer contient autre chose que des digit
            System.out.println(res[0]);
            System.out.println(res[1]);
        }
      }
      return buf;
    }


    // Méthodes
    public void play(){
      int i = 1; // player 1 starts playing
      int position = 0;
      int win1 = 0, win2 = 0, equality = 0;

      interfacePackage.Display.displayGrid(getGrid().values);

      while(win1 == 0 && win2 == 0 && equality == 0){
        i = whichPlayer(i);

        if (i == 1) // si le joueur 1 doit jouer
         position = getPlayer1().choice();

        else // si le joueur 2 doit jouer
         position = getPlayer2().choice();

        if (position == -1){ // le joueur a tapé la commande "sortir"
          // break;
          System.exit(0);
        }

        System.out.println("");
        getGrid().values = updateGrid(getGrid().values, position, i);
        interfacePackage.Display.displayGrid(getGrid().values);

        if(victoryCheck(grid, position-1) == 1){ // -1 Car index en java commence à 0
          if(i == 1){
            writeBuffer("Joueur 1 gagne");
            setScore(0);
            win1 = 1;
          }
          else{
            writeBuffer("Joueur 2 gagne");
            setScore(1);
            win2 = 1;
          }
        }

        else if(equalityCheck(grid) == 1){
          equality = 1;
          writeBuffer("Egalite");
        }

       i++;
      }
    }


}

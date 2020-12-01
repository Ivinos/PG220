package game_package;

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
    public Game(String[] input_players, int width, int height, int[] score){
      if (input_players[0].equals("humain")){
        this.player1 = new Human(input_players[1]);
      }
      else{
        this.player1 = new Ia(input_players[1]);
      }

      if (input_players[2].equals("humain")){
        this.player2 = new Human(input_players[3]);
      }
      else{
        this.player2 = new Ia(input_players[3]);
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
        File file_to_create = new File(filename);

        file_to_create.delete();

        // if (file_to_create.createNewFile())
        //   System.out.println("\nFile "+filename+" created\n");
        //
        // else{
        //   System.out.println("\nFile "+filename+" already exists");
        //   System.exit(0);
        // }

        FileWriter file_to_write = new FileWriter(filename);
        file_to_write.write("Joueur 1 est "+player1.getName());
        file_to_write.write("\nJoueur 2 est "+player2.getName());
        file_to_write.close();
      }
      catch (Exception e){
        System.err.println(e);
      }
    }

    //Write in log.txt the move of the player
    public static void writeMove(int player, int position){
      try{
        String filename = "log.txt";
        FileWriter file_to_write = new FileWriter(filename, true);
        file_to_write.write("\nJoueur "+player+" joue "+position);
        file_to_write.close();
      }
      catch (Exception e){
        System.err.println(e);
      }
    }

    // //Write in log.txt the victory of the player
    // public static void writeVictory(String win){
    //   try{
    //     String filename = "log.txt";
    //     FileWriter file_to_write = new FileWriter(filename, true);
    //     if (win.equals("win1"))
    //       file_to_write.write("\nJoueur 1 gagne");
    //     else
    //       file_to_write.write("\nJoueur 2 gagne");
    //     file_to_write.close();
    //   }
    //   catch (Exception e){
    //     System.err.println(e);
    //   }
    // }

    //Write in log.txt the string
    public static void writeBuffer(String buffer){
      try{
        String filename = "log.txt";
        FileWriter file_to_write = new FileWriter(filename, true);

        file_to_write.write("\n"+buffer);
        file_to_write.close();
      }

      catch (Exception e){
        System.err.println(e);
      }
    }


    // Update the grid after a turn
    public int[][] updateGrid(int[][] grid, int position, int player){
      int valid_move = 0;

      player = whichPlayer(player);            // define which is player's turn (player 1 or player 2 ?)

      while (valid_move == 0){
        for(int k = 0; k<6; k++){
            if (grid[5-k][position-1] == 0){ // if cell [position][k] is empty
                if (player == 1)
                    grid[5-k][position-1] = -1;
                else
                    grid[5-k][position-1] = 1;

                // int abs = 5-k+1;
                int ord = position-1+1;
                valid_move = 1;
                System.out.println("Player "+player+" joue en position "+ord+"\n");
                writeMove(player, ord);
                return grid;
            }
        }
        writeBuffer("Erreur colonne pleine "+position);
        System.out.println("Error : column is full. Please choose another column");

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
    int victoryCheck(Grid grid, int last_j){ // i -> width, j -> height
        int width = grid.width;
        int height = grid.height;
        int last_i = 0; // Recherche de la coordonné i du dernier coup
        while(grid.values[last_i][last_j] == 0){
            last_i++;
        }

        int vertical[] = new int[width];
        int horizontal[] = new int[height];
        int len_diag = 1;
        int len_anti_diag = 1;
        ArrayList<Integer> diagonal_t = new ArrayList<Integer>();
        ArrayList<Integer> anti_diagonal_t = new ArrayList<Integer>();

        diagonal_t.add(grid.values[last_i][last_j]); // On ajoute la position jouée
        anti_diagonal_t.add(grid.values[last_i][last_j]); // idem

        // On remplit diagonale
        int tmp_i = last_i; int tmp_j = last_j;
        while((tmp_i-- > 0) && (tmp_j++ < height-1)){ // Je regarde si la case suivante est possible
            diagonal_t.add(grid.values[tmp_i][tmp_j]);
            len_diag++;
        }
        tmp_i = last_i; tmp_j = last_j; // On les réinitialise
        while((tmp_i++ < width-1) && (tmp_j-- > 0)){
            diagonal_t.add(0, grid.values[tmp_i][tmp_j]);
            len_diag++;
        }

        // On remplit l'anti diagonale
        tmp_i = last_i; tmp_j = last_j; // On les réinitialise
        while((tmp_i++ < width-1) && (tmp_j++ < height-1)){
            anti_diagonal_t.add(grid.values[tmp_i][tmp_j]);
            len_anti_diag++;
        }
        tmp_i = last_i; tmp_j = last_j; // On les réinitialise
        while((tmp_i-- > 0) && (tmp_j-- > 0)){
            anti_diagonal_t.add(0, grid.values[tmp_i][tmp_j]);
            len_anti_diag++;
        }

        // On remplit vertical et horizontal
        for(int i = 0; i<width; i++){
            vertical[i] = grid.values[i][last_j];
        }
        for(int i = 0; i<height; i++){
            horizontal[i] = grid.values[last_i][i];
        }

        // On remet diag et anti_diag sous forme de vraie liste
        int diagonal[] = new int[len_diag];
        int anti_diagonal[] = new int[len_anti_diag];
        for (int i = 0; i<len_diag; i++){
            diagonal[i] = diagonal_t.get(i);
        }
        for (int i = 0; i<len_anti_diag; i++){
            anti_diagonal[i] = anti_diagonal_t.get(i);
        }

        // Vérifier ici les listes qu'on donnent à checker
//        printArray(vertical, width);
//        printArray(horizontal, height);
//        printArray(diagonal, len_diag);
//        printArray(anti_diagonal, len_anti_diag);

        // On vérifie chaque liste
        if(arrayCheck(vertical, width) == 1){
            return 1; // Victoire
        } else if(arrayCheck(horizontal, height) == 1){
            return 1; // Victoire
        } else if(arrayCheck(diagonal, len_diag) == 1){
            return 1; // Victoire
        } else if(arrayCheck(anti_diagonal, len_anti_diag) == 1){
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
      
      interface_package.Display.displayGrid(getGrid().values);

      while(win1 == 0 && win2 == 0 && equality == 0){
        i = whichPlayer(i);

        if (i == 1) // si le joueur 1 doit jouer
         position = getPlayer1().choice();

        else // si le joueur 2 doit jouer
         position = getPlayer2().choice();

        if (position == -1) // le joueur a taper la commande "sortir"
          System.exit(0);

        System.out.println("");
        getGrid().values = updateGrid(getGrid().values, position, i);
        interface_package.Display.displayGrid(getGrid().values);

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

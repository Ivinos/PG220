package game_package;

import java.io.Console;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

import interface_package.*;

public class Game{

    // Attributs
    public Player player1;
    public Player player2;
    public Grid grid;

    // Constructeur
    public Game(String[] input_players, int width, int height){
        if (input_players[1] == "1"){
            this.player1 = new Human(input_players[0]);
        } else {
            this.player1 = new Ia(input_players[0]);
        }

        if (input_players[3] == "1"){
          this.player1 = new Human(input_players[2]);
        } else {
          this.player1 = new Ia(input_players[2]);
        }

        this.grid = new Grid(width, height);
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

    // Return which player plays
    public static int which_player(int i){
      int res = i%2;
      if (res == 0)
        return 2;
      else
        return 1;
    }

    // Return a random number
    public static int getRandomNumber(int min, int max) throws IllegalArgumentException {
      if (min > max)
        throw new IllegalArgumentException("Max must be greater than min");

      Random nb = new Random();
      return nb.nextInt((max-min)+1)+min;
    }

    // Update the grid after a turn
    public static int[][] update_grid(int[][] grid, int position, int player){
      int valid = 0;
      Console console = System.console();

      player = which_player(player);            // define which is player's turn (player 1 or player 2 ?)

      while (valid == 0){

          for(int k = 0; k<6; k++){
              if (grid[5-k][position-1] == 0){ // if cell [position][k] is empty
                  if (player == 1)
                      grid[5-k][position-1] = -1;
                  else
                      grid[5-k][position-1] = 1;

                  int abs = 5-k+1;
                  int ord = position-1+1;
                  valid = 1;
                  System.out.println("Player "+player+" joue en position ("+abs+","+ord+")\n");
                  return grid;
              }
          }

          System.out.println("Error : column is full. Please choose another column");
          position = Integer.parseInt(console.readLine());
          System.out.println("");
      }
      return grid;
    }

    // Vérifie si le dernier coup permet la victoire ou non
    int victory_check(Grid grid, int last_j){ // i -> width, j -> height
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

        diagonal_t.add(grid.values[last_i][last_j]);
        anti_diagonal_t.add(grid.values[last_i][last_j]);

        // On remplit diagonale
        int tmp_i = last_i; int tmp_j = last_j;
        diagonal_t.add(grid.values[tmp_i][tmp_j]); // On ajoute la position jouée
        while((tmp_i-- < 0) || (tmp_j++ > height-1)){ // Je regarde si la case suivante est possible
            diagonal_t.add(grid.values[tmp_i][tmp_j]);
            tmp_i--; tmp_j++;
            len_diag++;
        }
        tmp_i = last_i; tmp_j = last_j; // On les réinitialise
        while((tmp_i++ > width-1) ||(tmp_j-- < 0)){
            diagonal_t.add(0, grid.values[tmp_i][tmp_j]);
            tmp_i++; tmp_j--;
            len_diag++;
        }

        // On remplit l'anti diagonale
        tmp_i = last_i; tmp_j = last_j; // On les réinitialise
        diagonal_t.add(grid.values[tmp_i][tmp_j]); // On ajoute la position jouée
        while((tmp_i++ > width-1) ||(tmp_j++ > height-1)){
            diagonal_t.add(grid.values[tmp_i][tmp_j]);
            tmp_i++; tmp_j++;
            len_anti_diag++;
        }
        tmp_i = last_i; tmp_j = last_j; // On les réinitialise
        while((tmp_i-- < 0) ||(tmp_j-- < 0)){
            diagonal_t.add(0, grid.values[tmp_i][tmp_j]);
            tmp_i--; tmp_j--;
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

        // On vérifie chaque liste
        if(array_check(vertical, width) == 1){
            return 1; // Victoire
        } else if(array_check(horizontal, height) == 1){
            return 1; // Victoire
        } else if(array_check(diagonal, len_diag) == 1){
            return 1; // Victoire
        } else if(array_check(anti_diagonal, len_anti_diag) == 1){
            return 1; // Victoire
        }
        return 0; // Pas encore de victoire
    }

    int array_check(int[] array, int length){
        int count = 0;
        int max = length - 4;

        for(int i = 0; i<=max; i++){
            for(int j = 0; j<4; j++){
                count += array[i+j];
            }
            if ((count == -4) || (count == 4)){
                return 1; // Victoire
            }
        }
        return 0; // Pas encore de victoire
    }


    // Méthodes
    public void play(){
      int i = 1; // player 1 starts playing
      int position = 0;
      int win1 = 0, win2 = 0;
      String buffer;
      Console console = System.console();

        interface_package.Display.display_grid(getGrid().values);

      while(win1 != 1 || win2 != 1){
         i = which_player(i);

         if (i == 1){
           buffer = console.readLine();

           if (buffer.equals("sortir")){
             System.out.println("You quit the game");
             System.exit(0);
           }
           else{
             position = Integer.parseInt(buffer);
             while (position < 1 || position > 7){
               System.out.print("Wrong position. Please choose a number from 1 to 7 : ");
               // position = position.next();
               position = Integer.parseInt(console.readLine());  // rajouter des conditions de test
               System.out.println("");
             }
           }
         }
         else
           position = getRandomNumber(1,7);

         System.out.println("");
         getGrid().values = update_grid(getGrid().values, position, i);
         interface_package.Display.display_grid(getGrid().values);

         if(victory_check(grid, position-1) == 1){ // -1 Car index en java commence à 0
             if(i == 1){
                 win1 = 1;
             } else {
                 win2 = 1;
             }
         }
         i++;
        }
        if(win1 == 1){
            System.out.println("Player 1 won");
        } else {
            System.out.println("Player 2 won");
        }

    }
}

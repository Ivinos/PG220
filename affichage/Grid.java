import java.io.Console;
import java.util.Random;
import java.util.Scanner;

public class Grid {

    public static void set_grid(int[][] grid, int width, int height){

        // Grid initialization
        for (int i = 0; i<height; i++){
            for (int j = 0; j<width; j++){
              grid[i][j] = 0;
            }
          }
        }

      public static void display_grid(int[][] grid){
        String line_numero = "1 2 3 4 5 6 7";
        System.out.println(line_numero);

        for (int i = 0; i<6; i++){
          for (int j = 0; j<7; j++){
            if (grid[i][j] == 0)
              System.out.print(". ");
            else if (grid[i][j] == -1)     // player 1
              System.out.print("X ");
            else if (grid[i][j] == 1)     // player 2
              System.out.print("O ");
            else                          // error
              System.out.print("E ");
          }
          System.out.println("");
        }
        System.out.println("");
    }

    public static int which_player(int i){
      int res = i%2;
      if (res == 0)
        return 2;
      else
        return 1;
    }


    public static int getRandomNumber(int min, int max) throws IllegalArgumentException {
      if (min > max)
        throw new IllegalArgumentException("Max must be greater than min");

      Random nb = new Random();
      return nb.nextInt((max-min)+1)+min;
    }

    public static int[][] player_turn(int[][] grid, int position, int player){
      player = which_player(player);            // define which is player's turn (player 1 or player 2 ?)



      for(int k = 0; k<6; k++){
        if (grid[5-k][position-1] == 0){        // if cell [position][k] is empty
          if (player == 1)
            grid[5-k][position-1] = -1;
          else
            grid[5-k][position-1] = 1;
          int abs = 5-k+1;
          int ord = position-1+1;
          System.out.println("Player "+player+" joue en position ("+abs+","+ord+")\n");
          return grid;
        }
      }
      System.out.println("Error : column is full. Please choose another column");
      return grid;
    }


    public static void update_grid(int[][] grid, int position, int player){
      Console console = System.console();
      String line_numero = "1 2 3 4 5 6 7";

      // player turn
      grid = player_turn(grid, position, player);

      // grid display
      System.out.println(line_numero);
      for (int i = 0; i<6; i++){
        for (int j = 0; j<7; j++){
          if (grid[i][j] == 0)
            System.out.print(". ");
          else if (grid[i][j] == -1)     // player 1
            System.out.print("X ");
          else if (grid[i][j] == 1)     // player 2
            System.out.print("O ");
          else                          // error
            System.out.print("E ");
        }
        System.out.println("");
      }
      System.out.println("");
    }

    public static void main(String[] args) {
        Console console = System.console();

        String buffer;
        int i = 1; // player 1 starts playing
        int position;
        int win1 = 0, win2 = 0;
        int width = 6;
        int height = 7;
        int grid[][]  = new int[width][height]; // matrix 6x7

        set_grid(grid, height, width);
        display_grid(grid);

        while(win1 != 1 || win2 != 1){
          i = which_player(i);

          if (i == 1){
            buffer = console.readLine();

            // if (buffer.equals("sortir")){
            //   System.out.println("You quit the game");
            //   System.exit(0);
            // }
            // else{
              position = Integer.parseInt(buffer);
              while (position < 1 || position > 7){
                System.out.print("Wrong position. Please choose a number from 1 to 7 : ");
                // position = position.next();
                position = Integer.parseInt(console.readLine());  // rajouter des conditions de test
                System.out.println("");
              }
            // }
          }
          else
            position = getRandomNumber(1,7);

          System.out.println("");
          update_grid(grid, position, i);
          i++;
          }
    }
}

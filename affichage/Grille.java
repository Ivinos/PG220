import java.io.Console;

public class Grille {

    public static void set_grid(int[][] grid, int length, int height){

        // Grid initialization
        for (int i = 0; i<height; i++){
          for (int j = 0; j<length; j++){
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


    public static int[][] player_turn(int[][] grid, int position, int player){
      for(int k = 0; k<6; k++){
        if (grid[5-k][position-1] == 0){        // if cell [position][k] is empty
          if (player == 1)
            grid[5-k][position-1] = -1;
          else
            grid[5-k][position-1] = 1;
          int abs = 5-k+1;
          int ord = position-1+1;
          System.out.println("Player "+player+" joue en position "+abs+" "+ord);
          return grid;
        }
      }
      System.out.println("Error : column is full. Please choose another column");
      return grid;
    }


    public static void update_grid(int[][] grid, int position, int player){
      String line_numero = "1 2 3 4 5 6 7";
      System.out.println(line_numero);

      // player turn
      if (position < 1 || position > 7)
        System.out.println("Wrong position. Please choose a number from 1 to 7");
      else{
        grid = player_turn(grid, position, player);

        // grid display
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
    }

    public static void main(String[] args) {
        Console console = System.console();

        int position;
        int win1 = 0, win2 = 0;
        int length = 6;
        int height = 7;
        int grid[][]  = new int[length][height]; // matrix 6x7
        set_grid(grid, height, length);
        display_grid(grid);

        while(win1 != 1 || win2 != 0){
          position = Integer.parseInt(console.readLine());
          update_grid(grid, position, 1);
          position = Integer.parseInt(console.readLine());
          update_grid(grid, position, 2);
        }
    }

}

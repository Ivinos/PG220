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


    public static void update_grid(int[][] grid, int position){
      String line_numero = "1 2 3 4 5 6 7";
      System.out.println(line_numero);

      // player turn
      if (position < 1 || position > 7)
        System.out.println("Wrong position. Please choose a number from 1 to 7");
      else{
        grid[5][position-1] = -1;

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
        int win = 0;
        int length = 6;
        int height = 7;
        int grid[][]  = new int[length][height]; // matrix 6x7
        set_grid(grid, height, length);
        display_grid(grid);

        while(win != 1){
          position = Integer.parseInt(console.readLine());
          update_grid(grid, position);
        }
    }

}

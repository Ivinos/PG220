package interface_package;


import java.io.Console;
import game_package.*;

public class Display{

    public static String[] displayMenu(){
        Console console = System.console();
        String s = new String("Bienvenue au puissance 4 !\n");
        String s1 = new String("Joueur 1?");
        String s2 = new String("Joueur 2?");

        String[] buf1;
        String[] buf2;
        String name1 = "";
        String name2 = "";
        String type1 = "";
        String type2 = "";


        System.out.println(s);

        System.out.println(s1);
        buf1 = CheckInput.checkPlayers(console.readLine());
        type1 = CheckInput.checkType(buf1[0]);
        name1 = buf1[1];


        System.out.println(s2);
        buf2 = CheckInput.checkPlayers(console.readLine());
        type2 = CheckInput.checkType(buf2[0]);
        name2 = buf2[1];

        System.out.println("Joueur 1 est <"+name1+"> (type : '"+type1+"') et Joueur 2 est <"+name2+ "> (type : '"+type2+"')");

        // b = s.startsWith("Hello"); // Test si la chaîne commence par une chaîne bien particulière
        // System.out.println(b);

        // String[] res = {buf1[1], "1", buf2[1], "2"};
//        String[] res = {buf1[0], buf1[1], buf2[0], buf2[1]}; // type1 player1 type2 player2
        String[] res = {type1, name1, type2, name2}; // type1 player1 type2 player2

        return res;
    }

    public static void displayGrid(int[][] grid){
      String line_numero = "1 2 3 4 5 6 7";
      System.out.println(line_numero);

      for (int i = 0; i<6; i++){ //Grid.getWidth()
        for (int j = 0; j<7; j++){ //Grid.getHeight()
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

package game_package;

import java.io.Console;
import java.util.Random;
import java.util.Scanner;

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
          if (grid[5-k][position-1] == 0){        // if cell [position][k] is empty

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
         i++;
       }
    }
}

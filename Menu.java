import gamePackage.*;

public class Menu{

    public static void main(String[] args){
      int numberPlayers = 2;
      int width = 7;
      int height = 6;
      int rounds = 3;
      int[] score = {0,0};
      String[] inputPlayers;
      int[] parameters = new int[4];

      // Display menu with options
      parameters = interfacePackage.BonusMenu.parametersMenu(numberPlayers, width, height, rounds);
      numberPlayers = parameters[0];
      width = parameters[1];
      height = parameters[2];
      rounds = parameters[3];

      // Players choose a pseudo + type
      inputPlayers = gamePackage.Game.selectPlayers(numberPlayers);

      // Game initialization
      Game game = new Game(inputPlayers, numberPlayers, width, height, score, rounds);
      System.out.println("");

      // The game starts
      while(game.getScore(0) != rounds && game.getScore(1) != rounds){
        gamePackage.WriteInLog.writeBuffer("Manche commence");
        game.play();
        gamePackage.WriteInLog.writeBuffer("Score "+game.getScore(0)+" - "+game.getScore(1));
        gamePackage.Grid.resetGrid(game.getGrid());
      }

      System.out.println("Partie finie");
    }

}

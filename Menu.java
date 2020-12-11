import gamePackage.*;

public class Menu{

    public static void main(String[] args){
      int numberPlayers = 2;
      int width = 7;
      int height = 6;
      int rounds = 3;
      int token = 4;
      int[] score;
      String[] inputPlayers;
      int[] parameters = new int[5];

      // Display menu with options
      parameters = interfacePackage.BonusMenu.parametersMenu(numberPlayers, width, height, rounds, token);
      numberPlayers = parameters[0];
      width = parameters[1];
      height = parameters[2];
      rounds = parameters[3];
      token = parameters[4];
      score = new int[numberPlayers];

      // Players choose a pseudo + type
      inputPlayers = gamePackage.Game.selectPlayers(numberPlayers);

      // Game initialization
      Game game = new Game(inputPlayers, numberPlayers, width, height, score, rounds, token);
      System.out.println("");

      // The game starts
      while(gamePackage.Game.checkRounds(game) == 0){
        gamePackage.WriteInLog.writeBuffer("Manche commence");
        game.play();
        gamePackage.WriteInLog.writeBuffer(gamePackage.Game.writeScore(game));
        gamePackage.Grid.resetGrid(game.getGrid());
      }

      System.out.println("Partie finie");
    }

}

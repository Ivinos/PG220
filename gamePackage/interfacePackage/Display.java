package gamePackage.interfacePackage;

public class Display {

  // Affichage de la grille
  public static void displayGrid(gamePackage.Grid grid) {
    int width = grid.width;
    int height = grid.height;
    String[] symbols = {". ", "x ", "o ", "V ", "T ", "Y ", "@ "}; // à changer plus tard surement parce que pas très beau // REMETTRE X ET O ensuite
    String[] colors = {"NONE", "NONE", "NONE", "GREEN", "PURPLE", "YELLOW", "CYAN"}; // REMETTRE RED ET BLUE
    String lineNumero = new String("1");

    for (int k = 2; k < width + 1; k++)
      lineNumero = lineNumero + " " + k;

    System.out.println(lineNumero);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++)
        System.out.print(Color.setColor(symbols[grid.values[i][j]], colors[grid.values[i][j]], 0));
      System.out.println("");
    }
    //System.out.println("");
  }

  // Affichage des paramètres de la partie
  public static void gameParameters(gamePackage.Game game) {
    // String[] symbols = {".", "X", "O", "V", "T", "Y", "@"}; // à changer plus tard surement parce que pas très beau
    // String[] colors = {"\u001B[37m","\u001B[31m","\u001B[34m","\u001B[32m","\u001B[35m","\u001B[33m","\u001B[36m"};

    System.out.println(Color.setColor("\n[JEU]", "YELLOW", 1));
    System.out.println("Victoire : " + game.rounds + " manches à remporter");
    System.out.println("Manche : " + game.tokens + " jetons à aligner pour remporter 1 manche");
    System.out.println(gamePackage.Game.writeScore(game));

    System.out.println(Color.setColor("\n[GRILLE]", "YELLOW", 1));
    System.out.println("Hauteur : " + game.grid.height + " lignes");
    System.out.println("Largeur : " + game.grid.width + " colonnes");

    System.out.println(Color.setColor("\n[JOUEURS]", "YELLOW", 1));
    System.out.println("Nombre de joueurs : " + game.numberPlayers);
    // for (int i = 1; i<=game.numberPlayers; i++)
    // System.out.println("Joueur "+i+" est "+game.getPlayer(i).getName()+" de type "+game.getPlayer(i).getType()+" (symbole : "+Color.setColor(symbols[i],colors[i],0)+")");
    System.out.println("");
  }
}

package gamePackage.interfacePackage;

import gamePackage.Game;
import gamePackage.Grid;

public class Display {

  private static final String[] symbols = {". ", "X ", "O ", "V ", "T ", "Y ", "@ "}; // Symboles des joeurs (index 0 = personne)

  // Affichage de la grille
  public static void displayGrid(Grid grid) {
    int width = grid.getWidth();
    int height = grid.getHeight();

    String lineNumero = new String("1");

    for (int k = 2; k < width + 1; k++)
      lineNumero = lineNumero + " " + k;

    System.out.println(lineNumero);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++)
        System.out.print(Color.setColor(symbols[grid.getValues()[i][j]], grid.getValues()[i][j], 0));
      System.out.println("");
    }
    System.out.println("");
  }

  // Affichage des paramètres de la partie
  public static void gameParameters(Game game) {

    System.out.println(Color.setColor("\n[JEU]", Color.getYellow(), Color.getTextBold()));
    System.out.println("Victoire : Celui avec le plus de victoire sur les " + game.getRounds() + " manches");
    System.out.println("Manche : " + game.getTokens() + " jetons à aligner pour remporter 1 manche");
    System.out.println(game.writeScore());

    System.out.println(Color.setColor("\n[GRILLE]", Color.getYellow(), Color.getTextBold()));
    System.out.println("Hauteur : " + game.getHeight() + " lignes");
    System.out.println("Largeur : " + game.getWidth() + " colonnes");

    System.out.println(Color.setColor("\n[JOUEURS]", Color.getYellow(), Color.getTextBold()));
    System.out.println("Nombre de joueurs : " + game.getNumberPlayers());
    for (int i = 1; i<=game.getNumberPlayers(); i++)
      System.out.println("Joueur "+i+" est "+game.getPlayer(i).getName()+" de type "+game.getPlayer(i).getType()+" (symbole : "+Color.setColor(symbols[i],i,0)+")");
    System.out.println("");
  }
}

package gamePackage.interfacePackage;

import gamePackage.Game;
import gamePackage.Grid;

public class Display {

  // Affichage de la grille
  public static void displayGrid(Grid grid) {
    int width = grid.getWidth();
    int height = grid.getHeight();
    String[] symbols = {". ", "x ", "o "};
    String lineNumero = new String("1");

    for (int k = 2; k < width + 1; k++)
      lineNumero = lineNumero + " " + k;

    System.out.println(lineNumero);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++)
        System.out.print(symbols[grid.getValues()[i][j]]);
      System.out.println("");
    }
  }
}

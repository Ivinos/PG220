package interfacePackage;

public class Display{

  public static String setColor(String buffer, String color) {
    String reset = "\u001B[0m";
    return color + buffer + reset;
  }

  public static void displayGrid(gamePackage.Grid grid){
    int width = grid.width;
    int height = grid.height;
    String[] symbols = {". ", "X ", "O ", "V ", "T ", "Y ", "@ "}; // à changer plus tard surement parce que pas très beau
    String[] colors = {"\u001B[37m","\u001B[31m","\u001B[34m","\u001B[32m","\u001B[35m","\u001B[33m","\u001B[36m"};
    String lineNumero = new String("1");
    
    for (int k = 2; k<width+1; k++)
      lineNumero = lineNumero+" "+k;

    System.out.println(lineNumero);

    for (int i = 0; i<height; i++){
      for (int j = 0; j<width; j++)
        System.out.print(setColor(symbols[grid.values[i][j]],colors[grid.values[i][j]]));
      System.out.println("");
    }
    System.out.println("");
  }
}

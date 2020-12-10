package interfacePackage;

public class Display{

    public static void displayGrid(gamePackage.Grid grid){
      int width = grid.width;
      int height = grid.height;
      String[] symboles = {". ", "X ", "O ", "V ", "T ", "Y ", "W ", "@ "}; // à changer plus tard surement parce que pas très beau
      String lineNumero = new String("1");
      
      for (int k = 2; k<width+1; k++)
        lineNumero = lineNumero+" "+k;

      System.out.println(lineNumero);

      for (int i = 0; i<height; i++){
        for (int j = 0; j<width; j++){
          System.out.print(symboles[grid.values[i][j]]);

//          if (grid.values[i][j] == 0)
//            System.out.print(". ");
//          else if (grid.values[i][j] == -1)     // player 1
//            System.out.print(Color.setColor("X ", "\u001B[31m")); // RED
//          else if (grid.values[i][j] == 1)     // player 2
//            System.out.print(Color.setColor("O ", "\u001B[34m")); // BLUE
//          else                          // error
//            System.out.print("E ");
        }
        System.out.println("");
      }
      System.out.println("");
  }
}

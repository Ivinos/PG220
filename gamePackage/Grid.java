package gamePackage;

public class Grid {

  // Attributs
  public int width;
  public int height;
  public int[][] values;

  // Constructeurs
  public Grid(int width, int height){
    this.width = width;
    this.height = height;
    this.values = initializeGrid(width, height);
  }

  public static int[][] initializeGrid(int width, int height){
    int grid[][]  = new int[height][width]; // matrix 6x7
    for (int i = 0; i<height; i++){
      for (int j = 0; j<width; j++){
        grid[i][j] = 0;
      }
    }
    return grid;
  }

  public int getWidth(){
    return this.width;
  }

  public int getHeight(){
    return this.height;
  }

  public int[][] getValues(){
    return this.values;
  }

  public static void resetGrid(Grid grid){
    int width = grid.width;
    int height = grid.height;
    for (int i = 0; i<height; i++){
      for (int j = 0; j<width; j++){
        grid.values[i][j] = 0;
      }
    }
  }

}

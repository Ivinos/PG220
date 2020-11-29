package game_package;

import java.io.Console;
import java.util.Random;
import java.util.Scanner;

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
    int grid[][]  = new int[width][height]; // matrix 6x7
    for (int i = 0; i<width; i++){
      for (int j = 0; j<height; j++){
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

  public static void resetGrid(int[][] grid){
    int width = 6;
    int height = 7;
    for (int i = 0; i<width; i++){
      for (int j = 0; j<height; j++){
        grid[i][j] = 0;
      }
    }
  }

}

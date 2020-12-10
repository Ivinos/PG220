package gamePackage;

import java.util.Random;

public class Monkey extends Player{

  public Monkey(String name,int numeroPlayer){
    super(name,numeroPlayer);
  }

  public static int isPlayable(int [][] grid,int x,int y){
    if (grid[x][y]!=0) {
      return 1;
    }else{
      return 0;
    }
  }

  public static int possibleWin(int[][] grid){
    int width=5;
    int height=5;
    for (int i=0;i<width;i++) {
      for (int j=0;j<height;j++) {
        //Check vers le haut
          if (j>3){
            if ((grid[i][j]==grid[i][j-1])&&(grid[i][j]==grid[i][j-2])) {
              if (grid[i][j-2]!=0) {
                return i;
              }
            }
          }
        // //Check vers le bas
        //   if (j<height-3){
        //     if ((grid[i][j]==grid[i][j+1])&&(grid[i][j]==grid[i][j+2])){
        //       if()
        //       return i;
        //     }
        //   }
        //Check vers la gauche
          if (i>3){
            if ((grid[i][j]==grid[i-1][j])&&(grid[i][j]==grid[i-2][j])){
              if((j==height)&&(grid[i-3][j]==0)){
                return i-3;
              }else{
                if ((grid[i-3][j]==0)&&(grid[i-3][j+1]!=0)){
                  return i-3;
                }
              }
            }
          }
        //Check vers la droite
          if (i>width-3){
            if((grid[i][j]==grid[i+1][j])&&(grid[i][j]==grid[i+2][j])){
              if((j==height)&&(grid[i+3][j]==0)){
                return i+3;
              }else{
                if ((grid[i+3][j]==0)&&(grid[i+3][j+1]!=0)){
                  return i+3;
                }
              }
            }
          }
        //Check vers le haut-gauche
          if ((j>3)&&(i>3)){
            if ((grid[i][j]==grid[i-1][j-1])&&(grid[i][j]==grid[i-2][j-2])){
              if ((grid[i-3][j-3]==0)&&(grid[i-3][j-2]!=0)){
                return i-3;
              }
            }
          }
        //Check vers le haut-droite
          if((j>3)&&(i<width-3)){
            if ((grid[i][j]==grid[i+1][j-1])&&(grid[i][j]==grid[i+2][j-2])){
              if ((grid[i+3][j-3]==0)&&(grid[i+3][j-2]!=0)){
                return i+3;
              }
            }
          }
        //Check vers le bas-droite
          if ((j<height-3)&&(i<width-3)){
            if ((grid[i][j]==grid[i+1][j+1])&&(grid[i][j]==grid[i+2][j+2])){
              if ((grid[i-3][j+3]==0)&&(j+4==height)){
                return i-3;
              }else{
                if ((grid[i-3][j+3]==0)&&(grid[i-3][j+4]!=0)) {
                  return i-3;
                }
              }
            }
          }
        //Check vers le bas-gauche
          if ((j<height-3)&&(i>3)) {
            if ((grid[i][j]==grid[i-1][j+1])&&(grid[i][j]==grid[i-2][j+2])){
              if ((grid[i+3][j+3]==0)&&(j+4==height)){
                return i+3;
              }else{
                if ((grid[i+3][j+3]==0)&&(grid[i+3][j+4]!=0)) {
                  return i+3;
                }
              }
            }
          }
      }
    }

    return -1;
  }

  // Return a random number
  public static int getRandomNumber(int min, int max) throws IllegalArgumentException {
    if (min > max)
      throw new IllegalArgumentException("Max must be greater than min");

    Random nb = new Random();
    return nb.nextInt((max-min)+1)+min;
  }


  public int choice(int [][] grid){
    int position;
    position=possibleWin(grid);
    if (position==-1){
      position = getRandomNumber(1,7);
    }
    return position;
  }


}

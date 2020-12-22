package gamePackage.playerPackage;

import gamePackage.interfacePackage.WriteInLog;

 import java.util.Random;

public class Pro extends Player{

    public Pro(String name,int numeroPlayer, String type){
        super(name,numeroPlayer,type);
        this.write = new WriteInLog();
    }

    private static int possibleWin(int[][] grid, int width, int height){
      //Cette méthode est la même que celle de Monkey.java, voir Monkey.java pour les commentaires
     for (int j=0;j<height;j++) {
       for (int i=0;i<width;i++) {
           if (j>2){
             if ((grid[j][i]==grid[j-1][i])&&(grid[j][i]==grid[j-2][i])&&(grid[j][i]!=0)) {
               if (grid[j-2][i]!=0) {
                 if (grid[j-3][i]==0) {
                   return i;
                 }
               }
             }
           }
           if (i>3){
             if ((grid[j][i]==grid[j][i-1])&&(grid[j][i]==grid[j][i-2])&&(grid[j][i]!=0)){
               if((j==height-1)&&(grid[j][i-3]==0)){
                 return i-3;
               }
               if(j<height-1){
                 if ((grid[j][i-3]==0)&&(grid[j+1][i-3]!=0)){
                   return i-3;
                 }
               }
            }
          }

           if (i<width-3){
             if(((grid[j][i]==grid[j][i+1])&&(grid[j][i]==grid[j][i+2]))&&(grid[j][i]!=0)){
               if(j==height-1){
                 if(grid[j][i+3]==0){
                   return i+3;
                 }
               }
               if(j<height-1){
                 if ((grid[j][i+3]==0)&&(grid[j+1][i+3]!=0)){
                   return i+3;
                 }
               }
             }
           }
           if ((j>3)&&(i>3)){
             if ((grid[j][i]==grid[j-1][i-1])&&(grid[j][i]==grid[j-2][i-2])&&(grid[j][i]!=0)){
               if ((grid[j-3][i-3]==0)&&(grid[j-2][i-3]!=0)){
                 return i-3;
               }
             }
           }
           if((j>3)&&(i<width-3)){
             if ((grid[j][i]==grid[j-1][i+1])&&(grid[j][i]==grid[j-2][i+2])&&(grid[j][i]!=0)){
               if ((grid[j-3][i+3]==0)&&(grid[j-2][i+3]!=0)){
                 return i+3;
               }
             }
           }
           if ((j<height-3)&&(i<width-3)){
             if ((grid[j][i]==grid[j+1][i+1])&&(grid[j][i]==grid[j+2][i+2])&&(grid[j][i]!=0)){
               if ((grid[j+3][i+3]==0)&&(j+3==height-1)){
                 return i+3;
               }if(j+3<height-1){
                 if ((grid[j+3][i+3]==0)&&(grid[j+4][i+3]!=0)) {
                   return i+3;
                 }
               }
             }
           }
           if ((j<height-3)&&(i>3)) {
             if ((grid[j][i]==grid[j+1][i-1])&&(grid[j][i]==grid[j+2][i-2])&&(grid[j][i]!=0)){
               if ((grid[j+3][i-3]==0)&&(j+3==height-1)){
                 return i-3;
               }if(j+3<height-1){
                 if ((grid[j+3][i-3]==0)&&(grid[j+4][i-3]!=0)) {
                   return i-3;
                 }
               }
             }
           }
       }
     }
     return -1;
    }


    // private static int goodMove(int [][]grid, int width, int height){
    //  for (int i=0;i<width;i++) {
    //    for (int j=0;j<height;j++) {
    //      //Check vers le haut
    //        if (j>2) {if (grid[i][j]==grid[i][j-1]){
    //            return i;}}
    //      //Check vers le bas
    //        if (j<height-2) {if ((grid[i][j]==grid[i][j+1])&&(grid[i][j]==grid[i][j+2])) {
    //            return i;}}
    //      //Check vers la gauche
    //        if (i>2) {if (grid[i][j]==grid[i-1][j]){
    //            return i-2;}}
    //      //Check vers la droite
    //        if (i>width-2) {if (grid[i][j]==grid[i+1][j]) {
    //            return i+2;}}
    //      //Check vers le haut-gauche
    //        if ((j>2)&&(i>2)) {if (grid[i][j]==grid[i-1][j-1]){
    //            return i-2;}}
    //      //Check vers le haut-droite
    //        if ((j>2)&&(i<width-2)) {if (grid[i][j]==grid[i+1][j-1]) {
    //            return i+2;}}
    //      //Check vers le bas-droite
    //        if ((j<height-2)&&(i<width-2)) {if (grid[i][j]==grid[i+1][j+1]){
    //            return i+2;}}
    //      //Check vers le bas-gauche
    //        if ((j<height-2)&&(i>2)) {if (grid[i][j]==grid[i-1][j+1]){
    //            return i-2;}}
    //    }
    //  }
    //  return 1;
    // }

    // Return a random number
    private static int getRandomNumber(int min, int max) throws IllegalArgumentException {
     if (min > max)
       throw new IllegalArgumentException("Max must be greater than min");

     Random nb = new Random();
     return nb.nextInt((max-min)+1)+min;
    }


    public int choice(int[][] grid, int width, int height, int tokens){
     int position;
     position=possibleWin(grid, width, height);
     if (position>-1) {
       while(grid[0][position]!=0) {
           position = (getRandomNumber(0,width-1)+getRandomNumber(0,width-1))/2;
       }
     }
     if(position==-1){
       //Si l'IA  ne trouve pas d'alignement de trois jetons, elle joue aléatoirement selon une gaussienne centrée au mileu du tableau
       position = (getRandomNumber(0,width-1)+getRandomNumber(0,width-1))/2;
        while(grid[0][position]!=0) {
         position = (getRandomNumber(0,width-1)+getRandomNumber(0,width-1))/2;
        }
     }
     return position+1;
    }


}

package gamePackage;

 import java.util.Random;

public class Pro extends Player{

    public Pro(String name,int numeroPlayer){
        super(name,numeroPlayer);
    }

    public static int possibleWin(int[][] grid, int width, int height){
     for (int i=0;i<width;i++) {
       for (int j=0;j<height;j++) {
         //Check vers le haut
           if (j>3) {if ((grid[i][j]==grid[i][j-1])&&(grid[i][j]==grid[i][j-2])) {
               return i;}}
         //Check vers le bas
           if (j<height-3) {if ((grid[i][j]==grid[i][j+1])&&(grid[i][j]==grid[i][j+2])) {
               return i;}}
         //Check vers la gauche
           if (i>3) {if ((grid[i][j]==grid[i-1][j])&&(grid[i][j]==grid[i-2][j])) {
               return i-3;}}
         //Check vers la droite
           if (i>width-3) {if ((grid[i][j]==grid[i+1][j])&&(grid[i][j]==grid[i+2][j])) {
               return i+3;}}
         //Check vers le haut-gauche
           if ((j>3)&&(i>3)) {if ((grid[i][j]==grid[i-1][j-1])&&(grid[i][j]==grid[i-2][j-2])) {
               return i-3;}}
         //Check vers le haut-droite
           if ((j>3)&&(i<width-3)) {if ((grid[i][j]==grid[i+1][j-1])&&(grid[i][j]==grid[i+2][j-2])) {
               return i+3;}}
         //Check vers le bas-droite
           if ((j<height-3)&&(i<width-3)) {if ((grid[i][j]==grid[i+1][j+1])&&(grid[i][j]==grid[i+2][j+2])){
               return i+3;}}
         //Check vers le bas-gauche
           if ((j<height-3)&&(i>3)) {if ((grid[i][j]==grid[i-1][j+1])&&(grid[i][j]==grid[i-2][j+2])) {
               return i-3;}}
       }
     }

     return -1;
    }

    public static int goodMove(int [][]grid, int width, int height){
     for (int i=0;i<width;i++) {
       for (int j=0;j<height;j++) {
         //Check vers le haut
           if (j>2) {if (grid[i][j]==grid[i][j-1]){
               return i;}}
         //Check vers le bas
           if (j<height-2) {if ((grid[i][j]==grid[i][j+1])&&(grid[i][j]==grid[i][j+2])) {
               return i;}}
         //Check vers la gauche
           if (i>2) {if (grid[i][j]==grid[i-1][j]){
               return i-2;}}
         //Check vers la droite
           if (i>width-2) {if (grid[i][j]==grid[i+1][j]) {
               return i+2;}}
         //Check vers le haut-gauche
           if ((j>2)&&(i>2)) {if (grid[i][j]==grid[i-1][j-1]){
               return i-2;}}
         //Check vers le haut-droite
           if ((j>2)&&(i<width-2)) {if (grid[i][j]==grid[i+1][j-1]) {
               return i+2;}}
         //Check vers le bas-droite
           if ((j<height-2)&&(i<width-2)) {if (grid[i][j]==grid[i+1][j+1]){
               return i+2;}}
         //Check vers le bas-gauche
           if ((j<height-2)&&(i>2)) {if (grid[i][j]==grid[i-1][j+1]){
               return i-2;}}
       }
     }
     return 1;
    }

    // Return a random number
    public static int getRandomNumber(int min, int max) throws IllegalArgumentException {
     if (min > max)
       throw new IllegalArgumentException("Max must be greater than min");

     Random nb = new Random();
     return nb.nextInt((max-min)+1)+min;
    }


    public int choice(int [][] grid, int width, int height){
     int position;
     position=possibleWin(grid, width, height);
     if (position!=-1){
       return position;
     }
     position = goodMove(grid, width, height);
     if (position!=-1){
       return position;
     }
     position = getRandomNumber(1,width);
     return position;
    }


}

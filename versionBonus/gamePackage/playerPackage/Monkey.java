package gamePackage.playerPackage;

import gamePackage.interfacePackage.WriteInLog;

import java.util.Random;

public class Monkey extends Player{

    public Monkey(String name,int numeroPlayer, String type){
         super(name,numeroPlayer,type);
         this.write = new WriteInLog();
    }

    private static int isPlayable(int [][] grid,int x,int y){
      //Fonction pour vérifier la jouabilité des coups, mais qui n'a au final pas servi
     if (grid[x][y]!=0) {
       return 1;
     }else{
       return 0;
     }
    }

    private static int possibleWin(int[][] grid, int width, int height, int tokens){
      //FOnction pour chercher les alignements de trois jetons
     for (int j=0;j<height;j++) {
       for (int i=0;i<width;i++) {
         //alignement vers le haut
           if (j>2){
             if ((grid[j][i]==grid[j-1][i])&&(grid[j][i]==grid[j-2][i])&&(grid[j][i]!=0)) {
               if (grid[j-2][i]!=0) {
                 if (grid[j-3][i]==0) {
                   return i;
                 }
               }
             }
           }
         //Alignement vers la gauche
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

         //Alignement vers la droite
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
         //ALignement vers le haut et la gauche
           if ((j>3)&&(i>3)){
             if ((grid[j][i]==grid[j-1][i-1])&&(grid[j][i]==grid[j-2][i-2])&&(grid[j][i]!=0)){
               if ((grid[j-3][i-3]==0)&&(grid[j-2][i-3]!=0)){
                 return i-3;
               }
             }
           }
         //Alignement vers le haut et la droite
           if((j>3)&&(i<width-3)){
             if ((grid[j][i]==grid[j-1][i+1])&&(grid[j][i]==grid[j-2][i+2])&&(grid[j][i]!=0)){
               if ((grid[j-3][i+3]==0)&&(grid[j-2][i+3]!=0)){
                 return i+3;
               }
             }
           }
         //ALignement vers le bas et la droite
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
         //Alignement vers le bas et la gauche
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

    // Return a random number
    private static int getRandomNumber(int min, int max) throws IllegalArgumentException {
     if (min > max)
       throw new IllegalArgumentException("Max must be greater than min");

     Random nb = new Random();
     return nb.nextInt((max-min)+1)+min;
    }


    public int choice(int[][] grid, int width, int height, int tokens){
     int position;
     position = possibleWin(grid, width, height, tokens);
     if (position>-1) {
       //Si la position calculée est injouable, alors le choix devient aléatoire jusqu'à ce qu'il soit jouable
       while(grid[0][position]!=0) {
           position = getRandomNumber(0,width);
       }
     }
     if(position==-1){
       //Si l'IA ne trouve pas d'alignement de trois jetons, elle joue aléatoirement de manière uniforme
       position = getRandomNumber(0,width-1);
        while(grid[0][position]!=0) {
         position = getRandomNumber(0,width-1);
        }
      }
     return position+1;
    }


}

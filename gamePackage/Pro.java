package gamePackage;

 import java.util.Random;

public class Pro extends Player{

    public Pro(String name,int numeroPlayer){
        super(name,numeroPlayer);
        this.type = "pro";
    }

    public static int possibleWin(int[][] grid, int width, int height){
     for (int j=0;j<height;j++) {
       for (int i=0;i<width;i++) {
         //Check vers le haut
           if (j>2){
             if ((grid[j][i]==grid[j-1][i])&&(grid[j][i]==grid[j-2][i])&&(grid[j][i]!=0)) {
               if (grid[j-2][i]!=0) {

                 if (grid[j-3][i]==0) {
                  // System.out.print("Victoire possible haut");
                   return i;
                 }
               }
             }
           }
         //Check vers la gauche
           if (i>3){
             if ((grid[j][i]==grid[j][i-1])&&(grid[j][i]==grid[j][i-2])&&(grid[j][i]!=0)){
               if((j==height-1)&&(grid[j][i-3]==0)){
                //  System.out.print("Victoire possible gauche");
                 return i-3;
               }
               if(j<height-1){
                 if ((grid[j][i-3]==0)&&(grid[j+1][i-3]!=0)){

                   return i-3;
                 }
               }
            }
          }

         //Check vers la droite
           if (i<width-3){
             if(((grid[j][i]==grid[j][i+1])&&(grid[j][i]==grid[j][i+2]))&&(grid[j][i]!=0)){
               if(j==height-1){
                // System.out.print(i);
                // System.out.print("Voilà\n");
                // System.out.print(j);
                // System.out.print("Voilà\n");
                 if(grid[j][i+3]==0){
                   return i+3;
                 }
               }
               if(j<height-1){
                 if ((grid[j][i+3]==0)&&(grid[j+1][i+3]!=0)){
                  //  System.out.print("Victoire possible droite");
                   return i+3;
                 }
               }
             }
           }
         // //Check vers le haut-gauche
           if ((j>3)&&(i>3)){
             if ((grid[j][i]==grid[j-1][i-1])&&(grid[j][i]==grid[j-2][i-2])&&(grid[j][i]!=0)){
               if ((grid[j-3][i-3]==0)&&(grid[j-2][i-3]!=0)){
                //  System.out.print("Victoire possible haut gauche");
                 return i-3;
               }
             }
           }
         //Check vers le haut-droite
           if((j>3)&&(i<width-3)){
             if ((grid[j][i]==grid[j-1][i+1])&&(grid[j][i]==grid[j-2][i+2])&&(grid[j][i]!=0)){
               if ((grid[j-3][i+3]==0)&&(grid[j-2][i+3]!=0)){
                //  System.out.print("Victoire possible haut droite");
                 return i+3;
               }
             }
           }
         //Check vers le bas-droite
           if ((j<height-3)&&(i<width-3)){
             if ((grid[j][i]==grid[j+1][i+1])&&(grid[j][i]==grid[j+2][i+2])&&(grid[j][i]!=0)){
               if ((grid[j+3][i+3]==0)&&(j+3==height-1)){
                //  System.out.print("Victoire possible bas droite");
                 return i+3;
               }if(j+3<height-1){
                 if ((grid[j+3][i+3]==0)&&(grid[j+4][i+3]!=0)) {
                  //  System.out.print("Victoire possible bas droite");
                   return i+3;
                 }
               }
             }
           }
         //Check vers le bas-gauche
           if ((j<height-3)&&(i>3)) {
             if ((grid[j][i]==grid[j+1][i-1])&&(grid[j][i]==grid[j+2][i-2])&&(grid[j][i]!=0)){
               if ((grid[j+3][i-3]==0)&&(j+3==height-1)){
                //  System.out.print("Victoire possible bas gauche");
                 return i-3;
               }if(j+3<height-1){
                 if ((grid[j+3][i-3]==0)&&(grid[j+4][i-3]!=0)) {
                  //  System.out.print("Victoire possible bas gauche");
                   return i-3;
                 }
               }
             }
           }
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


    public int choice(int[][] grid, int width, int height, int tokens){
     int position;
     position=possibleWin(grid, width, height);
    //  System.out.print("\nPosition calculée: ");
    //  System.out.print(position);
    //  System.out.print("\n");
     if (position>-1) {
       while(grid[0][position]!=0) {
           position = (getRandomNumber(0,width-1)+getRandomNumber(0,width-1))/2;
          //  System.out.print("Random1\n");
       }
     }
     if(position==-1){
       position = (getRandomNumber(0,width-1)+getRandomNumber(0,width-1))/2;
        while(grid[0][position]!=0) {
         position = (getRandomNumber(0,width-1)+getRandomNumber(0,width-1))/2;
        }
      // System.out.print("Random2\n");
     }
    //  System.out.print("\nPosition envbyée: ");
    //  System.out.print(position);
    //  System.out.print("\n");
     return position+1;
    }


}

package gamePackage;

import java.util.ArrayList; // Pour victoryCheck

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
    int grid[][]  = new int[height][width];
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

  // Update the grid after a move
  public int updateGrid(int position, int numeroPlayer){
    int width = this.width;
    int height = this.height;

    for(int k = 0; k<height; k++){
      if (this.values[height-1-k][position-1] == 0){ // if cell is empty

        this.values[height-1-k][position-1] = numeroPlayer;

        // System.out.println("Joueur "+numeroPlayer+" joue en position "+position); // On peut le rajouter pour plus de style
        WriteInLog.writeBuffer("Joueur "+numeroPlayer+" joue "+position);

        return position;
      }
    }

    WriteInLog.writeBuffer("Erreur colonne pleine "+position);
    System.out.print("Erreur : colonne "+position+" pleine. Choisis un nombre entre 1 et "+width+" : ");

    position = -3; // Move invalide, on doit recommencer

    return position;
  }



  // Check if there is equality
  int equalityCheck(){
    int width = this.width;
    int cpt = 0;

    for (int i = 0; i<width; i++){
      if (this.values[0][i] != 0)
        cpt++;
    }
    if (cpt == width)
      return 1;

    return 0;
  }


  // Check if the last move make a win
  int victoryCheck(int lastColumn){
    int width = this.width;
    int height = this.height;
    int lastLine = 0; // Recherche de la coordonné i du dernier coup

    while(this.values[lastLine][lastColumn] == 0)
      lastLine++;

    int vertical[] = new int[height];
    int horizontal[] = new int[width];
    int lenDiag = 1;
    int lenAntiDiag = 1;
    ArrayList<Integer> diagonalTmp = new ArrayList<Integer>();
    ArrayList<Integer> antidiagonalTmp = new ArrayList<Integer>();

    diagonalTmp.add(this.values[lastLine][lastColumn]); // the move has been added
    antidiagonalTmp.add(this.values[lastLine][lastColumn]); // idem

    // Filling diagonal
    int tmpLine = lastLine; int tmpColumn = lastColumn;
    while((tmpLine-- > 0) && (tmpColumn++ < width-1)){ // check if the next cell is possible
      diagonalTmp.add(this.values[tmpLine][tmpColumn]);
      lenDiag++;
    }
    tmpLine = lastLine; tmpColumn = lastColumn; // reinitialization
    while((tmpLine++ < height-1) && (tmpColumn-- > 0)){
      diagonalTmp.add(0, this.values[tmpLine][tmpColumn]);
      lenDiag++;
    }

    // Filling antidiagonal
    tmpLine = lastLine; tmpColumn = lastColumn; // reinitialization
    while((tmpLine++ < height-1) && (tmpColumn++ < width-1)){
      antidiagonalTmp.add(this.values[tmpLine][tmpColumn]);
      lenAntiDiag++;
    }
    tmpLine = lastLine; tmpColumn = lastColumn; // reinitialization
    while((tmpLine-- > 0) && (tmpColumn-- > 0)){
      antidiagonalTmp.add(0, this.values[tmpLine][tmpColumn]);
      lenAntiDiag++;
    }

    // Filling vertically and horizontally
    for(int i = 0; i<height; i++)
      vertical[i] = this.values[i][lastColumn];
    
    for(int i = 0; i<width; i++)
      horizontal[i] = this.values[lastLine][i];
    
    // diag and antidiag are now transformed into a list
    int diagonal[] = new int[lenDiag];
    int antiDiagonal[] = new int[lenAntiDiag];

    for (int i = 0; i<lenDiag; i++)
      diagonal[i] = diagonalTmp.get(i);
    
    for (int i = 0; i<lenAntiDiag; i++)
      antiDiagonal[i] = antidiagonalTmp.get(i);
    
    // check of each list
    if(arrayCheck(vertical, height) == 1)
      return 1; // victory
    else if(arrayCheck(horizontal, width) == 1)
      return 1; // victory
    else if(arrayCheck(diagonal, lenDiag) == 1)
      return 1; // victory
    else if(arrayCheck(antiDiagonal, lenAntiDiag) == 1)
      return 1; // victory
    
    return 0; // No victory
  }

  // Print an array DEBUGING
  void printArray(int[] array, int length){
    for (int i=0; i<length; i++)
      System.out.print(array[i]);
    
    System.out.print("\n");
  }

  // Check if there is a victory in an array
  int arrayCheck(int[] array, int length){ // Pour le moment le nombre de jeton n'est pas dynamique zebi
    //int count = 0;
    int max = length - 4;
    int vals [] = {0,0,0,0};

    for(int i = 0; i <= max; i++){
      for(int j = 0; j<4; j++){
        //count += array[i+j];
        vals[j] = array[i+j];

        if ((vals[0] != 0) && (vals[0] == vals[1]) && (vals[1] == vals[2]) && (vals[2] == vals[3]))
          return 1; // victory
//        if ((count == -4) || (count == 4))
//          return 1; // victory
      }
      //count = 0;
      vals[0] = 0; vals[1] = 0; vals[2] = 0; vals[3] = 0;
    }
    return 0; // no victory
  }

}

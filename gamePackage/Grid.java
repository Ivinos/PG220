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

  // Mise à jour de la grille après qu'un joueur ait joué
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
    WriteInLog.writeBuffer("Joueur "+numeroPlayer+" joue "+position);
    // WriteInLog.writeBuffer("Erreur colonne pleine "+position);
    System.out.println("Erreur colonne pleine "+position);
    // System.out.print("Erreur : colonne "+position+" pleine. Choisir un nombre entre 1 et "+width+" : ");

    position = -3; // Move invalide, on doit recommencer

    return position;
  }



  // Test s'il y a égalité
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


  // Test s'il y a victoire 
  int victoryCheck(int tokens, int lastColumn){
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

    diagonalTmp.add(this.values[lastLine][lastColumn]); // le move a été ajouté
    antidiagonalTmp.add(this.values[lastLine][lastColumn]); // idem

    // Remplissage de la diagonale
    int tmpLine = lastLine; int tmpColumn = lastColumn;
    while((tmpLine-- > 0) && (tmpColumn++ < width-1)){ // test si la cellule est possible
      diagonalTmp.add(this.values[tmpLine][tmpColumn]);
      lenDiag++;
    }
    tmpLine = lastLine; tmpColumn = lastColumn; // reinitialisation
    while((tmpLine++ < height-1) && (tmpColumn-- > 0)){
      diagonalTmp.add(0, this.values[tmpLine][tmpColumn]);
      lenDiag++;
    }

    // Remplissage de l'antidiagonale
    tmpLine = lastLine; tmpColumn = lastColumn; // reinitialisation
    while((tmpLine++ < height-1) && (tmpColumn++ < width-1)){
      antidiagonalTmp.add(this.values[tmpLine][tmpColumn]);
      lenAntiDiag++;
    }
    tmpLine = lastLine; tmpColumn = lastColumn; // reinitialisation
    while((tmpLine-- > 0) && (tmpColumn-- > 0)){
      antidiagonalTmp.add(0, this.values[tmpLine][tmpColumn]);
      lenAntiDiag++;
    }

    // Remplissage de l'horizontale + la verticale
    for(int i = 0; i<height; i++)
      vertical[i] = this.values[i][lastColumn];
    
    for(int i = 0; i<width; i++)
      horizontal[i] = this.values[lastLine][i];
    
    // Diagonale et Antidiagonale sont désormais des listes
    int diagonal[] = new int[lenDiag];
    int antiDiagonal[] = new int[lenAntiDiag];

    for (int i = 0; i<lenDiag; i++)
      diagonal[i] = diagonalTmp.get(i);
    
    for (int i = 0; i<lenAntiDiag; i++)
      antiDiagonal[i] = antidiagonalTmp.get(i);
    
    // Recherche d'une victoire dans chaque liste 
    if(arrayCheck(tokens, vertical, height) == 1)
      return 1; // victoire
    else if(arrayCheck(tokens, horizontal, width) == 1)
      return 1; // victoire
    else if(arrayCheck(tokens, diagonal, lenDiag) == 1)
      return 1; // victoire
    else if(arrayCheck(tokens, antiDiagonal, lenAntiDiag) == 1)
      return 1; // victoire
    
    return 0; // pas de victoire
  }

  // Affichage d'un tableau (DEBUGING)
  void printArray(int[] array, int length){
    for (int i=0; i<length; i++)
      System.out.print(array[i]);
    
    System.out.print("\n");
  }

  // Test s'il y a une victoire dans le tableau
  int arrayCheck(int tokens, int[] array, int length){
    int max = length - tokens;
    int[] vals  = new int[tokens];
    int cpt = 1;

    for(int i = 0; i <= max; i++){
      for(int j = 0; j<tokens; j++){
        vals[j] = array[i+j];
      }
      if (vals[0] != 0)
        for (int k = 1; k<tokens; k++){
          if (vals[0] == vals[k])
            cpt += 1;
        }
        if (cpt == tokens)
          return 1;
      
      vals = new int[tokens];
      cpt = 1;
    }
    return 0; // pas de victoire
  }

}

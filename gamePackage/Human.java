package gamePackage;

import java.util.Scanner;

public class Human extends Player{

  public Human(String name, int numeroPlayer){
    super(name,numeroPlayer);
    this.type = "humain";
  }

  // Actions en fonction des actions du joueur
  public static int playerReadLine(int width, int height){
    Scanner scanner = new Scanner(System.in);
    String buffer;
    int position = 0;
    int validBuffer = 0;

    while (validBuffer == 0){
      buffer = scanner.nextLine();

      if (buffer.equals("sortir")){
        WriteInLog.writeBuffer(buffer);
        return -1;
      }
      else if (buffer.equals("parametres")){
        return -2;
      }
      else{
        try{
          position = Integer.parseInt(buffer);
          if (position > 0 && position < width+1)
            return position;          
          else{
            WriteInLog.writeBuffer("Erreur colonne non valide "+buffer);
            System.out.print("Erreur : colonne non valide "+buffer+". Choisir un nombre entre 1 et "+width+" : ");
          }
        }

        catch(Exception e){
          WriteInLog.writeBuffer("Erreur saisie colonne "+buffer);
          System.out.print("Erreur : saisie colonne "+buffer+". Choisir un nombre entre 1 et "+width+" : ");
        }
      }
    }
    return position;
  }

  // Action du joueur
  public int choice(int[][] grid, int width, int height, int tokens){
    int position = playerReadLine(width, height);
    return position;
  }

}
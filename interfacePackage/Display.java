package interfacePackage;

import java.io.Console;

public class Display{

    public static void displayGrid(gamePackage.Grid grid){
      int width = grid.width;
      int height = grid.height;
      String lineNumero = new String("1");
      
      for (int k = 2; k<height+1; k++)
        lineNumero = lineNumero+" "+k;

      System.out.println("");
      System.out.println(lineNumero);

      for (int i = 0; i<width; i++){
        for (int j = 0; j<height; j++){ 
          if (grid.values[i][j] == 0)
            System.out.print(". ");
          else if (grid.values[i][j] == -1)     // player 1
            System.out.print(Color.setColor("X ", "\u001B[31m")); // RED
          else if (grid.values[i][j] == 1)     // player 2
            System.out.print(Color.setColor("O ", "\u001B[34m")); // BLUE
          else                          // error
            System.out.print("E ");
        }
        System.out.println("");
      }
      System.out.println("");
  }


  public static void interfaceMenu(){
    System.out.println("----- Menu -----\n");
    System.out.println("1 - Jouer");
    System.out.println("2 - Règles");
    System.out.println("3 - Paramètres");
    System.out.println("4 - Informations\n");
  }


  public static int changeNumberRounds(){
    Console console = System.console();
    int rounds = 3;
    int validAnswer = 0;
    String buf;

    System.out.print("Nombre de manches à gagner pour remporter la partie : ");
    buf = console.readLine();

    while(validAnswer == 0){
      rounds = Integer.parseInt(buf);
      
      if (rounds < 3){
        System.out.print("Erreur : nombre de manches insuffisant (>3 requis). Nombre de manches : ");
        buf = console.readLine();
      }
      else
        validAnswer = 1;
    }
    System.out.println("Le nombre de manches à gagner pour remporter la partie a été modifié avec succès");
    return rounds;
  }






  public static int[] parametersMenu(int numberPlayers, int width, int height, int rounds){
    Console console = System.console();
    int[] res = {numberPlayers, width, height, rounds};
    String buffer;
    String buf;
    int validAnswer = 0;

    System.out.println("Bienvenue au puissance 4 !\n");

    // Interface du menu
    interfaceMenu();
    System.out.print("Choix : ");
    buffer = console.readLine();

    while(!buffer.equals("1")){
      // Règles
      if (buffer.equals("2")){
        System.out.println("\n[Règles]");
        System.out.println("Blablabla règles");

        System.out.println("Pour retourner au menu, taper 'menu' : ");
        buf = console.readLine();

        while (validAnswer == 0){
          if (buf.equals("menu")){
            validAnswer = 1;
          }
          else{
            System.out.print("Erreur de saisie. Pour retourner au menu, taper 'menu' : ");
            buf = console.readLine();
          }
        }
        validAnswer = 0;
      }

      // Paramètres
      else if (buffer.equals("3")){
        System.out.println("\n[Paramètres]");
        System.out.print("Modifier les paramètres ? (Oui/Non) : ");
        buf = console.readLine();

        while (validAnswer == 0){
          if (buf.equals("Oui")){
            validAnswer = 1;
            // Choisir le nombre de joueurs

            // Choisir le nombre de manches
            res[0] = changeNumberRounds();

            // Choisir la taille du puissance 4

            // Choisir les couleurs des jetons
            System.out.print(Color.setColor("Les paramètres du jeu ont été modifiés avec succès", "\u001B[32m")); // vert
          }
          else if (buf.equals("Non")){
            validAnswer = 1;
          }
          else{
            System.out.print("Erreur de saisie. Modifier les paramètres ? (Oui/Non) : ");
            buf = console.readLine();
          }
        }
        validAnswer = 0;
      }

      // Informations
      else if (buffer.equals("4")){
        System.out.println("\n[Informations]");
        System.out.println("Blablabla infos");

        System.out.print("Pour retourner au menu, taper 'menu' : ");
        buf = console.readLine();

        while (validAnswer == 0){
          if (buf.equals("menu")){
            validAnswer = 1;
          }
          else{
            System.out.print("Erreur de saisie. Pour retourner au menu, taper 'menu' : ");
            buf = console.readLine();
          }
        }
        validAnswer = 0;
      }
      
      // Cas d'erreur : mauvaise saisie
      else{
        System.out.print("Erreur : saisie incorrecte. Choisis un numéro entre 1 et 4 : ");
      }

      System.out.println("\n");
      interfaceMenu();
      System.out.print("Choix : ");
      buffer = console.readLine();
  
    }

  System.out.println("\nC'est parti !\n");
  return res;
  }

}

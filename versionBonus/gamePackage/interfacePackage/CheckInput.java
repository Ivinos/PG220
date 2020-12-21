package gamePackage.interfacePackage;

import java.util.Scanner;

public class CheckInput{

    private static WriteInLog write;

    public static String[] checkNameType(String buf, int numeroPlayer){
      Scanner scanner = new Scanner(System.in);
      String[] res;
      String parameter = " ";
      int validString = 0;

      res = buf.split(parameter);

      while (validString == 0){
        if ((res.length > 1) && (res[0].equals("humain") || res[0].equals("ia") || res[0].equals("ia:monkey") || res[0].equals("ia:pro")))
          return res;
        else if (res[0].equals("sortir")){
          return res;
        }
        else{
          System.out.println("Erreur saisie Joueur "+numeroPlayer);
          System.out.println("Joueur "+numeroPlayer+"?");
          res = scanner.nextLine().split(parameter);
        }
      }     
         
      return res;
    }

}
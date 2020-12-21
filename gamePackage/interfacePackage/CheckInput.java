package gamePackage.interfacePackage;

//import java.io.Console;
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

    // // Test si la manière du choix du pseudo + type est valide
    // public static String[] checkPlayers(String buf, int numeroPlayer){
    //   //Console console = System.console();
    //   Scanner scanner = new Scanner(System.in);
    //   String[] res;
    //   String parameter = " ";

    //   res = buf.split(parameter);

    //   while (res.length == 1){
    //     System.out.println("Erreur saisie Joueur "+numeroPlayer);
    //     // write.writeBuffer("Erreur saisie Joueur "+numeroPlayer);
    //     // System.out.print("Erreur : déclaration du joueur incorrecte. Choisir un <type> <pseudo> : ");
    //     res = scanner.nextLine().split(parameter);
    //   }      

    //   return res;
    // }

    // // Test si le type choisi est valide
    // public static String checkType(String buf, int numeroPlayer){
    //   //Console console = System.console();
    //   Scanner scanner = new Scanner(System.in);
    //   int valid = 0;

    //   while (valid == 0){

    //     if (buf.equals("humain") || buf.equals("ia") || buf.equals("ia:monkey") || buf.equals("ia:pro"))
    //       return buf;

    //     else{
    //       System.out.println("Erreur saisie Joueur "+numeroPlayer);
    //       //  WriteInLog.writeBuffer("Erreur saisie Joueur "+numeroPlayer);
    //       //  System.out.print("Erreur : type invalide. Choisir un type valide (humain ou ia) : ");
    //        buf = scanner.nextLine();
    //       //  System.out.println(buf);
    //     }
    //   }
    //   return buf;
    // }

}
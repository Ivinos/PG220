package gamePackage;

//import java.io.Console;
import java.util.Scanner;

public class CheckInput{

    private static WriteInLog write;

    // Test si la manière du choix du pseudo + type est valide
    public static String[] checkPlayers(String buf, int numeroPlayer){
      //Console console = System.console();
      Scanner scanner = new Scanner(System.in);
      String[] res;
      String parameter = " ";

      res = buf.split(parameter);

      while (res.length == 1){
        write.writeBuffer("Erreur saisie Joueur "+numeroPlayer);
        System.out.print("Erreur : déclaration du joueur incorrecte. Choisis un <type> <pseudo> : ");
        res = scanner.nextLine().split(parameter);
      }      

      return res;
    }

    // Test si le type choisi est valide
    public static String checkType(String buf, int numeroPlayer){
      //Console console = System.console();
      Scanner scanner = new Scanner(System.in);
      int valid = 0;

      while (valid == 0){

        if (buf.equals("humain") || buf.equals("ia") || buf.equals("ia:monkey") || buf.equals("ia:pro"))
          return buf;

        else{
           WriteInLog.writeBuffer("Erreur saisie Joueur "+numeroPlayer);
           System.out.print("Erreur : type invalide. Choisis un type valide (humain ou ia) : ");
           buf = scanner.nextLine();
           System.out.println(buf);
        }
      }
      return buf;
    }

}
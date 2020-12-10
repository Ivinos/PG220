package gamePackage;

import java.io.Console;

public class CheckInput{

    public static String[] checkPlayers(String buf, int numeroPlayer){
      Console console = System.console();
      String[] res;
      String parameter = " ";

      res = buf.split(parameter);

      while (res.length == 1){
        WriteInLog.writeBuffer("Erreur saisie Joueur "+numeroPlayer);
        System.out.print("Erreur : déclaration du joueur incorrecte. Choisis un <type> <pseudo> : ");
        res = console.readLine().split(parameter);
      }      

      return res;
    }

    public static String checkType(String buf, int numeroPlayer){
      Console console = System.console();
      int valid = 0;

      while (valid == 0){

        if (buf.equals("humain") || buf.equals("ia") || buf.equals("ia:monkey") || buf.equals("ia:pro"))
          return buf;

        else{
           WriteInLog.writeBuffer("Erreur saisie Joueur "+numeroPlayer);
           System.out.print("Erreur : type invalide. Choisis un type valide (humain ou ia) : ");
           buf = console.readLine();
           System.out.println(buf);
        }
      }
      return buf;
    }

   }
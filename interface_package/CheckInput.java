package interface_package;

import java.io.Console;

public class CheckInput{

    public static String[] checkPlayers(String buf){
      Console console = System.console();
      String[] res;
      String parameter = " ";
      res = buf.split(parameter);

      while (res.length != 2 || true == res[1].contentEquals(" ")){
          System.out.print("Error : please enter <type> <pseudo> : ");
          res = console.readLine().split(parameter);
      }

      return res;
    }

    public static String checkType(String buf){
      Console console = System.console();
      int valid = 0;

      while (valid == 0){

        if (buf.equals("humain") || buf.equals("ia") ){
          return buf;
        }
        else{
           System.out.print("Error : please enter a valid type (human or ia) : ");
           buf = console.readLine();
           System.out.println(buf);
        }
      }
      return buf;
    }


    // public static String checkBuffer(String buf){
    //   Console console = System.console();
    //   String parameter = "0123456789";
    //
    //   System.out.println(parameter+"de taille "+parameter.length());
    //
    //   String[] res = buf.split(parameter);
    //
    //   System.out.println(res+"de taille "+res.length());
    //
    //   while (res.length() != buf.length()){
    //       System.out.print("Error : please enter a digit for position : ");
    //       buf = console.readLine();
    //       System.out.println(buf+"de taille "+buf.length());
    //       res = buf.split(parameter);
    //       System.out.println(res+"de taille "+res.length());
    //   }
    //
    //   return buf;
    // }

    public static String extractNumber(String str) {

    if(str == null || str.isEmpty())
      return "Erreur : saisie incorrecte";

    StringBuilder sb = new StringBuilder();
    boolean found = false;

    for(char c : str.toCharArray()){
        if(Character.isDigit(c)){ // si c'est un numero
            sb.append(c);
            found = true;
        }
        else if(found){
            // If we already found a digit before and this char is not a digit, stop looping
            break;
        }
    }

    if (sb.length() != str.length()){
      return "Erreur : saisie incorrecte";
    }

    return sb.toString();
}


}

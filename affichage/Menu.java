/*

Pour compiler ce fichier, il faut faire appel au compilateur Java : javac.
Vous pouvez compiler le fichier avec la commande suivante : javac Main.java.

Si tout se passe bien, vous devriez obtenir un fichier nommé Main.class. Il
contient le code source précédent sous forme de bytecode. Pour lancer le fichier, il faut
cette fois passer par l’intermédiaire de la machine virtuelle Java, avec la
commande suivante java Main.

*/

import java.io.Console;

public class Menu{ //extends Player{


    public static String[] check_buffer(String buf){
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


    public static void display_menu(){
        Console console = System.console();
        String s = new String("Bienvenue au puissance 4 !\n");
        String s1 = new String("Joueur 1?");
        String s2 = new String("Joueur 2?");

        String[] buf1;
        String[] buf2;
        String name1 = "";
        String name2 = "";
        String type1 = "";
        String type2 = "";


        System.out.println(s);

        System.out.println(s1);
        buf1 = check_buffer(console.readLine());
        type1 = buf1[0];
        name1 = buf1[1];


        System.out.println(s2);
        buf2 = check_buffer(console.readLine());
        type2 = buf2[0];
        name2 = buf2[1];

        System.out.println("Joueur 1 est <"+name1+"> (type : '"+type1+"') et Joueur 2 est <"+name2+ "> (type : '"+type2+"')");

        // b = s.startsWith("Hello"); // Test si la chaîne commence par une chaîne bien particulière
        // System.out.println(b);
    }

    public static void main(String[] args) {
        display_menu();
    }

}

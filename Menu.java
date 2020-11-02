/*

Pour compiler ce fichier, il faut faire appel au compilateur Java : javac.
Vous pouvez compiler le fichier avec la commande suivante : javac Main.java.

Si tout se passe bien, vous devriez obtenir un fichier nommé Main.class. Il
contient le code source précédent sous forme de bytecode. Pour lancer le fichier, il faut
cette fois passer par l’intermédiaire de la machine virtuelle Java, avec la
commande suivante java Main.

*/
import java.io.Console;

class Menu {

    public static void main(String[] args) {

        Console console = System.console();

        String s; //Déclaration d'un objet s de type String.

        String input1 = "";
        String input2 = "";

        s = new String("Bienvenue au puissance 4 !\n");

        System.out.println(s);

        System.out.println("Joueur 1?");
        input1 = console.readLine();
        
        System.out.println("Joueur 2?");
        input2 = console.readLine();

        System.out.println("Joueur 1 est "+input1+" et Joueur 2 est "+input2);

        // b = s.startsWith("Hello"); // Test si la chaîne commence par une chaîne bien particulière
        // System.out.println(b);

    }

}
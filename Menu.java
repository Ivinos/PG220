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

    // Point d'entrée des programmes Java. Le tableau de String args correspond
    // aux paramètres par la ligne de commande.
    public static void main(String[] args) {

        Console console = System.console();

        String s; //Déclaration d'un objet s de type String.
        Boolean b; //Déclaration d'un objet b de type Booléen.
        Integer i; //Déclaration d'un objet i de type Entier.

        String input1 = "";
        String input2 = "";
        // String s1;


        s = new String("Bienvenue au puissance 4 !\n");

        System.out.println(s);

        System.out.print("Joueur 1 : ");
        input1 = console.readLine();
        System.out.println();
        
        System.out.print("Joueur 2 : ");
        input2 = console.readLine();
        System.out.println();

        System.out.println("Joueur 1 est "+input1+" et Joueur 2 est "+input2);
        // b = s.startsWith("Hello"); // Test si la chaîne commence par une chaîne bien particulière
        // System.out.println(b);



    }

}
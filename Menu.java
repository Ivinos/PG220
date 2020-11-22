/*

Pour compiler ce fichier, il faut faire appel au compilateur Java : javac.
Vous pouvez compiler le fichier avec la commande suivante : javac Main.java.

Si tout se passe bien, vous devriez obtenir un fichier nommé Main.class. Il
contient le code source précédent sous forme de bytecode. Pour lancer le fichier, il faut
cette fois passer par l’intermédiaire de la machine virtuelle Java, avec la
commande suivante java Main.

*/
import interface_package.Display;

public class Menu{ //extends Player{

    // interface_package.Display display = new interface_package.Display(); // Pour essayer de raccourcir le nom..


    public static void main(String[] args) {
        interface_package.Display.display_menu();
    }

}

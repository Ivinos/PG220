public class Grille {

    public static void set_grille(){
        String ligne_numero = "1 2 3 4 5 6 7";
        String ligne = ". . . . . . .";

        System.out.println(ligne_numero);

        for (int i = 1; i<7; i++){
            System.out.println(ligne);
        }
    }

    public static void main(String[] args) {
        set_grille();
    }

}

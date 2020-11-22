package interface_package;

import java.io.Console;

public class Display{
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
        buf1 = CheckInput.check_buffer(console.readLine());
        type1 = buf1[0];
        name1 = buf1[1];


        System.out.println(s2);
        buf2 = CheckInput.check_buffer(console.readLine());
        type2 = buf2[0];
        name2 = buf2[1];

        System.out.println("Joueur 1 est <"+name1+"> (type : '"+type1+"') et Joueur 2 est <"+name2+ "> (type : '"+type2+"')");

        // b = s.startsWith("Hello"); // Test si la chaîne commence par une chaîne bien particulière
        // System.out.println(b);
    }



}

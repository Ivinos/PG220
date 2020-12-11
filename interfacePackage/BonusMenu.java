package interfacePackage;

import java.io.Console;

public class BonusMenu{

    public static void interfaceMenu(){
        System.out.println("\n----- Menu -----\n");
        System.out.println("1 - Jouer");
        System.out.println("2 - Règles");
        System.out.println("3 - Paramètres");
        System.out.println("4 - Informations\n");
    }

    
    public static void goBackToMenu(){
        Console console = System.console();
        String buf;
        int validAnswer = 0;

        System.out.print("Pour retourner au menu, taper 'menu' : ");
        

        while (validAnswer == 0){
            buf = console.readLine();

            if (buf.equals("menu")){
                validAnswer = 1;
            }
            else{
                System.out.print("Erreur de saisie. Pour retourner au menu, taper 'menu' : ");
            }
        }
    }

    public static void quitMenu(String buffer){
        if (buffer.equals("sortir")){
            gamePackage.WriteInLog.writeBuffer(buffer);
            System.out.println("Jeu terminé suite à la commande '"+buffer+"'");
            System.exit(0);
        }
    }


    public static void displayRules(){    
        System.out.println("\n[Règles]");
        System.out.println("Blablabla règles");

        goBackToMenu();
    }

    
    public static int[] displayParameters(int numberPlayers, int width, int height, int rounds, int tokens){
        Console console = System.console();
        String buf, buf2;
        int[] buff;
        int validAnswer = 0;
        int validAnswer2 = 0;
        int[] res = {numberPlayers, width, height, rounds, tokens};

        System.out.println("\n[Paramètres]");
        System.out.print("Modifier les paramètres ? [Oui/Non] : ");
        buf = console.readLine();

        while (validAnswer == 0){
            // Quitte le programme si commande "sortir"
            // quitMenu(buf);

            if (buf.equals("Oui")){
                validAnswer = 1;
                
                while (validAnswer2 == 0){
                    System.out.println("\nA - Nombre de joueurs");
                    System.out.println("B - Taille de la grille");
                    System.out.println("C - Nombre de manches");
                    System.out.println("D - Nombre de jetons");
                    System.out.println("E - Tous les paramètres ");
                    System.out.print("\nPour revenir au Menu, taper 'menu'\n>> ");
                    buf2 = console.readLine();
                    
                    if (buf2.equals("A")){
                        // Choisir le nombre de joueurs
                        res[0] = changeNumberPlayers();
                    }
                    else if (buf2.equals("B")){
                        // Choisir la taille du puissance 4
                        buff = changeWidthAndHeight();
                        res[1] = buff[0];
                        res[2] = buff[1];
                    }
                    else if (buf2.equals("C")){
                        // Choisir le nombre de manches
                        res[3] = changeNumberRounds();
                    }
                    else if (buf2.equals("D")){
                        // Choisir le nombre de jetons
                        res[4] = changeNumberTokens(); 
                    }
                    else if (buf2.equals("E")){
                        // Modifier tous les paramètres
                        res[0] = changeNumberPlayers();
                        buff = changeWidthAndHeight();
                        res[1] = buff[0];
                        res[2] = buff[1];
                        res[3] = changeNumberRounds();
                        res[4] = changeNumberTokens(); 
                    }
                    else if (buf2.equals("menu")){
                        validAnswer2 = 1;
                        System.out.println(Display.setColor("Les paramètres du jeu ont été modifiés avec succès", "\u001B[32m")); // vert
                        // goBackToMenu();
                    }
                    else{
                        System.out.print("Erreur de saisie. Mentionner les paramètres à modifier : ");
                    }
                }
            }
            else if (buf.equals("Non"))
                validAnswer = 1;
            else{
                System.out.print("Erreur de saisie. Modifier les paramètres ? [Oui/Non] : ");
                buf = console.readLine();
            }
        }

        return res;  
    }

    public static void displayInformations(){
        System.out.println("\n[Informations]");
        System.out.println("Blablabla infos");
        goBackToMenu();
    }


    public static int changeNumberPlayers(){
        Console console = System.console();
        int numberPlayers = 2;
        int validAnswer = 0;
        String buf;

        System.out.print("Nombre de joueurs : ");
        
        while(validAnswer == 0){
            buf = console.readLine();
            // Quitte le programme si commande "sortir"
            // quitMenu(buf);

            try{
            numberPlayers = Integer.parseInt(buf);
            if (numberPlayers >= 2 && numberPlayers <= 6)
                return numberPlayers;
            else
                System.out.print("Erreur : nombre de joueurs incorrect (2-6 joueurs requis). Nombre de joueurs : ");                
            }

            catch(Exception e){
                System.out.print("Erreur : saisie incorrecte. Nombre de joueurs : ");
            }
        }
        return numberPlayers;
    }

    public static int changeWidth(){
        Console console = System.console();
        int width = 7;
        int validAnswer = 0;
        String buf;

        System.out.print("Largeur de la grille : ");

        while(validAnswer == 0){
            buf = console.readLine();
            // Quitte le programme si commande "sortir"
            // quitMenu(buf);

            try{
                width = Integer.parseInt(buf);
                if (width <= 3)
                    System.out.print("Erreur : largeur de la grille incorrecte (>= 4 requise). Largeur de la grille : ");
                else
                    return width;
            }

            catch(Exception e){
                System.out.print("Erreur : saisie incorrecte. Largeur de la grille : ");
            }
        }
        return width;
    }

    public static int changeHeight(){
        Console console = System.console();
        int height = 6;
        int validAnswer = 0;
        String buf;
        System.out.print("Hauteur de la grille : ");

        while(validAnswer == 0){
            buf = console.readLine();
            // Quitte le programme si commande "sortir"
            // quitMenu(buf);

            try{
                height = Integer.parseInt(buf);
                if (height <= 1)
                    System.out.print("Erreur : hauteur de la grille incorrecte (>= 2 requise). Hauteur de la grille : ");
                else
                    return height;
            }

            catch(Exception e){
                System.out.print("Erreur : saisie incorrecte. Hauteur de la grille : ");
            }
        }
        return height;
    }

    public static int[] changeWidthAndHeight(){
        int validAnswer = 0;
        int width = -1; 
        int height = -1;
        int[]res = new int[2];

        while (validAnswer == 0){
            width = changeWidth();
            height = changeHeight(); 

            if ((width*height)%2 == 0 && (width*height) >= 8){
                validAnswer = 1;
            }
            else{
                System.out.println("Erreur : longueur x largeur n'est pas pair");
            }      
        }

        res[0] = width;
        res[1] = height;

        return res;
    }

    public static int changeNumberRounds(){
        Console console = System.console();
        int rounds = 3;
        int validAnswer = 0;
        String buf;

        System.out.print("Nombre de manches à gagner pour remporter la partie : ");

        while(validAnswer == 0){
            buf = console.readLine();
            // Quitte le programme si commande "sortir"
            // quitMenu(buf);

            try{
                rounds = Integer.parseInt(buf);
                if (rounds < 3)
                    System.out.print("Erreur : nombre de manches insuffisant (>= 3 requis). Nombre de manches : ");
                else
                    return rounds;
            }

            catch(Exception e){
            System.out.print("Erreur : saisie incorrecte. Nombre de manches : ");
            }
        }
        return rounds;
    }

    public static int changeNumberTokens(){
        Console console = System.console();
        int tokens = 3;
        int validAnswer = 0;
        String buf;

        System.out.print("Nombre de jetons à aligner pour remporter la partie : ");

        while(validAnswer == 0){
            buf = console.readLine();
            // Quitte le programme si commande "sortir"
            // quitMenu(buf);

            try{
                tokens = Integer.parseInt(buf);
                if (tokens >= 4 && tokens <= 6)
                    return tokens;
                else
                    System.out.print("Erreur : nombre de jetons incorrect (4-6 requis). Nombre de jetons : ");
            }

            catch(Exception e){
                System.out.print("Erreur : saisie incorrecte. Nombre de jetons : ");
            }
        }
        return tokens;
    }


    public static int[] parametersMenu(int numberPlayers, int width, int height, int rounds, int tokens){
        Console console = System.console();
        int[] res = {numberPlayers, width, height, rounds, tokens};
        String buffer;
        int[] buff;

        System.out.println("Bienvenue au puissance 4 !");

        // Interface du menu
        interfaceMenu();
        System.out.print("Choix : ");
        buffer = console.readLine();

        while(!buffer.equals("1")){
            // Quitte le programme si commande "sortir"
            quitMenu(buffer);

            // Règles
            if (buffer.equals("2"))
                displayRules();

            // Paramètres
            else if (buffer.equals("3")){
                buff = displayParameters(numberPlayers, width, height, rounds, tokens);
                res[0] = buff[0];
                res[1] = buff[1];
                res[2] = buff[2];
                res[3] = buff[3];
                res[4] = buff[4];
            }

            // Informations
            else if (buffer.equals("4"))
                displayInformations();
            
            // Cas d'erreur : mauvaise saisie
            else
                System.out.println("Erreur : saisie incorrecte. Le numéro doit être compris entre 1 et 4 !");

            // System.out.println("\n");
            interfaceMenu();
            System.out.print("Choix : ");
            buffer = console.readLine();
        }
        System.out.println("\nC'est parti !\n");
        return res;
    }

}
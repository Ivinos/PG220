package gamePackage.interfacePackage;

import java.util.Scanner;

public class BonusMenu{

    // Affichage de l'interface du Menu
    public static void interfaceMenu(){
        String bold = Color.getTextBold();
        String reset = Color.getTextReset();
        System.out.println(bold+"\n----- Menu -----\n"+reset);
        System.out.println("1 - Jouer");
        System.out.println("2 - Règles");
        System.out.println("3 - Paramètres");
        System.out.println("4 - Informations");
        System.out.println("5 - Statistiques\n");
    }

    // Commande pour retourner au Menu
    public static void goBackToMenu(){
        Scanner scanner = new Scanner(System.in);
        String buf;
        int validAnswer = 0;

        System.out.print("Pour retourner au menu, taper 'menu' : ");
        
        while (validAnswer == 0){
            buf = scanner.nextLine();

            if (buf.equals("menu")){
                validAnswer = 1;
            }
            else{
                System.out.print("Erreur de saisie. Pour retourner au menu, taper 'menu' : ");
            }
        }
    }

    // Affichage du message de fin lorsque la commande "sortir" est tapée
    public static void quitMenu(String buffer){
        if (buffer.equals("sortir")){
            gamePackage.interfacePackage.WriteInLog.writeBuffer(buffer);
            System.out.println("Jeu terminé suite à la commande '"+buffer+"'");
            System.exit(0);
        }
    }

    // Affichage des règles
    public static void displayRules(){    
        System.out.println(Color.setColor("\n[Règles]", "NONE",1));
        System.out.print(Color.setColor("       Le but du jeu est d'aligner une suite de 4 pions de même couleur sur", "NONE",2));
        System.out.print(Color.setColor(" une grille comptant 6 rangées et 7 colonnes. Chaque joueur dispose de", "NONE",2));
        System.out.print(Color.setColor(" 21 pions d'une couleur (par convention, en général jaune ou rouge). Tour", "NONE",2));
        System.out.print(Color.setColor(" à tour, les deux joueurs placent un pion dans la colonne de leur choix,", "NONE",2));
        System.out.print(Color.setColor(" le pion coulisse alors jusqu'à la position la plus basse possible dans", "NONE",2));
        System.out.print(Color.setColor(" la dite colonne à la suite de quoi c'est à l'adversaire de jouer.\n\nLe", "NONE",2));
        System.out.print(Color.setColor(" vainqueur est le joueur qui réalise le premier un alignement (horizontal,", "NONE",2));
        System.out.print(Color.setColor(" vertical ou diagonal) consécutif d'au moins quatre pions de sa couleur.\n\n", "NONE",2));
        System.out.print(Color.setColor("Si, alors que toutes les cases de la grille de jeu sont remplies, aucun", "NONE",2));
        System.out.println(Color.setColor(" des deux joueurs n'a réalisé un tel alignement, la partie est déclarée nulle.\n", "NONE",2));

        goBackToMenu();
    }

    // Affichage des paramètres modifiables du jeu
    public static int[] displayParameters(int numberPlayers, int width, int height, int rounds, int tokens){
        Scanner scanner = new Scanner(System.in);
        String buf, buf2;
        int[] buff;
        int validAnswer = 0;
        int validAnswer2 = 0;
        int modification = 0;
        int[] res = {numberPlayers, width, height, rounds, tokens};

        System.out.println(Color.setColor("\n[Paramètres]", "NONE",1));
        System.out.print("Modifier les paramètres ? [Oui/Non] : ");
        buf = scanner.nextLine();

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
                    System.out.println("Z - Tous les paramètres ");
                    System.out.print("\nPour revenir au Menu, taper 'menu'\n>> ");
                    buf2 = scanner.nextLine();
                    
                    // Choisir le nombre de joueurs
                    if (buf2.equals("A")){
                        res[0] = changeNumberPlayers();
                        modification = 1;
                    }

                    // Choisir la taille du puissance 4
                    else if (buf2.equals("B")){
                        buff = changeWidthAndHeight(res[1], res[2],res[4]);
                        res[1] = buff[0];
                        res[2] = buff[1];
                        modification = 1;
                    }

                    // Choisir le nombre de manches
                    else if (buf2.equals("C")){                     
                        res[3] = changeNumberRounds();
                        modification = 1;
                    }

                    // Choisir le nombre de jetons
                    else if (buf2.equals("D")){                        
                        res[4] = changeNumberTokens(res[1], res[2]);
                        modification = 1;
                    } 
                        
                    // Modifier tous les paramètres
                    else if (buf2.equals("Z")){                        
                        res[0] = changeNumberPlayers();
                        buff = changeWidthAndHeight(res[1], res[2],res[4]);
                        res[1] = buff[0];
                        res[2] = buff[1];
                        res[3] = changeNumberRounds();
                        res[4] = changeNumberTokens(res[1], res[2]); 
                        modification = 1;
                    }
                    // Retour au Menu
                    else if (buf2.equals("menu")){
                        validAnswer2 = 1;
                        if (modification == 1)
                            System.out.println(Color.setColor("Les paramètres du jeu ont été modifiés avec succès", "GREEN",0)); // vert
                    }

                    // Erreur de saisie
                    else{
                        System.out.print("Erreur de saisie. Mentionner les paramètres à modifier : ");
                    }
                }
            }
            else if (buf.equals("Non"))
                validAnswer = 1;
            else{
                System.out.print("Erreur de saisie. Modifier les paramètres ? [Oui/Non] : ");
                buf = scanner.nextLine();
            }
        }
        return res;  
    }

    // Affichage des informations 
    public static void displayInformations(){
        System.out.println(Color.setColor("\n[Informations]", "NONE",1));
        System.out.print(Color.setColor("       Puissance 4 (appelé aussi parfois 4 en ligne) est un jeu de stratégie ", "NONE",2));
        System.out.print(Color.setColor("combinatoire abstrait, commercialisé pour la première fois en 1974 par ", "NONE",2));
        System.out.print(Color.setColor("la Milton Bradley Company, plus connue sous le nom de MB et détenue ", "NONE",2));
        System.out.println(Color.setColor("depuis 1984 par la société Hasbro.\n", "NONE",2));

        goBackToMenu();
    }

    // Affichage des statistiques entre les ia
    public static void displayStats(){    
        System.out.println(Color.setColor("\n[Statistiques]", "NONE",1));
        System.out.print(Color.setColor("       Des simulations ont été realisées pour déterminer et comparer les", "NONE",2));
        System.out.print(Color.setColor(" différentes ia implémentées (ia:random, ia:monkey et ia:pro)\n\n", "NONE",2));
        System.out.print(Color.setColor("     Manches gagnantes                       IA/IA                      Score moyen\n", "NONE",1));
        System.out.print(Color.setColor("           100                        ia:random/ia:monkey                  23/100\n", "NONE",2));
        System.out.print(Color.setColor("           100                        ia:monkey/ia:pro                     56/100\n", "NONE",2));
        System.out.print(Color.setColor("           100                        ia:random/ia:pro                     12/100\n", "NONE",2));
        System.out.print(Color.setColor("           100                        ia:random/ia:monkey/ia:pro         35/64/100\n", "NONE",2));
        System.out.println(Color.setColor("\n", "NONE",2));

        goBackToMenu();
    }

    // Modification du nombre de joueurs
    public static int changeNumberPlayers(){
        Scanner scanner = new Scanner(System.in);
        int numberPlayers = 2;
        int validAnswer = 0;
        String buf;

        System.out.print("Nombre de joueurs : ");
        
        while(validAnswer == 0){
            buf = scanner.nextLine();
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

    // Modification de la largeur de la grille
    public static int changeWidth(int tokens){
        Scanner scanner = new Scanner(System.in);
        int width = 7;
        int validAnswer = 0;
        String buf;

        System.out.print("Largeur de la grille : ");

        while(validAnswer == 0){
            buf = scanner.nextLine();
            // Quitte le programme si commande "sortir"
            // quitMenu(buf);

            try{
                width = Integer.parseInt(buf);
                if (width <= 3)
                    System.out.print("Erreur : largeur de la grille incorrecte (>= 4 requise). Largeur de la grille : ");
                else if (width < tokens)
                    System.out.print("Erreur : largeur de la grille incorrecte (largeur < "+tokens+" : nombre de jetons à aligner). Largeur de la grille : ");
                else
                    return width;
            }

            catch(Exception e){
                System.out.print("Erreur : saisie incorrecte. Largeur de la grille : ");
            }
        }
        return width;
    }
    // Modification de la hauteur de la grille
    public static int changeHeight(int tokens){
        Scanner scanner = new Scanner(System.in);
        int height = 6;
        int validAnswer = 0;
        String buf;
        System.out.print("Hauteur de la grille : ");

        while(validAnswer == 0){
            buf = scanner.nextLine();
            // Quitte le programme si commande "sortir"
            // quitMenu(buf);

            try{
                height = Integer.parseInt(buf);
                if (height <= 1)
                    System.out.print("Erreur : hauteur de la grille incorrecte (>= 2 requise). Hauteur de la grille : ");
                else if (height < tokens)
                    System.out.print("Erreur : hauteur de la grille incorrecte (hauteur < "+tokens+" : nombre de jetons à aligner). Hauteur de la grille : ");
                else
                    return height;
            }

            catch(Exception e){
                System.out.print("Erreur : saisie incorrecte. Hauteur de la grille : ");
            }
        }
        return height;
    }

    // Vérification des nouvelles valeurs pour la largeur et la hauteur de la grille
    public static int[] changeWidthAndHeight(int width, int height, int tokens){
        int validAnswer = 0;
        int[]res = new int[2];

        while (validAnswer == 0){
            width = changeWidth(tokens);
            height = changeHeight(tokens); 

            if ((width*height)%2 == 0 && (width*height) >= 8)
                validAnswer = 1;
            
            else
                System.out.println("Erreur : longueur x largeur n'est pas pair");    
        }

        res[0] = width;
        res[1] = height;

        return res;
    }

    // Modification du nombre de manches à gagner
    public static int changeNumberRounds(){
        Scanner scanner = new Scanner(System.in);
        int rounds = 3;
        int validAnswer = 0;
        String buf;

        System.out.print("Nombre de manches à gagner pour remporter la partie : ");

        while(validAnswer == 0){
            buf = scanner.nextLine();
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

    // Modification du nombre de jetons à aligner pour gagner une manche
    public static int changeNumberTokens(int width, int height){
        Scanner scanner = new Scanner(System.in);
        int tokens = 3;
        int validAnswer = 0;
        String buf;

        System.out.print("Nombre de jetons à aligner pour remporter la partie : ");

        while(validAnswer == 0){
            buf = scanner.nextLine();
            // Quitte le programme si commande "sortir"
            // quitMenu(buf);

            try{
                tokens = Integer.parseInt(buf);
                if (tokens < 4)
                    System.out.print("Erreur : nombre de jetons incorrect (>= 4 jetons requis). Nombre de jetons : ");
                else if (tokens > width)
                    System.out.print("Erreur : nombre de jetons incorrect (nombre de jetons > "+tokens+" : largeur de la grille). Nombre de jetons : ");
                else if (tokens > height)
                    System.out.print("Erreur : nombre de jetons incorrect (nombre de jetons > "+tokens+" : hauteur de la grille). Nombre de jetons : ");
                else
                    return tokens;
            }

            catch(Exception e){
                System.out.print("Erreur : saisie incorrecte. Nombre de jetons : ");
            }
        }
        return tokens;
    }


    public static int[] parametersMenu(int numberPlayers, int width, int height, int rounds, int tokens){
        Scanner scanner = new Scanner(System.in);
        int[] res = {numberPlayers, width, height, rounds, tokens};
        String buffer;
        int[] buff;

        System.out.println("Bienvenue au puissance 4 !");

        // Interface du menu
        interfaceMenu();
        System.out.print("Choix : ");
        buffer = scanner.nextLine();

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

            // Statistiques
            else if (buffer.equals("5"))
                displayStats();
            
            // Cas d'erreur : mauvaise saisie
            else
                System.out.println("Erreur : saisie incorrecte. Le numéro doit être compris entre 1 et 4 !");

            // System.out.println("\n");
            interfaceMenu();
            System.out.print("Choix : ");
            buffer = scanner.nextLine();
        }
        System.out.println("\nC'est parti !\n");
        return res;
    }

}
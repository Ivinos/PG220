package gamePackage.interfacePackage;

import java.util.Scanner;

public class BonusMenu{

    // Affichage de l'interface du Menu
    public static void interfaceMenu(){
        System.out.println(Color.setColor("\n----- Menu -----\n", Color.getYellow(), Color.getTextBold()));
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
            quitMenu(buf);

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
        System.out.println(Color.setColor("\n[Règles]", Color.getNone(),1));
        System.out.print(Color.setColor("       Le but du jeu est d'aligner une suite de 4 pions de même couleur sur", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor(" une grille comptant 6 rangées et 7 colonnes. Chaque joueur dispose de", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor(" 21 pions d'une couleur (par convention, en général jaune ou rouge). Tour", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor(" à tour, les deux joueurs placent un pion dans la colonne de leur choix,", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor(" le pion coulisse alors jusqu'à la position la plus basse possible dans", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor(" la dite colonne à la suite de quoi c'est à l'adversaire de jouer.\n\nLe", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor(" vainqueur est le joueur qui réalise le premier un alignement (horizontal,", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor(" vertical ou diagonal) consécutif d'au moins quatre pions de sa couleur.\n\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("Si, alors que toutes les cases de la grille de jeu sont remplies, aucun", Color.getNone(),Color.getTextItalic()));
        System.out.println(Color.setColor(" des deux joueurs n'a réalisé un tel alignement, la partie est déclarée nulle.\n", Color.getNone(),Color.getTextItalic()));

        goBackToMenu();
    }

    // Affichage des paramètres modifiables du jeu
    public static String[] displayParameters(int numberPlayers, int width, int height, int rounds, int tokens, String[] symbols){
        Scanner scanner = new Scanner(System.in);
        String buf, buf2;
        int[] buff;
        int validAnswer = 0;
        int validAnswer2 = 0;
        int modification = 0;
        int[] res = {numberPlayers, width, height, rounds, tokens};
        String[] symb = symbols; // Symboles des joueurs (index 0 = personne)

        // String[] str1 = {"1","2","3","4"};
        // String[] str2 = {"5","6","7","8"};
        // for (int i = 0; i<str1.length+str2.length; i++)
        //     System.out.print(concatString(str1,str2)[i]);

        System.out.println(Color.setColor("\n[Paramètres]", Color.getNone(),Color.getTextBold()));
        System.out.print("Modifier les paramètres ? [Oui/Non] : ");
        buf = scanner.nextLine();

        while (validAnswer == 0){
            // Quitte le programme si commande "sortir"
            quitMenu(buf);

            if (buf.equals("Oui")){
                validAnswer = 1;
                
                while (validAnswer2 == 0){
                    System.out.println("\nA - Nombre de joueurs");
                    System.out.println("B - Taille de la grille");
                    System.out.println("C - Nombre de manches");
                    System.out.println("D - Nombre de jetons");
                    System.out.println("E - Symboles des joueurs");
                    System.out.println("Z - Tous les paramètres ");
                    System.out.print("\nPour revenir au Menu, taper 'menu'\n>> ");
                    buf2 = scanner.nextLine();
                    quitMenu(buf2);
                    
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

                    // Choisir les symboles des joueurs
                    else if (buf2.equals("E")){                        
                        symb = changeTokensSymbols(symb, res[0]); // symbols
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
                        symb = changeTokensSymbols(symb, res[0]);
                        modification = 1;
                    }
                    // Retour au Menu
                    else if (buf2.equals("menu")){
                        validAnswer2 = 1;
                        if (modification == 1)
                            System.out.println(Color.setColor("Les paramètres du jeu ont été modifiés avec succès", Color.getGreen(),0));
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
        String[] finalRes = concatString(convertIntToString(res), symb);
        return finalRes;  
    }

    // Affichage des informations 
    public static void displayInformations(){
        System.out.println(Color.setColor("\n[Informations]", Color.getNone(),1));
        System.out.print(Color.setColor("       Puissance 4 (appelé aussi parfois 4 en ligne) est un jeu de stratégie ", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("\n       combinatoire abstrait, commercialisé pour la première fois en 1974 par ", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("\n       la Milton Bradley Company, plus connue sous le nom de MB et détenue ", Color.getNone(),Color.getTextItalic()));
        System.out.println(Color.setColor("\n       depuis 1984 par la société Hasbro.\n", Color.getNone(),Color.getTextItalic()));

        goBackToMenu();
    }

    // Affichage des statistiques entre les ia
    public static void displayStats(){    
        System.out.println(Color.setColor("\n[Statistiques]", Color.getNone(),1));
        System.out.print(Color.setColor("       Des simulations ont été realisées pour déterminer et comparer les", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor(" différentes ia implémentées (ia:random, ia:monkey et ia:pro)\n\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("          Manches                             IA/IA                          Score\n", Color.getNone(),Color.getTextBold()));
        System.out.print(Color.setColor("           1000                        ia:random/ia:random                  503/497\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("           1000                        ia:random/ia:monkey                  188/812\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("           1000                        ia:random/ia:pro                     91/909\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("           1000                        ia:monkey/ia:pro                     336/664\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("           1000                    ia:random/ia:monkey/ia:pro               170/276/554\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("           6                           ia:pro/Evan Colly                    4/2\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("\nSur une grille de 12 de largeur et 10 de hauteur :\n           1000     ia:random/ia:random/ia:monkey/ia:monkey/ia:pro/ia:pro   72/86/198/113/255/276\n", Color.getNone(),Color.getTextItalic()));
        System.out.println(Color.setColor("\n", Color.getNone(),2));

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
            quitMenu(buf);

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
            quitMenu(buf);

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
            quitMenu(buf);

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
            quitMenu(buf);

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
            quitMenu(buf);

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

    // Modification des symaboles des joueurs
    public static String[] changeTokensSymbols(String[] symbols, int numberPlayers){
        Scanner scanner = new Scanner(System.in);
        int numeroPlayer;
        int validAnswer = 0, validAnswer2 = 0;
        int newSymbol = 1;
        String buf, buf2;
        System.out.print("Modifier le symbole du joueur : ");

        while(validAnswer == 0){
            buf = scanner.nextLine();
            // Quitte le programme si commande "sortir"
            // quitMenu(buf);

            try{
                numeroPlayer = Integer.parseInt(buf);
                if (numeroPlayer < 1 || numeroPlayer > numberPlayers)
                    System.out.print("Erreur : joueur "+numeroPlayer+" introuvable. Modifier le symbole du joueur : ");
                else{
                    while (validAnswer2 == 0){
                        System.out.print("Choix du nouveau symbole : ");
                        buf2 = scanner.nextLine();

                        if (buf2.length() == 1){
                            if (buf2.equals(".") || buf2.equals(" ")){
                                System.out.print("Erreur : symbole interdit ('.' ou ''). ");                               
                            }
                            else{
                                try{
                                    for (int k = 1; k<numberPlayers+1; k++){
                                        if (buf2 == symbols[k]){
                                            System.out.print("Erreur : symbole déjà utilisé. ");
                                            newSymbol = 0;
                                        }
                                    }
                                    if (newSymbol == 1){
                                        symbols[numeroPlayer] = buf2.concat(" ");
                                        return symbols;
                                    }
                                }
                                catch(Exception e){
                                    System.out.print("Erreur : saisie incorrecte. Choix du nouveau symbole : ");
                                }
                            }
                        }
                        else
                            System.out.print("Erreur : le symbole ne peut contenir qu'une lettre. ");
                    }
                }
            }

            catch(Exception e){
                System.out.print("Erreur : saisie incorrecte. Modifier le symbole du joueur : ");
            }
        }
        return symbols;
    }


    public static String[] convertIntToString(int[] res){
        int len = res.length;
        String[] intToString = new String[len];

        for (int l = 0; l<len; l++){
            intToString[l] = Integer.toString(res[l]);
        }

        return intToString;
    }

    public static int[] convertStringToInt(String[] res){
        int len = res.length;
        int[] stringToInt = new int[len];

        for (int m = 0; m<len; m++){
            stringToInt[m] = Integer.parseInt(res[m]);
        }

        return stringToInt;
    }

    public static String[] concatString(String[] str1, String[] str2){
        int len1 = str1.length;
        int len2 = str2.length;
        String[] res = new String[len1+len2];

        for (int i = 0; i<len1+len2; i++){
            if (i<len1)
                res[i] = str1[i];
            else
                res[i] = str2[i-len1];
        }
        return res;
    }

    public static String[] getStringFromTo(String[] str, int start, int end){
        String[] res = new String[end-start+1];
        for (int i = 0; i<end-start+1; i++)
            res[i] = str[start+i];
        return res;
    }


    public static String[] parametersMenu(int numberPlayers, int width, int height, int rounds, int tokens, String[] symbols){
        Scanner scanner = new Scanner(System.in);
        int[] res = {numberPlayers, width, height, rounds, tokens};
        String[] buffString;
        String[] symb = symbols;
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
                buffString = displayParameters(numberPlayers, width, height, rounds, tokens, symbols);
                buff = convertStringToInt(getStringFromTo(buffString,0,4));
                res[0] = buff[0];
                res[1] = buff[1];
                res[2] = buff[2];
                res[3] = buff[3];
                res[4] = buff[4];
                System.out.println("Taille de buffString.length : "+buffString.length);
                symb = getStringFromTo(buffString,5,buffString.length-1);
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

        String[] finalRes = concatString(convertIntToString(res), symb);
        return finalRes;  
    }

}
package game_package;

import java.io.Console;

public class Human extends Player{

  public Human(String name){
    super(name);
  }


  public String checkBuffer(String str) {

    if(str == null || str.isEmpty()){
      Game.writeBuffer("Erreur saisie Joueur .");
      return "Erreur saisie Joueur .";
    }
    else if (str.equals("sortir")){
      Game.writeBuffer(str);
      return str;
    }

    StringBuilder sb = new StringBuilder();

    for(char c : str.toCharArray()){
        if(Character.isDigit(c)){ // si c'est un numero
            sb.append(c);
        }
        else{
          // If we already found a digit before and this char is not a digit, stop looping
          break;
        }
    }

    if (sb.length() == str.length()){
      String buf = sb.toString();
      if (Integer.parseInt(buf) > 0 && Integer.parseInt(buf) < 8)
        return str;
      Game.writeBuffer("Erreur colonne non valide "+str);
      return "Erreur : position invalide";
    }
    Game.writeBuffer("Erreur saisie Joueur .");
    return "Erreur saisie Joueur .";
  }

  public int choice(){
    String buffer;
    String buf;
    Console console = System.console();
    int position = 0;

    buffer = console.readLine();
    buf = checkBuffer(buffer);


    while(!buf.equals(buffer)){
      if (buf.equals("sortir")){
        System.out.println(">>>>>");
        return -1;
      }
      else if(buf.equals("Erreur : position invalide")){
        System.out.println("Position invalide. Choisis un nombre entre 1 et 7 :  ");
      }
      else if(buf.equals("Erreur saisie Joueur .")){
        System.out.println(">Saisie invalide. Choisis un nombre entre 1 et 7 :  ");
      }
      
      else
        System.out.println("WHAT "+buf);

      buffer = console.readLine();
      buf = checkBuffer(buffer);
    }

    if (buf.equals("sortir")){
      System.out.println(">>>>>");
      return -1;
    }

    position = Integer.parseInt(buf);

  return position;
  }


}

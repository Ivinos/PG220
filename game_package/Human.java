package game_package;

import java.io.Console;

public class Human extends Player{

  public Human(String name){
    super(name);
  }


  public String checkBuffer(String str) {

    if(str == null || str.isEmpty())
      return "1 Erreur : saisie incorrecte";
    else if (str.equals("sortir"))
      return str;

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
      return "invalid position";
    }

    return "2 Erreur : saisie incorrecte";
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
        Game.writeBuffer("Commande 'sortir'");
        System.out.println("You quit the game");
        return -1;
      }
      else if(buf.equals("invalid position")){
        Game.writeBuffer("Erreur saisie colonne .");
        System.out.println(">Wrong position. Please choose a number from 1 to 7 ");
      }
      else
        System.out.println(buf);

      buffer = console.readLine();
      buf = checkBuffer(buffer);
    }

    position = Integer.parseInt(buf);

  return position;
  }


}

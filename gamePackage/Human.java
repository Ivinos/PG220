package gamePackage;

import java.io.Console;

public class Human extends Player{

  public Human(String name, int numeroPlayer){
    super(name,numeroPlayer);
  }


  public String checkBuffer(String str, int width) {

    if(str == null || str.isEmpty()){
      WriteInLog.writeBuffer("Erreur saisie colonne "+str);
      return "Erreur saisie colonne";
    }
    else if (str.equals("sortir")){
      WriteInLog.writeBuffer(str);
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
      if (Integer.parseInt(buf) > 0 && Integer.parseInt(buf) < width+1)
        return str;
      WriteInLog.writeBuffer("Erreur colonne non valide "+str);
      return "Erreur : position invalide";
    }
    WriteInLog.writeBuffer("Erreur saisie colonne "+str);
    return "Erreur saisie colonne";
  }

  public int choice(Grid grid){
    String buffer;
    String buf;
    Console console = System.console();
    int position = 0;
    int width = grid.width;

    buffer = console.readLine();
    buf = checkBuffer(buffer,width);


    while(!buf.equals(buffer)){
      if (buf.equals("sortir")){
        System.out.println(">>>>>     TCHAO     <<<<<");
        return -1;
      }
      else if(buf.equals("Erreur : position invalide")){
        System.out.print("Erreur : position invalide. Choisis un nombre entre 1 et "+width+" : ");
      }
      else if(buf.equals("Erreur saisie colonne")){
        System.out.print("Erreur : saisie invalide. Choisis un nombre entre 1 et "+width+" : ");
      }

      else
        System.out.println("> "+buf);

      buffer = console.readLine();
      buf = checkBuffer(buffer,width);
    }

    if (buf.equals("sortir")){
      System.out.println(">>>>>     TCHAO     <<<<<");
      return -1;
    }

    position = Integer.parseInt(buf);

    return position;
  }


}
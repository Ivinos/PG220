package gamePackage.interfacePackage;

public class Color{

  public static String getTextReset(){
    String reset = "\u001B[0m";
    return reset;
  }

  public static String getTextBold(){
    String bold = "\u001B[1m";
    return bold;
  }

  public static String getTextItalic(){
    String italic = "\u001B[3m";
    return italic;
  }


  public static String getBlack(){
    String black = "\u001B[30m";
    return black;
  }

  public static String getRed(){
    String red = "\u001B[31m";
    return red;
  }

  public static String getGreen(){
    String green = "\u001B[32m";
    return green;
  }

  public static String getYellow(){
    String yellow = "\u001B[33m";
    return yellow;
  }

  public static String getBlue(){
    String blue = "\u001B[34m";
    return blue;
  }

  public static String getPurple(){
    String purple = "\u001B[35m";
    return purple;
  }

  public static String getCyan(){
    String cyan = "\u001B[36m";
    return cyan;
  }

  public static String getWhite(){
    String white = "\u001B[37m";
    return white;
  }

  public static String setColor(String buffer, String color, int attr) {
    String[] colors = {"RED","GREEN","YELLOW","BLUE","PURPLE","CYAN","WHITE","BLACK"};
    String reset = getTextReset();
    String bold = getTextBold();
    String italic = getTextItalic();
    String color2 = new String();
    String bufferColor;

    if (color.equals(colors[0]))
      color2 = getRed();
    else if (color.equals(colors[1]))
      color2 = getGreen();
    else if (color.equals(colors[2]))
      color2 = getYellow();
    else if (color.equals(colors[3]))
      color2 = getBlue();
    else if (color.equals(colors[4]))
      color2 = getPurple();
    else if (color.equals(colors[5]))
      color2 = getCyan();
    else if (color.equals(colors[6]))
      color2 = getWhite();
    else if (color.equals(colors[7]))
      color2 = getBlack();
    else if (color.equals("NONE")){
      //color2 = "";
      return buffer; // à changer quand on veut code full fonctionnalité
    }
    else{
      System.out.println("Erreur : couleur inexistante");
      System.exit(0);
    }    

    if (attr == 1)
      bufferColor = bold+color2+buffer+reset;
    else if (attr == 2)
      bufferColor = italic+color2+buffer+reset;
    else 
      bufferColor = color2+buffer+reset;

    return bufferColor;
  }


}

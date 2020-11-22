package game_package;

import java.io.Console;
import java.util.Random;
import java.util.Scanner;

public class Ia extends Player{

  public Ia(String name, int type){
    super(name, type);
  }

  public static int getRandomNumber(int min, int max) throws IllegalArgumentException {
    if (min > max)
      throw new IllegalArgumentException("Max must be greater than min");

    Random nb = new Random();
    return nb.nextInt((max-min)+1)+min;
  }

}

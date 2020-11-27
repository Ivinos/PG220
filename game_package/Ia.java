package game_package;

import java.util.Random;

public class Ia extends Player{

  public Ia(String name){
    super(name);
  }


  // Return a random number
  public static int getRandomNumber(int min, int max) throws IllegalArgumentException {
    if (min > max)
      throw new IllegalArgumentException("Max must be greater than min");

    Random nb = new Random();
    return nb.nextInt((max-min)+1)+min;
  }
  

  public int choice(){
    int position;
    position = getRandomNumber(1,7);

    return position;
  }


}

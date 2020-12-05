package gamePackage;

import java.util.Random;

public class Ia extends Player{

  public Ia(String name, int numeroPlayer){
    super(name,numeroPlayer);
  }


  // Return a random number
  public static int getRandomNumber(int min, int max) throws IllegalArgumentException {
    if (min > max)
      throw new IllegalArgumentException("Max must be greater than min");

    Random nb = new Random();
    return nb.nextInt((max-min)+1)+min;
  }


  public int choice(Grid grid){
    int position;
    int min = 1;
    int max = grid.width;
    position = getRandomNumber(min,max); 

    return position;
  }


}

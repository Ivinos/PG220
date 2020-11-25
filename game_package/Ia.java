package game_package;

import java.util.Random;

public class Ia extends Player{

  public Ia(String name){
    super(name);
  }

  public int choice(){
    int position;
    position = getRandomNumber(1,7);

    return position;
  }


}

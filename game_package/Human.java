package game_package;

import java.io.Console;


public class Human extends Player{

  public Human(String name){
    super(name);
  }


  public int choice(){
    String buffer;
    Console console = System.console();
    int position = 0;

    buffer = console.readLine();

    if (buffer.equals("sortir")){
      System.out.println("You quit the game");
      System.exit(0);
    }
    else{
      // buffer = check_buffer(buffer);

      position = Integer.parseInt(buffer);
      while (position < 1 || position > 7){
        System.out.print("Wrong position. Please choose a number from 1 to 7 : ");
        // position = position.next();
        position = Integer.parseInt(console.readLine());  // rajouter des conditions de test
        System.out.println("");
      }
    }
  return position;
  }


}

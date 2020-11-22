package game_package;

public class Player{

  private String name; // name of the player
  private int type; // type of the player (human/IA)

  public Player(String name, int type){
    this.name = name;
    this.type = type;
  }

  public String getName(){
    return name;
  }

  public int getType(){
    return type;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setType(int type){
    this.type = type;
  }

}

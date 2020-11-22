package game_package;

public class Player{

  private String name; // name of the player
  private String type; // type of the player (human/IA)

  public Player(String name, String type){
    this.name = name;
    this.type = type;
  }

  public String getName(){
    return name;
  }

  public String getType(){
    return type;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setType(String type){
    this.type = type;
  }

}

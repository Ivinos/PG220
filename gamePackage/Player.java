package gamePackage;

abstract class Player {

  public String name; // name of the player

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public abstract int choice();
}

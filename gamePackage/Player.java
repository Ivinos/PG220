package gamePackage;

abstract class Player {

  public String name; // name of the player
  public int numeroPlayer;

  public Player(String name, int numeroPlayer) {
    this.name = name;
    this.numeroPlayer = numeroPlayer;
  }

  public String getName() {
    return name;
  }

  public int getNumeroPlayer() {
    return numeroPlayer;
  }

  // public void setName() {
  //   this.name = name;
  // }

  // public void setNumeroPlayer() {
  //   this.numeroPlayer = numeroPlayer;
  // }

  public abstract int choice();
}

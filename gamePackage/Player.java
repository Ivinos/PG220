package gamePackage;

abstract class Player {

  public String name;
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

  public abstract int choice(int[][] grid, int width, int height);
}

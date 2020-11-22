package game_package;

public class Game{

  // Attributs
  public Player player1;
  public Player player2;
  public Grid grid;

  // Constructeurs
  public Game(String player1, int type_player1, String player2, int type_player2, int width, int height){
    this.player1 = new Player(player1, type_player1);
    this.player2 = new Player(player2, type_player2);
    this.grid = new Grid(width, height);
  }

  public static void main(String[] args) {

  }

}

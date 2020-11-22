package game_package;

public class Game{

    // Attributs
    public Player player1;
    public Player player2;
    public Grid grid;

    // Constructeur
    public Game(String[] input_players, int width, int height){
        if (input_players[1] == "1"){
            this.player1 = new Human(input_players[0]);
        } else {
            this.player1 = new Ia(input_players[0]);
        }

        if (input_players[3] == "1"){
          this.player1 = new Human(input_players[2]);
        } else {
          this.player1 = new Ia(input_players[2]);
        }

        this.grid = new Grid(width, height);
    }

    // Getteurs
    public Grid getGrid(){
        return grid;
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

    // Méthodes
    public void play(){
        interface_package.Display.display_grid(getGrid().values);
    }

  public static void main(String[] args) {

  }

}

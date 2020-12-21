package gamePackage;

import gamePackage.interfacePackage.Display;
import gamePackage.interfacePackage.CheckInput;
import gamePackage.interfacePackage.WriteInLog;
import gamePackage.playerPackage.*;

import java.util.Scanner;
import java.util.ArrayList; // Pour tableau de Player dynamique

public class Game{

    // Attributs
    private ArrayList<Player> players;
    private Grid grid;
    private int[] score;
    private int rounds;
    private final int tokens;
    private final int numberPlayers;

    private final static Display disp = new Display();
    private final static WriteInLog write = new WriteInLog();
    private final static CheckInput check = new CheckInput();

    // Constructeur
    public Game(String[] inputPlayers, int numberPlayers, int width, int height, int[] score, int rounds, int tokens){
      int name;
      int type;
      int numeroPlayer;
      this.players = new ArrayList<Player>(); // On initialise la collection (elle est actuellement vide)

      for (int i = 0; i<2*numberPlayers; i+=2) {
          type = i;
          name = i + 1;
          numeroPlayer = (i / 2) + 1;

          if (inputPlayers[type].equals("humain"))
              this.players.add(new Human(inputPlayers[name], numeroPlayer, inputPlayers[type]));
          else // ia normale
              this.players.add(new Ia(inputPlayers[name], numeroPlayer, inputPlayers[type]));

      }
      this.grid = new Grid(width, height);
      this.score = score;
      this.rounds = rounds;
      this.tokens = tokens;
      this.numberPlayers = numberPlayers;
    }

    // Getteurs
    public Grid getGrid(){
        return grid;
    }

    public Player getPlayer(int who){
       return players.get(who-1);
    }

    public int getScore(int i){
        return score[i];
    }

    public int getRounds(){
      return rounds;
    }

    public int getNumberPlayers(){
      return numberPlayers;
    }

    public int getTokens(){
      return tokens;
    }

    public int getHeight(){
        return grid.getHeight();
    }

    public int getWidth(){
        return grid.getWidth();
    }

    // Setters
    private void setScore(int i){
        score[i] += 1;
    }

    // Autres méthodes
    private int checkRounds(){
      int res = 0;
      for (int i = 0; i<this.numberPlayers; i++){
        if (this.score[i] == this.rounds)
          res = 1;
      }
      return res;
    }

    // Renvoie vrai si le nombre total de manche jouée = le nombre de manche à jouer
    public int checkRoundsBestOf(){
        int res = 0;
        int sum = 0;
        for (int i = 0; i<this.numberPlayers; i++){
            sum += this.score[i];
        }
        if (sum == this.rounds)
            res = 1;
        return res;
    }

    public String writeScore(){
      String res;
      res = "Score "+this.score[0];

      for (int i = 1; i<this.numberPlayers; i++){
        res = res+" - "+this.score[i];
      }
      return res;
    }

    private int getRoundNumber(){
        int sum = 1; // Car on commence à la manche 1
        for (int i = 0; i<numberPlayers; i++){
            sum += score[i];
        }
        return sum;
    }

    // Retourne le numéro du joueur qui joue
    private int whichPlayer(int tour, int roundNumber){
      int res = tour%this.numberPlayers; // 1er tour = 1, 2ème = 0, puis 1 ect

      int roundParity = (roundNumber-1)%this.numberPlayers; // 1er round = 0, 2ème round = 1, puis 0 ect

      if (res + roundParity == 0)
          res = this.numberPlayers;
      // En gros 2%2 = 0 sauf que c'est le dernier joueur
      // Et rajouter plus 1 au res ne marche pas car sinon le p1 ne joue pas en premier
      return res + roundParity;
    }

    private int nullTab(int[] tab, int len){
      int bool = 0;
      for (int i=0; i<len; i++){
        if (tab[i] == 1)
            bool = 1;
      }
      return bool;
    }

    // Actions pendant le jeu
    public void play(){
      int tour = 1; // i = numéro de tour (le joueur 1 commence à jouer) (se réinitialise à chaque manche)
      int who; // numéro de joueur
      int position = -3; // -3 correspond à un move non valide (colonne pleine)
      int[] wins = new int[this.numberPlayers];
      int equality = 0;

      disp.displayGrid(grid);

      while(nullTab(wins, this.numberPlayers) == 0 && equality == 0){
        who = whichPlayer(tour, getRoundNumber()); // pour 2 joueurs who = 1 ou 2
        position = -3;

        while(position == -3){

            position = this.getPlayer(who).choice(grid.getValues(), grid.getWidth(), grid.getHeight(), this.tokens);

            if (position == -1) // commande "sortir"
                System.exit(0);
            else{
                position = grid.updateGrid(position, who, write);
            }
        }

        if (position == -1) // commande "sortir"
          System.exit(0);

        disp.displayGrid(grid);
        
        if(grid.victoryCheck(this.tokens,position-1) == 1){ // -1 car en java, l'indexe commence à 0
          write.writeBuffer("Joueur "+who+" gagne");
          System.out.println("Joueur "+who+" gagne");
          setScore(who-1); 
          wins[who-1] = 1; 
        }

        else if(grid.equalityCheck() == 1){
          equality = 1;
          write.writeBuffer("Egalite");
          System.out.println("Egalite");
        }

      tour++;
      }
      grid.resetGrid(); // On réinitialise la grille
    }

}
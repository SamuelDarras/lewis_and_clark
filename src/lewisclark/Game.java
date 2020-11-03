package lewisclark;

import java.util.*;

public class Game {
    int nbJoueur;
    List<Joueur> players;
    Joueur currentPlayer;
    Random rd;
    int curr_player_idx;

    public Game(Random random) {
        players = new ArrayList<>();
        rd = random;
    }

    public void start() {
        curr_player_idx = rd.nextInt(players.size()-1);
        currentPlayer = players.get(curr_player_idx);
    }

    public void initGame(String couleur) throws Exception {
        for (int i = 0; i < nbJoueur; i++){
            addPlayer(couleur);
        }
    }

    public void addPlayer(String couleur) throws Exception {
        players.add(new Joueur(couleur));
    }

    public void setNbJoueur(int nb) {
        this.nbJoueur = nb;
    }

    public int getNbJoueur(){
        return  this.nbJoueur;
    }
}

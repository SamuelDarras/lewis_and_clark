package lewisclark;

import java.util.*;

public class Game {
    int nbJoueur;
    List<Joueur> players;
    public Joueur currentPlayer;
    Plateau plateau;
    Random rd;
    int curr_player_idx;

    public Game(Random random) {
        players = new ArrayList<>();
        rd = random;
        plateau = new Plateau();
        nbJoueur=0;
        curr_player_idx=0;
        currentPlayer=null;
    }

    public void start() {
        curr_player_idx = rd.nextInt(players.size());
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
        return this.nbJoueur;
    }

    public void afficheJoueur(){
        for(Joueur j : players){
            j.getCouleur();
        }
    }
}

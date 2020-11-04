package lewisclark;

import java.util.*;

public class Game {
    int nbJoueur;
    List<Joueur> players;
    public Joueur currentPlayer;
    StartCharDeck startCharDeck;
    Plateau plateau;
    Random rd;
    int curr_player_idx;

    public Game(Random random) {
        players = new ArrayList<>();
        rd = random;
        plateau = new Plateau();
        startCharDeck = new StartCharDeck();
        nbJoueur=0;
        curr_player_idx=0;
        currentPlayer=null;
    }

    public void start() {
        curr_player_idx = rd.nextInt(players.size());
        currentPlayer = players.get(curr_player_idx);
    }



    public void addPlayer(String couleur) throws Exception {
        players.add(new Joueur(couleur));
        giveStartCardsToColor(couleur);
    }
    public void giveStartCardsToColor(String couleur){
        List<StartCharCard> cards = startCharDeck.getStartCharByColor(couleur);
        for(StartCharCard c : cards) {
            players.get(players.size()-1).addCard(c);
        }
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

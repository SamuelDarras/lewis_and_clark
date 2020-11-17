package lewisclark;

import java.util.*;

public class Game {
    int nbJoueur;
    List<Joueur> players;
    public Joueur currentPlayer;
    StartCharDeck startCharDeck;
    public Plateau plateau;
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

    public void start() throws Exception {
        curr_player_idx = rd.nextInt(players.size());
        currentPlayer = players.get(curr_player_idx);
        currentPlayer.addRessourceToMiniPlateauExpedition(1, new Ressource(PieceEnum.FOURRURE));
        currentPlayer.addRessourceToMiniPlateauExpedition(1, new Ressource(PieceEnum.FOURRURE));
        currentPlayer.addRessourceToMiniPlateauExpedition(1, new Ressource(PieceEnum.FOURRURE));
        currentPlayer.addRessourceToMiniPlateauExpedition(2, new Ressource(PieceEnum.EQUIPEMENT));
        currentPlayer.addRessourceToMiniPlateauExpedition(2, new Ressource(PieceEnum.EQUIPEMENT));
        currentPlayer.addRessourceToMiniPlateauExpedition(2, new Ressource(PieceEnum.EQUIPEMENT));

        for (int i = 0; i < 5; i++) {
            plateau.ajouterCarteAchat(plateau.deck.cards.remove(0));
        }
        plateau.trierCarteAchat();
    }

    public void addPlayer(Joueur joueur) {
        players.add(joueur);
        giveStartCardsToColor(joueur.couleur);
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

    public Joueur getCurrentPlayer() { return currentPlayer; }

    public void afficheJoueur(){
        for(Joueur j : players){
            System.out.println(j.getCouleur());
        }
    }

    public void nextTurn() {
        curr_player_idx += 1;
        curr_player_idx %= players.size();
        currentPlayer = players.get(curr_player_idx);
    }

    public Plateau getPlateau() {
        return plateau;
    }
}

package lewisclark;

import java.util.*;
import Error.*;

public class Game {
    int nbJoueur;
    public List<Joueur> players;
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
        players.get(curr_player_idx).setDejaAcheter(false);
        curr_player_idx += 1;
        curr_player_idx %= players.size();
        currentPlayer = players.get(curr_player_idx);
    }

    public void deplaceEclaireur(int count) throws NotPossibleToMoveException {
        if (count != 0){
            boolean isPositif = count > 0;
            if (!isPositif && currentPlayer.getPositionEclaireurs() + count < 0) throw new NotPossibleToMoveException();
            boolean isDeplaced;
            currentPlayer.setPositionEclaireurs(currentPlayer.getPositionEclaireurs() + count);
            do{
                isDeplaced = false;
                for (Joueur joueur : this.players){
                    if (!joueur.equals(currentPlayer))
                        if (joueur.getPositionEclaireurs() == currentPlayer.getPositionEclaireurs() && currentPlayer.getPositionEclaireurs() != 0){
                            if (isPositif)
                                currentPlayer.setPositionEclaireurs(currentPlayer.getPositionEclaireurs() + 1);
                            else
                                currentPlayer.setPositionEclaireurs(currentPlayer.getPositionEclaireurs() - 1);
                            isDeplaced = true;
                        }
                }
            }while (isDeplaced);

        }
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void playCard(Joueur joueur, Card cardJoue,int nbIndiens,Card cardAssocie ) throws RessourceOutOfDisponibleException {

    }

    public void chefExpedition(PieceEnum ressource, int offset) throws OutOfRessourceInBateauxException {
        if (currentPlayer.miniPlateau.countNbRessource(ressource) < 1)
            throw new OutOfRessourceInBateauxException();

        plateau.defausser(currentPlayer, ressource);
        System.out.println(currentPlayer.getPositionEclaireurs()+2);

        switch (ressource){
            case NOURRITURE: currentPlayer.setPositionEclaireurs(currentPlayer.getPositionEclaireurs()+2-offset); break;
            case PYROGUE: currentPlayer.setPositionEclaireurs(currentPlayer.getPositionEclaireurs()+4-offset); break;
            case CHEVAL: currentPlayer.setPositionEclaireurs(currentPlayer.getPositionEclaireurs()+2-offset); break;
        }
    }

    public Joueur getPlayerByColor(String color){
        for(Joueur j : players){
            if (j.couleur.equals(color))
                return j;
        }
        return null;
    }


    public void bucheron() throws Exception {
        if (plateau.getNbressource(PieceEnum.BOIS) >= 1)
            currentPlayer.miniPlateau.addRessourceDansBateau(currentPlayer.miniPlateau.getValideBateau(), plateau.giveRessource(PieceEnum.BOIS));
        else
            throw new RessourceOutOfDisponibleException();
    }

    public void chaseur() throws Exception {
        if (plateau.getNbressource(PieceEnum.NOURRITURE) >= 1)
            currentPlayer.miniPlateau.addRessourceDansBateau(currentPlayer.miniPlateau.getValideBateau(), plateau.giveRessource(PieceEnum.NOURRITURE));
        else
            throw new RessourceOutOfDisponibleException();
    }

    public void forgeron() throws Exception {
        if (plateau.getNbressource(PieceEnum.EQUIPEMENT) >= 1)
            currentPlayer.miniPlateau.addRessourceDansBateau(currentPlayer.miniPlateau.getValideBateau(), plateau.giveRessource(PieceEnum.EQUIPEMENT));
        else
            throw new RessourceOutOfDisponibleException();
    }

    public void trapeur() throws Exception {
        if (plateau.getNbressource(PieceEnum.FOURRURE) >= 1)
            currentPlayer.miniPlateau.addRessourceDansBateau(currentPlayer.miniPlateau.getValideBateau(), plateau.giveRessource(PieceEnum.FOURRURE));
        else
            throw new RessourceOutOfDisponibleException();
    }
}

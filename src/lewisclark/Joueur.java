package lewisclark;

import java.util.ArrayList;
import java.util.List;
import Error.*;

public class Joueur {

    String couleur;
    public MiniPlateauExpedition miniPlateau;
    public List<Card> cards;

    Plateau plateau;

    public Joueur(String couleur, Plateau plateau) throws Exception {
        this.couleur     = couleur;
        this.plateau     = plateau;
        this.miniPlateau = new MiniPlateauExpedition();
        this.cards       = new ArrayList<>();
        this.miniPlateau.addBasicRessource(this.plateau);
    }

    public void addRessourceToMiniPlateauExpedition(int numBateau, Ressource p) throws Exception {
        miniPlateau.addRessourceDansBateau(numBateau, p);
    }

    public void addCard(Card c){
        cards.add(c);
    }

    public String getCouleur(){
        return this.couleur;
    }

    public void jouer(Card card) throws Exception {
        if (card.getNombreChoixPossible() == 1){
            jouer(card, 1);
        }else throw new NotActionChooseException();
    }

    /**
     * Joue une carte
     * @param card la dite carte
     * @param index premiere action = 1, seconde = 2 etc...
     * @throws Exception Si il n'y a pas assez de ressource ou demande plus que normalement
     */
    public void jouer(Card card, int index) throws Exception {
        if (index > card.getNombreChoixPossible() || index <= 0) throw new OutOfActionPossibleException();
        if (card.getCoute() != null)
            if (this.miniPlateau.countNbRessource(card.getCoute()[index - 1].type) == 0)
                throw new RessourceOutOfDisponibleException();
            else
                this.plateau.defausser(this,card, index);
        int positionBatteau = this.miniPlateau.getValideBateau();
        if (positionBatteau == -1) throw new BateauFullException();
        this.miniPlateau.addRessourceDansBateau(positionBatteau,card.getPossede()[index - 1]);
    }

    public void print(){
        for (Card c : cards)
            System.out.println(c.toString());
        System.out.println(couleur);
    }
}

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
        if (card.getCoute() != null)
            if (this.miniPlateau.countNbRessource(card.getCoute().type) == 0)
                throw new RessourceOutOfDisponibleException();
            else
                this.plateau.defausser(this,card);
        int positionBatteau = this.miniPlateau.getValideBateau();
        if (positionBatteau == -1) throw new BateauFullException();
        this.miniPlateau.addRessourceDansBateau(positionBatteau,card.getPossede());
    }

    public void print(){
        for (Card c : cards)
            System.out.println(c.toString());
        System.out.println(couleur);
    }
}

package lewisclark;

import java.util.ArrayList;
import java.util.List;

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

    public void print(){
        for (Card c : cards)
            System.out.println(c.toString());
        System.out.println(couleur);
    }
}

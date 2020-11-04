package lewisclark;

import java.util.ArrayList;
import java.util.List;

public class Joueur {

    String couleur;
    public MiniPlateauExpedition miniPlateau;
    List<Card> cards;

    public Joueur(String couleur) throws Exception {
        this.couleur     = couleur;
        this.miniPlateau = new MiniPlateauExpedition();
        this.cards       = new ArrayList<>();
        this.miniPlateau.addBasicRessource();
    }

    private void addRessourceToMiniPlateauExpedition(int numBateau, Ressource p) throws Exception {
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

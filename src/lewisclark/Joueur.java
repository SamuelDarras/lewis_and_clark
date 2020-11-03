package lewisclark;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Joueur {

    String couleur;
    public MiniPlateauExpedition miniPlateau;

    public Joueur(String couleur) {
        this.couleur = couleur;
        this.miniPlateau = new MiniPlateauExpedition();
    }

    private void addRessourceToMiniPlateauExpedition(int numBateau, Ressource p) throws Exception {
        miniPlateau.addRessourceDansBateau(numBateau, p);
    }

    public void getCouleur(){
        System.out.println(this.couleur);
    }
}

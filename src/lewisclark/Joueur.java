package lewisclark;

public class Joueur {

    String couleur;
    public MiniPlateauExpedition miniPlateau;

    public Joueur(String couleur) throws Exception {
        this.couleur = couleur;
        this.miniPlateau = new MiniPlateauExpedition();
        this.miniPlateau.addBasicRessource();
    }

    private void addRessourceToMiniPlateauExpedition(int numBateau, Ressource p) throws Exception {
        miniPlateau.addRessourceDansBateau(numBateau, p);
    }

    public String getCouleur(){
        return this.couleur;
    }

    public void print(){
        System.out.println(couleur);
    }
}

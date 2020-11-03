package lewisclark;

public class Joueur {

    String couleur;
    public MiniPlateauExpedition miniPlateau;

    public Joueur(String couleur) throws Exception {
        this.couleur = couleur;
        this.miniPlateau = new MiniPlateauExpedition();
        this.addRessourceToMiniPlateauExpedition(0,Ressource.giveRessource(PieceEnum.INDIEN));
        this.addRessourceToMiniPlateauExpedition(0,Ressource.giveRessource(PieceEnum.FOURRURE));
        this.addRessourceToMiniPlateauExpedition(0,Ressource.giveRessource(PieceEnum.NOURRITURE));
        this.addRessourceToMiniPlateauExpedition(0,Ressource.giveRessource(PieceEnum.EQUIPEMENT));
    }

    private void addRessourceToMiniPlateauExpedition(int numBateau,PieceEnum p) throws Exception {
        if(p == PieceEnum.INDIEN)
            miniPlateau.addIndienDansBateau(numBateau,p);
        else
            miniPlateau.addRessourceDansBateau(numBateau, p);
    }

    public void getCouleur(){
        System.out.println(this.couleur);
    }

    public void print(){
        System.out.println(couleur);
    }
}

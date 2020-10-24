package lewisclark;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Joueur {

    String couleur;
    public MiniPlateauExpedition miniPlateau;

    public Joueur() throws Exception {
        this("rouge");
    }

    public Joueur(String couleur) throws Exception {
        this.couleur = couleur;
        this.miniPlateau = new MiniPlateauExpedition();
        this.addRessourceToMiniPlateauExpedition(0,Ressource.giveRessource(PieceEnum.INDIEN));
        this.addRessourceToMiniPlateauExpedition(0,Ressource.giveRessource(PieceEnum.FOURRURE));
        this.addRessourceToMiniPlateauExpedition(0,Ressource.giveRessource(PieceEnum.NOURRITURE));
        this.addRessourceToMiniPlateauExpedition(0,Ressource.giveRessource(PieceEnum.EQUIPEMENT));
    }

    public static void initJoueur(int nbJoueur, List<Joueur> players) throws Exception{
        Scanner scan = new Scanner(System.in);

        List<String> color = new ArrayList<>() {{
            add("rouge");
            add("jaune");
            add("bleu");
            add("vert");
            add("violet");

        }};

        for (int i=0; i< nbJoueur; i++){
            String equipe ="";

            do{
                System.out.println("Entrer couleur parmi :");
                for (String name : color)
                    System.out.print(name+" ");
                equipe = scan.nextLine();
            }while (!color.contains(equipe));

            color.remove(equipe);

            Joueur joueur = new Joueur(equipe);

            players.add(joueur);
        }
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
}

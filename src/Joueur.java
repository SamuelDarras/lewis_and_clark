import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Error.*;

public class Joueur {

    String couleur;
    public MiniPlateauExpedition miniPlateau;


    public Joueur(){

    }

    public Joueur(String equipe) {
        this.couleur = equipe;
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
            joueur.addMiniPlateauExpedition();
            joueur.addRessourceToMiniPlateauExpedition(0,Ressource.giveRessource(PieceEnum.INDIEN));
            joueur.addRessourceToMiniPlateauExpedition(0,Ressource.giveRessource(PieceEnum.FOURRURE));
            joueur.addRessourceToMiniPlateauExpedition(0,Ressource.giveRessource(PieceEnum.NOURRITURE));
            joueur.addRessourceToMiniPlateauExpedition(0,Ressource.giveRessource(PieceEnum.EQUIPEMENT));
            players.add(joueur);
        }
    }

    private void addRessourceToMiniPlateauExpedition(int numBateau,PieceEnum p) throws Exception {
        if(p == PieceEnum.INDIEN)
            miniPlateau.addIndienDansBateau(numBateau,p);
        else
            miniPlateau.addRessourceDansBateau(numBateau, p);

    }

    private void addMiniPlateauExpedition() {
        miniPlateau = new MiniPlateauExpedition();
    }


    public void getCouleur(){
        System.out.println(this.couleur);
    }
}

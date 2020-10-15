import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Error.*;

public class Joueur {

    String couleur;
    public List<PieceEnum> inventaireJoueur = new ArrayList<>();

    public Joueur(){

    }

    public Joueur(String equipe) {
        this.couleur = equipe;
    }

    public static void initJoueur(int nbJoueur, List<Joueur> players) throws RessourceOutOfDisponibleException, RessourceNotExisteException {
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
            joueur.addRessource(Ressource.giveRessource(PieceEnum.INDIEN));

            players.add(joueur);
        }
    }

    public void addRessource(PieceEnum pieceEnum){
        if (pieceEnum != null){
            inventaireJoueur.add(pieceEnum);
        }
    }

    public void getCouleur(){
        System.out.println(this.couleur);
    }
}

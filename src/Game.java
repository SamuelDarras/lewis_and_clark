import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    private List<PionEnum> indiensOnPlateau;

    public Game() {
        this.indiensOnPlateau = new ArrayList<>();
    }

    public static void main(String[] args) {

        int nbJoueur;
        List<Joueur> players = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

        System.out.println("Entrer nb de joueur(s) entre 1 et 5");
        nbJoueur = scan.nextInt();

        while (nbJoueur<1 || nbJoueur>5){
            System.out.println("Entrer nb de joueur(s) entre 1 et 5");
            nbJoueur = scan.nextInt();
        }

        colorChoose(nbJoueur, players);

    }

    private static void colorChoose(int nbJoueur, List<Joueur> players) {
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

            System.out.println("Entrer couleur parmi :");
            for (String name : color){
                System.out.print(name+" ");
            }
            equipe = scan.nextLine();

            while (!color.contains(equipe)){
                System.out.println("Entrer couleur parmi :");
                for (String name : color){
                    System.out.print(name+" ");
                }
                equipe = scan.nextLine();
            }

            color.remove(equipe);

            players.add(new Joueur(equipe));
        }
    }

    void init(){
        //Indiens
        for (int i = 0; i < 18; i++)
            indiensOnPlateau.add(PionEnum.INDIEN);
    }

    public List<PionEnum> getIndiensOnPlateau() {
        return indiensOnPlateau;
    }

    public void setIndiensOnPlateau(List<PionEnum> indiensOnPlateau) {
        this.indiensOnPlateau = indiensOnPlateau;
    }
}

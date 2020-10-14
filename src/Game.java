import org.jetbrains.annotations.NotNull;

import java.util.*;
import Error.*;

public class Game {

    private final Map<String, List<PieceEnum>> RESSOURCE;

    public Game() {
        RESSOURCE = new HashMap<>();
    }

    public static void main(String[] args) {

        int nbJoueur;
        List<Joueur> players = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

        do{
            System.out.println("Entrer nb de joueur(s) entre 1 et 5");
            nbJoueur = scan.nextInt();
        }while (nbJoueur<1 || nbJoueur>5);

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

            do{
                System.out.println("Entrer couleur parmi :");
                for (String name : color)
                    System.out.print(name+" ");
                equipe = scan.nextLine();
            }while (!color.contains(equipe));

            color.remove(equipe);

            players.add(new Joueur(equipe));
        }
    }

    void init(){
        List<PieceEnum> indiensOnPlateau = new ArrayList<PieceEnum>();

        //Indiens
        for (int i = 0; i < 18; i++)
            indiensOnPlateau.add(PieceEnum.INDIEN);

        List<PieceEnum> boisOnPlateau = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            boisOnPlateau.add(PieceEnum.BOIS);

        List<PieceEnum> fourrure = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            fourrure.add(PieceEnum.FOURRURE);

        List<PieceEnum> nouriture = new ArrayList<>();
        for (int i = 0; i < 15; i++)
            nouriture.add(PieceEnum.NOURRITURE);

        List<PieceEnum> equipement = new ArrayList<>();
        for (int i = 0; i < 15; i++)
            equipement.add(PieceEnum.EQUIPEMENT);

        RESSOURCE.put("Indiens", indiensOnPlateau);
        RESSOURCE.put("Bois", boisOnPlateau);
        RESSOURCE.put("Fourrure", fourrure);
        RESSOURCE.put("Nourriture", nouriture);
        RESSOURCE.put("Equipement", equipement);
    }

    public List<PieceEnum> getIndiensOnPlateau() {
        return RESSOURCE.get("Indiens");
    }

    public void setIndiensOnPlateau(List<PieceEnum> indiensOnPlateau) {
        RESSOURCE.put("Indiens", indiensOnPlateau);
    }

    public List<PieceEnum> getBoisOnPlateau() {
        return RESSOURCE.get("Bois");
    }

    public List<PieceEnum> getFourrureOnPlateau() {
        return RESSOURCE.get("Fourrure");
    }

    public List<PieceEnum> getNourritureOnPlateau() {
        return RESSOURCE.get("Nourriture");
    }

    public List<PieceEnum> getEquipementOnPlateau() {
        return RESSOURCE.get("Equipement");
    }

    public PieceEnum giveRessource(@NotNull PieceEnum ressource) throws RessourceOutOfDisponibleException, RessourceNotExisteException {
        PieceEnum tampon = null;
        try{
            switch (ressource){
                case BOIS -> tampon = RESSOURCE.get("Bois").remove(0);
                case INDIEN -> tampon = RESSOURCE.get("Indiens").remove(0);
                case FOURRURE -> tampon = RESSOURCE.get("Fourrure").remove(0);
                case EQUIPEMENT -> tampon = RESSOURCE.get("Equipement").remove(0);
                case NOURRITURE -> tampon = RESSOURCE.get("Nourriture").remove(0);
                default -> throw new RessourceNotExisteException();
            }

        }catch (Exception e){
            throw new RessourceOutOfDisponibleException();
        }
        return tampon;
    }
}

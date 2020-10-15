import org.jetbrains.annotations.NotNull;
import java.util.*;
import Error.*;

public class Game {



    public Game() {

    }

    public void init() throws RessourceOutOfDisponibleException, RessourceNotExisteException {
        int nbJoueur;
        List<Joueur> players = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

        do{
            System.out.println("Entrer nb de joueur(s) entre 1 et 5");
            nbJoueur = scan.nextInt();
        }while (nbJoueur<1 || nbJoueur>5);

        Joueur.initJoueur(nbJoueur, players);
    }
}

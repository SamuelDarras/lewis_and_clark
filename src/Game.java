import org.jetbrains.annotations.NotNull;
import java.util.*;
import Error.*;

public class Game {
    int nbJoueur;
    List<Joueur> players = new ArrayList<>();
    Joueur currentPlayer;

    public Game() {
        Random rd = new Random();
        currentPlayer = players.get(1+rd.nextInt(nbJoueur-1));
    }

    public void init() throws Exception{
        Scanner scan = new Scanner(System.in);

        do{
            System.out.println("Entrer nb de joueur(s) entre 1 et 5");
            nbJoueur = scan.nextInt();
        }while (nbJoueur<1 || nbJoueur>5);

        Joueur.initJoueur(nbJoueur, players);
    }

    public void getCurrentPlayer(){
        System.out.println(currentPlayer);;
    }
}

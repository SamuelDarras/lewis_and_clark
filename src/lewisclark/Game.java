package lewisclark;

import java.util.*;

public class Game {
    int nbJoueur;
    List<Joueur> players;
    Joueur currentPlayer;
    Random rd;
    int curr_player_idx;

    public Game(Random random) {
        players = new ArrayList<>();
        rd = new Random();
    }

    public void start() {
        curr_player_idx = rd.nextInt(players.size()-1);
        currentPlayer = players.get(curr_player_idx);
    }

    public void init() throws Exception{
        Scanner scan = new Scanner(System.in);

        do{
            System.out.println("Entrer nb de joueur(s) entre 1 et 5");
            nbJoueur = scan.nextInt();
        }while (nbJoueur<1 || nbJoueur>5);

        Joueur.initJoueur(nbJoueur, players);
    }

    public void addPlayer(Joueur new_joueur) {
        players.add(new_joueur);
    }

    public Joueur getCurrentPlayer(){
        return currentPlayer;
    }

    public void nextTurn() {
        curr_player_idx += 1;
        curr_player_idx %= players.size();
        currentPlayer = players.get(curr_player_idx);
    }
}

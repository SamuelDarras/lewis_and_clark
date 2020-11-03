package lewisclark;

import java.util.*;

public class Game {
    int nbJoueur;
    List<Joueur> players;
    Joueur currentPlayer;
    Plateau plateau;
    Random rd;
    int curr_player_idx;

    public Game(Random random) {
        players = new ArrayList<>();
        rd = random;
        plateau = new Plateau();
        nbJoueur=0;
        curr_player_idx=0;
        currentPlayer=null;
    }

    public void start() {
        curr_player_idx = rd.nextInt(players.size()-1);
        currentPlayer = players.get(curr_player_idx);
    }

    public void initGame(String couleur) throws Exception {
        for (int i = 0; i < nbJoueur; i++){
            addPlayer(couleur);
        }
    }

    public void addPlayer(String couleur) throws Exception {
        Joueur player = new Joueur(couleur);
        player.miniPlateau.addIndienDansBateau(0, plateau.giveRessource(PieceEnum.INDIEN));
        player.miniPlateau.addRessourceDansBateau(0, plateau.giveRessource(PieceEnum.FOURRURE));
        player.miniPlateau.addRessourceDansBateau(0, plateau.giveRessource(PieceEnum.NOURRITURE));
        player.miniPlateau.addRessourceDansBateau(0, plateau.giveRessource(PieceEnum.EQUIPEMENT));
        players.add(new Joueur(couleur));
    }

    public void setNbJoueur(int nb) {
        this.nbJoueur = nb;
    }

    public int getNbJoueur(){
        return this.nbJoueur;
    }
}

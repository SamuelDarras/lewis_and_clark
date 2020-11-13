package lewisclark;

import Error.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plateau {
    public Map<PieceEnum, List<Ressource>> ressources = new HashMap<>();
    private final List<Card> carteAchat = new ArrayList<>();

    public Plateau() {
        ressources.put(PieceEnum.INDIEN    , new ArrayList<>());
        ressources.put(PieceEnum.BOIS      , new ArrayList<>());
        ressources.put(PieceEnum.FOURRURE  , new ArrayList<>());
        ressources.put(PieceEnum.EQUIPEMENT, new ArrayList<>());
        ressources.put(PieceEnum.NOURRITURE, new ArrayList<>());

        for (int i = 0; i < 18; i++) {
            ressources.get(PieceEnum.INDIEN).add(new Ressource(PieceEnum.INDIEN));
        }
        for (int i = 0; i < 20; i++) {
            ressources.get(PieceEnum.BOIS).add(new Ressource(PieceEnum.BOIS));
        }
        for (int i = 0; i < 20; i++) {
            ressources.get(PieceEnum.FOURRURE).add(new Ressource(PieceEnum.FOURRURE));
        }
        for (int i = 0; i < 15; i++) {
            ressources.get(PieceEnum.NOURRITURE).add(new Ressource(PieceEnum.NOURRITURE));
        }
        for (int i = 0; i < 15; i++) {
            ressources.get(PieceEnum.EQUIPEMENT).add(new Ressource(PieceEnum.EQUIPEMENT));
        }

        //TODO Mettre les cartes possible a acheter
        //carteAchat.add();
    }

    public void achatCarte(Joueur joueur, int index) throws RessourceOutOfDisponibleException {
        if (joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE) >= index+1 && joueur.miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT)
                >= 1){
            for (int i = 0; i < index + 1; i++)
                this.defausser(joueur, PieceEnum.FOURRURE);
            this.defausser(joueur, PieceEnum.EQUIPEMENT);
            joueur.addCard(this.carteAchat.get(index));
            //TODO il faut add une carte possible a Acheter
            //this.carteAchat.add();
        }
        else
            throw new RessourceOutOfDisponibleException();
    }

    public void defausser(Joueur joueur, Card card) throws NotActionChooseException {
        if (card.getNombreChoixPossible() == 1)
            defausser(joueur, card.getCoute()[0].type);
        else throw new NotActionChooseException();
    }

    /**
     *
     * @param joueur
     * @param card
     * @param index premiere action = 1, seconde = 2 etc...
     * @throws Exception
     */
    public void defausser(Joueur joueur, Card card, int index) throws Exception {
        if (index > card.getNombreChoixPossible()) throw new OutOfActionPossibleException();
        defausser(joueur, card.getCoute()[index - 1].type);
    }

    public void defausser(Joueur joueur, PieceEnum pieceEnum){
        joueur.miniPlateau.deleteRessource(pieceEnum);
        this.dropRessource(pieceEnum);
    }

    public Ressource giveRessource(PieceEnum ressource) throws RessourceOutOfDisponibleException {
        Ressource tampon;
        try{
            tampon = ressources.get(ressource).remove(0);
        }catch (Exception e){
            throw new RessourceOutOfDisponibleException();
        }
        return tampon;
    }

    public void dropRessource(PieceEnum ressource){
        ressources.get(ressource).add(new Ressource(ressource));
    }

    public void dropRessource(Ressource ressource){
        ressources.get(ressource.type).add(ressource);
    }

    public List<Ressource> getTypeRessourceList(PieceEnum type) {
        return ressources.get(type);
    }

    public int getNbressource(PieceEnum pieceEnum){
        return  ressources.get(pieceEnum).size();
    }
}

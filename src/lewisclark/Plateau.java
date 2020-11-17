package lewisclark;

import Error.*;

import java.util.*;

public class Plateau {
    public Map<PieceEnum, List<Ressource>> ressources = new HashMap<>();
    private final List<Card> carteAchat = new ArrayList<>();
    public BuyCardDeck deck;

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

        deck = new BuyCardDeck();

        //carteAchat.add();
    }

    public void achatCarte(Joueur joueur, int index) throws RessourceOutOfDisponibleException, JournalVideException{
        if (carteAchat.isEmpty()) { throw new JournalVideException(); }
        if ((index < 0) || (index >= carteAchat.size())) throw new IndexOutOfBoundsException();
        if ((joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE) <= index+1) || (joueur.miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT) <= carteAchat.get(index).getStrength())){
            throw new  RessourceOutOfDisponibleException();
        }
        for (int i = 0; i < index+1; i++) {
            defausser(joueur,PieceEnum.FOURRURE);
        }
        for (int i = 0; i < carteAchat.get(index).getStrength(); i++) {
            defausser(joueur, PieceEnum.EQUIPEMENT);
        }
        joueur.addCard(carteAchat.remove(index));
        ajouterCarteAchat(deck.cards.remove(0));
    }

    public void trierCarteAchat() {
        Collections.sort(carteAchat);
    }

    public void defausser(Joueur joueur, Card card) throws Exception {
        if (card.getNombreChoixPossible() == 1)
            defausser(joueur, card,1);
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
        if (index > card.getNombreChoixPossible() || index <= 0) throw new OutOfActionPossibleException();
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

    public List<Card> getCarteAchat(){ return carteAchat; }

    public void ajouterCarteAchat(Card carte){ carteAchat.add(carte); }

}

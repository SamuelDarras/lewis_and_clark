package lewisclark;

import Error.*;

import java.util.*;

public class Plateau {
    public Map<PieceEnum, List<Ressource>> ressources = new HashMap<>();
    private final List<Card> carteAchat = new ArrayList<>();
    public BuyCardDeck deck;
    private final Map<PositionEmplacementVillage, Integer>  emplacementIndienOnVillage;

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
        this.emplacementIndienOnVillage = new HashMap<>();
        initEmplacement();
        //carteAchat.add();
    }

    private void initEmplacement(){
        emplacementIndienOnVillage.put(PositionEmplacementVillage.EquipementBois, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.Cheval, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.DeffauseTroisCarte, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.DoubleRessourceCondition, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.IndienReserve, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.JeSaisPasCeQueCest, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.Kayak, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.MelangeCarte, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.NouritureFourrure, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.Powo, 0);
    }

    public void achatCarte(Joueur joueur, int index) throws RessourceOutOfDisponibleException, JournalVideException, DejaAchatException {
        if (joueur.isDejaAcheter()) throw new DejaAchatException();
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
        joueur.setDejaAcheter(true);
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
     * @param joueur
     * @param card
     * @param index premiere action = 1, seconde = 2 etc...
     * @throws Exception
     */
    public void defausser(Joueur joueur, Card card, int index) throws Exception {
        if (index > card.getNombreChoixPossible() || index <= 0) throw new OutOfActionPossibleException();
        for (Ressource ressource : card.getCoute().get(index - 1))
            defausser(joueur, ressource.type);
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

    public int getNbIndienOnPosition(PositionEmplacementVillage positionEmplacementVillage){
        int nombreIndient = 0;
        switch (positionEmplacementVillage){
            case MelangeCarte, IndienReserve, EquipementBois,
                    NouritureFourrure, DeffauseTroisCarte,
                    DoubleRessourceCondition -> nombreIndient = 1;
            case JeSaisPasCeQueCest -> nombreIndient = 2;
            case Kayak, Cheval -> nombreIndient = 3;
            case Powo -> nombreIndient = 1000;
            default -> nombreIndient = -1;
        }
        return nombreIndient;
    }

    public int lastPlaceForIndienOnPosition(PositionEmplacementVillage positionEmplacementVillage){
        return getNbIndienOnPosition(positionEmplacementVillage) - emplacementIndienOnVillage.get(positionEmplacementVillage);
    }

    /**
     * Regarde si on peut placer un indient (juste si son position est possible)
     * @return true si le possitionement est possible
     */
    public boolean addOneIndientOnPossition(PositionEmplacementVillage positionEmplacementVillage) {
        return lastPlaceForIndienOnPosition(positionEmplacementVillage) > 0;
    }

    public void addIndien(PositionEmplacementVillage positionEmplacementVillage){
        emplacementIndienOnVillage.put(positionEmplacementVillage,emplacementIndienOnVillage.get(positionEmplacementVillage) + 1);
    }

    public Map<PositionEmplacementVillage, Integer> getEmplacementIndienOnVillage() {
        return emplacementIndienOnVillage;
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

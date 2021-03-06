package lewisclark;

import Error.*;

import java.util.*;

public class Plateau {
    public Map<PieceEnum, List<Ressource>> ressources = new HashMap<>();
    private final List<Card> carteAchat = new ArrayList<>();
    public BuyCardDeck deck;
    private final Map<PositionEmplacementVillage, Integer>  emplacementIndienOnVillage;
    private final EnvironnementEnum[] caseVictoire = new EnvironnementEnum[50];

    public Plateau() {
        ressources.put(PieceEnum.INDIEN    , new ArrayList<>());
        ressources.put(PieceEnum.BOIS      , new ArrayList<>());
        ressources.put(PieceEnum.FOURRURE  , new ArrayList<>());
        ressources.put(PieceEnum.EQUIPEMENT, new ArrayList<>());
        ressources.put(PieceEnum.NOURRITURE, new ArrayList<>());
        ressources.put(PieceEnum.PYROGUE, new ArrayList<>());
        ressources.put(PieceEnum.CHEVAL, new ArrayList<>());

        for (int i = 0; i < 18; i++)
            ressources.get(PieceEnum.INDIEN).add(new Ressource(PieceEnum.INDIEN));

        for (int i = 0; i < 20; i++)
            ressources.get(PieceEnum.BOIS).add(new Ressource(PieceEnum.BOIS));

        for (int i = 0; i < 20; i++)
            ressources.get(PieceEnum.FOURRURE).add(new Ressource(PieceEnum.FOURRURE));

        for (int i = 0; i < 15; i++)
            ressources.get(PieceEnum.NOURRITURE).add(new Ressource(PieceEnum.NOURRITURE));

        for (int i = 0; i < 15; i++)
            ressources.get(PieceEnum.EQUIPEMENT).add(new Ressource(PieceEnum.EQUIPEMENT));

        for (int i=0; i<15; i++)
            ressources.get(PieceEnum.PYROGUE).add(new Ressource(PieceEnum.PYROGUE));

        for (int i=0; i<15; i++)
            ressources.get(PieceEnum.CHEVAL).add(new Ressource(PieceEnum.CHEVAL));

        deck = new BuyCardDeck();
        this.emplacementIndienOnVillage = new HashMap<>();
        initEmplacement();
        initCaseToWin();
    }

    private void initEmplacement(){
        emplacementIndienOnVillage.put(PositionEmplacementVillage.FourrureBois, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.EquipementBois, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.Cheval, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.DefauseTroisCarteAndshuffle, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.DoubleRessourceCondition, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.IndienReserve, 1);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.BoisBateau, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.Kayak, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.ActiverCarte, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.NouritureFourrure, 0);
        emplacementIndienOnVillage.put(PositionEmplacementVillage.Powo, 0);
    }

    private void initCaseToWin(){
        for (int i = 0; i < 17;i++)
            this.caseVictoire[i] = EnvironnementEnum.riviere;
        this.caseVictoire[17] = EnvironnementEnum.mixte;

        for (int i = 18; i < 18+7; i++)
            this.caseVictoire[i] = EnvironnementEnum.montagne;
        this.caseVictoire[25] = EnvironnementEnum.mixte;

        for (int i = 26; i < 26+5; i++)
            this.caseVictoire[i] = EnvironnementEnum.riviere;

        for (int i = 31; i < 31+3; i++)
            this.caseVictoire[i] = EnvironnementEnum.montagne;

        for (int i = 34; i < 34+4;i++)
            this.caseVictoire[i] = EnvironnementEnum.riviere;
        for (int i = 39 ; i < 49 ; i++)
            this.caseVictoire[i] = EnvironnementEnum.victoire;
    }

    public void achatCarte(Joueur joueur, int index) throws RessourceOutOfDisponibleException, JournalVideException, DejaAchatException {
        if (joueur.isDejaAcheter()) { throw new DejaAchatException(); }
        if (carteAchat.isEmpty()) { throw new JournalVideException(); }
        if ((index < 0) || (index >= carteAchat.size())) throw new IndexOutOfBoundsException();
        if ((joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE) < index+1) || (joueur.miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT) < carteAchat.get(index).getStrength())){
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
        Ressource ressourceDefause = joueur.miniPlateau.deleteRessource(pieceEnum);
        this.dropRessource(ressourceDefause);
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
            case ActiverCarte, IndienReserve, EquipementBois, FourrureBois,
                    NouritureFourrure, DefauseTroisCarteAndshuffle,
                    DoubleRessourceCondition -> nombreIndient = 1;
            case BoisBateau -> nombreIndient = 2;
            case Kayak, Cheval -> nombreIndient = 3;
            case Powo -> nombreIndient = 1000;
            default -> nombreIndient = -1;
        }
        return nombreIndient;
    }

    public int getNombreIndienOnPosition(PositionEmplacementVillage pos){
        return emplacementIndienOnVillage.get(pos);
    }

    public int lastPlaceForIndienOnPosition(PositionEmplacementVillage positionEmplacementVillage){
        return getNbIndienOnPosition(positionEmplacementVillage) - emplacementIndienOnVillage.get(positionEmplacementVillage);
    }

    /**
     * Regarde si on peut placer un indien (juste si sa position est possible)
     * @return true si le possitionement est possible
     */
    public boolean addOneIndientOnPossition(PositionEmplacementVillage positionEmplacementVillage) {
        return lastPlaceForIndienOnPosition(positionEmplacementVillage) > 0;
    }

    public void addIndien(PositionEmplacementVillage positionEmplacementVillage, Joueur joueur) throws Exception {
        addIndien(positionEmplacementVillage,joueur, null);
    }

    /**
     * <div style="color:green">Test faits</div>
     * @param positionEmplacementVillage
     * @param joueur
     * @param cout
     * @throws Exception
     */
    public void addIndien(PositionEmplacementVillage positionEmplacementVillage, Joueur joueur, List<PieceEnum> cout) throws Exception {
        if (!addOneIndientOnPossition(positionEmplacementVillage))
            throw new EmplacementVillageFullException();
        emplacementIndienOnVillage.put(positionEmplacementVillage,emplacementIndienOnVillage.get(positionEmplacementVillage) + 1);
        switch (positionEmplacementVillage){
            case Cheval -> trocCheval(cout, joueur);
            case Kayak -> fabricationDePirogue(joueur);
            case FourrureBois -> throw new CarteNotCompatibleException();
            case EquipementBois -> artisanat(joueur);
            case NouritureFourrure -> chasse(joueur);
        }
    }

    public void addIndien(PositionEmplacementVillage pos){
        emplacementIndienOnVillage.put(pos,emplacementIndienOnVillage.get(pos) + 1);
    }

    /**
     * Action 1 = 1 ==> Fourrure
     * Action 2 = 2 ==> Bois
     * <div style="color:green">Test faits</div>
     * @param positionEmplacementVillage
     * @param joueur
     * @param nombre
     * @throws Exception
     */
    public void addIndien(PositionEmplacementVillage positionEmplacementVillage, Joueur joueur, int nombre) throws Exception {
        if (!addOneIndientOnPossition(positionEmplacementVillage))
            throw new EmplacementVillageFullException();
        if (positionEmplacementVillage != PositionEmplacementVillage.FourrureBois)
            throw new CarteNotCompatibleException();
        if (nombre == 1)
            for (int i = 0; i < 2; i++){
                joueur.miniPlateau.addRessourceDansBateau(giveRessource(PieceEnum.FOURRURE));
            }
        else if (nombre == 2)
            for (int i = 0; i < 2; i++)
                joueur.miniPlateau.addRessourceDansBateau(giveRessource(PieceEnum.BOIS));
    }

    public void deleteIndienOnPos(PositionEmplacementVillage pos){
        emplacementIndienOnVillage.put(pos, 0);
    }

    /**
     * <div style="color:green">Test faits</div>
     * @param joueur
     * @throws Exception
     */
    private void chasse(Joueur joueur) throws Exception {
        if (!joueur.miniPlateau.isEnoughPlace(2)) throw new BateauFullException();
        joueur.miniPlateau.addRessourceDansBateau(new Ressource(PieceEnum.NOURRITURE));
        joueur.miniPlateau.addRessourceDansBateau(new Ressource(PieceEnum.FOURRURE));
    }

    /**
     * Prend 3 ressources differentes pour donner un cheval
     * <div style="color:green">Test faits</div>
     * Est utiliser dans add indient
     * @param pieceEnums represente les ressources que le joueur est pres a défausser
     * @param joueur le joueur avec ses ressources
     * @throws Exception
     */
    private void trocCheval(List<PieceEnum> pieceEnums, Joueur joueur) throws Exception {
        //Regarde si il a les ressources
        for (PieceEnum pieceEnum : pieceEnums)
            if (joueur.miniPlateau.countNbRessource(pieceEnum) < 1)
                throw new OutOfRessourceInBateauxException();

        if (pieceEnums.size() != 3)
            throw new OutOfRessourceNeed();
        if (pieceEnums.get(0).equals(pieceEnums.get(1)) || pieceEnums.get(0).equals(pieceEnums.get(2)) || pieceEnums.get(1).equals(pieceEnums.get(2)))
            throw new IncompatiblePieceException();
        //Ajout et suppression des ressources
        for (PieceEnum pieceEnum : pieceEnums)
            this.defausser(joueur, pieceEnum);
        joueur.miniPlateau.addRessourceDansBateau(joueur.miniPlateau.getValideBateau(),new Ressource(PieceEnum.CHEVAL));
    }

    /**
     * <div style="color:green">Test faits</div>
     * @param joueur
     * @throws Exception
     */
    private void artisanat(Joueur joueur) throws Exception {
        if (!joueur.miniPlateau.isEnoughPlace(2)) throw new BateauFullException();
        joueur.miniPlateau.addRessourceDansBateau(giveRessource(PieceEnum.EQUIPEMENT));
        joueur.miniPlateau.addRessourceDansBateau(giveRessource(PieceEnum.BOIS));
    }

    /**
     * Permet en echange de deux bois d'avoir un pyrogue
     * <div style="color:green">Test faits</div>
     * @param  joueur est le joueur qui vas deffauser et recevoir la recompenses
     * @throws Exception
     */
    private void fabricationDePirogue(Joueur joueur) throws Exception {
        if (joueur.miniPlateau.countNbRessource(PieceEnum.BOIS) < 2) throw new OutOfRessourceInBateauxException();

        for (int i = 0; i < 2; i++)
            this.defausser(joueur, PieceEnum.BOIS);
        joueur.miniPlateau.addRessourceDansBateau(new Ressource(PieceEnum.PYROGUE));
    }

    public void cadeau(PieceEnum src, Joueur joueur) throws Exception{
        if (getNbressource(src) < 2) throw new RessourceOutOfDisponibleException();

        for (int i=0; i<2; i++){
            joueur.miniPlateau.addRessourceDansBateau(giveRessource(src));
        }
    }

    public Map<PositionEmplacementVillage, Integer> getEmplacementIndienOnVillage() {
        return emplacementIndienOnVillage;
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

    public EnvironnementEnum[] getCaseVictoire() {
        return caseVictoire;
    }

    public EnvironnementEnum getOneCaseVictoire(int pos){ return caseVictoire[pos];}

}

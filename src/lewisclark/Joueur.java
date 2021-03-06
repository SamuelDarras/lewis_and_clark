package lewisclark;

import java.util.ArrayList;
import java.util.List;
import Error.*;

public class Joueur {

    String couleur;
    public MiniPlateauExpedition miniPlateau;
    public List<Card> cards;
    private boolean dejaAcheter = false;
    int retardCard;
    Plateau plateau;
    public int positionEclaireurs;
    public int positionCampement;

    public Joueur(String couleur, Plateau plateau) throws Exception {
        this.couleur     = couleur;
        this.plateau     = plateau;
        this.miniPlateau = new MiniPlateauExpedition();
        this.cards       = new ArrayList<>();
        this.miniPlateau.addBasicRessource(this.plateau);
        this.positionEclaireurs = 0;
        positionCampement = 0;
        retardCard = 0;
    }

    public void addRessourceToMiniPlateauExpedition(int numBateau, Ressource p) throws Exception {
        miniPlateau.addRessourceDansBateau(numBateau, p);
    }

    public void addCard(Card c){
        cards.add(c);
    }

    public String getCouleur(){
        return this.couleur;
    }

    public void jouer(Card card,int nbIndiens, Card cardAssocie) throws Exception {
        if (card.getNombreChoixPossible() == 1){
            jouer(card, 1, nbIndiens, cardAssocie);
        }else throw new NotActionChooseException();
    }

    /**
     * Joue une carte
     * @param card la dite carte
     * @param index premiere action = 1, seconde = 2 etc...
     * @param nbIndiens : indiens associé à la carte (0 à 3)
     * @param cardAssocie : card qui est utilisé pour ça force.
     * @throws Exception Si il n'y a pas assez de ressource ou demande plus que normalement
     */
    public void jouer(Card card, int index, int nbIndiens, Card cardAssocie ) throws Exception {
        if (index > card.getNombreChoixPossible() || index <= 0) throw new OutOfActionPossibleException();
        //Regarder si on peut supprimer les ressources
        index--;
        boolean boolCout = card.getCoute().get(index) != null && card.getCoute().get(index).get(0) != null;
        boolean boolGain = card.getPossede().get(index) != null && card.getPossede().get(index).get(0) != null;
        //placement des indiens sur la carte.
        if(cardAssocie != null)
            cardAssocie.setUsed(true);
        List<Ressource> indienAssocie = new ArrayList<>();
        System.out.println(nbIndiens);
        for (int i = 0 ; i < nbIndiens ; i++){
            indienAssocie.add(miniPlateau.deleteRessource(PieceEnum.INDIEN));
        }
        card.placerIndiensSurCarte(indienAssocie,cardAssocie);
        //
        if (boolCout)
            for (Ressource ressource : card.getCoute().get(index))
                if (this.miniPlateau.countNbRessource(ressource.type) <= 0)
                    throw new RessourceOutOfDisponibleException();
        //Regarder les batteaux si ils peuvent stocker des ressources et combien
        if (boolGain)
            if (!this.miniPlateau.isEnoughPlace(card.getPossede().get(index).size()))
                throw new BateauFullException();
        //Traitement
        if (boolCout)
            for (Ressource ressource : card.getCoute().get(index))
                this.plateau.defausser(this,ressource.type);
        if (boolGain)
            for (Ressource ressource : card.getPossede().get(index))
                this.miniPlateau.addRessourceDansBateau(this.miniPlateau.getValideBateau(), ressource);
    }
    /**
     *
     */
    public void setCampementPreOrganisation()  {
        retardCard = calcRetardCards();
        for(Card card : cards){
            if(card.getUsed() && !card.isAssocied()){
                for(Ressource indien : card.renouvellementCard()){
                    try {
                        miniPlateau.addIndienDansBateauxDispo(indien);
                    } catch (BateauFullException e) {
                        e.printStackTrace();
                    }
                }
                card.removeIndiensAssocie();
            }
            else
                card.renouvellementCard();
        }
    }
    public void setCampementPostOrganisation(){
        int retard = retardCard + miniPlateau.calcRetardBateaux();
        if(positionEclaireurs-retard > positionCampement )
            positionCampement = positionEclaireurs-retard;
    }

    public void print(){
        for (Card c : cards)
            System.out.println(c.toString());
        System.out.println(couleur);
    }

    public boolean isDejaAcheter() {
        return dejaAcheter;
    }

    public void setDejaAcheter(boolean dejaAcheter) {
        this.dejaAcheter = dejaAcheter;
    }

    public void addIndienOnPositionIndien(PositionEmplacementVillage positionEmplacementVillage) throws Exception {
        addIndienOnPositionIndien(positionEmplacementVillage, null);
    }

    public void addIndienOnPositionIndien(PositionEmplacementVillage positionEmplacementVillage, List<PieceEnum> ressourceCout) throws Exception {
        if (this.miniPlateau.countNbRessource(PieceEnum.INDIEN) > 0){
            this.plateau.addIndien(positionEmplacementVillage, this, ressourceCout);
            this.miniPlateau.deleteRessource(PieceEnum.INDIEN);
        }
        else
            throw new notEnoughIndiansOnMiniPlateauException();
    }

    public void addIndienOnPositionIndien(PositionEmplacementVillage positionEmplacementVillage, int choix) throws Exception {
        if (this.miniPlateau.countNbRessource(PieceEnum.INDIEN) > 0){
            this.plateau.addIndien(positionEmplacementVillage, this, choix);
            this.miniPlateau.deleteRessource(PieceEnum.INDIEN);
        }
        else
            throw new notEnoughIndiansOnMiniPlateauException();
    }

    public boolean isWin(){
        return this.positionEclaireurs >= 39;
    }

    public int getPositionEclaireurs() {
        return positionEclaireurs;
    }

    public void setPositionEclaireurs(int positionEclaireurs) {
        this.positionEclaireurs = positionEclaireurs;
    }

    public Card findCard(String name){

        for (Card c : cards){
            if (c.getCardName().equals(name))
                return c;
        }
        return null;
    }

    /**
     * <div style="color:green">Test faits</div>
     * @return le retard
     */
    public int calcRetardCards(){
        int retard = 0;
        for (Card card : cards){
            if(!card.used){
                retard++;
            }
        }
        return retard;
    }
}

package lewisclark;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card implements Comparable<Card>{
    private final String cardName;
    private final int strength;
    private final boolean used;
    private final String actionDescription;
    private final PieceEnum badge;
    private final List<List<Ressource>> possede;
    private final List<List<Ressource>> coute;
    private final int nombreChoixPossible;

    public Card(Card nouvelleCard) {
        this(nouvelleCard.cardName, nouvelleCard.strength,nouvelleCard.actionDescription, nouvelleCard.badge,
                nouvelleCard.possede, nouvelleCard.coute);
    }

    public static Card nouvelleCard(){
        return nouvelleCard((Ressource)null,null);
    }

    public static Card nouvelleCard(Ressource ressource){
        return nouvelleCard(ressource, null);
    }

    public static Card nouvelleCard(List<Ressource> ressource){
        return nouvelleCard(ressource, null);
    }

    public static Card nouvelleCard(Ressource ressource, Ressource coute){
        return nouvelleCard("defaultName",0,"defaulfActionDescription",null, ressource, coute);
    }

    public static Card nouvelleCard(String cardName, int strength,String actionDescription,PieceEnum badge, Ressource possede, Ressource coute){
        List<Ressource> ressourcesPossede = new ArrayList<>();
        List<Ressource> ressourcesCoute = new ArrayList<>();
        ressourcesPossede.add(possede);
        ressourcesCoute.add(coute);

        return getCard(cardName, strength, actionDescription, badge, ressourcesPossede, ressourcesCoute);
    }

    public static Card nouvelleCard(String cardName, int strength,String actionDescription,PieceEnum badge, Ressource[] possede, Ressource[] coute){
        List<Ressource> ressourcesCoute = new ArrayList<>(Arrays.asList(coute));
        List<Ressource> ressourcesPossede = new ArrayList<>(Arrays.asList(possede));

        return getCard(cardName, strength, actionDescription, badge, ressourcesPossede, ressourcesCoute);
    }

    @NotNull
    private static Card getCard(String cardName, int strength, String actionDescription, PieceEnum badge, List<Ressource> ressourcesPossede, List<Ressource> ressourcesCoute) {
        List<List<Ressource>> listPossede = new ArrayList<>();
        List<List<Ressource>> listCoute = new ArrayList<>();

        listPossede.add(ressourcesPossede);
        listCoute.add(ressourcesCoute);

        return new Card(cardName, strength, actionDescription, badge, listPossede, listCoute);
    }

    public Card(String cardName,String actionDescription,int strength,PieceEnum badge){
        this(cardName,strength,actionDescription,badge, null, null);
    }

    public Card(String cardName, int strength,String actionDescription,PieceEnum badge,
                @Nullable List<List<Ressource>> possede, @Nullable List<List<Ressource>> coute){
        this.cardName          = cardName;
        this.strength          = strength;
        this.actionDescription = actionDescription;
        this.badge             = badge;
        this.used              = false;
        //assert possede.length == coute.length : "Doit être de la meme taille";
        this.possede           = possede;
        this.coute             = coute;
        if (coute == null)
            this.nombreChoixPossible = 0;
        else
            this.nombreChoixPossible = coute.size();
    }

    public Card(String cardName, int indianCost, String actionDescription, PieceEnum badge) {
        this(cardName,indianCost,actionDescription,badge, null,null);
    }

    public static Card nouvelleCard(List<Ressource> possede, List<Ressource> coute) {
        List<List<Ressource>> ressouce = new ArrayList<>();
        ressouce.add(possede);
        List<List<Ressource>> ressouceCoute = new ArrayList<>();
        ressouceCoute.add(possede);

        return new Card("defaultName",0,"defaulfActionDescription",null, ressouce, ressouceCoute);
    }

    public Card(List<List<Ressource>> possede, List<List<Ressource>> coute) {
        this("defaultName",0,"defaulfActionDescription",null, possede, coute);
    }

    public List<List<Ressource>> getPossede() {
        return possede;
    }

    public List<List<Ressource>> getCoute() {
        return coute;
    }

    public int getStrength() { return  strength; }

    public int getNombreChoixPossible() {
        return nombreChoixPossible;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardName='" + cardName + '\'' +
                ", strength=" + strength +
                ", used=" + used +
                ", actionDescription='" + actionDescription + '\'' +
                ", badges='" + badge + '\'' +
                '}';
    }

    public String getCardName(){
        return this.cardName;
    }

    public int compareTo(Card c){
        if (this.getStrength() < c.getStrength()) return -1;
        if (this.getStrength() == c.getStrength()) return 0;
        else return 1;
    }
}

package lewisclark;

public class Card implements Comparable<Card>{
    private final String cardName;
    private final int strength;
    private final boolean used;
    private final String actionDescription;
    private final PieceEnum badge;
    private final Ressource[] possede;
    private final Ressource[] coute;
    private final int nombreChoixPossible;

    public Card(){
        this((Ressource) null, null);
    }

    public Card(Ressource ressource){
        this(ressource, null);
    }
    public Card(Ressource ressource, Ressource coute){
        this("defaultName",0,"defaulfActionDescription",null, ressource, coute);
    }

    public Card(String cardName, int strength,String actionDescription,PieceEnum badge, Ressource possede, Ressource coute){
        this(cardName,strength,actionDescription,badge,new Ressource[]{possede},new Ressource[]{coute});
    }

    public Card(String cardName, int strength,String actionDescription,PieceEnum badge, Ressource[] possede, Ressource[] coute){
        this.cardName          = cardName;
        this.strength          = strength;
        this.actionDescription = actionDescription;
        this.badge             = badge;
        this.used              = false;
        assert possede.length == coute.length : "Doit être de la meme taille";
        this.possede           = possede;
        this.coute             = coute;
        this.nombreChoixPossible = coute.length;
    }

    public Card(String cardName, int indianCost, String actionDescription, PieceEnum badge) {
        this(cardName,indianCost,actionDescription,badge, (Ressource) null,null);
    }

    public Card(Ressource[] possede, Ressource[] coute) {
        this("defaultName",0,"defaulfActionDescription",null, possede, coute);
    }

    public Ressource[] getPossede() {
        return possede;
    }

    public Ressource[] getCoute() {
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

package lewisclark;

public class Card {
    private String cardName;
    private int strength;
    private boolean used;
    private String actionDescription;
    private String badge;
    private Ressource possede;
    private Ressource coute;

    public Card(){
        this( null, null);
    }

    public Card(Ressource ressource){
        this(ressource, null);
    }
    public Card(Ressource ressource, Ressource coute){
        this("defaultName",0,"defaulfActionDescription","nothing", ressource, coute);
    }

    public Card(String cardName, int strength,String actionDescription,String badge, Ressource possede, Ressource coute){
        this.cardName          = cardName;
        this.strength          = strength;
        this.actionDescription = actionDescription;
        this.badge             = badge;
        this.used              = false;
        this.possede           = possede;
        this.coute             = coute;
    }

    public Card(String cardName, int indianCost, String actionDescription, String badge) {
        this(cardName,indianCost,actionDescription,badge,null,null);
    }

    public Ressource getPossede() {
        return possede;
    }

    public Ressource getCoute() {
        return coute;
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
}

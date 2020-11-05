package lewisclark;

public class Card {
    private String cardName;
    private int strength;
    private boolean used;
    private String actionDescription;
    private String badge;

    public Card(){
        this("defaultName",0,"defaulfActionDescription","nothing");
    }

    public Card(String cardName, int strength,String actionDescription,String badge){
        this.cardName          = cardName;
        this.strength          = strength;
        this.actionDescription = actionDescription;
        this.badge             = badge;
        this.used              = false;
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

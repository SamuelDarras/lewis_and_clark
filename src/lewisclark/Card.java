package lewisclark;

public class Card {
    private String cardName;
    private int indianCost;
    private boolean used;
    private String description;

    public Card(){
        this("defaultName",0,"defaulfDescription");
    }

    public Card(String cardName, int indianCost,String description){
        this.cardName    = cardName;
        this.indianCost  = indianCost;
        this.description = description;
        this.used        = false;
    }

    public String getDescription() {
        return description;
    }
}

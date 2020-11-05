package lewisclark;

public class StartCharCard extends Card{
    private final String type;

    public StartCharCard(){
        super();
        type = "default";
    }
    public StartCharCard(String cardName, int indianCost,String actionDescription,String type,String badge){
        super(cardName, indianCost, actionDescription,badge);
        this.type = type;
    }

    @Override
    public String toString() {
        return "StartCharCard{" + super.toString()+
                "type='" + type + '\'' +
                '}';
    }
}

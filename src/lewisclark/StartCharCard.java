package lewisclark;

public class StartCharCard extends Card{
    private final String type;

    public StartCharCard(){
        super();
        type = "default";
    }
    public StartCharCard(String cardName, int indianCost,String description,String type){
        super(cardName, indianCost, description);
        this.type = type;
    }

    @Override
    public String toString() {
        return "StartCharCard{" + super.toString()+
                "type='" + type + '\'' +
                '}';
    }
}

package lewisclark;

public class StartCharCard extends Card{
    private final String type;

    public StartCharCard(){
        super(Card.nouvelleCard());
        type = "default";
    }

    public StartCharCard(String cardName, int strength,String actionDescription,String type,PieceEnum badge){
        super(cardName, strength, actionDescription,badge);
        this.type = type;
    }

    public StartCharCard(String cardName, int strength,String actionDescription,String type,PieceEnum badge, Ressource[] possede,Ressource[] coute){
        super(Card.nouvelleCard(cardName, strength, actionDescription, badge, possede, coute));
        this.type = type;
    }


    @Override
    public String toString() {
        return "StartCharCard{" + super.toString()+
                "type='" + type + '\'' +
                '}';
    }
}

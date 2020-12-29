package lewisclark;

public class StartCharCard extends Card{

    public StartCharCard(String cardName, int strength,String actionDescription,String type,PieceEnum badge){
        super(cardName, strength, actionDescription,badge, type);
    }

    public StartCharCard(String cardName, int strength,String actionDescription,String type,PieceEnum badge, Ressource[] possede,Ressource[] coute){
        super(Card.nouvelleCard(cardName, strength, actionDescription, badge, possede, coute));
    }

    @Override
    public String toString() {
        return "StartCharCard{" + super.toString()+
                "type='" + '\'' +
                '}';
    }
}

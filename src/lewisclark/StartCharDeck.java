package lewisclark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartCharDeck {
    Map<String, List<StartCharCard>> startCards;

    public StartCharDeck(){
        startCards = new HashMap<>();
        initDeck();
    }

    public void initDeck(){
        String[] commandersName   = new String[]{"Meriwether Lewis","William Clark","John OrdWay","Nathaniel Pryor","Charles Floyd"};
        String[] interpretersName = new String[]{"Pierre Cruzatte","François Labiche","Robert Frazer","George Gibson","J.Baptiste Lepage"};
        String[] lumberjacksName = new String[]{"Hugh McNeal","John B. Thompson","Thomas P. Howard","Patrick Gass ","Hugh Hall"};
        String[] huntersName = new String[]{"Seaman","York","Silas Goodrich","John Colter","William Werner"};
        String[] blacksmithsName = new String[]{"Alexander H. Willard","Joseph Whitehouse","John Shields","William Bratton","John Potts"};
        String[] furTradersName = new String[]{"Richard Windsor","Joseph & Ruben Field","George Shannon","Peter Weiser","John Collins"};
        String[] colors = new String[]{"rouge","jaune","bleu","vert","violet"};

        for(int i = 0 ; i < colors.length;i++){
            List<StartCharCard> cards = new ArrayList<>();
            cards.add(new StartCharCard(commandersName[i],1,"Avancez votre Éclaireur en défaussant Nourriture, Pirogues ou Chevaux.","Chef d'expédition",PieceEnum.FOURRURE));
            cards.add(new StartCharCard(interpretersName[i],2,"Prenez des Indiens sur le plateau et ajoutez-les à votre Expédition.","Interprète",PieceEnum.BOIS));
            cards.add(new StartCharCard(lumberjacksName[i],1,"Collectez du Bois.","Bûcherons",PieceEnum.BOIS));
            cards.add(new StartCharCard(huntersName[i],1,"Collectez de la Nourriture.","chasseur",PieceEnum.NOURRITURE));
            cards.add(new StartCharCard(blacksmithsName[i],1,"Collectez des Équipements.","Forgeron",PieceEnum.EQUIPEMENT));
            cards.add(new StartCharCard(furTradersName[i],1,"Collectez de la Fourrure.","Trappeur",PieceEnum.FOURRURE));
            startCards.put(colors[i], cards);
        }
    }

    public List<StartCharCard> getStartCharByColor(String color){
        List<StartCharCard> cards = startCards.get(color);
        startCards.remove(color);
        return cards;
    }

}

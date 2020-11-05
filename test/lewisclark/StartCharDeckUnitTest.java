package lewisclark;

import org.junit.Assert;
import org.junit.Test;

public class StartCharDeckUnitTest {
     @Test
    public void testGetStartCharByColor(){
        StartCharCard card = new StartCharCard("John Collins",1,"Collectez de la Fourrure.","Trappeur","Fourrure");
        StartCharDeck startDeck = new StartCharDeck();
         Assert.assertEquals(card.getActionDescription(),startDeck.getStartCharByColor("violet").get(5).getActionDescription());
     }
}

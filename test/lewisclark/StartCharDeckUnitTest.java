package lewisclark;

import org.junit.Assert;
import org.junit.Test;

public class StartCharDeckUnitTest {
     @Test
    public void testGetStartCharByColor(){
        StartCharCard card = new StartCharCard("John Collins",1,"Collect Fur.","The Fur Trader");
        StartCharDeck startDeck = new StartCharDeck();
         Assert.assertEquals(card.getDescription(),startDeck.getStartCharByColor("violet").get(5).getDescription());
     }
}

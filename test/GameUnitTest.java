import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameUnitTest {

    @Test
    public void testInitIndienPlateau(){
        Game game = new Game();
        game.init();
        List<PionEnum> pionEnumInit = new ArrayList<PionEnum>();
        for (int i = 0; i < 18; i++)
            pionEnumInit.add(PionEnum.INDIEN);
        Assert.assertEquals(pionEnumInit, game.getIndiensOnPlateau());
    }


}

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameUnitTest {

    @Test
    public void testInitIndienPlateau(){
        Game game = new Game();
        game.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 18; i++)
            pieceEnumInit.add(PieceEnum.INDIEN);
        Assert.assertEquals(pieceEnumInit, game.getIndiensOnPlateau());
    }

    @Test
    public void testInitRessourcesBois(){
        Game game = new Game();
        game.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 20; i++)
            pieceEnumInit.add(PieceEnum.BOIS);
        Assert.assertEquals(pieceEnumInit, game.getBoisOnPlateau());
    }

    @Test
    public void testInitRessourcesFourrure(){
        Game game = new Game();
        game.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 20; i++)
            pieceEnumInit.add(PieceEnum.FOURRURE);
        Assert.assertEquals(pieceEnumInit, game.getFourrureOnPlateau());
    }

    @Test
    public void testInitRessourcesNourriture(){
        Game game = new Game();
        game.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 15; i++)
            pieceEnumInit.add(PieceEnum.NOURRITURE);
        Assert.assertEquals(pieceEnumInit, game.getNourritureOnPlateau());
    }

    @Test
    public void testInitRessourcesEquipement(){
        Game game = new Game();
        game.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 15; i++)
            pieceEnumInit.add(PieceEnum.EQUIPEMENT);
        Assert.assertEquals(pieceEnumInit, game.getEquipementOnPlateau());
    }


}

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameUnitTest {

    @Test
    public void testInitIndienPlateau(){
        Game game = new Game();
        Ressource.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 18; i++)
            pieceEnumInit.add(PieceEnum.INDIEN);
        Assert.assertEquals(pieceEnumInit, Ressource.getIndiensOnPlateau());
    }

    @Test
    public void testInitRessourcesBois(){
        Game game = new Game();
        Ressource.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 20; i++)
            pieceEnumInit.add(PieceEnum.BOIS);
        Assert.assertEquals(pieceEnumInit, Ressource.getBoisOnPlateau());
    }

    @Test
    public void testInitRessourcesFourrure(){
        Game game = new Game();
        Ressource.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 20; i++)
            pieceEnumInit.add(PieceEnum.FOURRURE);
        Assert.assertEquals(pieceEnumInit, Ressource.getFourrureOnPlateau());
    }

    @Test
    public void testInitRessourcesNourriture(){
        Game game = new Game();
        Ressource.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 15; i++)
            pieceEnumInit.add(PieceEnum.NOURRITURE);
        Assert.assertEquals(pieceEnumInit, Ressource.getNourritureOnPlateau());
    }

    @Test
    public void testInitRessourcesEquipement(){
        Game game = new Game();
        Ressource.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 15; i++)
            pieceEnumInit.add(PieceEnum.EQUIPEMENT);
        Assert.assertEquals(pieceEnumInit, Ressource.getEquipementOnPlateau());
    }

}

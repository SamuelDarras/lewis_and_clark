package lewisclark;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameUnitTest {
    Random random;
    /*@Before
    public void setup() {
        random = new Random();
    }

    @Test
    public void testInitIndienPlateau(){
        Game game = new Game(random);
        Ressource.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 18; i++)
            pieceEnumInit.add(PieceEnum.INDIEN);
        Assert.assertEquals(pieceEnumInit, Ressource.getIndiensOnPlateau());
    }

    @Test
    public void testInitRessourcesBois(){
        Game game = new Game(random);
        Ressource.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 20; i++)
            pieceEnumInit.add(PieceEnum.BOIS);
        Assert.assertEquals(pieceEnumInit, Ressource.getBoisOnPlateau());
    }

    @Test
    public void testInitRessourcesFourrure(){
        Game game = new Game(random);
        Ressource.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 20; i++)
            pieceEnumInit.add(PieceEnum.FOURRURE);
        Assert.assertEquals(pieceEnumInit, Ressource.getFourrureOnPlateau());
    }

    @Test
    public void testInitRessourcesNourriture(){
        Game game = new Game(random);
        Ressource.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 15; i++)
            pieceEnumInit.add(PieceEnum.NOURRITURE);
        Assert.assertEquals(pieceEnumInit, Ressource.getNourritureOnPlateau());
    }

    @Test
    public void testInitRessourcesEquipement(){
        Game game = new Game(random);
        Ressource.initRessource();
        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 15; i++)
            pieceEnumInit.add(PieceEnum.EQUIPEMENT);
        Assert.assertEquals(pieceEnumInit, Ressource.getEquipementOnPlateau());
    }
    
    @Test
    public void testLesJoueursJouentAuTourParTourDansLeSensHoraire() throws Exception {
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);

        Game game = new Game(random);
        Joueur j1 = new Joueur("rouge");
        Joueur j2 = new Joueur("bleu");
        Joueur j3 = new Joueur("jaune");
        game.addPlayer(j1);
        game.addPlayer(j2);
        game.addPlayer(j3);

        game.start();
        Assert.assertEquals(game.getCurrentPlayer(), j1);
        game.nextTurn();
        Assert.assertEquals(game.getCurrentPlayer(), j2);
        game.nextTurn();
        Assert.assertEquals(game.getCurrentPlayer(), j3);
    }*/

}

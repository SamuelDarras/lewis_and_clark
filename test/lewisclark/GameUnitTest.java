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
    Game game;
    @Before
    public void setup() {
        random = new Random();
        game = new Game(random);
    }

    @Test
    public void testInitIndienPlateau(){
        for (var i : game.plateau.ressources.get(PieceEnum.INDIEN)) {
            Assert.assertEquals(i.type, PieceEnum.INDIEN);
        }
        Assert.assertEquals(game.plateau.ressources.get(PieceEnum.INDIEN).size(), 18);
    }

    @Test
    public void testInitRessourcesBois(){
        for (var i : game.plateau.ressources.get(PieceEnum.BOIS)) {
            Assert.assertEquals(i.type, PieceEnum.BOIS);
        }
        Assert.assertEquals(game.plateau.ressources.get(PieceEnum.BOIS).size(), 20);
    }

    @Test
    public void testInitRessourcesFourrure(){
        for (var i : game.plateau.ressources.get(PieceEnum.FOURRURE)) {
            Assert.assertEquals(i.type, PieceEnum.FOURRURE);
        }
        Assert.assertEquals(game.plateau.ressources.get(PieceEnum.FOURRURE).size(), 20);
    }

    @Test
    public void testInitRessourcesNourriture(){
        for (var i : game.plateau.ressources.get(PieceEnum.NOURRITURE)) {
            Assert.assertEquals(i.type, PieceEnum.NOURRITURE);
        }
        Assert.assertEquals(game.plateau.ressources.get(PieceEnum.NOURRITURE).size(), 15);
    }

    @Test
    public void testInitRessourcesEquipement(){
        for (var i : game.plateau.ressources.get(PieceEnum.EQUIPEMENT)) {
            Assert.assertEquals(i.type, PieceEnum.EQUIPEMENT);
        }
        Assert.assertEquals(game.plateau.ressources.get(PieceEnum.EQUIPEMENT).size(), 15);
    }

    @Test
    public void testLesJoueursJouentAuTourParTourDansLeSensHoraire() throws Exception {
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);

        Game game = new Game(random);
        Joueur j1 = new Joueur("rouge", game.getPlateau());
        Joueur j2 = new Joueur("bleu" , game.getPlateau());
        Joueur j3 = new Joueur("jaune", game.getPlateau());
        game.addPlayer(j1);
        game.addPlayer(j2);
        game.addPlayer(j3);

        game.start();
        Assert.assertEquals(game.getCurrentPlayer(), j1);
        game.nextTurn();
        Assert.assertEquals(game.getCurrentPlayer(), j2);
        game.nextTurn();
        Assert.assertEquals(game.getCurrentPlayer(), j3);
    }
}

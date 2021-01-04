package lewisclark;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Error.*;

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

    @Test
    public void testDeplacementEclairerJump() throws Exception {
        Game game = new Game(new Random());
        Plateau plateau = new Plateau();
        List<Joueur> joueurs = new ArrayList<>();
        Joueur joueur1 = new Joueur("red",plateau);
        Joueur joueur2 = new Joueur("yellow",plateau);
        Joueur joueur3 = new Joueur("green",plateau);

        joueur2.setPositionEclaireurs(1);
        joueur3.setPositionEclaireurs(2);
        joueurs.add(joueur1);
        joueurs.add(joueur2);
        joueurs.add(joueur3);

        game.players = joueurs;
        game.currentPlayer = joueur1;

        Assert.assertEquals(joueur1.getPositionEclaireurs(),0);

        game.deplaceEclaireur(1);

        Assert.assertEquals(joueur1.getPositionEclaireurs(),3);
    }

    @Test
    public void testDeplacementEclairer() throws Exception {
        Game game = new Game(new Random());
        Plateau plateau = new Plateau();
        List<Joueur> joueurs = new ArrayList<>();
        Joueur joueur1 = new Joueur("red",plateau);
        Joueur joueur2 = new Joueur("yellow",plateau);
        Joueur joueur3 = new Joueur("green",plateau);

        joueur2.setPositionEclaireurs(3);
        joueur3.setPositionEclaireurs(2);
        joueurs.add(joueur1);
        joueurs.add(joueur2);
        joueurs.add(joueur3);

        game.players = joueurs;
        game.currentPlayer = joueur1;

        Assert.assertEquals(joueur1.getPositionEclaireurs(),0);

        game.deplaceEclaireur(1);

        Assert.assertEquals(joueur1.getPositionEclaireurs(),1);
    }

    @Test (expected = NotPossibleToMoveException.class)
    public void testDeplacementArriereError() throws Exception {
        Game game = new Game(new Random());
        Plateau plateau = new Plateau();
        List<Joueur> joueurs = new ArrayList<>();
        Joueur joueur1 = new Joueur("red",plateau);
        Joueur joueur2 = new Joueur("yellow",plateau);
        Joueur joueur3 = new Joueur("green",plateau);

        joueurs.add(joueur1);
        joueurs.add(joueur2);
        joueurs.add(joueur3);

        game.players = joueurs;
        game.currentPlayer = joueur1;

        game.deplaceEclaireur(-1);
    }

    @Test
    public void testDeplacementArriere() throws Exception {
        Game game = new Game(new Random());
        Plateau plateau = new Plateau();
        List<Joueur> joueurs = new ArrayList<>();
        Joueur joueur1 = new Joueur("red",plateau);
        Joueur joueur2 = new Joueur("yellow",plateau);
        Joueur joueur3 = new Joueur("green",plateau);

        joueurs.add(joueur1);
        joueurs.add(joueur2);
        joueurs.add(joueur3);

        joueur1.setPositionEclaireurs(1);

        game.players = joueurs;
        game.currentPlayer = joueur1;

        game.deplaceEclaireur(-1);

        Assert.assertEquals(joueur1.getPositionEclaireurs(), 0);
    }

    @Test
    public void testDeplacementArriereBoost() throws Exception {
        Game game = new Game(new Random());
        Plateau plateau = new Plateau();
        List<Joueur> joueurs = new ArrayList<>();
        Joueur joueur1 = new Joueur("red",plateau);
        Joueur joueur2 = new Joueur("yellow",plateau);
        Joueur joueur3 = new Joueur("green",plateau);

        joueurs.add(joueur1);
        joueurs.add(joueur2);
        joueurs.add(joueur3);

        joueur1.setPositionEclaireurs(2);
        joueur2.setPositionEclaireurs(1);

        game.players = joueurs;
        game.currentPlayer = joueur1;

        game.deplaceEclaireur(-1);

        Assert.assertEquals(joueur1.getPositionEclaireurs(), 0);
    }

    @Test
    public void testGiveRes() throws Exception {
        Game game = new Game(new Random());
        Plateau plateau = new Plateau();
        List<Joueur> joueurs = new ArrayList<>();

        Joueur joueur1 = new Joueur("red", plateau);
        joueurs.add(joueur1);

        game.players = joueurs;
        game.currentPlayer = joueur1;

        game.giveRes(PieceEnum.BOIS);
        Assert.assertEquals(1, joueur1.miniPlateau.countNbRessource(PieceEnum.BOIS));
    }

   @Rule
    public ExpectedException ecouteur = org.junit.rules.ExpectedException.none();
    @Test
    public void testGiveResException() throws Exception {
        Game game = new Game(new Random());
        Plateau plateau = new Plateau();
        List<Joueur> joueurs = new ArrayList<>();

        Joueur joueur1 = new Joueur("red", plateau);
        Joueur joueur2 = new Joueur("bleu", plateau);
        Joueur joueur3 = new Joueur("violet", plateau);
        Joueur joueur4 = new Joueur("jaune", plateau);
        joueurs.add(joueur1);
        joueurs.add(joueur2);
        joueurs.add(joueur3);
        joueurs.add(joueur4);

        game.players = joueurs;
        game.currentPlayer = joueur1;
        for (int i = 0 ; i < joueurs.size() ; i++){
            for (int j = 0 ; j < 5 ;j++){
                game.currentPlayer = joueurs.get(i);
                game.giveRes(PieceEnum.BOIS);
            }
        }
        ecouteur.expect(RessourceOutOfDisponibleException.class);
        game.giveRes(PieceEnum.BOIS);
    }

    @Test
    public void testPowWow() throws Exception {
        Game game = new Game(new Random());

        Assert.assertEquals(1, game.powWow()); //toujours 1 indien sur le plateau
    }
}

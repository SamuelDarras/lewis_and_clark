package lewisclark;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import Error.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JoueurUnitTest {
    Random random;
    Game game;
    @Before
    public void setup() {
        random = new Random();
        game = new Game(random);
    }

    @Test
    public void testAddInventaire() throws Exception {
        Joueur joueur = new Joueur("rouge", game.getPlateau());
        joueur.addRessourceToMiniPlateauExpedition(1,new Ressource(PieceEnum.BOIS));

        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.BOIS));
    }

    @Test
    public void testAddCard() throws Exception {
        Joueur joueur = new Joueur("rouge", game.getPlateau());
        Card card = Card.nouvelleCard();

        joueur.addCard(card);

        Assert.assertEquals(card, joueur.cards.get(0));
    }

    @Test
    public void testJouer() throws Exception {
        Joueur joueur = new Joueur("Rouge", game.getPlateau());
        Card card = Card.nouvelleCard(new Ressource(PieceEnum.FOURRURE), new Ressource(PieceEnum.NOURRITURE));

        joueur.jouer(card);

        Assert.assertEquals(2, joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE));
        Assert.assertEquals(0, joueur.miniPlateau.countNbRessource(PieceEnum.NOURRITURE));
    }

    @Rule
    public ExpectedException ecouteur = ExpectedException.none();
    @Test
    public void testJouerFail() throws Exception {
        Joueur joueur = new Joueur("Rouge", game.getPlateau());
        Card card = Card.nouvelleCard(new Ressource(PieceEnum.FOURRURE), new Ressource(PieceEnum.NOURRITURE));

        joueur.jouer(card);
        ecouteur.expect(RessourceOutOfDisponibleException.class);
        joueur.jouer(card);
    }

    @Test (expected = NotActionChooseException.class)
    public void testJouerNoChoiseMade() throws Exception {
        Joueur joueur = new Joueur("Rouge", game.getPlateau());
        List<List<Ressource>> gainFinal = new ArrayList<>();
        List<List<Ressource>> coutFinal = new ArrayList<>();

        List<Ressource> gain = new ArrayList<>();
        List<Ressource> gain2 = new ArrayList<>();
        List<Ressource> cout = new ArrayList<>();

        gain.add(new Ressource(PieceEnum.FOURRURE));
        gain.add(new Ressource(PieceEnum.NOURRITURE));
        cout.add(new Ressource(PieceEnum.NOURRITURE));
        cout.add(new Ressource(PieceEnum.FOURRURE));

        gainFinal.add(gain);
        gainFinal.add(gain2);
        coutFinal.add(cout);

        Card card = new Card(gainFinal, coutFinal);

        joueur.jouer(card);
    }

    @Test (expected = OutOfActionPossibleException.class)
    public void testJouerChoiseWrongMade() throws Exception {
        Joueur joueur = new Joueur("Rouge", game.getPlateau());
        List<List<Ressource>> gainFinal = new ArrayList<>();
        List<List<Ressource>> coutFinal = new ArrayList<>();

        List<Ressource> gain = new ArrayList<>();
        List<Ressource> gain2 = new ArrayList<>();
        List<Ressource> cout = new ArrayList<>();

        gain.add(new Ressource(PieceEnum.FOURRURE));
        gain.add(new Ressource(PieceEnum.NOURRITURE));
        cout.add(new Ressource(PieceEnum.NOURRITURE));
        cout.add(new Ressource(PieceEnum.FOURRURE));

        gainFinal.add(gain);
        gainFinal.add(gain2);
        coutFinal.add(cout);

        Card card = new Card(gainFinal, coutFinal);

        joueur.jouer(card,2);
    }

    @Test (expected = OutOfActionPossibleException.class)
    public void testJouerOutOfChoix() throws Exception {
        Joueur joueur = new Joueur("Rouge", game.getPlateau());
        List<Ressource> gain = new ArrayList<>();
        List<Ressource> cout = new ArrayList<>();
        gain.add(new Ressource(PieceEnum.FOURRURE));
        gain.add(new Ressource(PieceEnum.NOURRITURE));
        cout.add(new Ressource(PieceEnum.NOURRITURE));
        cout.add(new Ressource(PieceEnum.FOURRURE));
        Card card = Card.nouvelleCard(gain, cout);

        joueur.jouer(card,3);
    }

    @Test (expected = OutOfActionPossibleException.class)
    public void testJouerOutOfChoix2() throws Exception {
        Joueur joueur = new Joueur("Rouge", game.getPlateau());
        List<Ressource> gain = new ArrayList<>();
        List<Ressource> cout = new ArrayList<>();
        gain.add(new Ressource(PieceEnum.FOURRURE));
        gain.add(new Ressource(PieceEnum.NOURRITURE));
        cout.add(new Ressource(PieceEnum.NOURRITURE));
        cout.add(new Ressource(PieceEnum.FOURRURE));
        Card card = Card.nouvelleCard(gain, cout);

        joueur.jouer(card,0);
    }

    @Test
    public void testJouerMultiChoise() throws Exception {
        Joueur joueur = new Joueur("Rouge", game.getPlateau());
        List<List<Ressource>> gainList = new ArrayList<>();
        List<List<Ressource>> coutList = new ArrayList<>();
        List<Ressource> gain = new ArrayList<>();
        List<Ressource> cout = new ArrayList<>();

        List<Ressource> gain2 = new ArrayList<>();
        List<Ressource> cout2 = new ArrayList<>();

        gain.add(new Ressource(PieceEnum.FOURRURE));
        gain2.add(new Ressource(PieceEnum.NOURRITURE));
        cout.add(new Ressource(PieceEnum.NOURRITURE));
        cout2.add(new Ressource(PieceEnum.FOURRURE));

        gainList.add(gain);
        gainList.add(gain2);

        coutList.add(cout);
        coutList.add(cout2);

        Card card = new Card(gainList, coutList);

        joueur.jouer(card,1);
        Assert.assertEquals(2, joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE));
        Assert.assertEquals(0, joueur.miniPlateau.countNbRessource(PieceEnum.NOURRITURE));

        joueur.jouer(card,2);
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE));
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.NOURRITURE));
    }

    @Test
    public void testJouerOnlyBenefi() throws Exception {
        Joueur joueur = new Joueur("Rouge", game.getPlateau());
        Card card = Card.nouvelleCard(new Ressource(PieceEnum.FOURRURE));

        joueur.jouer(card);
        Assert.assertEquals(2, joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE));
    }

    @Test
    public void testJoueurMultiGain() throws Exception {
        Joueur joueur = new Joueur("Rouge",new Plateau());
        List<Ressource> gain = new ArrayList<>();

        gain.add(new Ressource(PieceEnum.FOURRURE));
        gain.add(new Ressource(PieceEnum.FOURRURE));
        gain.add(new Ressource(PieceEnum.FOURRURE));

        Card card = Card.nouvelleCard(gain);

        joueur.jouer(card);

        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE), 4);
    }

    @Test
    public void testJoueurMultiCout() throws Exception {
        Joueur joueur = new Joueur("Rouge",new Plateau());
        List<Ressource> gain = new ArrayList<>();
        List<Ressource> cout = new ArrayList<>();

        gain.add(new Ressource(PieceEnum.FOURRURE));
        gain.add(new Ressource(PieceEnum.FOURRURE));
        gain.add(new Ressource(PieceEnum.FOURRURE));

        Card card = Card.nouvelleCard(gain);

        joueur.jouer(card);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE), 4);

        cout.add(new Ressource(PieceEnum.FOURRURE));
        cout.add(new Ressource(PieceEnum.FOURRURE));
        cout.add(new Ressource(PieceEnum.FOURRURE));

        card = Card.nouvelleCard(null, cout);

        joueur.jouer(card);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE), 1);
    }

    @Test
    public void testPlaceIndienOnPlateau() throws Exception {
        Joueur joueur  = new Joueur("red", new Plateau());
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.INDIEN),1);
        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Cheval);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.INDIEN),0);
    }
}

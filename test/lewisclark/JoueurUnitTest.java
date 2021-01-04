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

        joueur.jouer(card,1 ,1,null);

        Assert.assertEquals(2, joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE));
        Assert.assertEquals(0, joueur.miniPlateau.countNbRessource(PieceEnum.NOURRITURE));
    }

    @Rule
    public ExpectedException ecouteur = ExpectedException.none();
    @Test
    public void testJouerFail() throws Exception {
        Joueur joueur = new Joueur("Rouge", game.getPlateau());
        Card card = Card.nouvelleCard(new Ressource(PieceEnum.FOURRURE), new Ressource(PieceEnum.NOURRITURE));

        joueur.jouer(card,1,null);
        ecouteur.expect(RessourceOutOfDisponibleException.class);
        joueur.jouer(card,1,null);
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

        joueur.jouer(card,1,null);
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

        joueur.jouer(card,2,1,null);
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

        joueur.jouer(card,3,1,null);
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

        joueur.jouer(card,0,1,null);
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

        joueur.jouer(card,1,1,null);
        Assert.assertEquals(2, joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE));
        Assert.assertEquals(0, joueur.miniPlateau.countNbRessource(PieceEnum.NOURRITURE));

        joueur.jouer(card,2,1,null);
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE));
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.NOURRITURE));
    }

    @Test
    public void testJouerOnlyBenefi() throws Exception {
        Joueur joueur = new Joueur("Rouge", game.getPlateau());
        Card card = Card.nouvelleCard(new Ressource(PieceEnum.FOURRURE));

        joueur.jouer(card,1,null);
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

        joueur.jouer(card,1,null);

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

        joueur.jouer(card,1,null);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE), 4);

        cout.add(new Ressource(PieceEnum.FOURRURE));
        cout.add(new Ressource(PieceEnum.FOURRURE));
        cout.add(new Ressource(PieceEnum.FOURRURE));

        card = Card.nouvelleCard(null, cout);

        joueur.jouer(card,1,null);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE), 1);
    }

    @Test
    public void testPlaceIndienOnPlateau() throws Exception {
        Joueur joueur  = new Joueur("red", new Plateau());
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.INDIEN),1);
        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.EquipementBois);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.INDIEN),0);
    }

    @Test
    public void testCalcRetardCards() throws Exception {
        Joueur joueur  = new Joueur("red", new Plateau());
        Card card1 = Card.nouvelleCard();
        Card card2 = Card.nouvelleCard();
        Card card3 = Card.nouvelleCard();
        card3.used = true;

        joueur.addCard(card1);
        joueur.addCard(card2);
        joueur.addCard(card3);
        Assert.assertEquals(2,joueur.calcRetardCards());
    }
    @Test
    public void testSetCamptementPreOrganisation() throws Exception {
        Joueur joueur  = new Joueur("red", new Plateau()); //par défaut 2 indiens dans le miniplateau
        Card card1 = Card.nouvelleCard();
        joueur.addCard(card1);
        joueur.jouer(card1,1,null); // miniplateau avec 1 indien
        joueur.setCampementPreOrganisation();          //retour de l'indien utiliser sur le miniplateau
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.INDIEN));
    }
    @Test
    public void testSetCampementPostOrganisation() throws Exception {
        Joueur joueur  = new Joueur("red", new Plateau());
        joueur.retardCard = 1; // une seule carte n'a pas été joué
        joueur.positionEclaireurs = 2; // l'éclaireur est à la deuxième case du plateau
        joueur.setCampementPostOrganisation();
        Assert.assertEquals(1,joueur.positionCampement);
    }
}

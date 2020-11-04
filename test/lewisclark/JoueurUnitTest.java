package lewisclark;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Error.*;
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
        Card card = new Card();

        joueur.addCard(card);

        Assert.assertEquals(card, joueur.cards.get(0));
    }
}

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
    @Before
    public void setup() {
        random = new Random();
    }

    @Test
    public void testInvetaireAddBois() throws Exception {
        Ressource.initRessource();
        Joueur joueur = new Joueur();
        joueur.miniPlateau.addRessourceDansBateau(1,Ressource.giveRessource(PieceEnum.BOIS));
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.BOIS));
    }

    @Test
    public void testInvetaireAddFourrure() throws Exception {
        Ressource.initRessource();
        Joueur joueur = new Joueur();
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE));
    }

    @Test
    public void testInvetaireAddNourriture() throws Exception {
        Ressource.initRessource();
        Joueur joueur = new Joueur();
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.NOURRITURE));
    }

    @Test
    public void testInvetaireAddEquipement() throws Exception {
        Ressource.initRessource();
        Joueur joueur = new Joueur();
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT));
    }

    @Test
    public void testInvetaireAddIndien() throws Exception {
        Ressource.initRessource();
        Joueur joueur = new Joueur();
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.INDIEN));
    }
}

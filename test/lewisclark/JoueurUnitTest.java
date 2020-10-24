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
        Game game = new Game(random);
        Ressource.initRessource();
        Joueur joueur = new Joueur();
        joueur.miniPlateau.addRessourceDansBateau(1,Ressource.giveRessource(PieceEnum.BOIS));

        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 19; i++)
            pieceEnumInit.add(PieceEnum.BOIS);

        List<PieceEnum> inventaire = new ArrayList<>();
        inventaire.add(PieceEnum.BOIS);

        //Assert.assertEquals(pieceEnumInit, Ressource.getBoisOnPlateau());
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.BOIS));
    }

    @Test
    public void testInvetaireAddFourrure() throws Exception {
        Game game = new Game(random);
        Ressource.initRessource();
        Joueur joueur = new Joueur();
        //joueur.addRessource(Ressource.giveRessource(PieceEnum.FOURRURE));

        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 19; i++)
            pieceEnumInit.add(PieceEnum.FOURRURE);

        List<PieceEnum> inventaire = new ArrayList<>();
        inventaire.add(PieceEnum.FOURRURE);

        //Assert.assertEquals(pieceEnumInit, Ressource.getFourrureOnPlateau());
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE));
    }

    @Test
    public void testInvetaireAddNourriture() throws Exception {
        Game game = new Game(random);
        Ressource.initRessource();
        Joueur joueur = new Joueur();
        //joueur.addRessource(Ressource.giveRessource(PieceEnum.NOURRITURE));

        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 14; i++)
            pieceEnumInit.add(PieceEnum.NOURRITURE);

        List<PieceEnum> inventaire = new ArrayList<>();
        inventaire.add(PieceEnum.NOURRITURE);

        //Assert.assertEquals(pieceEnumInit, Ressource.getNourritureOnPlateau());
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.NOURRITURE));
    }

    @Test
    public void testInvetaireAddEquipement() throws Exception {
        Game game = new Game(random);
        Ressource.initRessource();
        Joueur joueur = new Joueur();
        //joueur.addRessource(Ressource.giveRessource(PieceEnum.EQUIPEMENT));

        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 14; i++)
            pieceEnumInit.add(PieceEnum.EQUIPEMENT);

        List<PieceEnum> inventaire = new ArrayList<>();
        inventaire.add(PieceEnum.EQUIPEMENT);

        //Assert.assertEquals(pieceEnumInit, Ressource.getEquipementOnPlateau());
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT));
    }

    @Test
    public void testInvetaireAddIndien() throws Exception {
        Game game = new Game(random);
        Ressource.initRessource();
        Joueur joueur = new Joueur();
        //joueur.addRessource(Ressource.giveRessource(PieceEnum.INDIEN));

        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 17; i++)
            pieceEnumInit.add(PieceEnum.INDIEN);

        List<PieceEnum> inventaire = new ArrayList<>();
        inventaire.add(PieceEnum.INDIEN);

        //Assert.assertEquals(pieceEnumInit, Ressource.getIndiensOnPlateau());
        Assert.assertEquals(1, joueur.miniPlateau.countNbRessource(PieceEnum.INDIEN));
    }

    @Test (expected = RessourceOutOfDisponibleException.class)
    public void testInvetaireExeption() throws Exception {
        Game game = new Game(random);
        Ressource.initRessource();
        Joueur joueur = new Joueur();
        //for (int i = 0; i < 19; i++)
            //joueur.addRessource(Ressource.giveRessource(PieceEnum.INDIEN));
    }
}

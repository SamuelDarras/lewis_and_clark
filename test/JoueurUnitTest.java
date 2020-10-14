import org.junit.Assert;
import org.junit.Test;
import Error.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurUnitTest {

    @Test
    public void testInvetaireAddBois() throws Exception {
        Game game = new Game();
        game.initRessource();
        Joueur joueur = new Joueur();
        joueur.addRessource(game.giveRessource(PieceEnum.BOIS));

        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 19; i++)
            pieceEnumInit.add(PieceEnum.BOIS);

        List<PieceEnum> inventaire = new ArrayList<>();
        inventaire.add(PieceEnum.BOIS);

        Assert.assertEquals(pieceEnumInit, game.getBoisOnPlateau());
        Assert.assertEquals(inventaire, joueur.pieceEnumArrayList);
    }

    @Test
    public void testInvetaireAddFourrure() throws Exception {
        Game game = new Game();
        game.initRessource();
        Joueur joueur = new Joueur();
        joueur.addRessource(game.giveRessource(PieceEnum.FOURRURE));

        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 19; i++)
            pieceEnumInit.add(PieceEnum.FOURRURE);

        List<PieceEnum> inventaire = new ArrayList<>();
        inventaire.add(PieceEnum.FOURRURE);

        Assert.assertEquals(pieceEnumInit, game.getFourrureOnPlateau());
        Assert.assertEquals(inventaire, joueur.pieceEnumArrayList);
    }

    @Test
    public void testInvetaireAddNourriture() throws Exception {
        Game game = new Game();
        game.initRessource();
        Joueur joueur = new Joueur();
        joueur.addRessource(game.giveRessource(PieceEnum.NOURRITURE));

        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 14; i++)
            pieceEnumInit.add(PieceEnum.NOURRITURE);

        List<PieceEnum> inventaire = new ArrayList<>();
        inventaire.add(PieceEnum.NOURRITURE);

        Assert.assertEquals(pieceEnumInit, game.getNourritureOnPlateau());
        Assert.assertEquals(inventaire, joueur.pieceEnumArrayList);
    }

    @Test
    public void testInvetaireAddEquipement() throws Exception {
        Game game = new Game();
        game.initRessource();
        Joueur joueur = new Joueur();
        joueur.addRessource(game.giveRessource(PieceEnum.EQUIPEMENT));

        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 14; i++)
            pieceEnumInit.add(PieceEnum.EQUIPEMENT);

        List<PieceEnum> inventaire = new ArrayList<>();
        inventaire.add(PieceEnum.EQUIPEMENT);

        Assert.assertEquals(pieceEnumInit, game.getEquipementOnPlateau());
        Assert.assertEquals(inventaire, joueur.pieceEnumArrayList);
    }

    @Test
    public void testInvetaireAddIndien() throws Exception {
        Game game = new Game();
        game.initRessource();
        Joueur joueur = new Joueur();
        joueur.addRessource(game.giveRessource(PieceEnum.INDIEN));

        List<PieceEnum> pieceEnumInit = new ArrayList<PieceEnum>();
        for (int i = 0; i < 17; i++)
            pieceEnumInit.add(PieceEnum.INDIEN);

        List<PieceEnum> inventaire = new ArrayList<>();
        inventaire.add(PieceEnum.INDIEN);

        Assert.assertEquals(pieceEnumInit, game.getIndiensOnPlateau());
        Assert.assertEquals(inventaire, joueur.pieceEnumArrayList);
    }

    @Test (expected = RessourceOutOfDisponibleException.class)
    public void testInvetaireExeption() throws RessourceOutOfDisponibleException, RessourceNotExisteException {
        Game game = new Game();
        game.initRessource();
        Joueur joueur = new Joueur();
        for (int i = 0; i < 19; i++)
            joueur.addRessource(game.giveRessource(PieceEnum.INDIEN));
    }
}

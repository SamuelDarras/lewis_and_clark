package lewisclark;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import Error.RessourceOutOfDisponibleException;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class RessourceUnitTest {

    @Test
    public void testGiveRessourceBois() throws Exception {
        Ressource.initRessource();
        PieceEnum pieceEnumRecup = Ressource.giveRessource(PieceEnum.BOIS);
        List<PieceEnum> boisOnPlateau = new ArrayList<>();
        for (int i = 0; i < 19; i++)
            boisOnPlateau.add(PieceEnum.BOIS);
        Assert.assertEquals(pieceEnumRecup, PieceEnum.BOIS);
        Assert.assertEquals(boisOnPlateau,Ressource.getBoisOnPlateau());
    }

    @Test
    public void testGiveRessourceFourrure() throws Exception {
        Ressource.initRessource();
        PieceEnum pieceEnumRecup = Ressource.giveRessource(PieceEnum.FOURRURE);
        List<PieceEnum> fourruresOnPlateau = new ArrayList<>();
        for (int i = 0; i < 19; i++)
            fourruresOnPlateau.add(PieceEnum.FOURRURE);
        Assert.assertEquals(pieceEnumRecup, PieceEnum.FOURRURE);
        Assert.assertEquals(fourruresOnPlateau,Ressource.getFourrureOnPlateau());
    }

    @Test
    public void testGiveRessourceEquipement() throws Exception {
        Ressource.initRessource();
        PieceEnum pieceEnumRecup = Ressource.giveRessource(PieceEnum.EQUIPEMENT);
        List<PieceEnum> equipementOnPlateau = new ArrayList<>();
        for (int i = 0; i < 14; i++)
            equipementOnPlateau.add(PieceEnum.EQUIPEMENT);
        Assert.assertEquals(pieceEnumRecup, PieceEnum.EQUIPEMENT);
        Assert.assertEquals(equipementOnPlateau,Ressource.getEquipementOnPlateau());
    }

    @Test
    public void testGiveRessourceIndiens() throws Exception {
        Ressource.initRessource();
        PieceEnum pieceEnumRecup = Ressource.giveRessource(PieceEnum.INDIEN);
        List<PieceEnum> indienOnPlateau = new ArrayList<>();
        for (int i = 0; i < 17; i++)
            indienOnPlateau.add(PieceEnum.INDIEN);
        Assert.assertEquals(pieceEnumRecup, PieceEnum.INDIEN);
        Assert.assertEquals(indienOnPlateau,Ressource.getIndiensOnPlateau());
    }

    @Rule
    public ExpectedException ecouteur = ExpectedException.none();
    @Test
    public void testGiveRessourceOutOfRessource() throws Exception {
        Ressource.initRessource();
        for (int i = 0; i < 20; i++)
            Ressource.giveRessource(PieceEnum.BOIS);
        ecouteur.expect(RessourceOutOfDisponibleException.class);
        Ressource.giveRessource(PieceEnum.BOIS);
    }
}

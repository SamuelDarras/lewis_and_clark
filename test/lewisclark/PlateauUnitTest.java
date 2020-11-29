package lewisclark;

import Error.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PlateauUnitTest {

    @Test
    public void testInitIndienPlateau(){
        Plateau plateau = new Plateau();
        Ressource ressource = new Ressource(PieceEnum.INDIEN);

        Assert.assertEquals(ressource.type, plateau.getTypeRessourceList(PieceEnum.INDIEN).get(0).type);
    }

    @Test
    public void testInitRessourcesBois(){
        Plateau plateau = new Plateau();
        Ressource ressource = new Ressource(PieceEnum.BOIS);

        Assert.assertEquals(ressource.type, plateau.getTypeRessourceList(PieceEnum.BOIS).get(0).type);
    }

    @Test
    public void testInitRessourcesFourrure(){
        Plateau plateau = new Plateau();
        Ressource ressource = new Ressource(PieceEnum.FOURRURE);

        Assert.assertEquals(ressource.type, plateau.getTypeRessourceList(PieceEnum.FOURRURE).get(0).type);
    }

    @Test
    public void testInitRessourcesNourriture(){
        Plateau plateau = new Plateau();
        Ressource ressource = new Ressource(PieceEnum.NOURRITURE);

        Assert.assertEquals(ressource.type, plateau.getTypeRessourceList(PieceEnum.NOURRITURE).get(0).type);
    }

    @Test
    public void testInitRessourcesEquipement(){
        Plateau plateau = new Plateau();
        Ressource ressource = new Ressource(PieceEnum.EQUIPEMENT);

        Assert.assertEquals(ressource.type, plateau.getTypeRessourceList(PieceEnum.EQUIPEMENT).get(0).type);
    }

    @Test
    public void testGiveRessource() throws RessourceOutOfDisponibleException {
        Plateau plateau = new Plateau();
        Ressource ressource = new Ressource(PieceEnum.INDIEN);

        Assert.assertEquals(ressource.type, plateau.giveRessource(PieceEnum.INDIEN).type);
    }

    @Rule
    public ExpectedException ecouteur = ExpectedException.none();
    @Test
    public void testOutOfBound() throws RessourceOutOfDisponibleException {
        Plateau plateau = new Plateau();
        Ressource ressource = new Ressource(PieceEnum.INDIEN);

        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        plateau.giveRessource(PieceEnum.INDIEN);
        ecouteur.expect(RessourceOutOfDisponibleException.class);
        plateau.giveRessource(PieceEnum.INDIEN);

    }

    @Test
    public void testDropRessource(){
        Plateau plateau = new Plateau();
        Ressource ressource = new Ressource(PieceEnum.FOURRURE);
        plateau.dropRessource(ressource);

        Assert.assertEquals(21,plateau.ressources.get(PieceEnum.FOURRURE).size());
    }

    @Test
    public void testDefausse() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("Rouge", plateau);
        Assert.assertEquals(19,plateau.getNbressource(PieceEnum.FOURRURE));
        plateau.defausser(joueur,PieceEnum.FOURRURE);
        Assert.assertEquals(20, plateau.getNbressource(PieceEnum.FOURRURE));
    }

    @Test
    public void testCountLastPlaceOnPosition(){
        Plateau plateau = new Plateau();
        Assert.assertEquals(plateau.lastPlaceForIndienOnPosition(PositionEmplacementVillage.DeffauseTroisCarte),1);

        plateau.addIndien(PositionEmplacementVillage.DeffauseTroisCarte);

        Assert.assertEquals(plateau.lastPlaceForIndienOnPosition(PositionEmplacementVillage.DeffauseTroisCarte),0);
    }

    @Test
    public void testPositionIndiens(){
        Plateau plateau = new Plateau();
        Assert.assertTrue(plateau.addOneIndientOnPossition(PositionEmplacementVillage.DeffauseTroisCarte));
        plateau.addIndien(PositionEmplacementVillage.DeffauseTroisCarte);
        Assert.assertFalse(plateau.addOneIndientOnPossition(PositionEmplacementVillage.DeffauseTroisCarte));
    }

    @Test (expected = DejaAchatException.class)
    public void testAchatCarte() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red",plateau);
        plateau.achatCarte(joueur,1);
        plateau.achatCarte(joueur,1);
    }
}

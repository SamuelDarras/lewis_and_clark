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
}
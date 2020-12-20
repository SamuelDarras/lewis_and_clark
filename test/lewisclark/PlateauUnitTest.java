package lewisclark;

import Error.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

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
    public void testCountLastPlaceOnPosition() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);
        Assert.assertEquals(plateau.lastPlaceForIndienOnPosition(PositionEmplacementVillage.DeffauseTroisCarte),1);

        plateau.addIndien(PositionEmplacementVillage.DeffauseTroisCarte, joueur);

        Assert.assertEquals(plateau.lastPlaceForIndienOnPosition(PositionEmplacementVillage.DeffauseTroisCarte),0);
    }

    @Test
    public void testAddIndienError() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        plateau.addIndien(PositionEmplacementVillage.DeffauseTroisCarte, joueur);
        ecouteur.expect(EmplacementVillageFullException.class);
        plateau.addIndien(PositionEmplacementVillage.DeffauseTroisCarte, joueur);
    }

    @Test
    public void testPositionIndiens() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        Assert.assertTrue(plateau.addOneIndientOnPossition(PositionEmplacementVillage.DeffauseTroisCarte));
        plateau.addIndien(PositionEmplacementVillage.DeffauseTroisCarte, joueur);
        Assert.assertFalse(plateau.addOneIndientOnPossition(PositionEmplacementVillage.DeffauseTroisCarte));
    }

    @Test (expected = DejaAchatException.class)
    public void testAchatCarte() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red",plateau);
        plateau.achatCarte(joueur,1);
        plateau.achatCarte(joueur,1);
    }

    @Test (expected = OutOfRessourceInBateauxException.class)
    public void testTrocChevalNotPossedeRessource() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        List<PieceEnum> offre = new ArrayList<>();
        offre.add(PieceEnum.FOURRURE);
        offre.add(PieceEnum.FOURRURE);
        offre.add(PieceEnum.BOIS);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Cheval, offre);
    }

    @Test (expected = OutOfRessourceNeed.class)
    public void testTrocChevalOutOfRessourcePropose() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        List<PieceEnum> offre = new ArrayList<>();
        offre.add(PieceEnum.FOURRURE);
        offre.add(PieceEnum.EQUIPEMENT);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Cheval, offre);
    }

    @Test (expected = IncompatiblePieceException.class)
    public void testTrocChevalIncompatiblePiece() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        List<PieceEnum> offre = new ArrayList<>();
        offre.add(PieceEnum.FOURRURE);
        offre.add(PieceEnum.FOURRURE);
        offre.add(PieceEnum.EQUIPEMENT);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Cheval, offre);
    }

    @Test
    public void testTrocCheval() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        List<PieceEnum> offre = new ArrayList<>();
        offre.add(PieceEnum.FOURRURE);
        offre.add(PieceEnum.NOURRITURE);
        offre.add(PieceEnum.EQUIPEMENT);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Cheval, offre);

        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.CHEVAL),1);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE),0);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.NOURRITURE),0);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT),0);
    }

    @Test (expected = OutOfRessourceInBateauxException.class)
    public void testPyrogueNotPossedeRessource() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        List<PieceEnum> offre = new ArrayList<>();
        offre.add(PieceEnum.BOIS);
        offre.add(PieceEnum.BOIS);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Kayak, offre);
    }

    @Test (expected = OutOfRessourceNeed.class)
    public void testPyrogueOutOfRessourcePropose() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        List<PieceEnum> offre = new ArrayList<>();
        offre.add(PieceEnum.BOIS);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Kayak, offre);
    }

    @Test (expected = IncompatiblePieceException.class)
    public void testPyrogueIncompatiblePiece() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        List<PieceEnum> offre = new ArrayList<>();
        offre.add(PieceEnum.BOIS);
        offre.add(PieceEnum.EQUIPEMENT);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Kayak, offre);
    }

    @Test
    public void testPyrogue() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        joueur.miniPlateau.addRessourceDansBateau(new Ressource(PieceEnum.BOIS));
        joueur.miniPlateau.addRessourceDansBateau(new Ressource(PieceEnum.BOIS));

        List<PieceEnum> offre = new ArrayList<>();
        offre.add(PieceEnum.BOIS);
        offre.add(PieceEnum.BOIS);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Kayak, offre);

        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.PYROGUE),1);
    }

    @Test
    public void testPositionVillageCadeauFourrure() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.FourrureBois,1);

        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.BOIS), 0);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE), 3);
        Assert.assertEquals(plateau.getNbressource(PieceEnum.FOURRURE), 17);
    }

    @Test
    public void testPositionVillageCadeauBois() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.FourrureBois,2);

        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE), 1);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.BOIS), 2);
        Assert.assertEquals(plateau.getNbressource(PieceEnum.BOIS), 18);
    }

    @Test (expected = CarteNotCompatibleException.class)
    public void testPositionVillageCadeauBoisErreurMauvaiseCarte() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Kayak,2);
    }

    @Test (expected = CarteNotCompatibleException.class)
    public void testPositionVillageCadeauBoiss() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.FourrureBois);
    }

    @Test
    public void testArtisant() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.EquipementBois);

        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT), 2);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.BOIS), 1);
    }

    @Test (expected = BateauFullException.class)
    public void testArtisantError() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("red", plateau);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.EquipementBois);

    }
}

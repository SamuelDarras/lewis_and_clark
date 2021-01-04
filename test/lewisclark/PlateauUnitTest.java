package lewisclark;

import Error.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlateauUnitTest {

    Joueur joueur;
    Plateau plateau;

    @Before
    public void setup() throws Exception {
        plateau = new Plateau();
        joueur = new Joueur("red", plateau);
    }

    @Test
    public void testInitIndienPlateau(){
        Ressource ressource = new Ressource(PieceEnum.INDIEN);

        Assert.assertEquals(ressource.type, plateau.getTypeRessourceList(PieceEnum.INDIEN).get(0).type);
    }

    @Test
    public void testInitRessourcesBois(){
        Ressource ressource = new Ressource(PieceEnum.BOIS);

        Assert.assertEquals(ressource.type, plateau.getTypeRessourceList(PieceEnum.BOIS).get(0).type);
    }

    @Test
    public void testInitRessourcesFourrure(){
        Ressource ressource = new Ressource(PieceEnum.FOURRURE);

        Assert.assertEquals(ressource.type, plateau.getTypeRessourceList(PieceEnum.FOURRURE).get(0).type);
    }

    @Test
    public void testInitRessourcesNourriture(){
        Ressource ressource = new Ressource(PieceEnum.NOURRITURE);

        Assert.assertEquals(ressource.type, plateau.getTypeRessourceList(PieceEnum.NOURRITURE).get(0).type);
    }

    @Test
    public void testInitRessourcesEquipement(){
        Ressource ressource = new Ressource(PieceEnum.EQUIPEMENT);

        Assert.assertEquals(ressource.type, plateau.getTypeRessourceList(PieceEnum.EQUIPEMENT).get(0).type);
    }

    @Test
    public void testGiveRessource() throws RessourceOutOfDisponibleException {
        Ressource ressource = new Ressource(PieceEnum.INDIEN);

        Assert.assertEquals(ressource.type, plateau.giveRessource(PieceEnum.INDIEN).type);
    }

    @Rule
    public ExpectedException ecouteur = ExpectedException.none();
    @Test
    public void testOutOfBound() throws RessourceOutOfDisponibleException {
        for (int i = 0; i < 17;i++)
            plateau.giveRessource(PieceEnum.INDIEN);
        ecouteur.expect(RessourceOutOfDisponibleException.class);
        plateau.giveRessource(PieceEnum.INDIEN);

    }

    @Test
    public void testDropRessource(){
        Ressource ressource = new Ressource(PieceEnum.FOURRURE);
        plateau.dropRessource(ressource);
        //Normalement 21 mais vue qu'il y a un joueur d'initier && qu'il prend une fourrure
        Assert.assertEquals(20,plateau.ressources.get(PieceEnum.FOURRURE).size());
    }

    @Test
    public void testDefausse() throws Exception {
        Assert.assertEquals(19,plateau.getNbressource(PieceEnum.FOURRURE));
        plateau.defausser(joueur,PieceEnum.FOURRURE);
        Assert.assertEquals(20, plateau.getNbressource(PieceEnum.FOURRURE));
    }

    @Test
    public void testCountLastPlaceOnPosition() throws Exception {
        Assert.assertEquals(plateau.lastPlaceForIndienOnPosition(PositionEmplacementVillage.DefauseTroisCarteAndshuffle),1);

        plateau.addIndien(PositionEmplacementVillage.DefauseTroisCarteAndshuffle, joueur);

        Assert.assertEquals(plateau.lastPlaceForIndienOnPosition(PositionEmplacementVillage.DefauseTroisCarteAndshuffle),0);
    }

    @Test
    public void testAddIndienError() throws Exception {

        plateau.addIndien(PositionEmplacementVillage.DefauseTroisCarteAndshuffle, joueur);
        ecouteur.expect(EmplacementVillageFullException.class);
        plateau.addIndien(PositionEmplacementVillage.DefauseTroisCarteAndshuffle, joueur);
    }

    @Test
    public void testPositionIndiens() throws Exception {

        Assert.assertTrue(plateau.addOneIndientOnPossition(PositionEmplacementVillage.DefauseTroisCarteAndshuffle));
        plateau.addIndien(PositionEmplacementVillage.DefauseTroisCarteAndshuffle, joueur);
        Assert.assertFalse(plateau.addOneIndientOnPossition(PositionEmplacementVillage.DefauseTroisCarteAndshuffle));
    }

    @Test (expected = DejaAchatException.class)
    public void testAchatCarte() throws Exception {
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
        List<PieceEnum> offre = new ArrayList<>();
        offre.add(PieceEnum.FOURRURE);
        offre.add(PieceEnum.FOURRURE);
        offre.add(PieceEnum.EQUIPEMENT);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Cheval, offre);
    }

    @Test
    public void testTrocCheval() throws Exception {
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
        List<PieceEnum> offre = new ArrayList<>();
        offre.add(PieceEnum.BOIS);
        offre.add(PieceEnum.BOIS);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Kayak, offre);
    }

    @Test (expected = OutOfRessourceNeed.class)
    public void testPyrogueOutOfRessourcePropose() throws Exception {
        List<PieceEnum> offre = new ArrayList<>();
        offre.add(PieceEnum.BOIS);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Kayak, offre);
    }

    @Test (expected = IncompatiblePieceException.class)
    public void testPyrogueIncompatiblePiece() throws Exception {
        List<PieceEnum> offre = new ArrayList<>();
        offre.add(PieceEnum.BOIS);
        offre.add(PieceEnum.EQUIPEMENT);

        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Kayak, offre);
    }

    @Test
    public void testPyrogue() throws Exception {
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
        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.FourrureBois,1);

        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.BOIS), 0);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE), 3);
        Assert.assertEquals(plateau.getNbressource(PieceEnum.FOURRURE), 17);
    }

    @Test
    public void testPositionVillageCadeauBois() throws Exception {
        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.FourrureBois,2);

        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE), 1);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.BOIS), 2);
        Assert.assertEquals(plateau.getNbressource(PieceEnum.BOIS), 18);
    }

    @Test (expected = CarteNotCompatibleException.class)
    public void testPositionVillageCadeauBoisErreurMauvaiseCarte() throws Exception {
        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.Kayak,2);
    }

    @Test (expected = CarteNotCompatibleException.class)
    public void testPositionVillageCadeauBoiss() throws Exception {
        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.FourrureBois);
    }

    @Test
    public void testArtisant() throws Exception {
        joueur.miniPlateau.deleteRessource(PieceEnum.NOURRITURE);
        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.EquipementBois);

        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT), 2);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.BOIS), 1);
    }

    @Test (expected = BateauFullException.class)
    public void testArtisantError() throws Exception {
        for (int i = 0; i < 8; i++)
            joueur.miniPlateau.addRessourceDansBateau(new Ressource(PieceEnum.FOURRURE));
        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.EquipementBois);
    }

    @Test
    public void testChasse() throws Exception {
        joueur.miniPlateau.deleteRessource(PieceEnum.EQUIPEMENT);
        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.NouritureFourrure);

        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.FOURRURE), 2);
        Assert.assertEquals(joueur.miniPlateau.countNbRessource(PieceEnum.NOURRITURE), 2);
    }

    @Test (expected = BateauFullException.class)
    public void testChasseError() throws Exception {
        for (int i = 0; i < 8; i++)
            joueur.miniPlateau.addRessourceDansBateau(new Ressource(PieceEnum.FOURRURE));
        joueur.addIndienOnPositionIndien(PositionEmplacementVillage.NouritureFourrure);
    }

    @Test
    public void testGetNombreIndienOnPosition(){
        Assert.assertEquals(1, plateau.getNombreIndienOnPosition(PositionEmplacementVillage.IndienReserve));
        Assert.assertEquals(0, plateau.getNombreIndienOnPosition(PositionEmplacementVillage.Kayak));
    }

    @Test
    public void testCadeau() throws Exception {
        plateau.cadeau(PieceEnum.BOIS, joueur);
        Assert.assertEquals(2, joueur.miniPlateau.countNbRessource(PieceEnum.BOIS));
    }

    @Test
    public void testCadeauException() throws Exception {
        Game game = new Game(new Random());
        Plateau plateau = new Plateau();
        List<Joueur> joueurs = new ArrayList<>();

        Joueur joueur1 = new Joueur("red", plateau);
        Joueur joueur2 = new Joueur("bleu", plateau);
        Joueur joueur3 = new Joueur("violet", plateau);
        Joueur joueur4 = new Joueur("jaune", plateau);
        joueurs.add(joueur1);
        joueurs.add(joueur2);
        joueurs.add(joueur3);
        joueurs.add(joueur4);

        game.players = joueurs;
        game.currentPlayer = joueur1;
        for (int i = 0 ; i < joueurs.size() ; i++){
            for (int j = 0 ; j < 5 ;j++){
                game.currentPlayer = joueurs.get(i);
                game.giveRes(PieceEnum.BOIS);
            }
        }
        ecouteur.expect(RessourceOutOfDisponibleException.class);
        game.giveRes(PieceEnum.BOIS);
    }
}

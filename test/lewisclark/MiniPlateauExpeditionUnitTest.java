package lewisclark;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import Error.*;
import org.junit.rules.ExpectedException;


public class MiniPlateauExpeditionUnitTest {
    private MiniPlateauExpedition mpe;
    @Before
    public void setUp(){
        mpe = new MiniPlateauExpedition();
    }
    @Test
    public void testAddRessource() throws Exception {
        Ressource r = new Ressource(PieceEnum.BOIS);
        mpe.addRessourceDansBateau(0,r);
        Assert.assertEquals(r,mpe.bateauRes.get(0).get(0));
    }
    @Test (expected = IncompatiblePieceException.class)
    public void testIncompatibleAddRessource() throws Exception{
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.INDIEN));
    }
    @Rule
    public ExpectedException ecouteur = ExpectedException.none();
    @Test
    public void testBateauFullAddRessource() throws Exception {
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
        ecouteur.expect(BateauFullException.class);
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
    }
    @Test
    public void testDeplacerRessource() throws Exception{
        Ressource r = new Ressource(PieceEnum.EQUIPEMENT);
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
        mpe.addRessourceDansBateau(1,r);
        mpe.deplacerRessourceMiniPlateau(1,0,0);
        Assert.assertEquals(r,mpe.bateauRes.get(0).get(1));
    }
    @Test (expected = BateauFullException.class)
    public void testFullBateauExceptionOnDeplacerRessource() throws Exception{
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
        mpe.addRessourceDansBateau(1,new Ressource(PieceEnum.EQUIPEMENT));
        mpe.deplacerRessourceMiniPlateau(1,0,0);
    }
    
    @Test
    public void testCountRessource() throws Exception {
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
        Assert.assertEquals(2,mpe.countNbRessource(PieceEnum.BOIS));
    }

    @Test
    public void testFindBateau1() throws Exception {
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));

        Assert.assertEquals(1,mpe.getValideBateau());
    }

    @Test
    public void testFindBateau2() throws Exception {
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));
        mpe.addRessourceDansBateau(0,new Ressource(PieceEnum.BOIS));

        mpe.addRessourceDansBateau(1,new Ressource(PieceEnum.BOIS));
        mpe.addRessourceDansBateau(1,new Ressource(PieceEnum.BOIS));
        mpe.addRessourceDansBateau(1,new Ressource(PieceEnum.BOIS));

        Assert.assertEquals(2,mpe.getValideBateau());
    }

    @Test
    public void testFindBateau0() {
        Assert.assertEquals(0,mpe.getValideBateau());
    }
}


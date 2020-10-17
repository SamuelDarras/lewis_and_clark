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

        mpe.addRessourceDansBateau(0,PieceEnum.BOIS);
        Assert.assertEquals(PieceEnum.BOIS,mpe.bateauRes.get(0).get(0));
    }
    @Test (expected = IncompatiblePieceException.class)
    public void testIncompatibleAddRessource() throws Exception{
        mpe.addRessourceDansBateau(0,PieceEnum.INDIEN);
    }
    @Rule
    public ExpectedException ecouteur = ExpectedException.none();
    @Test
    public void testBateauFullAddRessource() throws Exception{
        mpe.addRessourceDansBateau(0,PieceEnum.BOIS);
        mpe.addRessourceDansBateau(0,PieceEnum.BOIS);
        mpe.addRessourceDansBateau(0,PieceEnum.BOIS);
        ecouteur.expect(BateauFullException.class);
        mpe.addRessourceDansBateau(0,PieceEnum.BOIS);
    }
    @Test
    public void testDeplacerRessource() throws Exception{
        mpe.addRessourceDansBateau(0,PieceEnum.BOIS);
        mpe.addRessourceDansBateau(1,PieceEnum.EQUIPEMENT);
        mpe.deplacerRessourceMiniPlateau(1,0,0);
        Assert.assertEquals(PieceEnum.EQUIPEMENT,mpe.bateauRes.get(0).get(1));
    }
    @Test (expected = BateauFullException.class)
    public void testFullBateauExceptionOnDeplacerRessource() throws Exception{
        mpe.addRessourceDansBateau(0,PieceEnum.BOIS);
        mpe.addRessourceDansBateau(0,PieceEnum.BOIS);
        mpe.addRessourceDansBateau(0,PieceEnum.BOIS);
        mpe.addRessourceDansBateau(1,PieceEnum.EQUIPEMENT);
        mpe.deplacerRessourceMiniPlateau(1,0,0);
    }
}

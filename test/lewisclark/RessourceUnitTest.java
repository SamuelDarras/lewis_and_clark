package lewisclark;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import Error.RessourceOutOfDisponibleException;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static lewisclark.PieceEnum.*;

public class RessourceUnitTest {

    @Test
    public void testGiveRessources() throws Exception {
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur("rouge", plateau);

        for (var e : joueur.miniPlateau.bateauRes.get(0)) {
            System.out.println(e.type);
        }
        Assert.assertEquals(FOURRURE, joueur.miniPlateau.bateauRes.get(0).get(0).type);
        Assert.assertEquals(NOURRITURE, joueur.miniPlateau.bateauRes.get(0).get(1).type);
        Assert.assertEquals(EQUIPEMENT, joueur.miniPlateau.bateauRes.get(0).get(2).type);
        Assert.assertEquals(INDIEN, joueur.miniPlateau.bateauInd.get(0).get(0).type);
    }
}

package lewisclark;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CardUnitTest {

    @Test
    public void testCardaddIndien(){
        Card c1 = new Card(
                "John Boley",
                "Ce Personnage est sans effet au moment où vous l’activez. Si John Boley est actif sur votre plan de jeu au moment d’établir votre Campement, diminuez de 1 votre Temps d’installation. Cette carte est sans effet si votre Temps d’installation est nul. Le Temps d’installation d’un Campement ne peut jamais être négatif.",
                1,
                PieceEnum.NOURRITURE
        );
        List<Ressource> indiens = new ArrayList<>();
        indiens.add(new Ressource(PieceEnum.INDIEN)); // ajout de 1 indien
        c1.placerIndiensSurCarte(indiens,null);
        Assert.assertEquals(1,c1.indienAssocie.size());
    }
    @Test
    public void testCardassocieCard(){
        Card c1 = new Card(
                "John Boley",
                "Ce Personnage est sans effet au moment où vous l’activez. Si John Boley est actif sur votre plan de jeu au moment d’établir votre Campement, diminuez de 1 votre Temps d’installation. Cette carte est sans effet si votre Temps d’installation est nul. Le Temps d’installation d’un Campement ne peut jamais être négatif.",
                1,
                PieceEnum.NOURRITURE
        );
        Card c2 = new Card(
                "John Boley",
                "Ce Personnage est sans effet au moment où vous l’activez. Si John Boley est actif sur votre plan de jeu au moment d’établir votre Campement, diminuez de 1 votre Temps d’installation. Cette carte est sans effet si votre Temps d’installation est nul. Le Temps d’installation d’un Campement ne peut jamais être négatif.",
                1,
                PieceEnum.NOURRITURE
        );
        c1.placerIndiensSurCarte(null,c2);
        Assert.assertEquals(c2,c1.cardAssociePourIndiens);
    }
    @Test
    public void testUsedCardPlayed(){
        Card c1 = new Card(
                "John Boley",
                "Ce Personnage est sans effet au moment où vous l’activez. Si John Boley est actif sur votre plan de jeu au moment d’établir votre Campement, diminuez de 1 votre Temps d’installation. Cette carte est sans effet si votre Temps d’installation est nul. Le Temps d’installation d’un Campement ne peut jamais être négatif.",
                1,
                PieceEnum.NOURRITURE
        );
        List<Ressource> indiens = new ArrayList<>();
        indiens.add(new Ressource(PieceEnum.INDIEN)); // ajout de 1 indien
        c1.placerIndiensSurCarte(indiens,null);
        Assert.assertTrue(c1.used);
    }
    @Test
    public void testRenouvellementCard(){
        Card c1 = new Card(
                "John Boley",
                "Ce Personnage est sans effet au moment où vous l’activez. Si John Boley est actif sur votre plan de jeu au moment d’établir votre Campement, diminuez de 1 votre Temps d’installation. Cette carte est sans effet si votre Temps d’installation est nul. Le Temps d’installation d’un Campement ne peut jamais être négatif.",
                1,
                PieceEnum.NOURRITURE
        );
        Card c2 = new Card(
                "John Boley",
                "Ce Personnage est sans effet au moment où vous l’activez. Si John Boley est actif sur votre plan de jeu au moment d’établir votre Campement, diminuez de 1 votre Temps d’installation. Cette carte est sans effet si votre Temps d’installation est nul. Le Temps d’installation d’un Campement ne peut jamais être négatif.",
                1,
                PieceEnum.NOURRITURE
        );
        List<Ressource> indiens = new ArrayList<>();
        indiens.add(new Ressource(PieceEnum.INDIEN)); // ajout de 1 indien
        c1.placerIndiensSurCarte(indiens,c2);
        Assert.assertEquals(indiens,c1.renouvellementCard());
    }
}

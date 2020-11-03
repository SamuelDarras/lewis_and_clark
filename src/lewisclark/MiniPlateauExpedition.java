package lewisclark;

import java.util.ArrayList;
import java.util.List;
import Error.*;

public class MiniPlateauExpedition {
    public static int[] MAX_BATEAU_RES={3,3,5};
    public static int[] MAX_BATEAU_IND={1,100};
    List<List<Ressource>> bateauRes;
    List<List<Ressource>> bateauInd;
    public MiniPlateauExpedition(){
        bateauRes = new ArrayList<>();
        bateauRes.add(new ArrayList<>());
        bateauRes.add(new ArrayList<>());
        bateauRes.add(new ArrayList<>());
        bateauInd = new ArrayList<>();
        bateauInd.add(new ArrayList<>());
        bateauInd.add(new ArrayList<>());
        bateauInd.add(new ArrayList<>());
    }
    public void addRessourceDansBateau(int numBateau,Ressource p) throws Exception{
        if (p.type != PieceEnum.INDIEN){
            if(bateauRes.get(numBateau).size()<MAX_BATEAU_RES[numBateau]){
                bateauRes.get(numBateau).add(p);
            }
            else
                throw new BateauFullException();
        }
        else
            throw new IncompatiblePieceException();
    }
    public void addIndienDansBateau(int numBateau,Ressource p) throws Exception{
        if (p.type == PieceEnum.INDIEN){
            if(bateauInd.get(numBateau).size()<MAX_BATEAU_IND[numBateau]){
                bateauInd.get(numBateau).add(p);
            }
            else
                throw new BateauFullException();
        }
        else
            throw new IncompatiblePieceException();
    }
    public void deplacerRessourceMiniPlateau(int bateauS,int bateauD,int indexRes) throws Exception {
        if(bateauRes.get(bateauD).size()<MAX_BATEAU_RES[bateauD]){
            bateauRes.get(bateauD).add(bateauRes.get(bateauS).remove(indexRes));
        }
        else
            throw new BateauFullException();
    }
    public void deplacerIndienMiniPlateau(int bateauS,int bateauD,int indexRes) throws Exception {
        if(bateauInd.get(bateauD).size()<MAX_BATEAU_IND[bateauD]){
            bateauInd.get(bateauD).add(bateauInd.get(bateauS).remove(indexRes));
        }
        else
            throw new BateauFullException();
    }

    /**
     * La methode retourne le nombre d'une ressource en particulier
     * @return number = le nombre de ressources
     */
    public int countNbRessource(PieceEnum pieceEnum){
        int number = 0;
        if (pieceEnum == PieceEnum.INDIEN) {
            for (List<Ressource> ressource : bateauInd)
                for (Ressource piece : ressource)
                    if (piece.type == pieceEnum) number++;
        }else {
            for (List<Ressource> ressource : bateauRes)
                for (Ressource piece : ressource)
                    if (piece.type == pieceEnum) number++;
        }
        return number;
    }
}

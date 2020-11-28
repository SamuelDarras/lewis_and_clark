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

    /**
     * Supprime une piece dans la reseve du joueur
     * @param pieceEnum est la ressouce a delete
     */
    public void deleteRessource(PieceEnum pieceEnum){
        boolean isOk = false;
        for (List<Ressource> bateauRe : bateauRes) {
            for (int j = 0; j < bateauRe.size(); j++)
                if (bateauRe.get(j).type.equals(pieceEnum)) {
                    bateauRe.remove(j);
                    isOk = true;
                    break;
                }
            if (isOk)
                break;
        }
    }

    /**
     * Permet d'avoir le numero de bateau ou on peux mettre les ressources
     * @return -1 si il n'y a pas de possibiliter sinon le numéro du batteau
     */
    public int getValideBateau(){
        for (int i = 0; i < this.bateauRes.size(); i++)
            if(bateauRes.get(i).size()<MAX_BATEAU_RES[i])
                return i;
        return -1;
    }

    public boolean isEnoughPlace(int place){
        int count = 0;
        for (int i = 0; i < this.bateauRes.size(); i++){
            if(bateauRes.get(i).size()<MAX_BATEAU_RES[i]){
                count += MAX_BATEAU_RES[i];
                if (count >= place)
                    return true;
            }
        }
        return false;
    }

    public void addBasicRessource(Plateau plateau) throws Exception {
        this.addRessourceDansBateau(0, plateau.giveRessource(PieceEnum.FOURRURE));
        this.addRessourceDansBateau(0, plateau.giveRessource(PieceEnum.NOURRITURE));
        this.addRessourceDansBateau(0, plateau.giveRessource(PieceEnum.EQUIPEMENT));
        this.addIndienDansBateau   (0, plateau.giveRessource(PieceEnum.INDIEN));
    }

    public String getRessourceFourrure(){
        int c = 0;

        for (int i = 0; i < bateauRes.size()-1; i++){
            for (int j = 0; j <= 2; j++){
                if (bateauRes.get(0).get(0).equals(new Ressource(PieceEnum.FOURRURE)))
                    c++;
            }
        }
        return String.valueOf(c);
    }
}

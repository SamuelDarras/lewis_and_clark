package lewisclark;

import java.util.ArrayList;
import java.util.List;
import Error.*;

public class MiniPlateauExpedition {
    public int[] MAX_BATEAU_RES={3,3,5};
    public int[] MAX_BATEAU_IND={1,5};
    public boolean[] retardRes = {false, true, true};
    public boolean[] retardInd = {false, true};
    public List<List<Ressource>> bateauRes;
    public List<List<Ressource>> bateauInd;

    public MiniPlateauExpedition(){
        bateauRes = new ArrayList<>();
        bateauInd = new ArrayList<>();
        initBateau();
    }

    private void initBateau() {
        for (int i = 0 ; i < MAX_BATEAU_RES.length ; i++){
            bateauRes.add(new ArrayList<>());
            for (int j = 0 ; j < MAX_BATEAU_RES[i] ; j++)
                bateauRes.get(i).add(null);
        }
        for (int i = 0 ; i < MAX_BATEAU_IND.length ; i++){
            bateauInd.add(new ArrayList<>());
            for (int j = 0 ; j < MAX_BATEAU_IND[i] ; j++)
                bateauInd.get(i).add(null);
        }
    }

    public void addRessourceDansBateau(int numBateau,Ressource p) throws Exception{
        if (p.type != PieceEnum.INDIEN){
            if(!isFullBoat(bateauRes.get(numBateau))){
                int idx = bateauRes.get(numBateau).indexOf(null);
                bateauRes.get(numBateau).remove(idx);
                bateauRes.get(numBateau).add(idx,p);
            }
            else {
                throw new BateauFullException();
            }
        }
        else
            throw new IncompatiblePieceException();
    }

    public void addRessourceDansBateau(Ressource p) throws Exception{
        addRessourceDansBateau(getValideBateau(), p);
    }

    public void addIndienDansBateau(int numBateau,Ressource p) throws Exception{
        if (p.type == PieceEnum.INDIEN){
            if(!isFullBoat(bateauInd.get(numBateau))){
                int idx = bateauInd.get(numBateau).indexOf(null);
                bateauInd.get(numBateau).remove(idx);
                bateauInd.get(numBateau).add(idx,p);
            }
            else {
                throw new BateauFullException();
            }
        }
        else
            throw new IncompatiblePieceException();
    }
    public void addIndienDansBateauxDispo(Ressource p ) throws BateauFullException {
        for(List<Ressource> bateau : bateauInd){
            if(!isFullBoat(bateau)){
                bateau.set(bateau.indexOf(null),p);
                return;
            }
        }
        throw new BateauFullException();
    }

    public void deplacerRessourceMiniPlateau(int bateauS,int bateauD,int indexResS,int indexResD) {
        if (bateauD == bateauS)
            bateauRes.get(bateauS).add(indexResD,bateauRes.get(bateauD).remove(indexResS));
        else{
            bateauRes.get(bateauS).add(indexResS,bateauRes.get(bateauD).remove(indexResD));
            bateauRes.get(bateauD).add(indexResD,bateauRes.get(bateauS).remove(indexResS+1));
        }
    }

    public void deplacerIndienMiniPlateau(int bateauS,int bateauD,int indexIndS,int indexIndD) {
        if (bateauD==bateauS)
            bateauInd.get(bateauS).add(indexIndD,bateauInd.get(bateauD).remove(indexIndS));
        else{
            bateauInd.get(bateauS).add(indexIndS,bateauInd.get(bateauD).remove(indexIndD));
            bateauInd.get(bateauD).add(indexIndD,bateauInd.get(bateauS).remove(indexIndS+1));
        }
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
                    if (piece != null && piece.type == pieceEnum) number++;
        }else {
            for (List<Ressource> ressource : bateauRes)
                for (Ressource piece : ressource)
                    if (piece != null && piece.type == pieceEnum) number++;
        }
        return number;
    }

    /**
     * Supprime une piece dans la reseve du joueur
     * @param pieceEnum est la ressouce a delete
     */
    public Ressource deleteRessource(PieceEnum pieceEnum){
        if (pieceEnum != PieceEnum.INDIEN)
            return deletePiece(pieceEnum, bateauRes);
        return deletePiece(pieceEnum, bateauInd);

    }

    private Ressource deletePiece(PieceEnum pieceEnum, List<List<Ressource>> bateau) {
        Ressource ressourceDefause;
        for (List<Ressource> bateauRe : bateau) {
            for (int j = 0; j < bateauRe.size(); j++)
                if (bateauRe.get(j) != null && bateauRe.get(j).type.equals(pieceEnum)) {
                    ressourceDefause = bateauRe.remove(j);
                    bateauRe.add(j, null);
                    return ressourceDefause;
                }
        }
        return null;
    }

    /**
     * Permet d'avoir le numero de bateau ou on peux mettre les ressources
     * @return -1 si il n'y a pas de possibiliter sinon le numéro du batteau
     */
    public int getValideBateau(){
        for (int i = 0; i < bateauRes.size(); i++)
            if(!isFullBoat(bateauRes.get(i)))
                return i;
        return -1;
    }

    public int getValideBateauIndien(){
        for (int i = 0; i < bateauInd.size(); i++)
            if(!isFullBoat(bateauInd.get(i)))
                return i;
        return -1;
    }

    /**
     * <div style="color:green">Test faits</div>
     * @param place (Place peut etre négatif mais ca renveras true)
     * @return true si on peu mettre des ressources sinon false;
     */
    public boolean isEnoughPlace(int place){
        int count = 0;
        for (List<Ressource> bateauRe : bateauRes) {
            for (Ressource ressource : bateauRe) {
                if (ressource == null)
                    count++;
                if (count >= place)
                    return true;
            }
        }
        return false;
    }

    public void addBasicRessource(Plateau plateau) throws Exception {
        addRessourceDansBateau(0, plateau.giveRessource(PieceEnum.FOURRURE));
        addRessourceDansBateau(0, plateau.giveRessource(PieceEnum.NOURRITURE));
        addRessourceDansBateau(0, plateau.giveRessource(PieceEnum.EQUIPEMENT));
        addIndienDansBateau   (0, plateau.giveRessource(PieceEnum.INDIEN));
    }


    private boolean isFullBoat(List<Ressource> bateau){
        for (Ressource ressource : bateau) {
            if (ressource == null)
                return false;
        }
        return true;
    }
    /**
     *   <div style="color:green">Test faits</div>
     *   calcule le retard due aux bateaux lors d'un campement et le retourne
     */
    public int calcRetardBateaux(){
        int retard = 0;
        for (int i = 0 ; i < MAX_BATEAU_RES.length ; i++){
            for(int j = 0 ; j < bateauRes.get(i).size() ; j++){
                if(retardRes[i] && bateauRes.get(i).get(j) != null)
                    retard++;
            }
        }
        for (int i = 0 ; i < MAX_BATEAU_IND.length ; i++){
            for(int j = 0 ; j < bateauInd.get(i).size() ; j++){
                if(retardInd[i] && bateauInd.get(i).get(j) != null)
                    retard++;
            }
        }
        return  retard;
    }



}

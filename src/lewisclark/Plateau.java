package lewisclark;

import Error.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plateau {
    public Map<PieceEnum, List<Ressource>> ressources = new HashMap<>();

    public Plateau() {
        ressources.put(PieceEnum.INDIEN    , new ArrayList<>());
        ressources.put(PieceEnum.BOIS      , new ArrayList<>());
        ressources.put(PieceEnum.FOURRURE  , new ArrayList<>());
        ressources.put(PieceEnum.EQUIPEMENT, new ArrayList<>());
        ressources.put(PieceEnum.NOURRITURE, new ArrayList<>());

        for (int i = 0; i < 18; i++) {
            ressources.get(PieceEnum.INDIEN).add(new Ressource(PieceEnum.INDIEN));
        }
        for (int i = 0; i < 20; i++) {
            ressources.get(PieceEnum.BOIS).add(new Ressource(PieceEnum.BOIS));
        }
        for (int i = 0; i < 20; i++) {
            ressources.get(PieceEnum.FOURRURE).add(new Ressource(PieceEnum.FOURRURE));
        }
        for (int i = 0; i < 15; i++) {
            ressources.get(PieceEnum.NOURRITURE).add(new Ressource(PieceEnum.NOURRITURE));
        }
        for (int i = 0; i < 15; i++) {
            ressources.get(PieceEnum.EQUIPEMENT).add(new Ressource(PieceEnum.EQUIPEMENT));
        }
    }

    public Ressource giveRessource(PieceEnum ressource) throws RessourceOutOfDisponibleException {
        Ressource tampon;
        try{
                tampon = ressources.get(ressource).remove(0);
        }catch (Exception e){
            throw new RessourceOutOfDisponibleException();
        }
        return tampon;
    }

    public void dropRessource(PieceEnum ressource){
        ressources.get(ressource).add(new Ressource(ressource));
    }

    public void dropRessource(Ressource ressource){
        ressources.get(ressource.type).add(ressource);
    }

    public List<Ressource> getTypeRessourceList(PieceEnum type) {
        return ressources.get(type);
    }

    public int getNbressource(PieceEnum pieceEnum){
        return  ressources.get(pieceEnum).size();
    }
}

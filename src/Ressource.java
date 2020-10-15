import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Error.*;

public class Ressource {

    private static final Map<String, List<PieceEnum>> RESSOURCE = new HashMap<>();

    static void initRessource(){
        List<PieceEnum> indiensOnPlateau = new ArrayList<PieceEnum>();

        //Indiens
        for (int i = 0; i < 18; i++)
            indiensOnPlateau.add(PieceEnum.INDIEN);

        List<PieceEnum> boisOnPlateau = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            boisOnPlateau.add(PieceEnum.BOIS);

        List<PieceEnum> fourrure = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            fourrure.add(PieceEnum.FOURRURE);

        List<PieceEnum> nouriture = new ArrayList<>();
        for (int i = 0; i < 15; i++)
            nouriture.add(PieceEnum.NOURRITURE);

        List<PieceEnum> equipement = new ArrayList<>();
        for (int i = 0; i < 15; i++)
            equipement.add(PieceEnum.EQUIPEMENT);

        RESSOURCE.put("Indiens", indiensOnPlateau);
        RESSOURCE.put("Bois", boisOnPlateau);
        RESSOURCE.put("Fourrure", fourrure);
        RESSOURCE.put("Nourriture", nouriture);
        RESSOURCE.put("Equipement", equipement);
    }

    public static List<PieceEnum> getIndiensOnPlateau() {
        return RESSOURCE.get("Indiens");
    }

    public void setIndiensOnPlateau(List<PieceEnum> indiensOnPlateau) {
        RESSOURCE.put("Indiens", indiensOnPlateau);
    }

    public static List<PieceEnum> getBoisOnPlateau() {
        return RESSOURCE.get("Bois");
    }

    public static List<PieceEnum> getFourrureOnPlateau() {
        return RESSOURCE.get("Fourrure");
    }

    public static List<PieceEnum> getNourritureOnPlateau() {
        return RESSOURCE.get("Nourriture");
    }

    public static List<PieceEnum> getEquipementOnPlateau() {
        return RESSOURCE.get("Equipement");
    }

    public static PieceEnum giveRessource(@NotNull PieceEnum ressource) throws RessourceOutOfDisponibleException, RessourceNotExisteException {
        PieceEnum tampon = null;
        try{
            switch (ressource){
                case BOIS -> tampon = RESSOURCE.get("Bois").remove(0);
                case INDIEN -> tampon = RESSOURCE.get("Indiens").remove(0);
                case FOURRURE -> tampon = RESSOURCE.get("Fourrure").remove(0);
                case EQUIPEMENT -> tampon = RESSOURCE.get("Equipement").remove(0);
                case NOURRITURE -> tampon = RESSOURCE.get("Nourriture").remove(0);
                default -> throw new RessourceNotExisteException();
            }

        }catch (Exception e){
            throw new RessourceOutOfDisponibleException();
        }
        return tampon;
    }
}

package lewisclark;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Error.*;

public class Ressource {

    private final Map<String, List<PieceEnum>> RESSOURCE = new HashMap<>();

    public void initRessource(){
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

    public List<PieceEnum> getIndiensOnPlateau() {
        return RESSOURCE.get("Indiens");
    }

    public void setIndiensOnPlateau(List<PieceEnum> indiensOnPlateau) {
        RESSOURCE.put("Indiens", indiensOnPlateau);
    }

    public List<PieceEnum> getBoisOnPlateau() {
        return RESSOURCE.get("Bois");
    }

    public List<PieceEnum> getFourrureOnPlateau() {
        return RESSOURCE.get("Fourrure");
    }

    public List<PieceEnum> getNourritureOnPlateau() {
        return RESSOURCE.get("Nourriture");
    }

    public  List<PieceEnum> getEquipementOnPlateau() {
        return RESSOURCE.get("Equipement");
    }
}

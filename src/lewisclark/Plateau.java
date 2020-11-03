package lewisclark;

public class Plateau {

    Ressource ressource = new Ressource();

    public PieceEnum giveRessource(PieceEnum ressource) throws RessourceOutOfDisponibleException, RessourceNotExisteException {
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

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.List;

public class Joueur {

    String couleur;
    public List<PieceEnum> pieceEnumArrayList = new ArrayList<>();


    public Joueur(){

    }

    public Joueur(String equipe) {
        this.couleur = equipe;
    }

    public void addRessource(PieceEnum pieceEnum){
        if (pieceEnum != null){
            pieceEnumArrayList.add(pieceEnum);
        }
    }

    public void getCouleur(){
        System.out.println(this.couleur);
    }
}

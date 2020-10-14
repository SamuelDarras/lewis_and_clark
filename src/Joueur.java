import javax.swing.plaf.IconUIResource;

public class Joueur {

    String couleur;

    public Joueur(){

    }

    public Joueur(String equipe) {
        this.couleur = equipe;
    }

    public void getCouleur(){
        System.out.println(this.couleur);
    }
}

package graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lewisclark.Game;

import java.util.Random;

import static javafx.application.Application.launch;

public class Vue extends Application{

    private Game game;

    public void start(Stage primaryStage) {

        StackPane root = new StackPane();

        Label nbJoueur = new Label("Entrer nombre joueur :");
        TextField inputNbJoueur = new TextField();
        HBox hbNbJoueur = new HBox();

        Button submit = new Button("Submit");

        hbNbJoueur.getChildren().addAll(nbJoueur, inputNbJoueur, submit);
        hbNbJoueur.setSpacing(10);

        root.getChildren().addAll(hbNbJoueur);

        Scene scene = new Scene(root, 500, 300);

        primaryStage.setScene(scene);

        primaryStage.show();

        submit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> nbJoueur(inputNbJoueur.getText(), primaryStage));
    }

    private void nbJoueur(String text, Stage stage) {
        game.setNbJoueur(Integer.parseInt(text));
    }

    private void setCouleur(Stage stage) {
        for (int i = 0; i < this.game.getNbJoueur()-1; i++){
            StackPane root = new StackPane();

            Label couleurJoueur = new Label("Entrer couleur du joueur :");
            TextField inputCouleurJoueur = new TextField();
            HBox hbVouleurJoueur = new HBox();

            Button submit = new Button("Submit");

            hbVouleurJoueur.getChildren().addAll(couleurJoueur, inputCouleurJoueur, submit);
            hbVouleurJoueur.setSpacing(10);

            root.getChildren().addAll(hbVouleurJoueur);

            Scene scene = new Scene(root, 500, 300);

            stage.setScene(scene);

            stage.show();

            submit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                try {
                    couleurJoueur(inputCouleurJoueur.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            inputCouleurJoueur.clear();
        }
    }

    private void couleurJoueur(String text) throws Exception {
        this.game.addPlayer(text);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static void main(String[] args) {
        Vue vue = new Vue();
        Game game = new Game(new Random());

        vue.setGame(game);

        launch(Vue.class, args);
    }
}

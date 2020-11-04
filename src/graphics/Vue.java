package graphics;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lewisclark.Card;
import lewisclark.Joueur;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lewisclark.Game;
import lewisclark.PieceEnum;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static javafx.application.Application.launch;

public class Vue extends Application{

    private Game game;

    public void start(Stage primaryStage) {

        StackPane root = new StackPane();

        Label nbJoueur = new Label("Entrer nombre joueur :");
        ComboBox<Integer> comboBoxNbJoueur = new ComboBox<>();
        comboBoxNbJoueur.getItems().setAll(1,2,3,4,5);
        comboBoxNbJoueur.setValue(1);
        HBox hbNbJoueur = new HBox();

        Button submit = new Button("Submit");

        hbNbJoueur.getChildren().addAll(nbJoueur, comboBoxNbJoueur, submit);
        hbNbJoueur.setSpacing(10);
        hbNbJoueur.setAlignment(Pos.CENTER);

        root.getChildren().addAll(hbNbJoueur);

        Scene scene = new Scene(root, 500, 300);

        primaryStage.setScene(scene);

        primaryStage.show();

        game = new Game(new Random());


        submit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> nbJoueur(comboBoxNbJoueur.getValue(), primaryStage));
    }

    private void nbJoueur(int nb, Stage stage) {
        game.setNbJoueur(nb);
        setCouleur(stage);
    }

    private void setCouleur(Stage stage) {
        ComboBox<String> comboBoxColor = new ComboBox<>();
        comboBoxColor.getItems().setAll("rouge","jaune","bleu","vert","violet");
        comboBoxColor.setValue(comboBoxColor.getItems().get(0));
        StackPane root = new StackPane();
        AtomicInteger count= new AtomicInteger();
        Label couleurJoueur = new Label("Entrer couleur du joueur :");


        HBox hbCouleurJoueur = new HBox();

        Button submit = new Button("Submit");

        hbCouleurJoueur.getChildren().addAll(couleurJoueur, comboBoxColor, submit);
        hbCouleurJoueur.setSpacing(10);
        hbCouleurJoueur.setAlignment(Pos.CENTER);

        root.getChildren().addAll(hbCouleurJoueur);

        Scene scene = new Scene(root, 500, 300);

        stage.setScene(scene);
        stage.show();
        submit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                couleurJoueur(comboBoxColor.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            comboBoxColor.getItems().remove(comboBoxColor.getValue());
            comboBoxColor.setValue(comboBoxColor.getItems().get(0));
            count.getAndIncrement();
            if(count.get()==game.getNbJoueur()){
                play(stage);
            }

        });

    }
    private void play(Stage stage){
        game.start();

        StackPane root = new StackPane();

        String msg = game.currentPlayer.getCouleur()+" joue";

        Label currPlayer = new Label(msg);

        currPlayer.setAlignment(Pos.BASELINE_CENTER);

        Label title = new Label("Inventaire : ");

        String nbfourrure = String.valueOf(game.currentPlayer.miniPlateau.countNbRessource(PieceEnum.FOURRURE));
        Label fourrure = new Label("Fourrure : "+nbfourrure);

        String nbEquipement = String.valueOf(game.currentPlayer.miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT));
        Label equipement = new Label("Equipement : "+nbEquipement);

        String nbNourriture = String.valueOf(game.currentPlayer.miniPlateau.countNbRessource(PieceEnum.NOURRITURE));
        Label nourriture = new Label("Nourriture : "+nbNourriture);

        String nbIndien = String.valueOf(game.currentPlayer.miniPlateau.countNbRessource(PieceEnum.INDIEN));
        Label indien = new Label("Indien : "+nbIndien);

        VBox vbMiniPlateau = new VBox();
        vbMiniPlateau.getChildren().addAll(title, fourrure, equipement,nourriture,indien);
        vbMiniPlateau.setSpacing(10);
        vbMiniPlateau.setAlignment(Pos.CENTER_RIGHT);

        Label card = new Label("Inventaire carte :");

        VBox deck = new VBox();
        deck.getChildren().add(card);

        for (Card c : game.currentPlayer.cards){
            Label tmp = new Label(c.getCardName());
            deck.getChildren().add(tmp);
        }

        deck.setSpacing(10);
        deck.setAlignment(Pos.CENTER_LEFT);

        root.getChildren().addAll(currPlayer, vbMiniPlateau, deck);

        Scene scene = new Scene(root, 500, 300);

        stage.setScene(scene);

        stage.show();
    }

    private void couleurJoueur(String text) throws Exception {
        Joueur joueur = new Joueur(text, game.getPlateau());
        this.game.addPlayer(joueur);
    }


    public static void main(String[] args) {
        launch(Vue.class, args);
    }

}


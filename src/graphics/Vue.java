package graphics;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import lewisclark.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static javafx.application.Application.launch;
import Error.*;

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
                try {
                    game.start();
                    play(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }
    private void play(Stage stage) throws Exception {

        StackPane root = new StackPane();

        /*
           ? affichage des informations pour le joueur actif
        */
        HBox hbMiniPlateau = new HBox();
        MiniPlateauExpedition currentMPE = game.currentPlayer.miniPlateau;
        List<VBox> vbBateauRes = new ArrayList<>();
        for (int i = 0 ; i < currentMPE.MAX_BATEAU_RES.length ; i++){
            vbBateauRes.add(new VBox());
            Label lBateau = new Label("bateau "+ i);
            HBox hbEmplacementBateau = new HBox();
            for (int j = 0 ; j < currentMPE.MAX_BATEAU_RES[i];j++){
                FileInputStream fileRes;
                if(currentMPE.bateauRes.get(i).size() > j){
                    PieceEnum currentRes = currentMPE.bateauRes.get(i).get(j).type;
                    fileRes = new FileInputStream("src/image/"+currentRes+".png");
                }else
                    fileRes = new FileInputStream("src/image/nothing.png");

                Image iRes = new Image(fileRes,32,32,false,false);
                ImageView ivRes = new ImageView(iRes);
                ivRes.setId(i+"-"+j+"-Res");
                ivRes.setOnDragDetected(event ->{
                    ivRes.startFullDrag();
                    event.consume();
                });

                ivRes.setOnMouseDragReleased(event->{
                         ImageView source = null;
                         ImageView target = null;
                        try{
                            source = (ImageView) event.getGestureSource();
                            target = (ImageView) event.getTarget();
                        }catch (Exception e ){
                            event.consume();
                        }
                        if(source.getId().contains("Res") == target.getId().contains("Res")){
                            String[] infoS = source.getId().split("-");
                            String[] infoD = target.getId().split("-");
                            HBox hbS = ((HBox)((VBox) hbMiniPlateau.getChildren().get(Integer.parseInt(infoS[0]))).getChildren().get(1));
                            HBox hbD = ((HBox)((VBox) hbMiniPlateau.getChildren().get(Integer.parseInt(infoD[0]))).getChildren().get(1));
                            hbD.getChildren().remove(target);
                            hbS.getChildren().add(Integer.parseInt(infoS[1]),target);
                            hbS.getChildren().remove(source);
                            hbD.getChildren().add(Integer.parseInt(infoD[1]),source);

                            String tmp = source.getId();
                            source.setId(target.getId());
                            target.setId(tmp);
                        }

                        event.consume();
                });

                hbEmplacementBateau.getChildren().add(ivRes);

            }
            vbBateauRes.get(i).getChildren().add(lBateau);
            vbBateauRes.get(i).getChildren().add(hbEmplacementBateau);
        }
        hbMiniPlateau.getChildren().addAll(vbBateauRes);
        hbMiniPlateau.setSpacing(20);
        hbMiniPlateau.setAlignment(Pos.BOTTOM_CENTER);







        /*
          * inventaire
        */
        String msg = game.currentPlayer.getCouleur()+" joue";

        Label currPlayer = new Label(msg);
        currPlayer.setStyle("-fx-font: normal bold 50px 'serif'");

        Label title = new Label("Inventaire : ");
        title.setStyle("-fx-font: normal bold 20px 'serif'");

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
        vbMiniPlateau.setAlignment(Pos.CENTER_LEFT);
        vbMiniPlateau.setPadding(new Insets(20));

        /*
           * inventaire carte
         */

        Label card = new Label("Inventaire carte :");
        card.setStyle("-fx-font: normal bold 20px 'serif'");

        VBox deck = new VBox();
        deck.getChildren().addAll(currPlayer, card);

        for (Card c : game.currentPlayer.cards){
            Label tmp = new Label(c.getCardName());
            deck.getChildren().add(tmp);
        }

        deck.setSpacing(10);
        deck.setAlignment(Pos.TOP_LEFT);
        deck.setPadding(new Insets(20));

        /*
           ? Plateau
         */

        FileInputStream filePlateau = new FileInputStream("src/image/Plateau.png");
        Image plateau = new Image(filePlateau,800,700,false,false);
        ImageView plateauView = new ImageView(plateau);

        /*
           * inventaire plateau
         */

        Label srcPlateau = new Label("Inventaire plateau :");
        srcPlateau.setStyle("-fx-font: normal bold 20px 'serif'");

        VBox inventairePlateau = new VBox();
        inventairePlateau.getChildren().add(srcPlateau);

        String nbIndienOP = String.valueOf(game.plateau.getNbressource(PieceEnum.INDIEN));
        Label nbIndienView = new Label("Indien : "+nbIndienOP);

        String nbBoisOP = String.valueOf(game.plateau.getNbressource(PieceEnum.BOIS));
        Label nbBoisView = new Label("Bois : "+nbBoisOP);

        String nbFourrureOP = String.valueOf(game.plateau.getNbressource(PieceEnum.FOURRURE));
        Label nbFourrureView = new Label("Fourrure : "+nbFourrureOP);

        String nbNourritureOP = String.valueOf(game.plateau.getNbressource(PieceEnum.NOURRITURE));
        Label nbNourritureView = new Label("Nourriture : "+nbNourritureOP);

        String nbEquipementOP = String.valueOf(game.plateau.getNbressource(PieceEnum.EQUIPEMENT));
        Label nbEquipementView = new Label("Equipement : "+nbEquipementOP);

        inventairePlateau.getChildren().addAll(nbIndienView,nbBoisView,nbFourrureView,nbNourritureView,nbEquipementView);
        inventairePlateau.setSpacing(10);
        inventairePlateau.setAlignment(Pos.TOP_RIGHT);
        inventairePlateau.setPadding(new Insets(0,10,0,0));

        /*
           * inventaire autres joueurs
        */

        VBox test = new VBox();

        Label inventaireOther = new Label("Inventaire des autres : ");
        inventaireOther.setStyle("-fx-font: normal bold 20px 'serif'");

        test.getChildren().add(inventaireOther);

        for (int i = 0; i < game.getNbJoueur(); i++){
            if (!game.players.get(i).getCouleur().equals(game.currentPlayer.getCouleur())){
                Label lab = new Label(
                        game.players.get(i).getCouleur() +
                        " | Four. : " + game.players.get(i).miniPlateau.countNbRessource(PieceEnum.FOURRURE) +
                                " | Equi. : " + game.players.get(i).miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT) +
                                " | Nourr. : " + game.players.get(i).miniPlateau.countNbRessource(PieceEnum.NOURRITURE) +
                                " | Indien : " + game.players.get(i).miniPlateau.countNbRessource(PieceEnum.INDIEN) + "  "

                );
                test.getChildren().add(lab);
            }
        }

        test.setAlignment(Pos.CENTER_RIGHT);

        /*
           * carte pour achat
        */

        Button carte1 = new Button(game.plateau.getCarteAchat().get(0).getCardName());
        carte1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                game.plateau.achatCarte(game.getCurrentPlayer(), 0);
            } catch (RessourceOutOfDisponibleException | JournalVideException e) {
                e.printStackTrace();
            }
        });

        Button carte2 = new Button(game.plateau.getCarteAchat().get(1).getCardName());
        carte2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                game.plateau.achatCarte(game.getCurrentPlayer(), 1);
            } catch (RessourceOutOfDisponibleException | JournalVideException e) {
                e.printStackTrace();
            }
        });

        Button carte3 = new Button(game.plateau.getCarteAchat().get(2).getCardName());
        carte3.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                game.plateau.achatCarte(game.getCurrentPlayer(), 2);
            } catch (RessourceOutOfDisponibleException | JournalVideException e) {
                e.printStackTrace();
            }
        });

        Button carte4 = new Button(game.plateau.getCarteAchat().get(3).getCardName());
        carte4.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                game.plateau.achatCarte(game.getCurrentPlayer(), 3);
            } catch (RessourceOutOfDisponibleException | JournalVideException e) {
                e.printStackTrace();
            }
        });

        Button carte5 = new Button(game.plateau.getCarteAchat().get(4).getCardName());
        carte5.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                game.plateau.achatCarte(game.getCurrentPlayer(), 4);
            } catch (RessourceOutOfDisponibleException | JournalVideException e) {
                e.printStackTrace();
            }
        });

        VBox pileAchat = new VBox();
        pileAchat.getChildren().addAll(carte5,carte4,carte3,carte2,carte1);

        pileAchat.setAlignment(Pos.CENTER);
        pileAchat.setPadding(new Insets(130,0,0,650));
        pileAchat.setSpacing(85);

        /*
           ? actions
         */

        Button nextTurn = new Button("next turn");
        nextTurn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            game.nextTurn();
            try {
                play(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox action = new VBox();
        action.getChildren().addAll(nextTurn);
        action.setAlignment(Pos.BOTTOM_RIGHT);





        /*
            ! laisser action en dernier sinon les boutons ne marchent plus
         */
        GridPane gpGame = new GridPane();
        gpGame.setGridLinesVisible(true);
        VBox vbplateau = new VBox();
        vbplateau.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("src/image/Plateau.png"),800,700,false,false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        vbplateau.setMinSize(800,700);
        vbplateau.getChildren().addAll(pileAchat);
        gpGame.add(vbplateau,2,2);
        gpGame.add(vbMiniPlateau,1,1);
        gpGame.add(deck,1,2);
        gpGame.add(inventairePlateau,3,1);
        gpGame.add(test,3,2);
        gpGame.add(action,3,3);
        gpGame.add(hbMiniPlateau,2,3);


        root.getChildren().addAll(gpGame);


        Scene scene = new Scene(root, 1500, 950);

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


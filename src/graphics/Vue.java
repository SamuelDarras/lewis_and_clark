package graphics;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import lewisclark.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
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
            Label lBateau = new Label("bRes "+ i);
            HBox hbEmplacementBateau = new HBox();
            for (int j = 0 ; j < currentMPE.MAX_BATEAU_RES[i];j++){
                FileInputStream fileRes;
                if(currentMPE.bateauRes.get(i).get(j) != null){
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
                        if(source.getId().contains("Res") == target.getId().contains("Res") && !source.getId().equals(target.getId())){
                            String[] infoS = source.getId().split("-");
                            String[] infoD = target.getId().split("-");
                            HBox hbS = ((HBox)((VBox) ((HBox)hbMiniPlateau.getChildren().get(0)).getChildren().get(Integer.parseInt(infoS[0]))).getChildren().get(1));
                            HBox hbD = ((HBox)((VBox) ((HBox)hbMiniPlateau.getChildren().get(0)).getChildren().get(Integer.parseInt(infoD[0]))).getChildren().get(1));
                            hbD.getChildren().remove(target);
                            hbS.getChildren().add(Integer.parseInt(infoS[1]),target);
                            hbS.getChildren().remove(source);
                            hbD.getChildren().add(Integer.parseInt(infoD[1]),source);
                            currentMPE.deplacerRessourceMiniPlateau(Integer.parseInt(infoS[0]),Integer.parseInt(infoD[0]),Integer.parseInt(infoS[1]),Integer.parseInt(infoD[1]));

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
        HBox hbBateauRes = new HBox();
        hbBateauRes.getChildren().addAll(vbBateauRes);
        hbBateauRes.setSpacing(20);
        hbMiniPlateau.getChildren().addAll(hbBateauRes);

        List<VBox> vbBateauInd = new ArrayList<>();
        for (int i = 0 ; i < currentMPE.MAX_BATEAU_IND.length ; i++){
            vbBateauInd.add(new VBox());
            Label lBateau = new Label("bInd "+ i);
            HBox hbEmplacementBateau = new HBox();
            for (int j = 0 ; j < currentMPE.MAX_BATEAU_IND[i];j++){
                FileInputStream fileInd;
                if(currentMPE.bateauInd.get(i).get(j) != null){
                    fileInd = new FileInputStream("src/image/INDIEN.png");
                }else
                    fileInd = new FileInputStream("src/image/nothing.png");

                Image iInd = new Image(fileInd,32,32,false,false);
                ImageView ivInd = new ImageView(iInd);
                ivInd.setId(i+"-"+j+"-Ind");
                ivInd.setOnDragDetected(event ->{
                    ivInd.startFullDrag();
                    event.consume();
                });

                ivInd.setOnMouseDragReleased(event->{
                    ImageView source = null;
                    ImageView target = null;
                    try{
                        source = (ImageView) event.getGestureSource();
                        target = (ImageView) event.getTarget();
                    }catch (Exception e ){
                        event.consume();
                    }
                    if(source.getId().contains("Ind") == target.getId().contains("Ind")){
                        String[] infoS = source.getId().split("-");
                        String[] infoD = target.getId().split("-");
                        HBox hbS = ((HBox)((VBox) ((HBox)hbMiniPlateau.getChildren().get(1)).getChildren().get(Integer.parseInt(infoS[0]))).getChildren().get(1));
                        HBox hbD = ((HBox)((VBox) ((HBox)hbMiniPlateau.getChildren().get(1)).getChildren().get(Integer.parseInt(infoD[0]))).getChildren().get(1));
                        hbD.getChildren().remove(target);
                        hbS.getChildren().add(Integer.parseInt(infoS[1]),target);
                        hbS.getChildren().remove(source);
                        hbD.getChildren().add(Integer.parseInt(infoD[1]),source);
                        currentMPE.deplacerIndienMiniPlateau(Integer.parseInt(infoS[0]),Integer.parseInt(infoD[0]),Integer.parseInt(infoS[1]),Integer.parseInt(infoD[1]));

                        String tmp = source.getId();
                        source.setId(target.getId());
                        target.setId(tmp);
                    }

                    event.consume();
                });

                hbEmplacementBateau.getChildren().add(ivInd);

            }
            vbBateauInd.get(i).getChildren().add(lBateau);
            vbBateauInd.get(i).getChildren().add(hbEmplacementBateau);
        }
        HBox hbBateauInd = new HBox();
        hbBateauInd.getChildren().addAll(vbBateauInd);
        hbBateauInd.setSpacing(20);
        hbMiniPlateau.getChildren().addAll(hbBateauInd);
        hbMiniPlateau.setSpacing(20);
        hbMiniPlateau.setAlignment(Pos.BOTTOM_CENTER);
        
        /*
          * inventaire
        */
        String msg = game.currentPlayer.getCouleur()+" joue";

        Label currPlayer = new Label(msg);
        currPlayer.setStyle("-fx-font: normal bold 50px 'serif'");

        /*Label title = new Label("Inventaire : ");
        title.setStyle("-fx-font: normal bold 20px 'serif'");

        String nbfourrure = String.valueOf(game.currentPlayer.miniPlateau.countNbRessource(PieceEnum.FOURRURE));
        Label fourrure = new Label("Fourrure : "+nbfourrure);

        String nbEquipement = String.valueOf(game.currentPlayer.miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT));
        Label equipement = new Label("Equipement : "+nbEquipement);

        String nbNourriture = String.valueOf(game.currentPlayer.miniPlateau.countNbRessource(PieceEnum.NOURRITURE));
        Label nourriture = new Label("Nourriture : "+nbNourriture);

        String nbIndien = String.valueOf(game.currentPlayer.miniPlateau.countNbRessource(PieceEnum.INDIEN));
        Label indien = new Label("Indien : "+nbIndien);*/

        VBox vbMiniPlateau = new VBox();
        /*vbMiniPlateau.getChildren().addAll(title, fourrure, equipement,nourriture,indien);
        vbMiniPlateau.setSpacing(10);
        vbMiniPlateau.setAlignment(Pos.CENTER_LEFT);
        vbMiniPlateau.setPadding(new Insets(20));*/

        /*
           * inventaire carte
         */

        Label card = new Label("Inventaire carte :");
        card.setStyle("-fx-font: normal bold 20px 'serif'");

        VBox deck = new VBox();
        deck.getChildren().addAll(currPlayer, card);

        for (Card c : game.currentPlayer.cards){

            Button cardPop = new Button(c.getCardName());
            createPopUp(cardPop, c, stage);

            if (c.getUsed()){
                cardPop.setText(c.getCardName()+" : used");
            }

            if(c.isAssocied()){
                cardPop = null;
            }
            else {
                deck.getChildren().add(cardPop);
            }
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

        VBox vbOther = new VBox();

        Label inventaireOther = new Label("Inventaire des autres joueurs: ");
        inventaireOther.setStyle("-fx-font: normal bold 20px 'serif'");

        vbOther.getChildren().add(inventaireOther);

        for (int i = 0; i < game.getNbJoueur(); i++){
            if (!game.players.get(i).getCouleur().equals(game.currentPlayer.getCouleur())){
                Label lab = new Label(
                        game.players.get(i).getCouleur() +
                        " | Four. : " + game.players.get(i).miniPlateau.countNbRessource(PieceEnum.FOURRURE) +
                                " | Equi. : " + game.players.get(i).miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT) +
                                " | Nourr. : " + game.players.get(i).miniPlateau.countNbRessource(PieceEnum.NOURRITURE) +
                                " | Indien : " + game.players.get(i).miniPlateau.countNbRessource(PieceEnum.INDIEN) + "  "

                );
                vbOther.getChildren().add(lab);
            }
        }

        vbOther.setAlignment(Pos.CENTER_RIGHT);

        /*
           * carte pour achat
        */

        Button carte1 = new Button(game.plateau.getCarteAchat().get(0).getCardName());
        carte1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                game.plateau.achatCarte(game.getCurrentPlayer(), 0);
                try {
                    play(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } catch (RessourceOutOfDisponibleException | JournalVideException | DejaAchatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec achat carte");
                alert.setHeaderText(null);
                alert.setContentText("Pas assez de ressources : \n\n Coût fourrure: 1 , Coût Equipement : "+String.valueOf(game.plateau.getCarteAchat().get(0).getStrength())+"\n\n" +
                                     "Votre fourrure : "+String.valueOf(game.getCurrentPlayer().miniPlateau.countNbRessource(PieceEnum.FOURRURE))+" , votre Equipement : "+String.valueOf(game.getCurrentPlayer().miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT)));
                alert.showAndWait();
            }
        });

        Button carte2 = new Button(game.plateau.getCarteAchat().get(1).getCardName());
        carte2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                game.plateau.achatCarte(game.getCurrentPlayer(), 1);
                try {
                    play(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } catch (RessourceOutOfDisponibleException | JournalVideException | DejaAchatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec achat carte");
                alert.setHeaderText(null);
                alert.setContentText("Pas assez de ressources : \n\n Coût fourrure: 2 , Coût Equipement : "+String.valueOf(game.plateau.getCarteAchat().get(0).getStrength())+"\n\n" +
                        "Votre fourrure : "+String.valueOf(game.getCurrentPlayer().miniPlateau.countNbRessource(PieceEnum.FOURRURE))+" , votre Equipement : "+String.valueOf(game.getCurrentPlayer().miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT)));
                alert.showAndWait();
            }
        });

        Button carte3 = new Button(game.plateau.getCarteAchat().get(2).getCardName());
        carte3.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                game.plateau.achatCarte(game.getCurrentPlayer(), 2);
                try {
                    play(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } catch (RessourceOutOfDisponibleException | JournalVideException | DejaAchatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec achat carte");
                alert.setHeaderText(null);
                alert.setContentText("Pas assez de ressources : \n\n Coût fourrure: 3 , Coût Equipement : "+String.valueOf(game.plateau.getCarteAchat().get(0).getStrength())+"\n\n" +
                        "Votre fourrure : "+String.valueOf(game.getCurrentPlayer().miniPlateau.countNbRessource(PieceEnum.FOURRURE))+" , votre Equipement : "+String.valueOf(game.getCurrentPlayer().miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT)));
                alert.showAndWait();
            }
        });

        Button carte4 = new Button(game.plateau.getCarteAchat().get(3).getCardName());
        carte4.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                game.plateau.achatCarte(game.getCurrentPlayer(), 3);
                try {
                    play(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } catch (RessourceOutOfDisponibleException | JournalVideException | DejaAchatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec achat carte");
                alert.setHeaderText(null);
                alert.setContentText("Pas assez de ressources : \n\n Coût fourrure: 4 , Coût Equipement : "+String.valueOf(game.plateau.getCarteAchat().get(0).getStrength())+"\n\n" +
                        "Votre fourrure : "+String.valueOf(game.getCurrentPlayer().miniPlateau.countNbRessource(PieceEnum.FOURRURE))+" , votre Equipement : "+String.valueOf(game.getCurrentPlayer().miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT)));
                alert.showAndWait();
            }
        });

        Button carte5 = new Button(game.plateau.getCarteAchat().get(4).getCardName());
        carte5.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                game.plateau.achatCarte(game.getCurrentPlayer(), 4);
                try {
                    play(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } catch (RessourceOutOfDisponibleException | JournalVideException | DejaAchatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec achat carte");
                alert.setHeaderText(null);
                alert.setContentText("Pas assez de ressources : \n\n Coût fourrure: 5 , Coût Equipement : "+String.valueOf(game.plateau.getCarteAchat().get(0).getStrength())+"\n\n" +
                        "Votre fourrure : "+String.valueOf(game.getCurrentPlayer().miniPlateau.countNbRessource(PieceEnum.FOURRURE))+" , votre Equipement : "+String.valueOf(game.getCurrentPlayer().miniPlateau.countNbRessource(PieceEnum.EQUIPEMENT)));
                alert.showAndWait();
            }
        });

        VBox pileAchat = new VBox();
        pileAchat.getChildren().addAll(carte5,carte4,carte3,carte2,carte1);
        pileAchat.setAlignment(Pos.CENTER_RIGHT);

        /*
           ? actions
         */

        /*
           * next turn
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

        /*
           * emplacements plateau
        */
        GridPane gridButton = new GridPane();
        gridButton.setGridLinesVisible(true);
        gridButton.setMaxSize(200,200);

        VBox action = new VBox();
        action.getChildren().add(nextTurn);
        action.setAlignment(Pos.BOTTOM_RIGHT);

        Pane vbplateau = new Pane();

        Button[] btActions = new Button[8];
        int[][] positions = {
                {190, 250},
                {300, 260},
                {175, 450},
                {235, 630},
                {360, 550},
                {470, 540},
                {530, 440},
                {100, 300},
        };

        for (int i = 0; i < btActions.length; i++) {
            btActions[i] = new Button(String.valueOf(i));
            createPopUpPlateau(btActions[i],stage);
        }

        Label tmp[] = new Label[8];
        for (int i = 0; i < btActions.length; i++) {
            tmp[i] = new Label(String.valueOf(i));
            tmp[i].setStyle(" -fx-background-color: white;");
            tmp[i].setLayoutX(positions[i][0]);
            tmp[i].setLayoutY(positions[i][1]);
            vbplateau.getChildren().add(tmp[i]);
        }

        final int numCols = 8 ;
        final int numRows = 8 ;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            gridButton.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            gridButton.getRowConstraints().add(rowConst);
        }
        btActions[0].setAlignment(Pos.CENTER);

        gridButton.add(btActions[0],1,0);
        gridButton.add(btActions[1],3,1);
        gridButton.add(btActions[7],0,2);
        gridButton.add(btActions[2],1,4);
        gridButton.add(btActions[4],7,4);
        gridButton.add(btActions[6],4,5);
        gridButton.add(btActions[5],6,5);
        gridButton.add(btActions[3],2,7);

        /*
           * emplacement indien
        */
        if (game.plateau.getNbressource(PieceEnum.INDIEN) != 0) {
            FileInputStream indienFIS = new FileInputStream("src/image/INDIEN.png");
            Image indienIMG = new Image(indienFIS, 28, 28, false, false);
            ImageView indienIV = new ImageView(indienIMG);

            indienIV.setX(330);
            indienIV.setY(620);

            vbplateau.getChildren().add(indienIV);
        }



        /*
           * campement
        */

        Button campement = new Button("campement");
        campement.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            game.currentPlayer.setCampement();
            try {
                play(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        action.getChildren().add(campement);

        /*
            ? affichage
         */
        GridPane gpGame = new GridPane();
        gpGame.setGridLinesVisible(true);

        vbplateau.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("src/image/Plateau.png"),800,700,false,false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        vbplateau.setMinSize(800,700);
        gpGame.add(gridButton,1,0);
        gpGame.add(vbplateau,1,1,2,1);
        gpGame.add(pileAchat,2,1);
        gpGame.add(deck,0,1);
        gpGame.add(inventairePlateau,3,0);
        gpGame.add(vbOther,3,1);
        gpGame.add(action,3,2);
        gpGame.add(hbMiniPlateau,1,2);


        root.getChildren().addAll(gpGame);


        Scene scene = new Scene(root, 1500, 950);

        stage.setScene(scene);

        stage.show();
    }

    private Button createPopUp(Button cardPop, Card c, Stage stage) {

        Button submitButton = new Button("Submit");

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);
        grid.setStyle(" -fx-background-color: white;");
        grid.minHeight(20);
        grid.minWidth(20);

        Label name = new Label(c.getCardName());
        name.setStyle("-fx-font: normal bold 12px 'serif'");

        Label description = new Label(c.getActionDescription());

        Label labNbIndien = new Label("Nombre d'indiens à associer");
        ComboBox<Integer> nbIndien = new ComboBox<>();
        nbIndien.getItems().setAll(0,1,2,3);
        nbIndien.setValue(0);

        Label labCardChoice = new Label("Associer une carte");
        ComboBox<String> card = new ComboBox<>();

        card.getItems().add("Pas de carte associe");
        card.setValue("Pas de carte associe");
        for (Card deck : game.currentPlayer.cards) {
            if (!deck.getUsed() && deck != c) {
                if (deck.getStrength() < (3 - nbIndien.getValue())) {
                    String nom = deck.getCardName() + "-" + deck.getStrength();
                    card.getItems().add(nom);
                }
            }
        }

        grid.add(name,1,0);
        grid.add(description,1,1);

        if (!c.getUsed()){

        grid.add(labNbIndien,0,3);
        grid.add(nbIndien,0,4);

        grid.add(labCardChoice,2,3);
        grid.add(card,2,4);

        grid.add(submitButton,3,5);
        }

        Popup pop = new Popup();
        pop.getContent().addAll(grid);

        cardPop.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!pop.isShowing()) {
                pop.show(stage);
            }
            else
                pop.hide();
        });

        submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            pop.hide();
            if (nbIndien.getValue() > game.currentPlayer.miniPlateau.countNbRessource(PieceEnum.INDIEN))
                try {
                    throw new RessourceOutOfDisponibleException();
                } catch (RessourceOutOfDisponibleException e) {
                    e.printStackTrace();
                }
            else {
                int nb = nbIndien.getValue();
                String[] cardUsed = null;

                if (!card.getValue().equals("Pas de carte associe")) {

                    cardUsed = card.getValue().split("-");
                    Card used = game.currentPlayer.findCard(cardUsed[0]);
                    used.setUsed(true);

                    nb += Integer.parseInt(cardUsed[1]);
                }

                List<Ressource> src = new ArrayList<>();
                for (int i = 0; i < nb; i++){
                    src.add(game.currentPlayer.miniPlateau.deleteRessource(PieceEnum.INDIEN));
                    game.plateau.dropRessource(new Ressource(PieceEnum.INDIEN));
                }

                c.placerIndiensSurCarte(src, c);

                try {
                    play(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        return cardPop;
    }

    private Button createPopUpPlateau(Button cardPop, Stage stage) {

        Button submitButton = new Button("Submit");


        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);
        grid.setStyle(" -fx-background-color: white;");
        grid.minHeight(20);
        grid.minWidth(20);

        Label name = new Label("test");
        name.setStyle("-fx-font: normal bold 12px 'serif'");

        Label description = new Label("ceci est un test");

        Label labNbIndien = new Label("Nombre d'indiensà placer");
        ComboBox<Integer> nbIndien = new ComboBox<>();
        nbIndien.getItems().setAll(0,1,2,3);
        nbIndien.setValue(0);


        grid.add(name,1,0);
        grid.add(description,1,1);

        grid.add(labNbIndien,0,3);
        grid.add(nbIndien,0,4);


        grid.add(submitButton,3,5);


        Popup pop = new Popup();
        pop.getContent().addAll(grid);

        cardPop.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!pop.isShowing())
                pop.show(stage);
            else
                pop.hide();
        });

        submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            pop.hide();
            if (nbIndien.getValue() > game.currentPlayer.miniPlateau.countNbRessource(PieceEnum.INDIEN))
                try {
                    throw new RessourceOutOfDisponibleException();
                } catch (RessourceOutOfDisponibleException e) {
                    e.printStackTrace();
                }
            else {
                int nb = nbIndien.getValue();

                List<Ressource> src = new ArrayList<>();
                for (int i = 0; i < nb; i++) {
                    src.add(game.currentPlayer.miniPlateau.deleteRessource(PieceEnum.INDIEN));
                    game.plateau.dropRessource(new Ressource(PieceEnum.INDIEN));
                }
                try {
                    play(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return cardPop;
    }

    private void couleurJoueur(String text) throws Exception {
        Joueur joueur = new Joueur(text, game.getPlateau());
        this.game.addPlayer(joueur);
    }


    public static void main(String[] args) {
        launch(Vue.class, args);
    }

}


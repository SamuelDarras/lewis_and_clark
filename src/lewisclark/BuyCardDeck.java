package lewisclark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuyCardDeck {
    List<Card> cards;
    final PieceEnum bois = PieceEnum.BOIS;
    final PieceEnum fourrure = PieceEnum.FOURRURE;
    final PieceEnum nourriture = PieceEnum.NOURRITURE;
    final PieceEnum equipement = PieceEnum.EQUIPEMENT;

    

    public BuyCardDeck(){
        cards = new ArrayList<>();
        initDeck();
    }
    public void initDeck(){
        String[] cardsName = new String[]{
                "Hay","Little Raven","Buffalo Medicine","Ebenezer Tuttle","René Jessaume","Big Horse","Moses B. Reed","John Robertson","Joseph Barter","J. Baptiste Deschamps",
                "John Newman","Charles Mackenzie","John Dame","Cuscalar","Toussaint Charbonneau","F. Antoine Larocque","Joseph Gravelines","John Boley","John Hay","Big White",
                "Dickson & Hancock","Black Moccasin","Yellept","P. Antoine Tabeau","Three Eagles","Hawk’s Feather","Man Crow","Cutssahnem","Black Buffalo","Half Man",
                "Richard Warfington","James Mackay","Weuche","Pierre Dorion","The Partisan","Nicholas Jarrot","Régis Loisel","Hugh Heney","Black Cat","Twisted Hair",
                "Cameahwait","Broken Arm","Comcomly","Little Thief","Watkuweis","Daniel Boone","Old Toby","Coboway","Crow at Rest","George Drouillard",
                "Tetoharsky","One Eye","Sacagawea","Cut Nose"
        };
        String[] cardsActionDescription = new String[]{
                "Prenez autant de ressources primaires différentesque de Personnages actifs sur votre plan de jeu (Hay inclus) (jusqu’à un maximum de 4 ressources donc). Ne comptez que les cartes Personnage actifs (et non les cartes retournées sous les Personnages actifs). L’effet de cette Action ne peut pas s’appliquer plusieurs fois. Activer cette carte avec une Force supérieure à 1 est donc inutile (mais pas interdit).",
                "Choisissez une des quatre ressources primaires. Prenez autant d’hexagones de cette ressource que de Personnages actifs sur votre plan de jeu (Little Raven inclus). Ne comptez que les cartes Personnage actifs (et non les cartes retournées sous les Personnages actifs). L’effet de cette Action ne peut pas s’appliquer plusieurs fois. Activer cette carte avec une Force supérieure à 1 est donc inutile (mais pas interdit).",
                "Défaussez 1 Nourriture et prenez 1 Pirogue.",
                "Défaussez 1 Bois et prenez 1 Pirogue.",
                "Défaussez 3 Fourrures et prenez 1 Cheval.",
                "Défaussez 2 Indiens présents sur les Bateaux de votre Expédition vers la zone de Pow-wow et prenez 1 Cheval.",
                "Défaussez 2 Bois et avancez votre Éclaireur de 2 cases sur la Rivière.",
                "Défaussez 2 Équipements et avancez votre Éclaireur de 3 cases sur la Rivière.",
                "Défaussez 1 Pirogue et avancez votre Éclaireur de 5 cases sur la Rivière.",
                "Défaussez 1 Pirogue et 1 Nourriture et avancez votre Éclaireur de 6 cases sur la Rivière.",
                "Défaussez 1 Nourriture et avancez votre Éclaireur d’une case sur la Rivière ou dans la Montagne.",
                "Défaussez 1 Cheval et 1 Fourrure et avancez votre Éclaireur de 3 cases dans la Montagne.",
                "Défaussez 2 Bois et avancez votre Éclaireur d’une case dans la Montagne.",
                "Pour chaque Force qui active cette carte, défaussez 1 Cheval et ajoutez à votre main la carte Personnage de votre choix, prise sur le Journal des rencontres. Puis complétez le Journal avec la (ou les) première(s) carte(s) de la pioche.",
                "Défaussez 1 ressource de votre choix et réalisez l’Action d’un lieu du Village occupé par au moins un Indien. Si vous activez ce Personnage plusieurs fois, vous pouvez réaliser l’Action de plusieurs lieux différents ou plusieurs fois la même Action. Vous ne pouvez pas bénéficier d’éventuelles ressources ou Indiens reçus lors de la première (ou deuxième) activation pour les activations suivantes. ",
                "Ce Personnage est sans effet au moment où vous l’activez.Mais si F. Antoine Larocque est actif sur votre plan de jeu au moment où vous déplacez votre Éclaireur à l’aide d’un autre Personnage, ne comptez pas, si vous le souhaitez, les cases où sont présents les Éclaireurs adverses lors de ce déplacement. Exemple : s’il y a 2 Éclaireurs adverses sur le chemin de votre Éclaireur alors qu’il doit avancer de 4 cases, avancez-le au final de 6 cases. Votre Éclaireur «saute» les cases occupées par les Éclaireurs adverses. Bien entendu, l’effet de cette Action ne peut pas s’appliquer plusieurs fois.",
                "Ce Personnage est sans effet au moment où vous l’activez.Mais si Joseph Gravelines est actif sur votre plan de jeu au moment où vous recrutez un nouveau Personnage, vous bénéficiez d’une remise de 2 Fourrures sur le coût du Personnage recruté. ",
                "Ce Personnage est sans effet au moment où vous l’activez. Si John Boley est actif sur votre plan de jeu au moment d’établir votre Campement, diminuez de 1 votre Temps d’installation. Cette carte est sans effet si votre Temps d’installation est nul. Le Temps d’installation d’un Campement ne peut jamais être négatif.",
                "Pour chaque Force qui active cette carte, choisissez une des deux ressources : Fourrure ou Bois. Puis collectez-la. (En l’activant trois fois, vous pouvez par exemple collecter deux fois de la Fourrure et une fois du Bois.)",
                "Pour chaque Force qui active cette carte, choisissez une des deux ressources : Équipement ou Bois. Puis collectez-la. (En l’activant trois fois, vous pouvez par exemple collecter deux fois de l’Équipement et une fois du Bois.)",
                "Pour chaque Force qui active cette carte, choisissez une des deux ressources : Nourriture ou Fourrure. Puis collectez-la. (En l’activant trois fois, vous pouvez par exemple collecter deux fois de la Nourriture et une fois de la Fourrure.)",
                "Pour chaque Force qui active cette carte, choisissez une des deux ressources : Équipement ou Nourriture. Puis collectez-la. (En l’activant trois fois, vous pouvez par exemple collecter deux fois de l’Équipement et une fois de la Nourriture.)",
                "Défaussez 1 ressource quelconque et prenez 2 ressources primaires de votre choix. Si vous activez ce Personnage plusieurs fois, vous ne pouvez pas utiliser les ressources reçues lors de la première (ou deuxième) activation pour déclencher les suivantes.",
                "Défaussez 1 Bois et 1 Nourriture et prenez 2 Pirogues.",
                "Défaussez 2 Équipements et prenez 1 Cheval.",
                "Défaussez 1 Nourriture et avancez votre Éclaireur de 3 cases sur la Rivière.",
                "Défaussez 3 Bois et avancez votre Éclaireur de 4 cases sur la Rivière.",
                "Défaussez 3 ressources toutes différentes et avancez votre Éclaireur de 5 cases sur la Rivière.",
                "Défaussez 1 Nourriture et avancez votre Éclaireur d’autant de cases sur la Rivière qu’il y a de macarons Nourriture visibles sur votre plan de jeu et celui de vos deux voisins (ou votre voisin à 2 joueurs). Dans une partie en solitaire, comptez les macarons Nourriture de votre plan de jeu et des lieux occupés du Village.",
                "Défaussez 1 ressource quelconque et avancez votre Éclaireur d’autant de cases sur la Rivière qu’il y a d’Éclaireurs situés sur et en avant de votre Camp (votre propre Éclaireur inclus). Dans une partie solitaire, Alexander Mackenzie est considéré comme un Éclaireur.",
                "Défaussez 2 ressources quelconques et avancez votre Éclaireur d’autant de cases sur la Rivière qu’il y a d’Indiens sur les Bateaux de votre Expédition. Ne comptez pas les Indiens éventuellement présents sur les cartes Personnage de votre plan de jeu.",
                "Défaussez 1 Cheval et avancez votre Éclaireur de 3 cases soit sur la Rivière, soit en Montagne. Pour chaque Force qui active cette carte, le déplacement se fait entièrement soit sur la Rivière, soit en Montagne.",
                "Défaussez 2 Équipements et avancez votre Éclaireur d’autant de cases en Montagne qu’il y a de macarons Équipement visibles sur votre plan de jeu et celui de vos deux voisins (ou votre voisin à 2 joueurs). Dans une partie en solitaire, comptez les macarons Équipement de votre plan de jeu et des lieux occupés du Village.",
                "Défaussez 3 Fourrures et avancez votre Éclaireur de 2 cases dans la Montagne.",
                "Défaussez 1 Nourriture et activez avec une Force de 1 un Personnage actif sur votre plan de jeu ou celui d’un adversaire (identique à l’Action Chamanisme). Exemple : en associant une Force de 2 au Partisan, vous pouvez copier les Actions de 2 Personnages actifs, ou 2 fois l’Action d’un Personnage actif. Vous ne pouvez pas bénéficier d’éventuelles ressources ou Indiens reçus lors de la première (ou deuxième) activation pour les activations suivantes.",
                "Ce Personnage est sans effet au moment où vous l’activez.Mais si Nicholas Jarrot est actif sur votre plan de jeu lorsque vous avancez votre Éclaireur d’au moins une case sur la Rivière à l’aide d’un autre Personnage, ajoutez 2 cases de Rivière à un seul déplacement sur la Rivière, quelle que soit la Force qui active cet autre Personnage.",
                "Pour chaque Force qui active cette carte, choisissez une des trois ressources : Équipement, Nourriture ou Bois. Puis collectez-la. (En l’activant trois fois, vous pouvez par exemple collecter une fois de l’Équipement, une fois de la Nourriture et une fois du Bois.)",
                "Pour chaque Force qui active cette carte, choisissez une des trois ressources : Fourrure, Nourriture ou Bois. Puis collectez-la. (En l’activant trois fois, vous pouvez par exemple collecter une fois de la Fourrure, une fois de la Nourriture et une fois du Bois.)",
                "Prenez 2 ressources primaires de votre choix. Elles peuvent être identiques ou différentes.",
                "Défaussez 1 Pirogue et prenez-en 2. Si vous activez ce Personnage plusieurs fois, vous ne pouvez pas utiliser les Pirogues reçues lors de la première (ou deuxième) activation pour déclencher les suivantes.",
                "Défaussez 1 Pirogue et prenez 1 Cheval ou défaussez 1 Cheval et prenez 1 Pirogue.",
                "Défaussez 1 Équipement et prenez 1 Cheval.",
                "Défaussez 1 Équipement, 1 Nourriture, 1 Fourrure et 1 Bois et avancez votre Éclaireur de 7 cases sur la Rivière.",
                "Défaussez 1 Indien présent sur un Bateau de votre Expédition vers la zone de Pow-wow, puis avancez votre Éclaireur d’autant de cases sur la Rivière qu’il y a de paires de ressources quelconques sur vos Bateaux. Il n’est pas nécessaire que les ressources soient identiques. Ne défaussez pas vos ressources.",
                "Défaussez 1 ressource quelconque et avancez votre Éclaireur d’autant de cases sur la Rivière que de Personnages actifs sur votre plan de jeu (Watkuweis inclus).",
                "Défaussez 2 ressources quelconques et avancez votre Éclaireur d’autant de cases sur la Rivière qu’il y a de pions Indien au Village (sur le plateau).",
                "Défaussez 1 Pirogue et 1 Cheval et avancez votre Éclaireur de 6 cases soit sur la Rivière, soit en Montagne. Pour chaque Force qui active cette carte, le déplacement se fait entièrement soit sur la Rivière, soit en Montagne.",
                "Défaussez 1 Équipement, 1 Nourriture, 1 Fourrure et 1 Bois et avancez votre Éclaireur de 4 cases dans la Montagne.",
                "Défaussez 2 Équipements et 2 Fourrures et avancez votre Éclaireur de 3 cases dans la Montagne.",
                "Défaussez 1 Indien présent sur un Bateau de votre Expédition et 1 ressource quelconque. Puis avancez votre Éclaireur de 2 cases dans la Montagne. L’Indien défaussé est placé sur la zone de Pow-wow.",
                "Défaussez 1 Indien présent sur un Bateau de votre Expédition vers la zone de Pow-wow. Puis avancez votre Éclaireur d’une case dans la Montagne pour chaquegroupe de 3 ressources quelconques présent sur vos Bateaux. Il n’est pas nécessaire que les ressources soient identiques. Ne défaussez pas vos ressources.",
                "Défaussez 2 ressources quelconques puis avancez votre Éclaireur d’autant de cases en Montagne qu’il y a d’Éclaireurs situés sur et en avant de votre Camp (votre Éclaireur inclus). Dans une partie solitaire, Alexander Mackenzie est considéré comme un Éclaireur.",
                "Réalisez une Action du Village indien, que le lieu soit occupé ou non. Vous ne pouvez pas bénéficier d’éventuelles ressources ou Indiens reçus lors de la première (ou deuxième) activation pour les activations suivantes. (Par exemple en l’activant deux fois, vous ne pouvez pas collecter deux Bois grâce à l’Artisanat puis Fabriquer une Pirogue avec ces deux Bois.)",
                "Ce Personnage est sans effet au moment où on l’active. Mais si Cut Nose est actif sur votre plan de jeu lorsque vous avancez votre Éclaireur d’au moins une case en Montagne à l’aide d’un autre Personnage, ajoutez une case de Montagne à un seul déplacement en Montagne, quelle que soit la Force qui active cet autre Personnage."
        };
        int[] cardsStrength = new int[]{
                1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,2,2,
                2,2,2,2,2,2,2,2,2,2,
                2,2,2,2,2,2,2,3,3,3,
                3,3,3,3,3,3,3,3,3,3,
                3,3,3,3
        };
        PieceEnum[] cardsBadge = new PieceEnum[]{
                bois,bois,equipement,fourrure,fourrure,fourrure,fourrure,bois,fourrure,nourriture,
                bois,equipement,equipement,fourrure,bois,nourriture,bois,nourriture,fourrure,equipement,
                nourriture,fourrure,bois,fourrure,bois,fourrure,bois,bois,bois,equipement,
                fourrure,nourriture,fourrure,equipement,nourriture,nourriture,equipement,nourriture,bois,bois,
                fourrure,nourriture,equipement,equipement,fourrure,bois,fourrure,fourrure,equipement,bois,
                bois,nourriture,bois,fourrure
        };
        for(int i = 0 ; i < cardsBadge.length ; i++){
            cards.add(new Card(cardsName[i],cardsStrength[i],cardsActionDescription[i],cardsBadge[i]));
        }
        shuffleDeck();
    }

    public void shuffleDeck(){
        Collections.shuffle(cards);
    }
}

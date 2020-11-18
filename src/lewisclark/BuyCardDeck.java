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
       
               cards.add(new Card(
                       "Hay",
                       "Prenez autant de ressources primaires différentesque de Personnages actifs sur votre plan de jeu (Hay inclus) (jusqu’à un maximum de 4 ressources donc). Ne comptez que les cartes Personnage actifs (et non les cartes retournées sous les Personnages actifs). L’effet de cette Action ne peut pas s’appliquer plusieurs fois. Activer cette carte avec une Force supérieure à 1 est donc inutile (mais pas interdit).",
                       1,
                       bois
               ));
               cards.add(new Card(
                       "Little Raven",
                       "Choisissez une des quatre ressources primaires. Prenez autant d’hexagones de cette ressource que de Personnages actifs sur votre plan de jeu (Little Raven inclus). Ne comptez que les cartes Personnage actifs (et non les cartes retournées sous les Personnages actifs). L’effet de cette Action ne peut pas s’appliquer plusieurs fois. Activer cette carte avec une Force supérieure à 1 est donc inutile (mais pas interdit).",
                       1,
                       bois
               ));
               cards.add(new Card(
                       "Buffalo Medicine",
                       "Défaussez 1 Nourriture et prenez 1 Pirogue.",
                       1,
                       equipement
               ));
               cards.add(new Card(
                       "Ebenezer Tuttle",
                       "Défaussez 1 Bois et prenez 1 Pirogue.",
                       1,
                       fourrure
               ));
               cards.add(new Card(
                       "René Jessaume",
                       "Défaussez 3 Fourrures et prenez 1 Cheval.",
                       1,
                       fourrure
               ));
               cards.add(new Card(
                       "Big Horse",
                       "Défaussez 2 Indiens présents sur les Bateaux de votre Expédition vers la zone de Pow-wow et prenez 1 Cheval.",
                       1,
                       fourrure
               ));
               cards.add(new Card(
                       "Moses B. Reed",
                       "Défaussez 2 Bois et avancez votre Éclaireur de 2 cases sur la Rivière.",
                       1,
                       fourrure
               ));
               cards.add(new Card(
                       "John Robertson",
                       "Défaussez 2 Équipements et avancez votre Éclaireur de 3 cases sur la Rivière.",
                       1,
                       bois
               ));
               cards.add(new Card(
                       "Joseph Barter",
                       "Défaussez 1 Pirogue et avancez votre Éclaireur de 5 cases sur la Rivière.",
                       1,
                       fourrure
               ));
               cards.add(new Card(
                       "J. Baptiste Deschamps",
                       "Défaussez 1 Pirogue et 1 Nourriture et avancez votre Éclaireur de 6 cases sur la Rivière.",
                       1,
                       nourriture
               ));
               cards.add(new Card(
                       "John Newman",
                       "Défaussez 1 Nourriture et avancez votre Éclaireur d’une case sur la Rivière ou dans la Montagne.",
                       1,
                       bois
               ));
               cards.add(new Card(
                       "Charles Mackenzie",
                       "Défaussez 1 Cheval et 1 Fourrure et avancez votre Éclaireur de 3 cases dans la Montagne.",
                       1,
                       equipement
               ));
               cards.add(new Card(
                       "John Dame",
                       "Défaussez 2 Bois et avancez votre Éclaireur d’une case dans la Montagne.",
                       1,
                       equipement
               ));
               cards.add(new Card(
                       "Cuscalar",
                       "Pour chaque Force qui active cette carte, défaussez 1 Cheval et ajoutez à votre main la carte Personnage de votre choix, prise sur le Journal des rencontres. Puis complétez le Journal avec la (ou les) première(s) carte(s) de la pioche.",
                       1,
                       fourrure
               ));
               cards.add(new Card(
                       "Toussaint Charbonneau",
                       "Défaussez 1 ressource de votre choix et réalisez l’Action d’un lieu du Village occupé par au moins un Indien. Si vous activez ce Personnage plusieurs fois, vous pouvez réaliser l’Action de plusieurs lieux différents ou plusieurs fois la même Action. Vous ne pouvez pas bénéficier d’éventuelles ressources ou Indiens reçus lors de la première (ou deuxième) activation pour les activations suivantes. ",
                       1,
                       bois
               ));
               cards.add(new Card(
                       "F. Antoine Larocque",
                       "Ce Personnage est sans effet au moment où vous l’activez.Mais si F. Antoine Larocque est actif sur votre plan de jeu au moment où vous déplacez votre Éclaireur à l’aide d’un autre Personnage, ne comptez pas, si vous le souhaitez, les cases où sont présents les Éclaireurs adverses lors de ce déplacement. Exemple : s’il y a 2 Éclaireurs adverses sur le chemin de votre Éclaireur alors qu’il doit avancer de 4 cases, avancez-le au final de 6 cases. Votre Éclaireur «saute» les cases occupées par les Éclaireurs adverses. Bien entendu, l’effet de cette Action ne peut pas s’appliquer plusieurs fois.",
                       1,
                       nourriture
               ));
               cards.add(new Card(
                       "Joseph Gravelines",
                       "Ce Personnage est sans effet au moment où vous l’activez.Mais si Joseph Gravelines est actif sur votre plan de jeu au moment où vous recrutez un nouveau Personnage, vous bénéficiez d’une remise de 2 Fourrures sur le coût du Personnage recruté. ",
                       1,
                       bois
               ));
               cards.add(new Card(
                       "John Boley",
                       "Ce Personnage est sans effet au moment où vous l’activez. Si John Boley est actif sur votre plan de jeu au moment d’établir votre Campement, diminuez de 1 votre Temps d’installation. Cette carte est sans effet si votre Temps d’installation est nul. Le Temps d’installation d’un Campement ne peut jamais être négatif.",
                       1,
                       nourriture
               ));
               cards.add(new Card(
                       "John Hay",
                       "Pour chaque Force qui active cette carte, choisissez une des deux ressources : Fourrure ou Bois. Puis collectez-la. (En l’activant trois fois, vous pouvez par exemple collecter deux fois de la Fourrure et une fois du Bois.)",
                       2,
                       fourrure
               ));
               cards.add(new Card(
                       "Big White",
                       "Pour chaque Force qui active cette carte, choisissez une des deux ressources : Équipement ou Bois. Puis collectez-la. (En l’activant trois fois, vous pouvez par exemple collecter deux fois de l’Équipement et une fois du Bois.)",
                       2,
                       equipement
               ));
               cards.add(new Card(
                       "Dickson & Hancock",
                       "Pour chaque Force qui active cette carte, choisissez une des deux ressources : Nourriture ou Fourrure. Puis collectez-la. (En l’activant trois fois, vous pouvez par exemple collecter deux fois de la Nourriture et une fois de la Fourrure.)",
                       2,
                       nourriture
               ));
               cards.add(new Card(
                       "Black Moccasin",
                       "Pour chaque Force qui active cette carte, choisissez une des deux ressources : Équipement ou Nourriture. Puis collectez-la. (En l’activant trois fois, vous pouvez par exemple collecter deux fois de l’Équipement et une fois de la Nourriture.)",
                       2,
                       fourrure
               ));
               cards.add(new Card(
                       "Yellept",
                       "Défaussez 1 ressource quelconque et prenez 2 ressources primaires de votre choix. Si vous activez ce Personnage plusieurs fois, vous ne pouvez pas utiliser les ressources reçues lors de la première (ou deuxième) activation pour déclencher les suivantes.",
                       2,
                       bois
               ));
               cards.add(new Card(
                       "P. Antoine Tabeau",
                       "Défaussez 1 Bois et 1 Nourriture et prenez 2 Pirogues.",
                       2,
                       fourrure
               ));
               cards.add(new Card(
                       "Three Eagles",
                       "Défaussez 2 Équipements et prenez 1 Cheval.",
                       2,
                       bois
               ));
               cards.add(new Card(
                       "Hawk’s Feather",
                       "Défaussez 1 Nourriture et avancez votre Éclaireur de 3 cases sur la Rivière.",
                       2,
                       fourrure
               ));
               cards.add(new Card(
                       "Man Crow",
                       "Défaussez 3 Bois et avancez votre Éclaireur de 4 cases sur la Rivière.",
                       2,
                       bois
               ));
               cards.add(new Card(
                       "Cutssahnem",
                       "Défaussez 3 ressources toutes différentes et avancez votre Éclaireur de 5 cases sur la Rivière.",
                       2,
                       bois
               ));
               cards.add(new Card(
                       "Black Buffalo",
                       "Défaussez 1 Nourriture et avancez votre Éclaireur d’autant de cases sur la Rivière qu’il y a de macarons Nourriture visibles sur votre plan de jeu et celui de vos deux voisins (ou votre voisin à 2 joueurs). Dans une partie en solitaire, comptez les macarons Nourriture de votre plan de jeu et des lieux occupés du Village.",
                       2,
                       bois
               ));
               cards.add(new Card(
                       "Half Man",
                       "Défaussez 1 ressource quelconque et avancez votre Éclaireur d’autant de cases sur la Rivière qu’il y a d’Éclaireurs situés sur et en avant de votre Camp (votre propre Éclaireur inclus). Dans une partie solitaire, Alexander Mackenzie est considéré comme un Éclaireur.",
                       2,
                       equipement
               ));
               cards.add(new Card(
                       "Richard Warfington",
                       "Défaussez 2 ressources quelconques et avancez votre Éclaireur d’autant de cases sur la Rivière qu’il y a d’Indiens sur les Bateaux de votre Expédition. Ne comptez pas les Indiens éventuellement présents sur les cartes Personnage de votre plan de jeu.",
                       2,
                       fourrure
               ));
               cards.add(new Card(
                       "James Mackay",
                       "Défaussez 1 Cheval et avancez votre Éclaireur de 3 cases soit sur la Rivière, soit en Montagne. Pour chaque Force qui active cette carte, le déplacement se fait entièrement soit sur la Rivière, soit en Montagne.",
                       2,
                       nourriture
               ));
               cards.add(new Card(
                       "Weuche",
                       "Défaussez 2 Équipements et avancez votre Éclaireur d’autant de cases en Montagne qu’il y a de macarons Équipement visibles sur votre plan de jeu et celui de vos deux voisins (ou votre voisin à 2 joueurs). Dans une partie en solitaire, comptez les macarons Équipement de votre plan de jeu et des lieux occupés du Village.",
                       2,
                       fourrure
               ));
               cards.add(new Card(
                       "Pierre Dorion",
                       "Défaussez 3 Fourrures et avancez votre Éclaireur de 2 cases dans la Montagne.",
                       2,
                       equipement
               ));
               cards.add(new Card(
                       "The Partisan",
                       "Défaussez 1 Nourriture et activez avec une Force de 1 un Personnage actif sur votre plan de jeu ou celui d’un adversaire (identique à l’Action Chamanisme). Exemple : en associant une Force de 2 au Partisan, vous pouvez copier les Actions de 2 Personnages actifs, ou 2 fois l’Action d’un Personnage actif. Vous ne pouvez pas bénéficier d’éventuelles ressources ou Indiens reçus lors de la première (ou deuxième) activation pour les activations suivantes.",
                       2,
                       nourriture
               ));
               cards.add(new Card(
                       "Nicholas Jarrot",
                       "Ce Personnage est sans effet au moment où vous l’activez.Mais si Nicholas Jarrot est actif sur votre plan de jeu lorsque vous avancez votre Éclaireur d’au moins une case sur la Rivière à l’aide d’un autre Personnage, ajoutez 2 cases de Rivière à un seul déplacement sur la Rivière, quelle que soit la Force qui active cet autre Personnage.",
                       2,
                       nourriture
               ));
               cards.add(new Card(
                       "Régis Loisel",
                       "Pour chaque Force qui active cette carte, choisissez une des trois ressources : Équipement, Nourriture ou Bois. Puis collectez-la. (En l’activant trois fois, vous pouvez par exemple collecter une fois de l’Équipement, une fois de la Nourriture et une fois du Bois.)",
                       2,
                       equipement
               ));
               cards.add(new Card(
                       "Hugh Heney",
                       "Pour chaque Force qui active cette carte, choisissez une des trois ressources : Fourrure, Nourriture ou Bois. Puis collectez-la. (En l’activant trois fois, vous pouvez par exemple collecter une fois de la Fourrure, une fois de la Nourriture et une fois du Bois.)",
                       3,
                       nourriture
               ));
               cards.add(new Card(
                       "Black Cat",
                       "Prenez 2 ressources primaires de votre choix. Elles peuvent être identiques ou différentes.",
                       3,
                       bois
               ));
               cards.add(new Card(
                       "Twisted Hair",
                       "Défaussez 1 Pirogue et prenez-en 2. Si vous activez ce Personnage plusieurs fois, vous ne pouvez pas utiliser les Pirogues reçues lors de la première (ou deuxième) activation pour déclencher les suivantes.",
                       3,
                       bois
               ));
               cards.add(new Card(
                       "Cameahwait",
                       "Défaussez 1 Pirogue et prenez 1 Cheval ou défaussez 1 Cheval et prenez 1 Pirogue.",
                       3,
                       fourrure
               ));
               cards.add(new Card(
                       "Broken Arm",
                       "Défaussez 1 Équipement et prenez 1 Cheval.",
                       3,
                       nourriture
               ));
               cards.add(new Card(
                       "Comcomly",
                       "Défaussez 1 Équipement, 1 Nourriture, 1 Fourrure et 1 Bois et avancez votre Éclaireur de 7 cases sur la Rivière.",
                       3,
                       equipement
               ));
               cards.add(new Card(
                       "Little Thief",
                       "Défaussez 1 Indien présent sur un Bateau de votre Expédition vers la zone de Pow-wow, puis avancez votre Éclaireur d’autant de cases sur la Rivière qu’il y a de paires de ressources quelconques sur vos Bateaux. Il n’est pas nécessaire que les ressources soient identiques. Ne défaussez pas vos ressources.",
                       3,
                       equipement
               ));
               cards.add(new Card(
                       "Watkuweis",
                       "Défaussez 1 ressource quelconque et avancez votre Éclaireur d’autant de cases sur la Rivière que de Personnages actifs sur votre plan de jeu (Watkuweis inclus).",
                       3,
                       fourrure
               ));
               cards.add(new Card(
                       "Daniel Boone",
                       "Défaussez 2 ressources quelconques et avancez votre Éclaireur d’autant de cases sur la Rivière qu’il y a de pions Indien au Village (sur le plateau).",
                       3,
                       bois
               ));
               cards.add(new Card(
                       "Old Toby",
                       "Défaussez 1 Pirogue et 1 Cheval et avancez votre Éclaireur de 6 cases soit sur la Rivière, soit en Montagne. Pour chaque Force qui active cette carte, le déplacement se fait entièrement soit sur la Rivière, soit en Montagne.",
                       3,
                       fourrure
               ));
               cards.add(new Card(
                       "Coboway",
                       "Défaussez 1 Équipement, 1 Nourriture, 1 Fourrure et 1 Bois et avancez votre Éclaireur de 4 cases dans la Montagne.",
                       3,
                       fourrure
               ));
               cards.add(new Card(
                       "Crow at Rest",
                       "Défaussez 2 Équipements et 2 Fourrures et avancez votre Éclaireur de 3 cases dans la Montagne.",
                       3,
                       equipement
               ));
               cards.add(new Card(
                       "George Drouillard",
                       "Défaussez 1 Indien présent sur un Bateau de votre Expédition et 1 ressource quelconque. Puis avancez votre Éclaireur de 2 cases dans la Montagne. L’Indien défaussé est placé sur la zone de Pow-wow.",
                       3,
                       bois
               ));
               cards.add(new Card(
                       "Tetoharsky",
                       "Défaussez 1 Indien présent sur un Bateau de votre Expédition vers la zone de Pow-wow. Puis avancez votre Éclaireur d’une case dans la Montagne pour chaquegroupe de 3 ressources quelconques présent sur vos Bateaux. Il n’est pas nécessaire que les ressources soient identiques. Ne défaussez pas vos ressources.",
                       3,
                       bois
               ));
               cards.add(new Card(
                       "One Eye",
                       "Défaussez 2 ressources quelconques puis avancez votre Éclaireur d’autant de cases en Montagne qu’il y a d’Éclaireurs situés sur et en avant de votre Camp (votre Éclaireur inclus). Dans une partie solitaire, Alexander Mackenzie est considéré comme un Éclaireur.",
                       3,
                       nourriture
               ));
               cards.add(new Card(
                       "Sacagawea",
                       "Réalisez une Action du Village indien, que le lieu soit occupé ou non. Vous ne pouvez pas bénéficier d’éventuelles ressources ou Indiens reçus lors de la première (ou deuxième) activation pour les activations suivantes. (Par exemple en l’activant deux fois, vous ne pouvez pas collecter deux Bois grâce à l’Artisanat puis Fabriquer une Pirogue avec ces deux Bois.)",
                       3,
                       bois
               ));
               cards.add(new Card(
                       "Cut Nose",
                       "Ce Personnage est sans effet au moment où on l’active. Mais si Cut Nose est actif sur votre plan de jeu lorsque vous avancez votre Éclaireur d’au moins une case en Montagne à l’aide d’un autre Personnage, ajoutez une case de Montagne à un seul déplacement en Montagne, quelle que soit la Force qui active cet autre Personnage.",
                       3,
                       fourrure
               ));
        shuffleDeck();
    }

    public void shuffleDeck(){
        Collections.shuffle(cards);
    }
}

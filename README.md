# Rogopoly

   Projet crée par SARRAZIN Alexandre, SOUSSAN Jimmy, TAÏLY Pierrick.

   Nous avons rempli toutes les conditions demandées dans le projet.
   Ce que nous avons fait en plus :  
    - Nous avons crées différentes classes de personnage aved différents sorts et différentes
    caractèristiques.  
    - Nous avons mis en place un inventaire pour stocker de l'équipement.  
    - Nous avons ajouté un système achat/vente qui est effectué quand nous parlons avec un
    PNJ. Et par conséquant un système de monnaie.  
    - Nous avons mis en place diffèrents types de cartes (Prison, Chance ...)  

	Commande windows :
	gradlew build
	
	Commande linux :
	./gradlew build pour compiler
	Rejoindre le dossier build/classes/main
	./test pour exécuter
	
   Manuel utilisateur :  

   Le but du jeu est de réussir à survivre le plus longtemps possible à travers un nombre
   infini de cartes et des monstres qui deviennent de plus en plus fort. Tout cela est à
   réaliser avec une seule et unique vie.  
  
   Lors du lancement du jeu, vous aurez la possibilité de choisir entre créer une nouvelle
   partie ou charger une partie sauvegardée (lors du 1er lancement, il n'y aura pas de partie
   sauvegardé... Bonne chance dans votre aventure).

   Vous devrez créer votre propre personnage. Vous devrez choisir votre pseudonyme et
   votre classe.  
            Par exemple, écrivez dans le terminal Alexandre 3. (Ici 3 correspond à Mage)
   Ensuite, vous accédere+z à un univers infini de cartes et de créatures dangereuses( cela
   dépendra de votre niveau... ).  
   Pour voyager dans cet univers, vous aurez besoin d'utiliser des touches directionnelles :
   Z(en haut), Q(a gauche), S(en bas), D(a droite). Et de rentrer le nombre de pas que vous
   voulez faire. Il faut faire attention à ses points de mouvements.  
            Par exemple, écrivez dans le terminal Z 4 (qui signifie se déplacer vers le haut
            de 4 cases).  

   Lors de votre déplacement sur la carte, vous pourrez faire des rencontres innatendues( les
   monstres peuvent vous attaquer si vous passez près d'eux), mais vous pourrez aussi commu-
   niquer avec des marchands ou des intendants.

   Lors de combat, vous devrez utiliser vos compétences. Pour avoir accès aux informations
   des compétences de votre classe, vous pouvez utiliser /info avec le numéro de votre compé-
   tence. Les combats se déroulent jusqu'à la mort d'un des deux participants ou la fuite de
   l'un d'entre d'eux. Par ailleurs, certaines de vos compétences ont des temps de récupéra-
   tions. Les temps de récupérations sont réduits lorsque le joueur passe un tour en combat
   ou en se déplacant sur la map(utilisation de tous les points de déplacements).  
    Lors d'une victoire, votre personnage obtiendra de l'expérience et de l'argent. Mais il
   pourra aussi récuperer un coffre dans lequel il y aura diffèrents objets(Prenez soin de
   tout prendre, vous pourrez devenir RICHE...). Mais parmi ses objets, il y aura des
   clés de coffre qui vous permettront d'ouvrir les autres coffres de la salle.

   Lorsque vous vous trouvez à proximité d'un coffre il vous sera proposé d'utiliser la
   commande /ouvrir. Le jeu vous demandera alors si vous souhaitez récupérer tout les objets
   présents dans le coffre ou juste quelques-uns.

   Il n'y a pas que les combats, vous pouvez aussi parler à des marchands qui vous propose-
   rons d'acheter des objets mais aussi de revendre les objets que vous avez obtenu. Pour
   parler à un PNJ, mettez-vous à coté de lui et écrivez la commande /parler. Mais on vous
   proposera aussi de l'agresser(CELA EST A VOTRE RISQUE ET PERIL...).  
   Les intendants vous vendront des cartes pour pouvoir vous téléporter vers la carte de votre
   choix.

   Votre personnage aura un inventaire illimité donc vous pourrez prendre tout ce que vous
   voulez( psiiit, revendez les objets inutiles pour gagner de l'argent, je vous en ai parlé
   plutôt). Pour pouvoir accéder à votre inventaire, ecrivez la commande /inventaire. Depuis
   votre inventaire, vous pourrez équiper ou retirer des équipements.

   Tant que vous ne mourrez pas, vous pourrez continuer votre aventure et la sauvegarder si
   vous voulez faire une pause (/save) pour reprendre votre aventure plus tard en chargeant la
   partie. Mais si vous avez le malheur de mourrir ( ps : n'oubliez pas d'utiliser vos potions)
   votre personnage sera définitivement mort et votre partie sauvegardée sera supprimée. Il n'y
   a pas de retour en arrière possible !

   Manuel technique :

   La conception de notre jeu a été très interessante. Ce projet fût notre premier projet en
   et surtout notre premier projet en groupe. Nous avons donc tout de suite pensé à comment
   répartir les tâches. Pour cela, nous avons écrit tout ce que nous devions réaliser pour mener
   ce projet.

   Premièrement, nous avons ciblé les principaux éléments du jeu qui sont la carte, le joueur en
   lui-même et le déplacement qui sont pour nous très importants pour la conception.
   Pour la carte, nous avons une classe mère qui est la classe map. Cette classe va permettre de
   créer des sous-classes de différents types de map (ex : prison, chance, ect).  
    Pour le joueur, nous avons une classe entity qui va contenir des caractèristiques du joueur ou
   de monstre et de PNJ. De cette classe, nous pouvons créer notre classe joueur qui va contenir
   les différentes attaques du joueur et toutes ses caractéristiques.  
    Pour le déplacement, nous avons fait une classe move qui va nous servir à déplacer le joueur et
   tout les autres PNJ. On va regarder qui fait appel à la fonction, si les PNJ font appel à la
   fonction, elle va s'éxécuter normalement. Mais si c'est le joueur qui a besoin de se déplacer,
   nous avons besoin de notre classe qui s'occupe de la gestion du clavier pour pouvoir récupérer les
   infos qu'a tapé le joueur sur son clavier. Dans notre fonction move, nous nous occupons aussi de
   la détection des collisions et de la mise a jour de la position des entités.

   Lorsque nous avons fini ses 3 premières tâches, nous avons commencé le système de combat. Pour
   le combat nous avions besoin de créer des compétences pour le joueur mais aussi pour les PNJ.
   Puis nous devions mettre en place des stratégies de jeu pour tout les PNJ. Le combat se déroule
   en tour par tour, et le joueur devra écrire ce qu'il veut faire donc nous refaisons appel à notre
   Keyboard pour collecter ses informations.  
   En parallèle de cela, nous avons créer la classe item et ses sous-classes(potion, clé), et aussi
   la classe equipement avec toutes sous-classes. Le package inventaire va contenir les classes
   armorstorage qui va nous permettre d'équiper ou de retirer notre équipement et la classe stock qui
   va contenir les clés et les potions mais aussi les équipements non portés.  
   La classe coffre va permettre de d'initialiser chacun des coffres sur la map et de les remplir
   aléatoirement mais aussi de vérifier si le joueur a bien la clé pour ouvrir le coffre.

   Pour terminer notre projet, nous avons mis en place toutes les interfaces contenues dans le
   package ui, elles vont permettre de guider le joueur à travers le jeu. Nous avons aussi fait le
   système de ssauvegarde, qui va sauvegarder des objets dans un fichier et pouvoir lire ce fichier
   lors du chargement de la partie.

   Dans le package gestion se trouve MainSystem, cette classe va permettre d'exécuter tout le jeu.

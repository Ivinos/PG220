# PG220

Projet de programmation orientée objet de seconde année en Télécommunications à l'ENSEIRB-MATMECA.

Le projet contient deux dossiers : versionBase et versionBonus
Dans le premier, les fonctionnalités de base ont été implémentées et le programme passe tous les scenarios de tests avec succès.
Dans le second dossier, nous avons essayé d'ajouter un maximum de fonctionnalites bonus pour rendre le jeu plus agréable à jouer et plus personnalisable. Cette version ne peut pas passer les tests car l'interface terminal y est différente.


# Fonctionnalités supplémentaires

1) Implémentation d'une interface Menu avec options
Plusieurs rubriques sont proposées à l'utilisateur lors du lancement du jeu. Pour accéder à l'une de ces rubriques, il suffit de taper le numéro correspondant à celle-ci. Voici plus en détail, la description des rubriques :
- 1 - Jouer (pour lancer le jeu)
- 2 - Règles (pour afficher les règles du jeu)
- 3 - Paramètres (pour modifier certains paramètres du jeu)
- 4 - Informations (pour afficher les règles du jeu)
- 5 - Statistiques (pour afficher les simulations réalisées entre les différents types de joueurs)

Dans la rubrique "Paramètres", un message de confirmation [Oui/Non] de modification des paramètres est demandé. Dans le cas d'une validation, l'utilisateur peut
choisir de modifier :

- A - le nombre de joueurs (2 joueurs min et 6 joueurs max car nous avons à disposition     uniquement 6 couleurs mais le code a été construit de manière à ce que si davantage de couleurs sont disponibles, alors le jeu pourra fonctionner avec +6 joueurs)
- B - la taille de la grille (nombre de lignes > 2, nombre de colonnes > 4, et nombre de lignes x nombres de colonnes >= 8)
- C - le nombre de manches (3 manches min)
- D - le nombre de jetons à aligner pour remporter 1 manche (4 jetons min avec comme condition : nombre de jetons <= nombre de lignes et de colonnes)
- E - les symboles des joueurs (sous réserve que le nouveau symbole ne soit utilisé par aucun autre joueur (quoiqu'il en soit, les couleurs des joueurs sont uniques donc ce n'est pas bien grave))
- F - tous les paramètres mentionnés précédemment (modifications possibles d'un seul coup !)
Pour accéder à l'une de ces catégories, il suffit de taper la lettre correspondant à celle-ci.

Par ailleurs, pour revenir au menu principal, il suffira à l'utilisateur de taper la commande 'menu' (un message s'affiche lorsque c'est le cas).
Dans le cas d'une modification d'un/des paramètre(s), un message en vert s'affiche pour confirmer la modification du/des paramètre(s).

2) Création d'une commande 'parametres'
Cette commande (utilisable uniquement pendant le jeu) permet de visualiser l'état de la partie (victoire/manches/score/paramètres de la grille/paramètres des joueurs). Il suffit à l'utilisateur de taper la commande 'parametres' dans le terminal.

3) - 3 niveaux d'IA:
- Random (il faut juste rentrer ia 'nom'): joue toujours aléatoirement, selon une loi uniforme (toutes les colonnes ont la même probabilité d'être jouées). Cette IA est la plus basique, et est donc la moins bonne.
- Monkey (il faut rentrer ia:monkey 'nom'): cette IA a compris les règles du jeu, si elle voit trois jetons alignés de sa couleur ou de la couleur de son adversaire, elle mettra le quatrième jeton si le coup est jouable (pour gagner ou bloquer l'adversaire). Si elle ne trouve pas d'alignement de trois jetons, elle joue comme l'IA random, de manière uniforme
- Pro (il faut rentrer ia:pro 'nom'): comme monkey, elle peut détecter les alignements de trois jetons et jouer le quatrième si possible. Cependant, si elle ne trouve pas d'alignement de trois jetons, elle jouera aléatoirement selon une loi gaussienne centrée au milieu du tableau, car les coups au milieu du tableau ont plus de chances de faire des alignements que les coups sur les côtés. Cette simple amélioration permet à l'IA Pro d'être meilleure que l'IA monkey comme le montrent les statistiques:

  Random vs Monkey: 81% pour Monkey -
  Random vs Pro:    90% pour Pro - 
  Monkey vs Pro:    66% pour Pro

 Par ailleurs, c'est très simple de rajouter une Ia, il suffit de faire une classe de la même forme que les autres Ia et de rajouter deux lignes dans Game.java. Ces deux lignes doivent malheureusement être ajoutés car on ne peut pas rendre cela dynamique puisqu'il faut instancier des classes dont on ne connaitrait pas le nom.


# Points faibles

1) La gestion d'erreurs et d'exceptions

Nous pensons que notre code n'est pas optimal pour gérer les erreurs et les exceptions mais nous ne savons pas vraiment comment l'améliorer. Nous pensons que nous aurions dû approfondir la gestion des erreurs mais par manque de temps cela n'a pas été fait (néanmoins, toutes les possibles erreurs sont traitées).

2) La classe WriteInLog

Cette classe n'est pas cachée des autres classes et ses méthodes sont publiques, cela veut dire que tout le monde peut écrire dans le fichier de log.txt. En effet, un joueur de type Human doit écrire dans le log.txt donc par etension, les Ia peuvent (mais ne le font pas dans notre code) également écrire. Nous aurions dû changer la structure du code pour que ce soit uniquement la classe Game qui puisse écrire dans le fichier de log mais nous nous sommes rendus compte de ce problème trop tard.

3) L'utilisation de l'outil 'scanner'

Pour lire les inputs utilisateur, nous passons par la classe Scanner. Nous n'avons pas très bien compris si après avoir ouvert un scanner il fallait le fermer (comme les descripteurs de fichier en c par exemple), dans le doute, nous n'avons rien fait.


# Pistes d'amélioration

- Optimiser la recherche de victoire via des algorithmes plus efficaces (c'est à dire faisant moins de calcul). Nous aurions pu optimiser nos algorithmes de recherche de victoire mais cela prenait du temps et comme le projet était plutôt orienté programmation objet, nous n'avons pas voulu passer autant de temps sur de l'arithmétique.
- Le code des Ia n'est pas spécialement dynamique si jamais le nombre de joueur ou le nombre de jetons à aligner pour gagner change. Cependant, le code ne plante pas pour autant et dans certains cas de figure les Ia se montrent quand même efficaces (en effet, vu qu'elles cherchent à aligner 4 jetons, cela va les aider à en aligner 5 par exemple). Si nous prenons 5 jetons, l'Ia pro gagne quand même 100 à 0 contre l'Ia random.
- Une quatrième IA Champion était prévue, elle devait reprendre les capacités de la précédente, et en plus générer des alignements de 3 jetons à partir de 2 jetons. Nous n'avons cependant pas eu le temps de l'implémenter. De manière générale, nous aurions pu optimiser les autres Ia et essayer de faire la meilleure Ia possible mais cela s'éloignait un peu du sujet de base aussi donc nous n'avons pas consacré tout notre temps là dessus, mais plutôt à la structure du projet.
- Rajouter un fichier pour les paramètres de base (nombre de jetons/joueurs, taille de la grille ect). Le programme aurait pu lire un fichier .txt présent dans le même dossier pour connaitre les paramètres de base souhaité par le joueur.
- Marquer les égalités dans le log.txt quand on compte le score.
- Ajouter une interface graphique.


# Conclusion
Nous sommes plutôt satisfaits de ce projet, nous avons réussi à implémenter toutes les tâches obligatoires. Des tâches facultatives ont été ajoutées également de manière à rendre l'interface du jeu plus agréable pour le joueur.


COLLY Evan, BREJON Louis & LEPAJOLEC Théo

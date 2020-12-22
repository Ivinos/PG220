# PG220

Expliquer utilisation, les deux dossiers différents ect (notamment dire que le code dans versionBase peut être moins bien mais changé depuis)

- 3 niveaux d'IA:
  -> Random (il faut juste rentrer ia 'nom'): joue toujours aléatoirement, selon une loi uniforme (toutes les colonnes ont la même probabilité d'être jouées). Cette IA est la plus basique, et est donc la moins bonne.
  -> Monkey (il faut rentrer ia:monkey 'nom'): cette IA a compris les règles du jeu, si elle voit trois jetons alignés de sa couleur ou de la couleur de son adversaire, elle mettra le quatrième jeton si le coup est jouable (pour gagner ou bloquer l'adversaire). Si elle ne trouve pas d'alignement de trois jetons, elle joue comme l'IA random, de manière uniforme
  -> Pro (il faut rentrer ia:pro 'nom'): comme monkey, elle peut détecter les alignements de trois jetons et jouer le quatrième si possible. Cependant, si elle ne trouve pas d'alignement de trois jetons, elle jouera aléatoirement selon une loi gaussienne centrée au milieu du tableau, car les coups au milieu du tableau ont plus de chances de faire des alignements que les coups sur les côtés. Cette simple amélioration permet à l'IA Pro d'être meilleure que l'IA monkey comme le montrent les statistiques:

  Random vs Monkey: 82% pour Monkey
  Monkey vs Random: 88% pour Monkey
  Random vs Pro:    89% pour Pro
  Pro vs Random:    94% pour Pro
  Monkey vs Pro:    62% pour Pro
  Pro vs Monkey:    69% pour Pro

# Fonctionnalités supplémentaires

1) Implémentation d'une interface Menu avec options
Plusieurs rubriques sont proposées à l'utilisateur lors du lancement du jeu. Pour accéder à l'une de ces rubriques, il suffit de taper le numéro correspondant à celle-ci. Voici plus en détail, la description des rubriques : 
      1 - Jouer (pour lancer le jeu)
      2 - Règles (pour afficher les règles du jeu)
      3 - Paramètres (pour modifier certains paramètres du jeu)
      4 - Informations (pour afficher les règles du jeu)
      5 - Statistiques (pour afficher les simulations réalisées entre les différents types de joueurs)
Dans la rubrique "Paramètres", un message de confirmation [Oui/Non] de modification des paramètres est demandé. Dans le cas d'une validation, l'utilisateur peut choisir de modifier :
      A - le nombre de joueurs (2 joueurs min et 6 joueurs max car nous avons à disposition     uniquement 6 couleurs mais le code a été construit de manière à ce que si davantage de couleurs sont disponibles, alors le jeu pourra fonctionner avec +6 joueurs)
      B - la taille de la grille (nombre de lignes > 2, nombre de colonnes > 4, et nombre de lignes x nombres de colonnes >= 8)
      C - le nombre de manches (3 manches min)
      D - le nombre de jetons à aligner pour remporter 1 manche (4 jetons min avec comme condition : nombre de jetons <= nombre de lignes et de colonnes)
      E - les symboles des joueurs (sous réserve que le nouveau symbole ne soit utilisé par aucun autre joueur (quoiqu'il en soit, les couleurs des joueurs sont uniques donc ce n'est pas bien grave))
      F - tous les paramètres mentionnés précédemment (modification possible d'un seul coup !)
Pour accéder à l'une de ces catégories, il suffit de taper la lettre correspondant à celle-ci.

Par ailleurs, pour revenir au menu principal, il suffira à l'utilisateur de taper la commande 'menu' (un message s'affiche lorsque c'est le cas).
Dans le cas d'une modification d'un/des paramètre(s), un message en vert s'affiche pour confirmer la modification du/des paramètre(s).

2) Création d'une commande 'parametres'
Cette commande (utilisable uniquement pendant le jeu) permet de visualiser l'état de la partie (victoire/manches/score/paramètres de la grille/paramètres des joueurs). Il suffit à l'utilisateur de taper la commande 'parametres' dans le terminal. 




- Je laisse Louis tout remplir comme il faut
- Avec des petits tirets stp :)

- Pour les joueurs supplémentaires bien explqiué qu'on se limite à 6 à cause des symboles et des couleurs du terminal, mais que le code en lui même (l'algo) pourrait marcher peut importe le nombre de joueur.
- Tokens

- Insister sur le fait que Ia très modulable, juste deux lignes à rajouter dans Game.java l 40 environ (pas le choix)

# Pistes d'amélioration

- Optimiser la recherche de victoire
- Ia (les dynamiser parce que c'est pas vraiment le cas, plus efficaces, plus rapides, plus fortes) + 0/100 contre ia random donc quand même pas degueu
- Une quatrième IA Champion était prévue, elle devait reprendre les capacités de la précédente, et en plus générer des alignements de 3 jetons à partir de 2 jetons.
- Fichier pour les paramètres de base
- Chaque joueur peut choisir son jeton et sa couleur
- marquer les égalités dans le log.txt quand on compte le score
- Gestion des erreurs
- Fermer le scanner mais on a pas tout compris
- Optimiser victory check en faisant full maths





-> roundnumber = nombre de rounds fini (ie qq1 a aggné)

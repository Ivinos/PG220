# PG220

Expliquer utilisation, les deux dossiers différents ect (notamment dire que le code dans versionBase peut être moins bien mais changé depuis)

- 3 niveaux d'IA:
  -> Random (il faut juste rentrer ia 'nom'): joue toujours aléatoirement, selon une loi uniforme (toutes les colonnes ont la même probabilité d'être jouées). Cette IA est la plus basique, et est donc la moins bonne
  -> Monkey (il faut rentrer ia:monkey 'nom'): cette IA a compris les règles du jeu, si elle voit trois jetons alignés de sa couleur ou de la couleur de son adversaire, elle mettra le quatrième jeton si le coup est jouable (pour gagner ou bloquer l'adversaire). Si elle ne trouve pas d'alignement de trois jetons, elle joue comme l'IA random, de manière uniforme
  -> Pro (il faut rentrer ia:pro 'nom'): comme monkey, elle peut détecter les alignements de trois jetons et jouer le quatrième si possible. Cependant, si elle ne trouve pas d'alignement de trois jetons, elle jouera aléatoirement selon une loi gaussienne centrée au milieu du tableau, car les coups au milieu du tableau ont plus de chances de faire des alignements que les coups sur les côtés. Cette simple amélioration permet à l'IA Pro d'être meilleure que l'IA monkey comme le montrent les stats:
  Random vs Monkey: 82% pour Monkey
  Monkey vs Random: 88% pour Monkey
  Random vs Pro:    89% pour Pro
  Pro vs Random:    94% pour Pro
  Monkey vs Pro:    62% pour Pro
  Pro vs Monkey:    69% pour Pro

# Fonctionnalités supplémentaires

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

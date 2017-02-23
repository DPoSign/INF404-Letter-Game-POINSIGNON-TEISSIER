# TP Architecture Logicielle / Inf4043 - 2017 - Jeux de lettres

## Compilation
Pour compiler et lancer le projet avec Maven, utilisez les commandes ci dessous en étant dans le dossier "LetterGame"
```bash
mvn package
java -jar target/LetterGame-1.0.jar
```
## Règles du jeux, objectif du jeux et modalités de rendu. 

```
https://github.com/MLabusquiere/TP_4A_2017_Letter_Game
```

## Fonctionnalités développées (Ajouter assets et capture d'écran)
- Une première étape consiste à pouvoir jouer à plusieurs autour d'un même écran.

- Une interface en ligne de commande offre la possibilité d'améliorer un mot ou de le voler. (not done yet)

- Une intelligence artificielle permet de jouer contre l'ordinateur. L'intelligence artificielle privilégie les mots les plus long du dictionnaire. Elle favorise dans l'ordre le vol de mot, la création de mot à partir de bol et l'amélioration de ces propres mots. Le choix a été fait de la rendre difficile à battre, mais il est possible de lui conférer une attitude plus humaine en lui donnant un pourcentage d'échec (au lieu de trouver systématiquement des mots) ou de privilégier des mots plus court. (Not done yet)

## Description de l'architecture
Le projet contient 8 packages distints qui assurent le fonctionnement de l'application.

- Main initialise et lance le jeu
- Bag gère les interactions liées au sac comme prendre une lettre du sac. Les tirages sont associés avec des fréquences adaptées à la langue de Molière.
- Bowl à la gestion des lettres du pot commun.
- Dictionary vérifie si un mot existe dans le dictionnaire, enlève les accents des chaînes de caractère.
- Game permet les interactions des joueurs sur le modèle hot seat où les joueurs intéragissent à tour de rôle.
- Move contient des interactions spécifiques comme le vol de mot, la complétion de mot, des affichages graphiques.
- Player comprends les éléments liés aux joueurs (ordre de passage, comparaison de score) et également l'IA.
- Util contient des éléments utilisés dans d'autres packages comme la comparaison de la longueur des mots (et par extension permet la comparaison par le score).

## Illustration des principes SOLID

###Factory Method
###Singleton

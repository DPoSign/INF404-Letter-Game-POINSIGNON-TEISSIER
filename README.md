# TP Architecture Logicielle / Inf4043 - 2017 - Jeux de lettres

## Compilation & Démarrage
Pour compiler et lancer le projet avec Maven, utilisez les commandes ci dessous en étant dans le dossier "LetterGame"
```bash
mvn package
java -jar target/LetterGame-1.0.jar
```
Pour jouer, entrer un nom de joueur ou BOT et appuyez sur le touche ENTRÉE

## Règles du jeux, objectif du jeux et modalités de rendu. 

```
https://github.com/MLabusquiere/TP_4A_2017_Letter_Game
```

## Fonctionnalités développées (Ajouter assets et capture d'écran)
- Une première étape consiste à pouvoir jouer à plusieurs autour d'un même écran.

![alt tag](https://github.com/DPoSign/INF4043-Letter-Game-POINSIGNON-TEISSIER/blob/master/assets/completing.PNG)

- Une interface en ligne de commande offre la possibilité d'améliorer un mot ou de le voler.

![alt tag](https://github.com/DPoSign/INF4043-Letter-Game-POINSIGNON-TEISSIER/blob/master/assets/steal.PNG)

- Une intelligence artificielle permet de jouer contre l'ordinateur. Elle privilégie les mots les plus long du dictionnaire et favorise dans l'ordre le vol, la création à partir du pot commun et l'amélioration de ces propres mots. Le choix a été fait de la rendre difficile à battre, mais il est possible de lui conférer une attitude plus humaine en lui donnant un pourcentage d'échec (au lieu de trouver systématiquement des mots) ou de privilégier des mots plus court. 

![alt tag](https://github.com/DPoSign/INF4043-Letter-Game-POINSIGNON-TEISSIER/blob/master/assets/bot.PNG)

## Description de l'architecture
Le projet contient 8 packages distints qui assurent le fonctionnement de l'application.

- Main initialise et lance le jeu.
- Bag gère les interactions liées au sac comme prendre une lettre du sac. Les tirages sont associés avec des fréquences adaptées à la langue de Molière.
- Bowl à la gestion des lettres du pot commun.
- Dictionary vérifie si un mot existe dans le dictionnaire, enlève les accents des chaînes de caractère.
- Game permet les interactions des joueurs sur le modèle hot seat où les joueurs intéragissent à tour de rôle.
- Move contient des interactions spécifiques comme le vol de mot, la complétion de mot, des affichages graphiques.
- Player comprends les éléments liés aux joueurs (ordre de passage, comparaison de score) et également l'IA.
- Util contient des éléments utilisés dans d'autres packages comme la comparaison de la longueur des mots (et par extension permet la comparaison par le score).

## Illustration des principes SOLID/Design Pattern

###Singleton
Pour assurer qu'il n'existe qu'une seule instance de la classe et de fournir un moyen d'obtenir cette instance unique nous avons mis en oeuvre le pattern Singleton. Nous avons créer un attribut statique qui va permettre de stocker l’unique instance de la classe, nous avons ensuite testé cet attribut. 
```java
public static final Bowl getInstance() {
    if (instance == null)
      instance = new Bowl();
    return instance;
  }
```

###Factory Method
La classe correspondant aux AMove va être fortement couplée à tous les types de mouvement (c'est un problème si l'on ajoute ou supprime des mouvements). Le pattern à été utilisé pour découpler les différents type de mouvement à instancier (que l'on connaisse leur type par avance ou pas). Enfin, nous avons déclaré la méthode  comme « final » afin que ses sous classes ne puissent pas la redéfinir.
```java
public static final AMove factory(String type) {
  try {
      Class<?> moveClass = Class.forName("fr.esiea.poinsignon.teissier.move." + type);
      
      if (moveClass != AMove.class && AMove.class.isAssignableFrom(moveClass))
        return (AMove)moveClass.newInstance();
    }
    catch (Exception e) {
    }
    
    throw new RuntimeException(type + " is not an available move");
  }
```

###Flyweight
L'instanciation de toute la panoplie des lettres ayant des accents est très gourmande en mémoire. Au lieu de manipuler tous ces objets, nous avons utiliser le pattern Flyweight.  
```java
String original = "aáeéiíoóöőuúüűAÁEÉIÍOÓÖŐUÚÜŰ";
```java

###SOLID- Single Responsibility Principle
Dans le cadre d'une démarche SOLID, nous avons fais en sorte que certaine classe comme "ADctionnary" soit à responsabilité unique, cette classe ne fait qu'une unique chose : gérer la relation des mots inscrit par rapport au dictionnaire. On gagne en souplesse et l'ensemble est moins sensible au changement.

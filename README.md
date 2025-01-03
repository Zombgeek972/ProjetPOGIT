# ProjetPO
Le Boulicaut Gaël
Oheix Mewen

Nous avons réalisés toutes les étapes de la partie 9 du projet, sans toucher aux bonus.

Pour lancer le projet vous avez juste à lancer le fichier app.java puis vous amuser.

le fichier "game.g" est lu par une instance de la classe "Progression.java" pour créer la structure du jeu et met à jour l'affichage du level et des vagues.

Pour l'affichage et le calcul des pieces et de la vie, c'est le fichier "joueur.java" qui gère ca.

le fichier "carte.java" calcul et affiche la carte, calcul le chemin pour permettre aux ennemis d'avancer.

La classe Ennemis permet aux énnemis de progresser sur la carte, chaque appel de cette fonction se fait dans la methode update du fichier "game.java"

Le magasin comporte toutes les tours disponibles, cliquez sur une tour, puis sur une case constructible. Votre argent sera ensuite déduit du coût de la tour.
Aucune utilisation de l'IA a été utilisé pour le projet.

Les combattants jaune sont de type neutre,
les blanc sont de type air,
les orange de type feu,
les marrons de type terre,
les bleux de type eau.

J'ai changé certaines valeurs des énnemis et des tours car c'était impossible de faire une partie entière avec les valeurs données dans le PDF.
Je n'arrive pas à passer le LVL : 3, WVE : 3/4, les Earth Caster sont trop fort.

Pour savoir si vous avez gagné ou perdu, le jeu s'arrête instantanément et il y a un message dans la console.
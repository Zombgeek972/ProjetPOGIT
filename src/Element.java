/**
 * cette énumeration permet de donner aux tours et aux ennemis un type d'attaque.
 */
public enum Element {
    /**
     * l'element neutre est vulnérable à rien et n'est résistant à rien.
     */
    Neutre,

    /**
     * le feu est vulnérable à l'eau et est résistant a la terre.
     */
    Feu,

    /**
     * la terre est vulnérable au feu et résistante à l'air.
     */
    Terre,

    /**
     * l'air est vulnérable à la terre et résistant à l'eau.
     */
    Air,

    /**
     * l'eau est vulnérable à l'air et résistant au feu.
     */
    Eau
}

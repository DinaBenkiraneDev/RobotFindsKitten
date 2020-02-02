/**
 * Classe qui gere les interactions entre le robot et la porte selon les cles disponibles.
 *
 * @auteur Alexandre Dufour et Dina Benkirane
 */
public final class Porte extends Case {

    /**
     * Methode constructeur de la classe Porte, donne la representation par defaut
     */
    public Porte() {
        this.representation = '!';
    }

    /**
     * @param robot Le robot qui interagirait avec la case
     * @return si l'interaction est possible (si il y a une cle disponible alors la cle est utilise)
     */
    public boolean interactionPossible(Robot robot) {
        if (robot.getNbrClef() > 0)
            return true;
        else
            return false;
    }

    /**
     * Permet d'enlever une cle de l'inventaire du robot
     *
     * @param robot Robot du jeu, possedant possiblement les clefs.
     */
    public void interagir(Robot robot) {
        robot.utiliserClef();
    }
}


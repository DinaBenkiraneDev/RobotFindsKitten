/**
 * Class permettant de generer les murs du jeu, soit les limites de deplacement du robot.
 *
 * @auteur Alexandre Dufour et Dina Benkirane
 */
public final class Mur extends Case {
    /**
     * Constructeur de la classe, affecte la representation (toujours un '%')
     */
    public Mur() {
        this.representation = '%';
    }

    /**
     * @param robot Le robot qui interagirait avec la case mur.
     * @return boolean Interaction impossible, le mur empeche le robot de se deplacer.
     */
    public boolean interactionPossible(Robot robot) {
        return false;
    }

    /**
     * Aucune interaction donc la methode est vide
     */
    public void interagir(Robot robot) {
    }

}

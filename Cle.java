/**
 * Class permettant de generer les clefs. On en retourve une par piece, le robot doit en avoir pour ouvrir les portes.
 *
 * @auteur Alexandre Dufour et Dina Benkirane
 */
public final class Cle extends Case {
    /**
     * Constructeur de la class clef, affecte une representation non aleatoire, un apostrophe.
     */
    public Cle() {
        this.representation = (char) 39;
    }

    /**
     * @param robot Le robot qui interagirait avec la case
     * @return true, toujours possible de ramasser les cles
     */
    public boolean interactionPossible(Robot robot) {
        return true;
    }

    /**
     * Appel la methode clefTrouvee qui incremente le nombre de cle du robot.
     *
     * @param robot Le robot qui interagirait avec la case
     */
    public void interagir(Robot robot) {
        robot.clefTrouvee();
    }
}

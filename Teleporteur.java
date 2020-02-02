/**
 * La classe Teleporteur, donne les interactions possible avec le robot
 *
 * @auteur Alexandre Dufour et Dina Benkirane
 */
public final class Teleporteur extends Case {
    private Point positionT;

    /**
     * Constructeur classe
     *
     * @param position Position du teleporteur initialiser aleatoirement dans la classe grille
     */
    public Teleporteur(Point position) {
        this.representation = 'T';
        this.positionT = position;
    }

    //Methode get
    public Point getPositionT() {
        return this.positionT;
    }

    /**
     * @param robot Le robot qui interagirait avec la case
     * @return: Interaction toujours possible
     */
    public boolean interactionPossible(Robot robot) {
        return true;
    }

    /**
     * Permet de changer l'inventaire du robot pour ajouter le teleporteur
     *
     * @param robot Le robot qui interagirait avec l'item
     */
    public void interagir(Robot robot) {
        robot.teleporteurTrouve();
    }

}
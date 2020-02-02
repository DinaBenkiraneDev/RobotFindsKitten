/**
 * Class permettant de generer le kitten, soit l'objet que le joueur doit retrouver
 *
 * @auteur Alexandre Dufour et Dina Benkirane
 */
public final class Kitten extends Case {
    private String nom = "";
    private Point position;

    /**
     * Le constructeur de la classe Kitten
     *
     * @param nom:     le nom du kitten
     * @param position Position du kitten
     */
    public Kitten(String nom, Point position) {
        this.nom = nom;
        this.representation = super.getRandomSymbole();
        this.position = new Point(position.getX(), position.getY());
    }

    /**
     * @param robot Le robot qui interagirait avec la case kitten
     * @return interaction possible
     */
    public boolean interactionPossible(Robot robot) {
        return true;
    }

    /**
     * Interaction entre le robot et le kitten fais finir la partie et retourne ce message dans la boîte de commande
     *
     * @param robot
     */
    public void interagir(Robot robot) {
        System.out.println("You found kitten! Way to go, robot.\n" + this.nom + " <3 " + robot.getNom());
        robot.trouverKitten();
    }

}


/**
 * Class permettant de generer le robot, l'objet controler par le joueur,peut gerer les deplacements, l'inventaire du robot et si kitten a ete trouve
 *
 * @auteur Alexandre Dufour et Dina Benkirane
 */

public final class Robot {
    //Attributs
    private String nom;
    private Point position;
    private int nbrClef;
    private boolean teleporteur;
    private char representation = '#';
    private boolean kittenTrouve;

    /**
     * Constructeur de la classe robot, elle permet d'initialiser l'inventaire du robot et la position initiale.
     *
     * @param nom:               Nom du Robot
     * @param position:Postition aleatoire, initialise dans la classe grille
     */
    public Robot(String nom, Point position) {
        this.nom = nom;
        this.position = new Point(position.getX(), position.getY());
        nbrClef = 0;
        teleporteur = false;
        kittenTrouve = false;
    }

    //Methodes getter/setter
    public String getNom() {
        return this.nom;
    }

    public char getRepresentation() {
        return this.representation;
    }

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(Point newPosition) {
        position = new Point(newPosition.getX(), newPosition.getY());
    }


    //Methodes clef
    public int getNbrClef() {
        return this.nbrClef;
    }

    public void clefTrouvee() {
        this.nbrClef++;
    }

    public void utiliserClef() {
        this.nbrClef--;
    }

    //Methodes teleporteur
    public void teleporteurTrouve() {
        this.teleporteur = true;
    }

    public boolean getTeleporteur() {
        return this.teleporteur;
    }

    public void teleporteurUtilise() {
        this.teleporteur = false;
    }

    //Methodes Kitten
    public boolean getKitten() {
        return this.kittenTrouve;
    }

    public void trouverKitten() {
        this.kittenTrouve = true;
    }

    //Methodes pour le deplacement
    public void deplacementHaut() {
        position = new Point(this.position.getX() - 1, this.position.getY());
    }

    public void deplacementBas() {
        position = new Point(this.position.getX() + 1, this.position.getY());
    }

    public void deplacementGauche() {
        position = new Point(this.position.getX(), this.position.getY() - 1);
    }

    public void deplacementDroite() {
        position = new Point(this.position.getX(), this.position.getY() + 1);
    }

}
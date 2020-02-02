import java.util.Random;

/**
 * Class permettant de generer la grille du jeu et ses interactions
 *
 * @auteur Alexandre Dufour et Dina Benkirane
 */

public final class Grille {

    //Attributs
    private Case[][] grille;
    private int nbrPieceX;
    private int nbrPiecesY;
    private int largeurPiece;
    private int hauteurPiece;

    /**
     * Cette fonction initialise la grille en creant les pieces, les portes, les murs
     * les cles et les items (le teleporteur, les NonKittenItems et le Kitten)
     * Il y a "nbrNonKitten" NonKittenItems au total sur tout le jeu
     *
     * @param nbrPiecesX   nombre de piece en hauteur
     * @param nbrPiecesY   nombre de piece en largeur
     * @param largeurPiece
     * @param hauteurPiece
     * @param nbrNonKitten nombre de non kitten item total dans tout le jeu
     * @param nomKitten    nom du kitten choisit par l'utilisateur.
     */
    public Grille(int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece, int nbrNonKitten, String nomKitten) {

        //Definis les attributs de la classe
        grille = new Case[nbrPiecesX * hauteurPiece + nbrPiecesX + 1][(nbrPiecesY * largeurPiece) + (nbrPiecesY) + 1];
        this.nbrPiecesY = nbrPiecesY;
        this.nbrPieceX = nbrPiecesX;
        this.largeurPiece = largeurPiece;
        this.hauteurPiece = hauteurPiece;

        //Initialisation des murs
        for (int k = 0; k < grille.length; k += hauteurPiece + 1) {
            for (int i = 0; i < grille.length; i++) {
                for (int j = 0; j <= grille[i].length; j += largeurPiece + 1) {
                    this.grille[i][j] = new Mur();
                }
            }
            //Ligne de separation horizontale des pieces.
            for (int i = 0; i < grille[k].length; i++) {
                this.grille[k][i] = new Mur();
            }
        }

        //Initialisation des cases vides.
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                if (this.grille[i][j] instanceof Mur) {
                } else
                    this.grille[i][j] = new NonKitten("vide");
            }
        }

        //Ajout du teleporteur dans la grille (prend une case aleatoire)
        Point teleporteur = randomEmptyCell();
        this.grille[teleporteur.getX()][teleporteur.getY()] = new Teleporteur(teleporteur);

        //Ajout du  kitten dans la grille (prend une case aleatoire)
        Point kitten = randomEmptyCell();
        this.grille[kitten.getX()][kitten.getY()] = new Kitten(nomKitten, kitten);

        //Ajout des portes dans la grille
        //Porte horizontale
        for (int i = 1; i < nbrPiecesX; i++) {
            int demiLargeurPiece = largeurPiece / 2;
            for (int j = 1; j <= nbrPiecesY; j++) {
                grille[hauteurPiece * i + i][demiLargeurPiece + j] = new Porte();
                demiLargeurPiece += largeurPiece;
            }
        }
        //Porte verticale
        int demiHauteurPiece = hauteurPiece / 2 + 1;
        for (int i = 0; i < nbrPiecesX; i++) {
            for (int j = 1; j < nbrPiecesY; j++) {
                grille[demiHauteurPiece][largeurPiece * j + j] = new Porte();
            }
            demiHauteurPiece += hauteurPiece + 1;
        }

        //Implementation des clees
        int debutPieceX = 1;
        for (int i = 0; i < nbrPiecesX; i++) {
            //Cree un cle par ligne en premier
            int debutPieceY = 1;
            for (int j = 0; j < nbrPiecesY; j++) {
                Point newCle = randomEmptyCellPiece(debutPieceX + i, debutPieceY + j, debutPieceX + hauteurPiece + i, debutPieceY + largeurPiece + j);
                grille[newCle.getX()][newCle.getY()] = new Cle();
                debutPieceY += largeurPiece;
            }
            //changement de ligne
            debutPieceX += hauteurPiece;
        }

        //Initialisation nonKittenItem
        for (int k = 0; k < nbrNonKitten; k++) {
            Point newNonKitten = randomEmptyCell();
            grille[newNonKitten.getX()][newNonKitten.getY()] = new NonKitten();
        }
    }


    /**
     * Methode qui donne une case vide mais selon des dimensions predefinis pour une piece.
     *
     * @param xDebut Le coin en haut a gauche de la case voulu
     * @param yDebut Le coin en haut a gauche de la case voulu
     * @param xFin   Le coin en bas a gauche de la case voulu
     * @param yFin   le coin en haut a droite de la case voulu
     * @return le point trouve
     */
    public Point randomEmptyCellPiece(int xDebut, int yDebut, int xFin, int yFin) {
        boolean isEmpty = false;
        int[] vide = new int[2];
        Random random = new Random();
        while (!isEmpty) {
            int x = xDebut + random.nextInt(xFin - xDebut);
            int y = yDebut + random.nextInt(yFin - yDebut);
            if (this.grille[x][y].getRepresentation() == ' ') {
                vide[0] = x;
                vide[1] = y;
                isEmpty = true;
            }
        }
        Point empty = new Point(vide[0], vide[1]);
        return empty;
    }

    //Retourne une coordonnee de cellule qui ne contient rien dans toute la grille
    public Point randomEmptyCell() {
        return randomEmptyCellPiece(0, 0, nbrPieceX * hauteurPiece + nbrPieceX, nbrPiecesY * largeurPiece + nbrPiecesY);
    }

    /**
     * Indique si c'est possible pour le Robot robot de marcher sur la cellule de coordonnee (x, y)
     * Le robot ne pourra pas marcher sur les cases contenant des murs, ou sur des portes s'il ne possede aucune clef.
     *
     * @param robot
     * @param x     point coordonnee x
     * @param y     point coordonee y
     * @return Si le deplacement est possible, (regarde s'il y a un mur ou une porte)
     */
    public boolean deplacementPossible(Robot robot, int x, int y) {
        if (grille[x][y] instanceof Mur)
            return false;
        else if (grille[x][y] instanceof Porte && robot.getNbrClef() == 0)
            return false;
        else
            return true;
    }

    /**
     * Lance l'interaction entre le Robot robot et la case de la grille sur laquelle il se trouve
     * L'interaction" peut être l'affichage d'un message (pour les NonKittenItems),
     * l'ouverture d'une Porte, le fait de ramasser une clef ou un teleporteur, ou encore
     * le fait de gagner la partie en trouvant le Kitten.
     * Si dans la case se trouve une porte, une clef ou un teleporteur, et si le robot peut interagir avec, la case
     * est remplace par une case vide.
     *
     * @param robot qui va interagir avec les cellules.
     */
    void interagir(Robot robot) {
        if (grille[robot.getPosition().getX()][robot.getPosition().getY()].interactionPossible(robot)) {
            grille[robot.getPosition().getX()][robot.getPosition().getY()].interagir(robot);
            if ((grille[robot.getPosition().getX()][robot.getPosition().getY()] instanceof Porte)
                    || (grille[robot.getPosition().getX()][robot.getPosition().getY()] instanceof Cle)
                    || (grille[robot.getPosition().getX()][robot.getPosition().getY()] instanceof Teleporteur)) {
                grille[robot.getPosition().getX()][robot.getPosition().getY()] = new NonKitten("vide");
            }
        }
    }

    /**
     * Affiche la grille dans la console a coups de System.out.println(...)
     * Affiche egalement en ligne de commande le nom du robot, le nombre de clef et s'il possede le teleporteur.
     *
     * @param robot qui a une position precise dans la grille a chaque affichage de la grille.
     */
    public void afficher(Robot robot) {
        //Affichage de la grille
        for (int i = 0; i < this.grille.length; i++) {
            for (int j = 0; j < this.grille[i].length; j++) {
                //If qui regarde si le robot se trouve a cette case
                if (grille[robot.getPosition().getX()][robot.getPosition().getY()] == grille[i][j])
                    System.out.print(robot.getRepresentation());
                else
                    System.out.print(this.grille[i][j].getRepresentation());
            }
            System.out.println();
        }
        //Affichage de la ligne de commande, elle affiche le nombre de cle et si le robot a un teleporteur
        if (robot.getTeleporteur()) {
            System.out.println("" + robot.getNom() + " [" + robot.getNbrClef() + "]" + "T>");
        } else
            System.out.println("" + robot.getNom() + " [" + robot.getNbrClef() + "]>");
    }
}

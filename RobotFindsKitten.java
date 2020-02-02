import java.util.Scanner;

/**
 * Class contenant le main() permettant d'executer le jeu.
 *
 * @auteur Alexandre Dufour et Dina Benkirane
 */

//Classe qui contient la methode main du projet, tout est initialiser ici

public final class RobotFindsKitten {

    public static void main(String[] args) {


        Scanner joueur = new Scanner(System.in);
        //variables, qui va être change par le joueur.
        String move = "";
        String nomRobot;
        String nomChatton;

        System.out.println("Quel est le nom de votre robot");
        nomRobot = joueur.nextLine();

        System.out.println("Quel est le nom du chatton");
        nomChatton = joueur.nextLine();

        //Initialisation de la grille et du robot
        Grille jeu = new Grille(2, 4, 11, 5, 30, nomChatton);

        Robot robot = new Robot(nomRobot, jeu.randomEmptyCell());

        System.out.println("Entrez vos deplacements");

        //Boucle qui demande au joueur les actions du robot, gere aussi les interactions jusqu'a trouver le Kitten
        while (robot.getKitten() != true) {
            //Affiche le jeu a chaque commande
            jeu.afficher(robot);

            move = joueur.nextLine();
            //Si move.length==0 alors le programme va seulement afficher la grille a nouveau
            //Si l'utilisateur entre plus qu'un seul char on prendra seulement le premier entree
            if (move.length() > 0)
                move = move.substring(0, 1);

            //Analyse pour chaque mouvement et gere si le deplacement est possible
            switch (move) {

                case "w":
                    if (jeu.deplacementPossible(robot, robot.getPosition().getX() - 1, robot.getPosition().getY()))
                        robot.deplacementHaut();
                    break;


                case "s":
                    if (jeu.deplacementPossible(robot, robot.getPosition().getX() + 1, robot.getPosition().getY()))
                        robot.deplacementBas();
                    break;


                case "a":
                    if (jeu.deplacementPossible(robot, robot.getPosition().getX(), robot.getPosition().getY() - 1))
                        robot.deplacementGauche();
                    break;


                case "d":
                    if (jeu.deplacementPossible(robot, robot.getPosition().getX(), robot.getPosition().getY() + 1))
                        robot.deplacementDroite();
                    break;
                // Case du teleporteur, regarde s'il y a un teleporteur,
                // si oui on teleporte le robot a une position aleatoire
                case "T":
                case "t":
                    if (robot.getTeleporteur()) {
                        Point newPosition = jeu.randomEmptyCell();
                        robot.setPosition(newPosition);
                        robot.teleporteurUtilise();
                    }
                default:
                    System.out.print("Entrez w, s, a ou d pour aller respectivement en haut, en bas, a gauche ou a droite");
                    break;
            }
            jeu.interagir(robot);
        }
    }
}
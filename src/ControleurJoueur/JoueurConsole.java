package ControleurJoueur;

import modele.Mer;
import modele.bateau.Bateau;
import modele.bateau.Direction;
import modele.joueurs.Joueur;
import modele.utilities.Coordonnees;

import java.util.Scanner;

/**
 * Classe permettant que le joueur puisse jouer en console
 */
public class JoueurConsole extends Controleur {
    /**
     * Constructeur du joueur
     * @param joueur Joueur
     */
    public JoueurConsole(Joueur joueur) {
        super(joueur);
    }

    /**
     * Permet le choix d'un tir
     * @return Tableau de coordonnees du tir effectue
     */
    @Override
    public Coordonnees choixTir() {
        int x, y;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Coordonees x ?");
        x = scanner.nextInt();
        System.out.println("Coordonees y ?");
        y = scanner.nextInt();
        return (new Coordonnees(x, y));
    }

    /**
     * Permet le placement des bateaux
     * @param taille Taille du bateau
     * @param mer Instance de Mer qui regroupe tous les elements du jeu
     * @return Instance de bateau place au bonnes coordonnees
     */
    @Override
    public Bateau choixPlacement(int taille, Mer mer) {
        int x, y, z;
        Direction d;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Coordonees x ?");
        x = scanner.nextInt();
        System.out.println("Coordonees y ?");
        y = scanner.nextInt();
        System.out.println("direction?0=droite,1=bas");
        z = scanner.nextInt();
        if (z == 0)
            d = Direction.HORIZONTALE;
        else
            d = Direction.VERTICALE;
        Coordonnees coord = new Coordonnees(x, y);
        return new Bateau(coord, taille, d);
    }

}

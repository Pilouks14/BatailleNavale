package ControleurJoueur;

import modele.Mer;
import modele.Modele;
import modele.bateau.Bateau;
import modele.bateau.Direction;
import modele.joueurs.Joueur;
import modele.utilities.Coordonnees;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe creant un joueur jouant aleatoirement
 */
public class Aleatoire extends Controleur {
    public final static int TAILLE_GRILLE = 10;
    private Random randomGenerator = new Random();
    private ArrayList<Coordonnees> caseATirer = new ArrayList<>();

    /**
     * Constructeur qui initialise le random et caseATirer.
     */
    public Aleatoire(Modele modele, Joueur joueur) {
        super(joueur);
        this.randomGenerator = new Random();
        initCaseATirer();
    }

    /**
     * Remplit le tableau caseATirer de coordonnées possible.
     */
    private void initCaseATirer() {
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                caseATirer.add(new Coordonnees(i, j));
            }
        }
    }

    /**
     * Choisit une coordonnée du tableau case à tirer la retourne et la retire du tableau
     *
     */
    @Override
    public Coordonnees choixTir(){
        int random = randomGenerator.nextInt(caseATirer.size());
        Coordonnees res = caseATirer.get(random);
        caseATirer.remove(res);
        return (res);
    }

    /**
     * Place les bateaux aléatoirement
     * @param taille Taille du bateau
     * @param mer Instance de Mer qui regroupe tous les elements du jeu
     * @return Tableau contenant les cases possibles pour placer les bateaux
     */
    @Override
    public Bateau choixPlacement(int taille, Mer mer) {
        ArrayList<Bateau> possibleRes = new ArrayList<>();
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                Bateau bateau = new Bateau(new Coordonnees(i, j), taille, Direction.HORIZONTALE);
                if (mer.estPlacable(getJoueurControle(), bateau))
                    possibleRes.add(bateau);
                bateau = new Bateau(new Coordonnees(i, j), taille, Direction.VERTICALE);
                if (mer.estPlacable(getJoueurControle(), bateau))
                    possibleRes.add(bateau);
            }
        }
        return possibleRes.get(randomGenerator.nextInt(possibleRes.size()));
    }

}

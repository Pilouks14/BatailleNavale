package ControleurJoueur;

import modele.Mer;
import modele.Modele;
import modele.bateau.Bateau;
import modele.bateau.Direction;
import modele.joueurs.Joueur;
import modele.utilities.Coordonnees;
import vue.GameVue;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe permettant l'affichage sur une fenetre graphique du joueur
 */
public class JoueurSwing extends Controleur implements MouseListener {
    private GameVue gameVue;
    private Modele modele;
    int TAILLE_GRILLE = 10;
    int TAILLE_CASE;
    private Random randomGenerator = new Random();
    private Coordonnees mousePos = new Coordonnees(-1, -1);

    /**
     * Constructeur du joueur
     * @param joueurControle Instance de joueur
     * @param gameVue Instance de gamevue
     * @param modele Instance du modele
     */
    public JoueurSwing(Joueur joueurControle, GameVue gameVue, Modele modele) {
        super(joueurControle);
        this.gameVue = gameVue;
        this.modele = modele;
        TAILLE_CASE = gameVue.getVueGrille2().getTailleCase();
    }

    /**
     * Permet d'attendre entre deux clics
     */
    public void attend() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Effectue la traduction entre l'endroit clique et une case sur la grille
     * @param x Premiere coordonnee
     * @param y Deuxieme coordonnee
     * @return coordonnees contenant la case cible
     */
    public Coordonnees mousePosConvertisseur(int x, int y) {
        int resX = 0, resY = 0;
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            if (x >= i * TAILLE_CASE && x < TAILLE_CASE * (i + 1))
                resX = i;
            if (y >= i * TAILLE_CASE && y < TAILLE_CASE * (i + 1))
                resY = i;
        }
        return new Coordonnees(resX, resY);
    }

    /**
     * Choisit un tir a effectuer
     * @return Coordonnees du tir
     */
    @Override
    public Coordonnees choixTir() {
        //on ajoute l'écouteur à la grille concerné
        gameVue.getVueGrille2().addMouseListener(this);
        Coordonnees oldCoord = mousePos;
        while (oldCoord.compare(mousePos)) {

            oldCoord = new Coordonnees(mousePos.getX(), mousePos.getY());

            attend();
        }
        return mousePos;
    }

    /**
     * Permet le placement des bateaux
     * @param taille Taille du bateau
     * @param mer Instance de Mer qui regroupe tous les elements du jeu
     * @return
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

    //modifie mousePos

    /**
     * Stocke les coordonnees d'un clic
     * @param e Action effectue par la souris
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        mousePos = mousePosConvertisseur(e.getX(), e.getY());
    }

    /**
     * Detecte quand il y a un clic d'enfonce
     * @param mouseEvent Action effectue par la souris
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    /**
     * Detecte quand le clic est relache
     * @param mouseEvent Action effectue par la souris
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    /**
     * Detecte quand la souris est sur une zone
     * @param mouseEvent Action effectue par la souris
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    /**
     * Detecte quand la souris sors d'une zone
     * @param mouseEvent Action effectue par la souris
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

}

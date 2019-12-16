package vue;

import modele.Modele;
import modele.bateau.Bateau;
import modele.utilities.Coordonnees;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Classe permettant l'affichage de la grille 2
 */
public class VueGrille2 extends JPanel implements VueDessinable {
    public static final int TAILLE_CASE = 50;
    public static final int TAILLE_GRILLE = 10;
    private int dimX, dimY;

    /**
     * Retourne la Taille des cases
     * @return Taille des cases
     */
    public static int getTailleCase() {
        return TAILLE_CASE;
    }

    private Modele modele;

    /**
     * Constructeur de la Vue de la grille 2
     * @param modele Instance du modele du jeu
     */
    public VueGrille2(Modele modele) {
        //modele.ajoutEcouteur(this);
        this.modele = modele;
        dimX = 500;
        dimY = 500;
        setPreferredSize(new Dimension(dimX, dimY));
    }

    /**
     * Permet l'affichage de la grille 2
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        paintLevel(g);
    }
    /**
     * Definit les elements a dessiner
     * @param g
     */
    private void paintLevel(Graphics g) {

        Bateau[][] grille2 = modele.getJoueur2().getGrille();

        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                if (grille2[j][i] != null) {
                    if (grille2[j][i].estEndomage(new Coordonnees(j, i))) {
                        g.setColor(Color.orange);
                        g.fillOval(j * TAILLE_CASE, i * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);

                        if (grille2[j][i].estCoule()){
                            g.setColor(Color.red);
                            g.fillOval(j * TAILLE_CASE, i * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                        }
                    }
                } else {
                    if (modele.getMer().getCaseTireJ1()[j][i]) {
                        g.setColor(Color.green);
                        g.fillOval(j * TAILLE_CASE, i * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                    }
                }
            }
        }
    }
    /**
     * Permet l'actualisation de la grille
     */
    @Override
    public void dessine() {
        this.repaint();
        this.revalidate();
    }

}
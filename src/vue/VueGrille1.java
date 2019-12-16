package vue;

import modele.Modele;
import modele.bateau.Bateau;
import modele.utilities.Coordonnees;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Classe permettant l'affichage de la grille 1
 */
public class VueGrille1 extends JPanel implements VueDessinable {
    /**
     * Retourne la Taille des cases
     * @return Taille des cases
     */
    public static int getTailleCase() {
        return TAILLE_CASE;
    }

    public static final int TAILLE_CASE = 50;
    public static final int TAILLE_GRILLE = 10;
    private int dimX, dimY;
    private Modele modele;

    /**
     * Constructeur de la Vue de la grille 1
     * @param modele Instance du modele
     */
    public VueGrille1(Modele modele) {
        //modele.ajoutEcouteur(this);
        this.modele = modele;
        dimX = 500;
        dimY = 500;
        setPreferredSize(new Dimension(dimX, dimY));
    }

    /**
     * Permet l'affichage de la grille 1
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

        Bateau[][] grille1 = modele.getJoueur1().getGrille();

        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                if (grille1[j][i] != null) {
                    if (grille1[j][i].estEndomage(new Coordonnees(j, i))) {
                        g.setColor(Color.orange);
                        g.fillOval(j * TAILLE_CASE, i * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);

                        if (grille1[j][i].estCoule()){
                            g.setColor(Color.red);
                            g.fillOval(j * TAILLE_CASE, i * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                        }
                    } else {
                        g.setColor(Color.gray);
                        g.fillOval(j * TAILLE_CASE, i * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                    }
                } else {
                    if (modele.getMer().getCaseTireJ2()[j][i]) {
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
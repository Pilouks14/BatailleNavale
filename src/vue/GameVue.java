package vue;

import modele.Modele;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 * Classe permettant d'afficher les grilles
 */
public class GameVue extends JPanel implements VueDessinable {
    private Modele modele;
    private VueGrille1 vueGrille1;
    private VueGrille2 vueGrille2;

    /**
     * Constructeur creant les grilles
     * @param modele Instance du modele
     */
    public GameVue(Modele modele) {
        this.modele=modele;
        setPreferredSize(new Dimension(1100, 500));

        setLayout(new BorderLayout());
        vueGrille1 = new VueGrille1(modele);
        vueGrille2 = new VueGrille2(modele);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        vueGrille1.setBorder(blackline);
        vueGrille2.setBorder(blackline);

        add(vueGrille2, BorderLayout.EAST);
        add(vueGrille1, BorderLayout.WEST);

        setFocusable(true);
        setVisible(true);
    }

    /**
     * Retourne la vue de la grille 1
     * @return Vue de la grille 1
     */
    public VueGrille1 getVueGrille1() {
        return vueGrille1;
    }

    /**
     * Retourne la vue de la grille 2
     * @return Vue de la grille 2
     */
    public VueGrille2 getVueGrille2() {
        return vueGrille2;
    }

    /**
     * Permet de dessiner les grilles
     */
    @Override
    public void dessine() {
        vueGrille1.dessine();
        vueGrille2.dessine();
        this.repaint();
        this.revalidate();
    }


}

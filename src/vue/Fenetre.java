package vue;

import modele.Modele;

import javax.swing.JFrame;

/**
 * Classe permettant l'affichage en fenetre
 */
public class Fenetre extends JFrame implements VueDessinable {
    public GameVue getGameVue() {
        return gameVue;
    }

    private GameVue gameVue;

    /**
     * Constructeur de la fenetre
     * @param modele Instance du modele de jeu
     */
    public Fenetre(Modele modele) {
        super("Bataille Navale");
        gameVue = new GameVue(modele);

        add(gameVue);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Dessine la fenetre
     */
    @Override
    public void dessine() {
        this.repaint();
        this.revalidate();
        gameVue.dessine();
    }


}

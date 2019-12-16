package modele.utilities;

/**
 * Classe des Coordonnees
 */
public class Coordonnees{
    private int x, y;

    /**
     * Redéfinit l'affichage classique
     * @return String indiquant les coordonnees
     */
    @Override
    public String toString() {
        return "x=" + Integer.toString(x) + " y=" + Integer.toString(y);
    }

    /**
     * Constructeur des coordonnees
     * @param x Premiere coordonnee
     * @param y Deuxieme coordonnee
     */
    public Coordonnees(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne la premiere coordonnee
     * @return premiere coordonnee
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la deuxieme coordonnee
     * @return Deuxieme coordonnee
     */
    public int getY() {
        return y;
    }

    /**
     * Compare les coordonnees donnees avec celle de l'instance voulue
     * @param coordAComparer Instance de Coordonnee
     * @return Booleen indiquant si les deux instance de coordonnes sont les memes
     */
    public boolean compare(Coordonnees coordAComparer) {
        return (getX() == coordAComparer.getX() && getY() == coordAComparer.getY());
    }
}

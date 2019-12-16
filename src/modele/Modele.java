package modele;

import modele.bateau.Bateau;
import modele.joueurs.Joueur;
import modele.utilities.AbstractModeleEcouteur;
import modele.utilities.Coordonnees;
/**
 * Classe du Modele
 */
public class Modele extends AbstractModeleEcouteur {
    public Mer getMer() {
        return mer;
    }
    /**
     * Retourne une instance de mer
     * @return Instance de mer
     */
    private Mer mer;
//rechanger déclaration Joeur en humain ou aléatoire une fois les tests fini, plus propre.

    /**
     * Retourne le joueur 1
     * @return Joueur 1
     */
    public Joueur getJoueur1() {
        return joueur1;
    }

    /**
     * Retourne le joueur 2
     * @return Joueur 2
     */
    public Joueur getJoueur2() {
        return joueur2;
    }

    private Joueur joueur1;
    private Joueur joueur2;

    /**
     * Constructeur du modele
     */
    public Modele(){
        joueur1 = new Joueur("alea");
        joueur2 = new Joueur("robot");
        mer = new Mer(joueur1, joueur2);

    }

    /**
     * Permet de placer les bateaux
     * @param joueur Joueur actuel
     * @param choix Bateau choisi
     * @return Booleen indiquant si on peut placer le bateau
     */
    public Boolean placerBateau(Joueur joueur, Bateau choix) {
        if (joueur.placerBateau(choix)) {
            fireChangement();
            return true;
        }
        return false;
    }

    /**
     * Effectue un tir
     * @param joueur Joueur actuel
     * @param choix Coordonnee choisi
     * @return Booleen indiquant si le tir est faisable
     */
    public Boolean tirer(Joueur joueur, Coordonnees choix) {
        if (mer.tirez(choix, joueur)) {
            fireChangement();
            return true;
        }
        return false;
    }

    /**
     * Effectue le changement de joueur
     * @param joueur Joueur actuel
     * @return Joueur suivant
     */
    public Joueur getAutreJoueur(Joueur joueur){
        if(joueur == joueur1)
            return joueur2;
        return joueur1;
    }


}

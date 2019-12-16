package modele;

import modele.bateau.Bateau;
import modele.joueurs.Joueur;
import modele.utilities.Coordonnees;

/**
 * Classe de la mer qui represente tous les elements du jeu
 */
public class Mer {
    public final static int TAILLE_GRILLE = 10;
    private Joueur joueur1;
    private Joueur joueur2;

    /**
     * Retourne la case tire pour le joueur 1
     * @return Case tire pour le joueur 1
     */
    public boolean[][] getCaseTireJ1() {
        return caseTireJ1;
    }

    /**
     * Retourne la case tire pour le joueur 2
     * @return Case tire pour le joueur 2
     */
    public boolean[][] getCaseTireJ2() {
        return caseTireJ2;
    }

    //pour ne pas tirer 2 fois la meme case (meme si il n'y a pas de bateau
    private boolean caseTireJ1[][] = new boolean[TAILLE_GRILLE][TAILLE_GRILLE];
    private boolean caseTireJ2[][] = new boolean[TAILLE_GRILLE][TAILLE_GRILLE];

    /**
     * Constructeur de la mer avec les deux joueurs
     * @param joueur1 Joueur 1
     * @param joueur2 Joueur 2
     */
    public Mer(Joueur joueur1, Joueur joueur2 ){
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                caseTireJ1[i][j] = false;
                caseTireJ2[i][j] = false;
            }
        }

    }

    /**
     * Effectue un tir
     * @param caseSelectionne Tableau de coordonnees indiquant la case voulue
     * @param joueur_qui_tire Joueur actuel
     * @return Booleen indiquant si le tir est faisable
     */
    public boolean tirez(Coordonnees caseSelectionne, Joueur joueur_qui_tire){
        //return true si tir possible false sinon
        boolean[][] caseTire;
        Boolean dejaChoisi;
        if(joueur_qui_tire == joueur1 ){
            dejaChoisi = caseTireJ1[caseSelectionne.getX()][caseSelectionne.getY()];
            caseTire = caseTireJ1;
            if (dejaChoisi)
                return false;
        }else{
            dejaChoisi = caseTireJ2[caseSelectionne.getX()][caseSelectionne.getY()];
            caseTire = caseTireJ2;
            if (dejaChoisi)
                return false;
        }
        caseTire[caseSelectionne.getX()][caseSelectionne.getY()] = true;
        Joueur joueur_vise = get_other_player(joueur_qui_tire);
        for(Bateau bateau: joueur_vise.getFlottes()){
            if(bateau.estTouche(caseSelectionne)){
                bateau.applicationDegat(caseSelectionne);
                return true;
            }
        }
        return true;
    }

    /**
     * Effectue le changement de joueur
     * @param joueur Joueur actuel
     * @return Joueur suivant
     */
    public Joueur get_other_player(Joueur joueur){
        if(joueur == joueur1)
            return joueur2;
        return joueur1;
    }

    /**
     * Indique si le joueur a perdu
     * @param joueur Joueur actuel
     * @return booleen indiquant si le joueur actuel a perdu
     */
    public Boolean aPerdu(Joueur joueur) {
        for (Bateau bateau : joueur.getFlottes()) {
            if (!bateau.estCoule())
                return false;
        }
        return true;
    }

    /**
     * Indique si la partie est fini
     * @return Booleen indiquant si l'un des joueurs a fini
     */
    public Boolean estFini(){
        return aPerdu(joueur1) || aPerdu(joueur2);
    }

    /**
     * Determine si un bateau est placable
     * @param joueur Joueur actuel
     * @param bateau Instance de bateau a placer
     * @return Booleen indiquant si le bateau est placable
     */
    public Boolean estPlacable(Joueur joueur, Bateau bateau) {
        Bateau[][] grille = joueur.getGrille();
        for (Coordonnees emplacement : bateau.getEmplacements()) {
            if (emplacement.getX() >= TAILLE_GRILLE || emplacement.getY() >= TAILLE_GRILLE)
                return false;
            if (grille[emplacement.getX()][emplacement.getY()] != null)
                return false;
        }
        return true;
    }


}

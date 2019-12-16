package ControleurJoueur;

import modele.Mer;
import modele.bateau.Bateau;
import modele.joueurs.Joueur;
import modele.utilities.Coordonnees;

/**
 * Classe abstraite du Controleur de jeu
 */
public abstract class Controleur {

    private Joueur joueurControle;

    /**
     * Retourne le joueur controle
     * @return Joueur controle
     */
    public Joueur getJoueurControle() {
        return joueurControle;
    }

    /**
     * Constructeur du joueur controle
     * @param joueurControle Instance de joueur
     */
    public Controleur(Joueur joueurControle) {
        this.joueurControle = joueurControle;
    }

    /**
     * permet le placement des bateaux
     * @param taille Taille du bateau
     * @param mer Instance de Mer qui regroupe tous les elements du jeu
     * @return Bateaux places
     */
    public abstract Bateau choixPlacement(int taille, Mer mer);

    /**
     * Choisit le tir
     * @return Coordonnees du tir choisi
     */
    public abstract Coordonnees choixTir();
}

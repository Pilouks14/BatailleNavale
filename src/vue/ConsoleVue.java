package vue;

import modele.Modele;
import modele.bateau.Bateau;
import modele.joueurs.Joueur;
import modele.utilities.Coordonnees;

import java.util.ArrayList;

/**
 * Classe permettant l'affichage en console
 */
public class ConsoleVue implements VueDessinable {
    private Modele modele;
    public final static int TAILLE_GRILLE = 10;

    public ConsoleVue(Modele modele){
        this.modele = modele;

    }

    /**
     * Annonce le gagnant
     * @param gagnant Instance d'un joueur
     */
    public void annonceGagnant(Joueur gagnant){
        System.out.println(gagnant.getName());
        ArrayList<Bateau> flotte1 = modele.getJoueur1().getFlottes();
        ArrayList<Bateau> flotte2 = modele.getJoueur2().getFlottes();
        for (Bateau bateau : flotte1) {
            System.out.println("taille" + bateau.getEmplacements().size() + "  endomagé" + bateau.coordToucher.size());
        }
        for (Bateau bateau : flotte2) {
            System.out.println("taille" + bateau.getEmplacements().size() + "  endomagé" + bateau.coordToucher.size());
        }


    }

    /**
     * Permet d'afficher la grille en console
     */
    @Override
    public void dessine(){
        Bateau[][] grille1 = modele.getJoueur1().getGrille();
        Bateau[][] grille2 = modele.getJoueur2().getGrille();

        //constante à définit (10)
        //j'ai inversé i j
        //grille j2
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
                if (grille2[j][i] != null && grille2[j][i].estEndomage(new Coordonnees(j, i))) {
                    System.out.print("!");
                } else{
                    if(modele.getMer().getCaseTireJ1()[j][i])
                        System.out.print("X");
                    else
                        System.out.print("*");
                }
            }System.out.print("\n");
        }
        System.out.println("\n ############ \n");
        //grille j1
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                if (grille1[j][i] != null) {
                    if (grille1[j][i].estEndomage(new Coordonnees(j, i))) {
                        System.out.print("!");
                    } else
                        System.out.print("O");
                } else {
                    if(modele.getMer().getCaseTireJ2()[j][i])
                        System.out.print("X");
                    else
                        System.out.print("*");
                }
            }System.out.print("\n");
        }
        System.out.println("\n");
    }


}

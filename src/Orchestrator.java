import ControleurJoueur.Aleatoire;
import ControleurJoueur.Controleur;
import ControleurJoueur.JoueurSwing;
import modele.Modele;
import modele.bateau.Bateau;
import modele.joueurs.Joueur;
import modele.utilities.Coordonnees;
import modele.utilities.EcouteurModele;
import vue.Fenetre;

import java.util.ArrayList;

/**
 * Classe de l'Orchestrator
 */
public class Orchestrator implements EcouteurModele {
    Modele modele;
    Fenetre vue;
    //ConsoleVue consoleVue;
    Controleur controleur1;
    Controleur controleur2;

    /**
     * Constructeur de l'Orchestrator
     */
    public Orchestrator(){
        modele = new Modele();


        vue = new Fenetre(modele);

        vue.dessine();
        //consoleVue = new ConsoleVue(modele);
        controleur2 = new Aleatoire(modele, modele.getJoueur2());
        //controleur1 = new JoueurConsole(modele.getJoueur1());
        controleur1 = new JoueurSwing(modele.getJoueur1(), vue.getGameVue(), modele);

        //controleur2 = new JoueurConsole(modele.getJoueur2());

        modele.ajoutEcouteur(this);
        //Joueur gagnant = modele.joueUnePartie();
        //vue.annonceGagnant(gagnant);
        //5 bateaux 1-2 2-3 1-4 1-5
        ArrayList<Integer> typeFlotte = new ArrayList<Integer>();
        typeFlotte.add(new Integer(2));
        typeFlotte.add(new Integer(3));
        typeFlotte.add(new Integer(3));
        typeFlotte.add(new Integer(4));
        typeFlotte.add(new Integer(5));
        placerFlotte(controleur1, typeFlotte);
        placerFlotte(controleur2, typeFlotte);
        joueUnePartie();

    }

    /**
     * Permet de placer les bateaux
     * @param controleur Instance du controleur
     * @param taillesBateaux Tailles des bateaux
     */
    public void placerFlotte(Controleur controleur, ArrayList<Integer> taillesBateaux) {
        for (Integer taille : taillesBateaux) {
            Bateau choix = controleur.choixPlacement(taille, modele.getMer());
            while (!modele.placerBateau(controleur.getJoueurControle(), choix)) {
                choix = controleur.choixPlacement(taille, modele.getMer());
            }
        }
    }

    /**
     * Joue une partie
     * @return Joueur gagnant la partie
     */
    public Joueur joueUnePartie() {
        Controleur controleurCourant = controleur1;
        while (!modele.getMer().estFini()) {
            boolean bonTir = false;
            while (!bonTir) {
                Coordonnees choix = controleurCourant.choixTir();
                bonTir = modele.tirer(controleurCourant.getJoueurControle(), choix);
            }
            controleurCourant = autreControleur(controleurCourant);
        }//le gagnant est celui qui n'a pas joué
        return autreControleur(controleur1).getJoueurControle();
    }

    /**
     * Permet de mettre à jour les grilles
     * @param o
     */
    @Override
    public void modeleMisAJour(Object o){
        //consoleVue.dessine();
        vue.dessine();
    }

    //Il y a pas un raccourci pour ?

    /**
     * Permet de changer Controleur
     * @param controleur Instance du controleur
     * @return Instance suivante de controleur
     */
    public Controleur autreControleur(Controleur controleur) {
        if (controleur == controleur1)
            return controleur2;
        return controleur1;
    }
}

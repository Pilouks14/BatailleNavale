package tests;

import modele.Mer;
import modele.joueurs.Joueur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Classe de test de la fin
 */
public class TestFin {
    Mer mer;
    Joueur joueur1, joueur2;
    Boolean fin;

    public TestFin() throws Exception {
    }

    /**
     * La partie n'est pas fini si un des bateaux n'est pas coulé, ici il n'y a pas de bateau
     * la partie doit donc etre fini.
     */
    @Test
    public void testFin(){
        joueur1 = new Joueur("premier");
        joueur2 = new Joueur("deuxième");
        mer = new Mer(joueur1, joueur2);
        fin=mer.estFini();

        Assertions.assertTrue(fin==true);
    }
}

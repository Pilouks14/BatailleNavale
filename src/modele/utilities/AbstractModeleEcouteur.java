package modele.utilities;

import java.util.ArrayList;

/**
 * Classe abstraite implémentant les écouteurs
 */
public abstract class AbstractModeleEcouteur implements ModeleEcouteur {
    ArrayList<EcouteurModele> ecouteurModeles = new ArrayList<>();

    /**
     * Ajoute un ecouteur
     * @param e Instance d'un EcouteurModele
     */
    @Override
    public void ajoutEcouteur(EcouteurModele e) {
        ecouteurModeles.add(e);
    }

    /**
     * Detecte un changement
     */
    protected void fireChangement() {
        for (EcouteurModele e :
                ecouteurModeles) {
            e.modeleMisAJour(this);
        }
    }
}

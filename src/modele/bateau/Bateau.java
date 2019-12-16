package modele.bateau;

import modele.utilities.Coordonnees;

import java.util.ArrayList;

/**
 * Classe des Bateaux
 */
public class Bateau{
    private int x, y, taille;


    private ArrayList<Coordonnees> emplacements;
    public ArrayList<Coordonnees> coordToucher = new ArrayList<Coordonnees>();

    /**
     * Constructeur d'un bateau en donnant un tableau de de coordonnee
     * @param placement Coordonnees du bateau
     * @param taille Taille du bateau
     * @param dir Direction du bateau
     */
    public Bateau(Coordonnees placement,int taille, Direction dir){
        this(placement.getX(),placement.getY(),taille,dir);
    }

    /**
     * Constructeur d'un bateau avec des coordonnees x et y
     * @param x Premiere coordonnee du bateau
     * @param y Deuxieme coordonnee du bateau
     * @param taille Taille du bateau
     * @param dir Direction du bateau
     */
    public Bateau(int x, int y, int taille, Direction dir){
        this.x=x;
        this.y=y;
        this.taille=taille;
        this.emplacements =getCoord(dir);
    }

    /**
     * Retourne les emplacements du bateau
     * @return Emplacements du bateau
     */
    public ArrayList<Coordonnees> getEmplacements() {
        return emplacements;
    }

    /**
     * Retourne la premiere coordonnee
     * @return Premiere coordonnee
     */
    public int getX(){
        return this.x;
    }

    /**
     * Retourne la deuxième coordonnee
     * @return Deuxième coordonnee
     */
    public int getY(){
        return this.y;
    }

    /**
     * Retourne la taille du bateau
     * @return Taille du bateau
     */
    public int getTaille(){
        return this.taille;
    }

    /**
     * Retourne un tableau avec toutes les coordonnees du bateau
     * @param dir Direction du bateau
     * @return Emplacements du bateau par un tableau avec toutes ses coordonnees
     */
    public ArrayList<Coordonnees> getCoord(Direction dir){
        ArrayList<Coordonnees> emplacements = new ArrayList<Coordonnees>();
        for (int i=0; i<getTaille(); i++){
            if (dir==Direction.HORIZONTALE){
                emplacements.add(new Coordonnees(this.getX()+i, this.getY()));
            }
            else{
                emplacements.add(new Coordonnees(this.getX(), this.getY()+i));
            }
        }
        return emplacements;
    }

    /**
     * Applique un degat sur le bateau
     * @param degat Coordonnee de la case attaque
     */
    public void applicationDegat(Coordonnees degat) {
        coordToucher.add(degat);
    }

    /**
     * Verifie si la case visee est deja touchee
     * @param caseVise Coordonnee de la case visee
     * @return Booleen indiquant si la case visee est deja touchee ou si elle ne l'est pas
     */
    public boolean estEndomage(Coordonnees caseVise) {
        for (Coordonnees caseEndomager : coordToucher) {
            if (caseEndomager.compare(caseVise))
                return true;
        }
        return false;
    }

    /**
     * Determine si le bateau est touche
     * @param caseTouche Coordonnee de la case à tester
     * @return Booleen indiquant si le bateau est touche pour une coordonnee
     */
    public boolean estTouche(Coordonnees caseTouche) {
        for (Coordonnees dejaTouche : coordToucher) {
            if (dejaTouche.compare(caseTouche))
                return false;
        }
        for (Coordonnees emplacement : emplacements) {
            if (emplacement.compare(caseTouche)) {
                return true;
            }
        }return false;
    }

    /**
     * Determine si le bateau est coule
     * @return Booleen indiquant si le bateau est coule
     */
    public boolean estCoule(){
        return coordToucher.size() == getTaille();
    }
}

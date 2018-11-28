package model.entites;

import model.Jeu;
import model.plateau.Plateau;

import java.awt.*;
import java.util.Objects;

public abstract class Entites {

    /**
     * Position de l'entite
     */
    protected Point coord;
    /**
     * Plateau auquel appartient l'entite
     */
    protected Plateau plateau;
    /**
     * Vie de l'entit�
     */
    protected int vie;
    /**
     * Numero identifiant d'une entite
     */
    protected int id;
    /**
     * Modificateur d'ID
     */
    private static int ID = 1;

    /**
     * Degat de l'entite
     */
    protected int degat;

    /**
     * Direction de l'entite
     */
    protected Direction dir;

    protected int vieMax = 1;

    protected int vitesse;

    /**
     *
     * Constructeur a partir d'une position et d'un plateau
     * @param coord Coordonnee de l'entite sur le plateau
     * @param plateau Plateau au qu'elle appartient l'entite
     */
    public Entites(Point coord, Plateau plateau) {
        this.coord = coord;
        this.plateau = plateau;
        this.id = ID;
        ID++;
    }

    public int getId() {
        return id;
    }

    public Rectangle getSurfaceCollision(){
        return new Rectangle(coord.x,coord.y + Jeu.TAILLE - Jeu.ECHELLE/4,Jeu.TAILLE,Jeu.ECHELLE/4);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public Point getCoord() {
        return coord;
    }

    public void setCoord(Point coord) {
        this.coord = coord;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public int soigner(int soin) {
            int effet = vie + soin - vieMax;
            if(effet <= 0) {
                vie += soin;
                return soin;
            } else {
                if(soin - effet > 0 ) {
                    vie += soin - effet;
                    return soin-effet;
                }
            }
        return 0;
    }

    public abstract int getDegat();

    public abstract String getType();

    public void subirDegat(int degat) {
        vie -= degat;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entites)) return false;
        Entites entites = (Entites) o;
        return id == entites.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

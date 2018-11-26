package model.entites;

import model.Jeu;
import model.plateau.Plateau;

import java.awt.*;

public abstract class Monstre extends Entites {

	/**
     * Constructeur � partir d'une position et d'un plateau
     *
     * @param coord   Coordonn�e du Monstre sur le plateau
     * @param plateau Plateau au qu'elle appartient le Monstre
     */
    public Monstre(Point coord, Plateau plateau) {
        super(coord, plateau);
    }

    /**
     * Calcul et v�rifie le d�placement du Monstre avant de la deplacer
     */
    public abstract void deplacer();

    @Override
    public void subirDegat(int degat) {
        super.subirDegat(degat);
        estMort();
    }

    /**
     * V�rifie si le Monstre est vivant et applique un effet sinon
     */
    public void estMort() {
        if (vie <= 0)
            Jeu.getInstance().addCimetiere(this);
    }

    public String getType() {
        return "m";
    }

    public int getDegat() {
        return this.degat;
    }
}

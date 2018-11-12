package model.entites;

import model.Jeu;
import model.plateau.Plateau;

import java.awt.*;

public abstract class Monstre extends Entites{
    public Monstre(Point coord, Plateau plateau) {
        super(coord, plateau);
    }

    public abstract void deplacer();

    public void estMort() {
        if (vie <= 0)
            Jeu.getInstance().addCimetiere(this);
    }
}

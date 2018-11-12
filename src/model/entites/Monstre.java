package model.entites;

import model.plateau.Plateau;

import java.awt.*;

public abstract class Monstre extends Entites{
    public Monstre(Point coord, Plateau plateau) {
        super(coord, plateau);
    }

    public abstract void deplacer();
}

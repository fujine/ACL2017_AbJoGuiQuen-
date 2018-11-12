package model.entites;

import model.plateau.Plateau;

import java.awt.*;

public abstract class Entites {
    protected Point coord;
    protected Plateau plateau;
    protected int vie;

    public Entites(Point coord, Plateau plateau) {
        this.coord = coord;
        this.plateau = plateau;
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
}

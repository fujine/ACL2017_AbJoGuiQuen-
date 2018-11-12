package model.entites;

import model.plateau.Plateau;

import java.awt.*;
import java.util.Objects;

public abstract class Entites {
    protected Point coord;
    protected Plateau plateau;
    protected int vie;
    protected int id;
    private static int ID = 1;

    public Entites(Point coord, Plateau plateau) {
        this.coord = coord;
        this.plateau = plateau;
        this.id = ID;
        ID++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

package model.plateau;

import model.plateau.objet.Objet;

import java.awt.*;

public interface ICase {
    ECase getType();
    public Objet getObjet();
    boolean estTraversable();
    void appliquerEffet();
}

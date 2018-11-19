package model.plateau;

import model.plateau.objet.Objet;

public interface ICase {
    ECase getType();
    public Objet getObjet();
    boolean estTraversable();
    void appliquerEffet();
}

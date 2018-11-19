package model.plateau;

import model.plateau.objet.Objet;

public class CaseVide implements ICase {



    @Override
    public ECase getType() { return ECase.VIDE; }

    @Override
    public Objet getObjet() {
        return null;
    }

    @Override
    public boolean estTraversable() {
        return false;
    }

    @Override
    public void appliquerEffet() {

    }
}

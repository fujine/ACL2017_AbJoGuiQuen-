package model.plateau;

import model.plateau.objet.Objet;

public class Case implements ICase {

    private Objet objet;

    public Case(Objet objet) {
        this.objet = objet;
    }

    public Case() {

    }

    @Override
    public void appliquerEffet() {
        if(objet!= null)
            objet.appliquerEffet();
    }

    @Override
    public boolean estTraversable() {
        return true;
    }

}

package model.plateau;

import model.plateau.objet.Objet;

public class Case implements ICase {

    private Objet objet;
    private ECase type;

    public Case(Objet objet) {
        this.objet = objet;
        type = objet.getType();
    }

    public Case() {
        type = ECase.SOL;
    }

    @Override
    public void appliquerEffet() {
        if(objet!= null)
            objet.appliquerEffet();
    }

    @Override
    public ECase getType() {
        return type;
    }

    @Override
    public boolean estTraversable() {
        return true;
    }

}

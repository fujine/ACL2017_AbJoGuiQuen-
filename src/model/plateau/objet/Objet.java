package model.plateau.objet;

import model.plateau.ECase;

public abstract class Objet {
    protected ECase type;

    public abstract void appliquerEffet();
    public ECase getType() {
        return type;
    }
}

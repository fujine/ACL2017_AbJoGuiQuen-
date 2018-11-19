package model.plateau.objet;

import model.plateau.ECase;

public abstract class Objet {
    /**
     * Type d'objet
     */
    protected ECase type;

    protected int info;

    /**
     * Applique l'effet de l'objet
     */
    public abstract void appliquerEffet();

    public int getInfo() {
        return info;
    }

    public ECase getType() {
        return type;
    }
}

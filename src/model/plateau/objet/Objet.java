package model.plateau.objet;

import model.plateau.ECase;

public abstract class Objet {
    /**
     * Type d'objet
     */
    protected ECase type;

    /**
     * Applique l'effet de l'objet
     */
    public abstract void appliquerEffet();

    public ECase getType() {
        return type;
    }
}

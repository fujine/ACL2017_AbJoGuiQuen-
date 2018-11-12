package model.plateau;

import model.plateau.objet.Objet;

public class Case implements ICase {

    /**
     * Objet que possède la case
     */
    private Objet objet;

    /**
     * Type de la case
     */
    private ECase type;

    /**
     * Création d'une case avec un Objet
     * @param objet
     */
    public Case(Objet objet) {
        this.objet = objet;
        type = objet.getType();
    }

    /**
     * Crée une case vide sans objet
     */
    public Case() {
        type = ECase.VIDE;
    }

    /**
     * Applique l'effet de l'objet si la case en a une
     */
    @Override
    public void appliquerEffet() {
        if(objet!= null)
            objet.appliquerEffet();
    }

    @Override
    public ECase getType() {
        return type;
    }

    /**
     * Vérifie que la case peut etre trraversé
     * @return vrai si case traversable faux sinon
     */
    @Override
    public boolean estTraversable() {
        return true;
    }

}

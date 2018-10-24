package model.factory;

import model.plateau.objet.Objet;
import model.plateau.objet.ObjetPiege;
import model.plateau.objet.ObjetTresor;

public class ObjetFactory {
    public static final int PIEGE = 0;
    public static final int TRESOR = 1;

    public static Objet creerObjet(int type) {
        switch (type){
            case PIEGE:
                return new ObjetPiege();
            case TRESOR:
                return new ObjetTresor();
            default:
                return null;
        }
    }
}

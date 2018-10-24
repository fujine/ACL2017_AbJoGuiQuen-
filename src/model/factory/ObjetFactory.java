package model.factory;

import model.plateau.ECase;
import model.plateau.objet.Objet;
import model.plateau.objet.ObjetPiege;
import model.plateau.objet.ObjetTresor;

public class ObjetFactory {
    public static Objet creerObjet(ECase type) {
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

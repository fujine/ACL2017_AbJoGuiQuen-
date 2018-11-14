package model.factory;

import model.Jeu;
import model.plateau.ECase;
import model.plateau.ICase;
import model.plateau.objet.*;

import java.awt.*;
import java.util.ArrayList;

public class ObjetFactory {
    /**
     * Créer un objet en fonction d'un type donné
     * @param type d'objet
     * @return l'objet correspondant au type
     */
    public static Objet creerObjet(ECase type) {
        switch (type){
            case PIEGE:
                return new ObjetPiege();
            case TRESOR:
                return new ObjetTresor();
            case VIE:
                return new ObjetVie();
            default:
                return null;
        }
    }

    /**
     * Créer un objet en fonction d'un type donné et de paramètre pour la création d'objet
     * @param type d'objet
     * @param arguments parametre pour la création d'objet
     * @return l'objet correspondant
     */
    public static Objet creerObjet(ECase type, Object ... arguments) {
        switch (type){
            case TELEPORTEUR:
                if (arguments[0] instanceof Point)
                    return new ObjetTp((Point)arguments[0]);
            case ESCALIER:
                if (arguments[0] instanceof Point && arguments[1] instanceof Integer)
                    return new ObjetEscalier((Point) arguments[0],(Integer) arguments[1]);
            default:
                return null;
        }
    }
}

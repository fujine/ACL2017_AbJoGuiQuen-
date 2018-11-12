package model.factory;

import model.Jeu;
import model.plateau.ECase;
import model.plateau.ICase;
import model.plateau.objet.Objet;
import model.plateau.objet.ObjetPiege;
import model.plateau.objet.ObjetTp;
import model.plateau.objet.ObjetTresor;

import java.awt.*;

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
    public static Objet creerObjet(ECase type, Object arguments) {
        switch (type){
            case TELEPORTEUR:
                if (arguments instanceof Point && Jeu.getInstance().verifLibre((Point)arguments))
                    return new ObjetTp((Point)arguments);
            default:
                return null;
        }
    }
}

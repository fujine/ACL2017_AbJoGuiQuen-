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

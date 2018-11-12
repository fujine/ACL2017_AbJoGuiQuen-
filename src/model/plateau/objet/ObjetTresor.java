package model.plateau.objet;

import model.Jeu;
import model.plateau.ECase;

public class ObjetTresor extends Objet {

    /**
     * Constructore par défaut qui défini le type de la case
     */
    public ObjetTresor() {
        type = ECase.TRESOR;
    }

    /**
     * Termine le jeu
     */
    @Override
    public void appliquerEffet() {
        Jeu.getInstance().setFini(true);
    }
}

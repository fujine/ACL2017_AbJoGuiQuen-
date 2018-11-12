package model.plateau.objet;

import model.Jeu;
import model.plateau.ECase;

public class ObjetPiege extends Objet {

    /**
     * Constructore par défaut qui défini le type de la case
     */
    public ObjetPiege() {
        type = ECase.PIEGE;
    }

    /**
     * Retire la vie au héro quand il marche dessus
     */
    @Override
    public void appliquerEffet() {
        Jeu.getInstance().appliquerDegats(2);
    }

}

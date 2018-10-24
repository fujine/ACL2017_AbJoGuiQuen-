package model.plateau.objet;

import model.Jeu;
import model.plateau.ECase;

public class ObjetPiege extends Objet {

    public ObjetPiege() {
        type = ECase.PIEGE;
    }
    @Override
    public void appliquerEffet() {
        Jeu.getInstance().appliquerDegats(2);
    }

}

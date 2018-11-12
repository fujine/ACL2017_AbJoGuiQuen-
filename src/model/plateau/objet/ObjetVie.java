package model.plateau.objet;

import model.Jeu;
import model.plateau.ECase;

public class ObjetVie extends Objet {
    private int soin = 2;

    public ObjetVie() {
        type = ECase.VIE;
    }

    @Override
    public void appliquerEffet() {
        Jeu.getInstance().getHero().soigner(soin);
        soin-=2;
    }
}

package model.plateau.objet;

import model.Jeu;
import model.plateau.ECase;

public class ObjetVie extends Objet {

    public ObjetVie() {
        type = ECase.VIE;
        info = 2;
    }

    @Override
    public void appliquerEffet() {
        int soin = Jeu.getInstance().getHero().soigner(info);
        if(soin > 0) {
            info -= soin;
        }
    }
}

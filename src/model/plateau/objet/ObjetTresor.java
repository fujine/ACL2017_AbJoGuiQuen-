package model.plateau.objet;

import model.Jeu;
import model.plateau.ECase;

public class ObjetTresor extends Objet {

    public ObjetTresor() {
        type = ECase.TRESOR;
    }
    @Override
    public void appliquerEffet() {
        Jeu.getInstance().setFini(true);
    }
}

package model.plateau.objet;

import model.Jeu;

public class ObjetTresor implements Objet {

    @Override
    public void appliquerEffet() {
        Jeu.getInstance().setFini(true);
    }
}

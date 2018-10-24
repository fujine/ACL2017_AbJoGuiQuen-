package model.plateau.objet;

import model.Jeu;

public class ObjetPiege implements Objet {

    @Override
    public void appliquerEffet() {
        Jeu.getInstance().appliquerDegats(2);
    }

}

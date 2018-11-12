package model.plateau.objet;

import model.Jeu;
import model.plateau.ECase;

import java.awt.*;

public class ObjetTp extends Objet {
    private Point coord;

    public ObjetTp(Point coord) {
        type = ECase.TELEPORTEUR;
        this.coord = coord;
    }

    @Override
    public void appliquerEffet() {
        Jeu.getInstance().getHero().deplacer(coord);
        Jeu.getInstance().getPlateau().appliquerEffetCase(coord);
    }
}

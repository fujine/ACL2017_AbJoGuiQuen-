package model.plateau.objet;

import model.Jeu;
import model.plateau.ECase;

import java.awt.*;

public class ObjetEscalier extends Objet {
    /**
     * Coordonnées de la case ou est téléporté le héro
     */
    private Point coord;

    /**
     * Index du plateau à atteindre
     */
    private int plateau;

    /**
     * Constructore qui défini le type de la case avec les coordonnées de la téléportation
     */
    public ObjetEscalier(Point coord, int index) {
        type = ECase.ESCALIER;
        this.coord = coord;
        plateau = index;
    }

    @Override
    public void appliquerEffet() {
        Jeu.getInstance().changerPlateaur(plateau);
        Jeu.getInstance().getHero().deplacer(coord);
    }
}

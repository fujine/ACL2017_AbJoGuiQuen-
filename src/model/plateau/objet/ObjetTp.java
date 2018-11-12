package model.plateau.objet;

import model.Jeu;
import model.plateau.ECase;

import java.awt.*;

public class ObjetTp extends Objet {
    /**
     * Coordonnées de la case ou est téléporté le héro
     */
    private Point coord;

    /**
     * Constructore qui défini le type de la case avec les coordonnées de la téléportation
     */
    public ObjetTp(Point coord) {
        type = ECase.TELEPORTEUR;
        this.coord = coord;
        System.out.println(coord.toString());
    }

    /**
     * Téléporte le héro aux coordonnées définies
     */
    @Override
    public void appliquerEffet() {
        Jeu.getInstance().getHero().deplacer(coord);
        Jeu.getInstance().getPlateau().appliquerEffetCase(coord);
    }
}

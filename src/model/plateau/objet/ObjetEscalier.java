package model.plateau.objet;

import model.Jeu;
import model.entites.Direction;
import model.plateau.ECase;

import java.awt.*;

public class ObjetEscalier extends Objet {
    /**
     * Coordonnées de la case ou est téléporté le héro
     */
    private Point coord;

    public Point getCoord() {
		return coord;
	}

	public void setCoord(Point coord) {
		this.coord = coord;
	}

	public int getPlateau() {
		return plateau;
	}

	public void setPlateau(int plateau) {
		this.plateau = plateau;
	}

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
        info = index;

    }

    @Override
    public void appliquerEffet() {
        Jeu.getInstance().changerPlateau(plateau);
        Jeu.getInstance().getHero().deplacer(coord, Direction.BAS);
    }
}

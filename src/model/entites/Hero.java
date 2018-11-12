package model.entites;

import model.Jeu;
import model.plateau.Plateau;

import java.awt.*;

public class Hero extends Entites {

    public Hero(Point coord, Plateau plateau) {
        super(coord, plateau);
        coord = new Point(1,1);
        vie = 4;
    }


    public void deplacer(Point coord) {
        this.coord = coord;
    }

    public void subirDegat(int nbDegats) {
        this.vie = vie - nbDegats;
        System.out.println("aie");
        if (vie <= 0 ){
            Jeu.getInstance().estMort();
            System.out.println("je suis mort");
        }
    }

    public boolean estVivant() {
        return this.vie <= 0;
    }

}


package model.entites;

import model.Jeu;
import model.plateau.Plateau;

import java.awt.*;
import java.util.Random;

public class Chevalier extends Monstre {
    public Chevalier(Point coord, Plateau plateau) {
        super(coord, plateau);
        vie = 10;
    }

    @Override
    public void deplacer() {
        Random r = new Random();
        int dep = r.nextInt(6);
        int posX = getCoord().x;
        int posY = getCoord().y;
        Jeu mod = Jeu.getInstance();
        switch (dep) {
            //Haut
            case 0 :
                posY--;
                break;
                //Bas
            case 1: case 4:
                posY++;
                break;
                //Gauche
            case 2:
                posX--;
                break;
            case 3 : case 5:
                posX++;
                break;
        }
        if(mod.verifLibre(new Point(posX,posY)) && !mod.collisionEntites(this,new Point(posX,posY)))
            coord.move(posX,posY);
    }
}

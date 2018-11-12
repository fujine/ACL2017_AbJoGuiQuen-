package model.entites;

import model.Jeu;
import model.plateau.Plateau;

import java.awt.*;
import java.util.Random;

public class Chevalier extends Monstre {
    /**
     * Constructeur à partir d'une position et d'un plateau avec définition de la vie du chevalier
     * @param coord Coordonnée du Chevalier sur le plateau
     * @param plateau Plateau au qu'elle appartient le Chevalier
     */
    public Chevalier(Point coord, Plateau plateau) {
        super(coord, plateau);
        vie = 10;
    }

    /**
     * Calcul et vérifie le déplacement du chevalier avant de la deplacer
     */
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
        //Vérification de la case du plateau si elle est libre et vérifie la collision avec d'autres entités
        if(plateau.estLibre(posX,posY) && !mod.collisionEntites(this,new Point(posX,posY)))
            coord.move(posX,posY);
    }
}

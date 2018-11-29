package model.entites;

import model.Jeu;
import model.entites.Hero;
import model.entites.Monstre;
import model.plateau.Plateau;
//import monJeu.Entite;
//import moteurJeu.Commande;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Chevalier<dep> extends Monstre {
	Hero h = new Hero(coord, plateau);
    /**
     * Constructeur a partir d'une position et d'un plateau avec definition de la vie du chevalier
     * @param coord Coordonnee du Chevalier sur le plateau
     * @param plateau Plateau au qu'elle appartient le Chevalier
     */
    
    public Chevalier(Point coord, Plateau plateau) {
        super(coord, plateau);
        vie = 1;
        degat = 1;
        dir = Direction.BAS;
        vitesse = 3;
        distanceObservation = 3;
    }

    protected boolean canMove(int posX, int posY){
        Jeu mod = Jeu.getInstance();
        Point coordBG = new Point(posX,posY+Jeu.TAILLE-1);
        Point coordBD = new Point(posX + Jeu.TAILLE-1, posY + Jeu.TAILLE-1);
        Point coordHG = new Point(posX,posY+Jeu.TAILLE + 1 - Jeu.ECHELLE/4);
        Point coordHD = new Point(posX + Jeu.TAILLE-1,posY+Jeu.TAILLE-1- Jeu.ECHELLE/4);
        Rectangle colli = new Rectangle(coordHG,new Dimension(Jeu.TAILLE,Jeu.ECHELLE/4));
        if(plateau.estLibre(coordBG) && plateau.estLibre(coordBD) && plateau.estLibre(coordHD) && plateau.estLibre(coordHG)  && mod.collisionEntites(this,colli) == null) {
            if (posX >= 0 && posY >= 0 && posX < mod.getPlateau().getLargeur() && posY < mod.getPlateau().getHauteur()) {
                return true;
            }
        }
        return false;
    }

}




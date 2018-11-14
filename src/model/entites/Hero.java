package model.entites;

import model.Jeu;
import model.plateau.Plateau;

import java.awt.*;

public class Hero extends Entites {

    /**
     * Constructeur à partir d'une position et d'un plateau avec définition de la vie du héro
     * @param coord Coordonnée du héro sur le plateau
     * @param plateau Plateau au qu'elle appartient le héro
     */
    public Hero(Point coord, Plateau plateau) {
        super(coord, plateau);
        coord = new Point(1,1);
        vie = 4;
        degat = 1;
    }

    /**
     * TODO
     * @return degat du hero
     */
    public int getDegat() { return degat; }

    /**
     * Vérifie les coordonnées avant de deplacer le hero au coordonnées données
     * @param coord futur position du héro
     */
    public void deplacer(Point coord) {
        Jeu mod = Jeu.getInstance();
        if (coord.x >= 0 && coord.y>= 0 && coord.x < mod.getPlateau().getLargeur() && coord.y < mod.getPlateau().getHauteur() ){
            //Test de colision et de zone libre pour le deplacement du héro
            if(mod.verifLibre(coord) && mod.collisionEntites(this,coord) == null) {
                this.coord = coord;
                Jeu.getInstance().getPlateau().appliquerEffetCase(coord);
            }
        }

    }

    /**
     * Diminue la vie du héro en fonction du nombre de dégats subit
     * @param nbDegats
     */
    public void subirDegat(int nbDegats) {
        this.vie = vie - nbDegats;
        System.out.println("aie");
        if (vie <= 0 ){
            Jeu.getInstance().estMort();
            System.out.println("je suis mort");
        }
    }

    /**
     * Verifie que le héro soit encore en vie
     * @return true si le héro a plus de 0 de vie, false sinon
     */
    public boolean estVivant() {
        return this.vie <= 0;
    }

    public String getType(){return "h";}


}


package model.entites;

import model.Jeu;
import model.plateau.Plateau;

import java.awt.*;
import java.util.Random;

public class Poule extends Monstre{
    public Poule(Point coord, Plateau plateau) {
        super(coord, plateau);
        vie = 1;
        dir = Direction.BAS;
        vitesse = 9;
        distanceObservation = 5;
    }

    /**
     * Permet à la methode deplacer de fuire le hero (seulement pour la poule)
     * 
     */
    protected void suivreHero(double dist,Jeu mod, Point posM, Point posH){
        
        Direction[] tab = {Direction.HAUT, Direction.BAS,Direction.GAUCHE,Direction.DROITE};

        for (int i = 0; i < 4 ; i++){
            Direction choix = tab[i];
            int x = coord.x;
            int y = coord.y;

            switch (choix) {
                case HAUT:
                    y-=vitesse;
                    break;
                case BAS:
                    y+=vitesse;
                    break;
                case GAUCHE:
                    x-=vitesse;
                    break;
                case DROITE:
                    x+=vitesse;
                    break;
            }

            //Test d'éloignement
            if(dist < posH.distance(new Point(x, y))) {
                //Test Collision mur
                Point coordBG = new Point(x,y+Jeu.TAILLE-1);
                Point coordBD = new Point(x + Jeu.TAILLE-1, y + Jeu.TAILLE-1);
                Point coordHG = new Point(x,y+Jeu.TAILLE + 1 - Jeu.ECHELLE/4);
                Point coordHD = new Point(x + Jeu.TAILLE-1,y+Jeu.TAILLE-1- Jeu.ECHELLE/4);
                Rectangle colli = new Rectangle(coordHG,new Dimension(Jeu.TAILLE,Jeu.ECHELLE/4));
                Entites e = mod.collisionEntites(this,colli);
                if(e == null) {
                    if (canMove(x, y)) {
                        coord.move(x, y);
                        dir = choix;
                        i = 4;
                    }
                }  else if (e instanceof Hero) {
                    if (timer == 0) {
                        e.subirDegat(this.degat);
                        timer = System.currentTimeMillis();
                    } else {
                        if(System.currentTimeMillis() - timer > 1000) {
                            timer = 0;
                        }
                    }
                }
            }

        }
    
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

    public String getType() {
        return "p";
    }
}
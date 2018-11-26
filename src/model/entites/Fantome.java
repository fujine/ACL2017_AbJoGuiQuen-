package model.entites;

import model.Jeu;
import model.plateau.Plateau;

import java.awt.*;
import java.util.Random;

public class Fantome extends Monstre{

    private long timer = 0;

    /**
     * Constructeur à partir d'une position et d'un plateau avec définition de la vie du chevalier
     * @param coord Coordonnée du Chevalier sur le plateau
     * @param plateau Plateau au qu'elle appartient le Chevalier
     */
    public Fantome(Point coord, Plateau plateau) {
        super(coord, plateau);
        vie = 1;
        degat = 1;
        dir = Direction.BAS;
        vitesse = 3;
    }


    @Override
    public void deplacer() {
        Jeu mod = Jeu.getInstance();

        Point posM = this.getCoord();
        Point posH = new Point(mod.getHero().getCoord());
        double dist = posM.distance(posH);

        Random r = new Random();
        int dep = r.nextInt(6);
        int posX = getCoord().x;
        int posY = getCoord().y;

        if (dist > 8*Jeu.ECHELLE) {

            switch (dep) {
                //Haut
                case 0:
                    posY-=vitesse;
                    dir = Direction.HAUT;
                    break;
                //Bas
                case 1:
                case 4:
                    dir = Direction.BAS;
                    posY+=vitesse;
                    break;
                //Gauche
                case 2:
                    posX-=vitesse;
                    dir = Direction.GAUCHE;
                    break;
                case 3:
                case 5:
                    posX+=vitesse;
                    dir = Direction.DROITE;
                    break;
            }

            Point coordBG = new Point(posX,posY+Jeu.TAILLE-1);
            Point coordBD = new Point(posX + Jeu.TAILLE-1, posY + Jeu.TAILLE-1);
            Point coordHG = new Point(posX,posY+Jeu.TAILLE + 1 - Jeu.ECHELLE/4);
            Point coordHD = new Point(posX + Jeu.TAILLE-1,posY+Jeu.TAILLE-1- Jeu.ECHELLE/4);
            Rectangle colli = new Rectangle(coordHG,new Dimension(Jeu.TAILLE,Jeu.ECHELLE/4));
            if(mod.collisionEntites(this,colli) == null) {
                if (posX >= 0 && posY >= 0 && posX < mod.getPlateau().getLargeur() && posY < mod.getPlateau().getHauteur()) {
                    coord.move(posX, posY);
                }
            }


        }else{

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
                if(dist> posH.distance(new Point(x, y))) {
                    //Test Collision mur
                    Point coordBG = new Point(x,y+Jeu.TAILLE-1);
                    Point coordBD = new Point(x + Jeu.TAILLE-1, y + Jeu.TAILLE-1);
                    Point coordHG = new Point(x,y+Jeu.TAILLE + 1 - Jeu.ECHELLE/4);
                    Point coordHD = new Point(x + Jeu.TAILLE-1,y+Jeu.TAILLE-1- Jeu.ECHELLE/4);
                    Rectangle colli = new Rectangle(coordHG,new Dimension(Jeu.TAILLE,Jeu.ECHELLE/4));
                    Entites e = mod.collisionEntites(this,colli);
                    if(e == null) {
                        if (x >= 0 && y >= 0 && x < mod.getPlateau().getLargeur() && y < mod.getPlateau().getHauteur()) {
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

    }


}

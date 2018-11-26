package model.entites;

import model.Jeu;
import model.entites.Hero;
import model.entites.Monstre;
import model.plateau.Plateau;

import java.awt.*;
import java.util.Random;

public class Chevalier<dep> extends Monstre {
    Hero h = new Hero(coord, plateau);

    /**
     * Constructeur à partir d'une position et d'un plateau avec définition de la vie du chevalier
     * @param coord Coordonnée du Chevalier sur le plateau
     * @param plateau Plateau au qu'elle appartient le Chevalier
     */
    public Chevalier(Point coord, Plateau plateau) {
        super(coord, plateau);
        vie = 1;
        degat = 1;
        dir = Direction.BAS;
        vitesse = 3;
    }

    /**
     * Calcul et vérifie le déplacement du chevalier avant de la deplacer
     */
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

        if (dist > 3*Jeu.ECHELLE) {

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
            if(plateau.estLibre(coordBG) && plateau.estLibre(coordBD) && plateau.estLibre(coordHD) && plateau.estLibre(coordHG)  && mod.collisionEntites(this,colli) == null) {
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
                    if(plateau.estLibre(coordBG) && plateau.estLibre(coordBD) && plateau.estLibre(coordHD) && plateau.estLibre(coordHG)  && mod.collisionEntites(this,colli) == null) {
                        if (x >= 0 && y >= 0 && x < mod.getPlateau().getLargeur() && y < mod.getPlateau().getHauteur()) {
                            coord.move(x, y);
                            dir = choix;
                            i = 4;
                        }
                    } else if (mod.collisionEntites(this, colli)==null){
                        Point p = new Point(coord);
                        switch (choix) {
                            case DROITE:
                                if((coordBD.x +1 ) % Jeu.ECHELLE != 0 ) {
                                    this.coord.x = coord.x - (coordBD.x ) % Jeu.ECHELLE;
                                    if(!p.equals(coord))
                                        i = 4;
                                    dir = choix;
                                }
                                break;
                            case GAUCHE:
                                if(coordBG.x % Jeu.ECHELLE != 0 ) {
                                    this.coord.x = this.coord.x / Jeu.ECHELLE * Jeu.ECHELLE;
                                    if(!p.equals(coord))
                                        i = 4;
                                    dir = choix;
                                }
                                break;
                            case BAS:
                                if((coordBD.y +1 ) % Jeu.ECHELLE != 0 ) {
                                    this.coord.y = (this.coord.y + Jeu.ECHELLE)/Jeu.ECHELLE*Jeu.ECHELLE - Jeu.TAILLE ;
                                    if(!p.equals(coord))
                                        i = 4;
                                    dir = choix;
                                }
                                break;
                            case HAUT:
                                if((coordBD.y ) % Jeu.ECHELLE != 0 ) {
                                    this.coord.y = this.coord.y / Jeu.ECHELLE * Jeu.ECHELLE + (Jeu.ECHELLE - Jeu.TAILLE + Jeu.ECHELLE/4 +1) ;
                                    if(!p.equals(coord))
                                        i = 4;
                                    dir = choix;
                                }
                                break;
                        }
                    }
                }

            }
        }

        /*if (mod.collisionEntites(this,new Point(posX,posY)) != null && mod.collisionEntites(this,new Point(posX,posY)).getType().equals("h")){
            mod.appliquerDegats(this.getDegat());
        }*/
    }

}


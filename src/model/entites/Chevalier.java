package model.entites;

import model.Jeu;
import model.entites.Hero;
import model.entites.Monstre;
import model.plateau.Plateau;

import java.awt.*;
import java.util.Random;

public class Chevalier<dep> extends Monstre {
    Hero h = new Hero(coord, plateau);
    int vitesse = 1;
    
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

        if (dist > 3) {

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

        }else{

            double distHaut = posH.distance(new Point(posX, posY-vitesse));
            double distBas = posH.distance(new Point(posX, posY+vitesse));
            double distDroite = posH.distance(new Point(posX+vitesse, posY));
            double distGauche = posH.distance(new Point(posX-vitesse, posY));

            if(distHaut < dist && plateau.estLibre(new Point(posX,posY-vitesse))){
                System.out.println("haut");
                posY-=vitesse;
                dir = Direction.HAUT;
            }else if(distBas < dist && plateau.estLibre(new Point(posX,posY+vitesse)) ){
                System.out.println("bas");
                posY+=vitesse;
                dir = Direction.BAS;

            }else if(distDroite < dist && plateau.estLibre(new Point(posX+vitesse,posY))){
                System.out.println("droite");
                posX+=vitesse;
                dir = Direction.DROITE;

            }else if(distGauche < dist && plateau.estLibre(new Point(posX-vitesse,posY))){
                System.out.println("gauche");
                posX-=vitesse;
                dir = Direction.GAUCHE;
            }


        }

        if (mod.collisionEntites(this,new Point(posX,posY)) != null && mod.collisionEntites(this,new Point(posX,posY)).getType().equals("h")){
            mod.appliquerDegats(this.getDegat());
        }


        //Vérification de la case du plateau si elle est libre et vérifie la collision avec d'autres entités
        if(plateau.estLibre(new Point(posX,posY)) && mod.collisionEntites(this,new Point(posX,posY)) == null)
            if (posX >= 0 && posY>= 0 && posX < mod.getPlateau().getLargeur() && posY < mod.getPlateau().getHauteur())
                coord.move(posX,posY);
    }

}


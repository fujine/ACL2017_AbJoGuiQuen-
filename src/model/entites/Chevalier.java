package model.entites;

import model.Jeu;
import model.entites.Hero;
import model.entites.Monstre;
import model.plateau.Plateau;
//import monJeu.Entite;
//import moteurJeu.Commande;

import java.awt.*;
import java.util.Random;

public class Chevalier<dep> extends Monstre {
    Hero h = new Hero(coord, plateau);
    int posX,posY;

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
    public int Distance(Point p1, Point p2) {
        //p1=h.coord;
        //p2=this.coord;
        return (p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y);
    }

    //calculer le minimum des distances

    int MinDis(int x, int y, int w, int z) {
        if(x<=y && x<=w && x<=z) return x;
        else if(y<=x && y<=w && y<=z) return y;
        else if(w<=x && w<=y && w<=z) return w;
        else return z;
    }




    /**
     * Calcul et vérifie le déplacement du chevalier avant de la deplacer
     */
    @Override

    // d�placement intelligent 
    public void deplacer() {
    //	setCoord(new Point(getCoord().x+1,getCoord().y));
    	
    	
    	/*
    	
    	
    	
    	//Commande c = new Commande();
		int deplacement=-1;
		
		Jeu mod = Jeu.getInstance();
		if (posX < h.coord.x+3 && posX > h.coord.x-3 && posY < h.coord.y+3 && posY > h.coord.y -3){
			if(posX >= h.coord.x && posY >= h.coord.y){
				if (posX-h.coord.x >= posY-h.coord.y)deplacement = 1; //gauche
				else deplacement = 0;//haut
			}
			if (posX >= h.coord.x && posY < h.coord.y){
				if (posX-h.coord.x > h.coord.y-posY)deplacement = 1;
				else deplacement = 2;//bas
			}
			if (posX < h.coord.x && posY >= h.coord.y){
				if (h.coord.x-posX > posY-h.coord.y)deplacement = 3;//droit
				else deplacement = 0;
			}
			if (posX < h.coord.x && posY < h.coord.y){
				if (h.coord.x-posX > h.coord.y-posY)deplacement = 3;
				else deplacement = 2;
			}
		}
		else{
			deplacement = (int)(Math.random()*6);
		}
		switch (deplacement){
		case 1:
			posX--;
            dir = Direction.GAUCHE;
			break;
		case 0 :
            posY--;
            dir = Direction.HAUT;
			break;
		case 2: case 4:
            dir = Direction.BAS;
            posY++;
			break;
		case 3: case 5:
            dir = Direction.DROITE;
            posY++;
			break;
		}
	
    	
    	*/
    	
        //Random r = new Random();


       // int posX = this.getCoord().x;
        //int posY = this.getCoord().y;

        //calculer les 4 distance possible
        int dis1= Distance(h.getCoord(),new Point(getCoord().x+1,getCoord().y));
        int dis2 = Distance(h.getCoord(),new Point(getCoord().x,getCoord().y+1));
        int dis3=Distance(h.getCoord(),new Point(getCoord().x-1,getCoord().y));
        int dis4 = Distance(h.getCoord(),new Point(getCoord().x,getCoord().y-1));

        Jeu mod = Jeu.getInstance();

        //choisir le minimum des 4 distnces par rapport � la position de l'hero
        int m=MinDis(dis1,dis2,dis3,dis4);

        
        	
         
            if(m==dis1) setCoord(new Point(getCoord().x+1,getCoord().y));
            else if(m==dis2) setCoord(new Point(getCoord().x-1,getCoord().y));
            else if(m==dis3) setCoord(new Point(getCoord().x,getCoord().y+1));
            else setCoord(new Point(getCoord().x+1,getCoord().y-1)); 
    		
    	/*	 
    		switch(m) {
    		//aller vers la
    		case dis1: posX++; break;
    		//aller vers la gauche
    		case dis2: posX--; break;
    		//aller vers le haut
    		case dis3: posY++; break;
    		//aller vers le bas
    		case dis4: posY--; break;
    		
    		}
    		*/


///////////////////////////////////////////////
    	/*

        Jeu mod = Jeu.getInstance();
        Random r = new Random();
        int dep = r.nextInt(6);
        int posX = getCoord().x;
        int posY = getCoord().y;

        switch (dep) {
            //Haut
            case 0 :
                posY--;
                dir = Direction.HAUT;
                break;
                //Bas
            case 1: case 4:
                dir = Direction.BAS;
                posY++;
                break;
                //Gauche
            case 2:
                posX--;
                dir = Direction.GAUCHE;
                break;
            case 3 : case 5:
                posX++;
                dir = Direction.DROITE;
                break;
        }


*/
		/*
        if (mod.collisionEntites(this,new Point(posX,posY)) != null && mod.collisionEntites(this,new Point(posX,posY)).getType().equals("h")){
            mod.appliquerDegats(this.getDegat());
        }
*/
       
		
		//Vérification de la case du plateau si elle est libre et vérifie la collision avec d'autres entités
		/*
        if(plateau.estLibre(posX,posY) && mod.collisionEntites(this,new Point(posX,posY)) == null)
            if (posX >= 0 && posY>= 0 && posX < mod.getPlateau().getLargeur() && posY < mod.getPlateau().getHauteur())
                coord.move(posX,posY);
    }
*/

}
}


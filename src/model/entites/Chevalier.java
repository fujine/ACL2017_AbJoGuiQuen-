package model.entites;

import model.Jeu;
import model.entites.Hero;
import model.entites.Monstre;
import model.plateau.Plateau;

import java.awt.*;
import java.util.Random;

public class Chevalier extends Monstre {
	Hero h = new Hero(coord, plateau);
 int posX , posY;
 
    /**
     * Constructeur à partir d'une position et d'un plateau avec définition de la vie du chevalier
     * @param coord Coordonnée du Chevalier sur le plateau
     * @param plateau Plateau au qu'elle appartient le Chevalier
     */
    public Chevalier(Point coord, Plateau plateau) {
        super(coord, plateau);
        vie = 10;
    }
    public int distance(Point p1, Point p2) {
    	//p1=h.coord;
    	//p2=this.coord;
    	 return (int) Math.sqrt(Math.pow(p1.x-p2.x, 2)+Math.pow((p1.y-p2.y), 2));
    }
    
    //calculer le minimum des distances
    
    int minDis(int x, int y, int w, int z) {
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
        //Random r = new Random();
       
    	
       int posX = this.getCoord().x;
       int posY = this.getCoord().y;
       
       //calculer les 4 distance possible
       int dis1= distance(h.getCoord(),new Point(posX++,posY));
       int dis2 = distance(h.getCoord(),new Point(posX,posY++));
       int dis3=distance(h.getCoord(),new Point(posX--,posY));
       int dis4 = distance(h.getCoord(),new Point(posX,posY--));
       
    	 Jeu mod = Jeu.getInstance();
    	 
    	 //choisir le minimum des 4 distnces par rapport � la position de l'hero
    	 int m=minDis(dis1,dis2,dis3,dis4);
    	 
    	while(h.estVivant()) {
    		//aller vers la droite
    		if(m==dis1) posX++;
    		//aller vers la gauche
    		else if(m==dis2) posX--;
    		//aller vers le haut
    		else if(m==dis3) posY++;
    		//aller vers le bas
    		else  posY--;
    	/*	 
    		switch(m) {
    		//aller vers la droite
    		case dis1: posX++; break;
    		//aller vers la gauche
    		case dis2: posX--; break;
    		//aller vers le haut
    		case dis3: posY++; break;
    		//aller vers le bas
    		case dis4: posY--; break;
    		
    		}
    		*/
    	
    			
    		}
        
    
    	
   /* 	
       // Jeu mod = Jeu.getInstance();
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
        */
        
        //Vérification de la case du plateau si elle est libre et vérifie la collision avec d'autres entités
        if(plateau.estLibre(posX,posY) && !mod.collisionEntites(this,new Point(posX,posY)))
            coord.move(posX,posY);
    }
   
    }


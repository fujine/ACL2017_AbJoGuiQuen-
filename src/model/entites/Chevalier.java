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
    int m;
    
 // Jeu mod = Jeu.getInstance();
    




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
    
    //verifier deplacement chevalier
/*
    private boolean estAccessible (Point p) {
    	
    	 if(plateau.estLibre(p.x,p.y) && mod.collisionEntites(this,new Point(p.x,p.y)) == null)
	            if (p.x >= 0 && p.y>= 0 && p.x < mod.getPlateau().getLargeur() && p.y < mod.getPlateau().getHauteur())
	                return true;
		return false;
    }
    */
   
    public int distance(Point p1, Point p2) {
        //p1=h.coord;
        //p2=this.coord;
        return (p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y);
    }

    //calculer le minimum des distances

    private int minDis(int a, int b, int c, int d) {
    	if(a<=b && a<=c && a<=d) return 0;
    	else if(b<=a && b<=c && b<=d) return 1;
    	else if(c<=a && c<=b && c<=d) return 2;
    	else return 3;
        	
        	
       
    }

    
    /**
     * Calcul et vérifie le déplacement du chevalier avant de la deplacer
     */
    @Override

    // d�placement intelligent 
    
    
  
    /*
    public void deplacer() {
    	
    	
    	int posX=coord.x;
    	int posY=coord.y;
    	

    	 ArrayList<Point> CaseAc = new ArrayList<>();
    	 CaseAc.add(new Point(1,0));
    	 CaseAc.add(new Point(-1,0));
    	 CaseAc.add(new Point(0,1));
    	 CaseAc.add(new Point(0,-1));
    	
    	for(Point m: CaseAc) {
    		if(!plateau.estLibre(posX+m.x,posY+m.y))
    			CaseAc.remove(m);
    	}
  
    	Point min = minDis(CaseAc,posX,posY);
    	this.coord.move(posX+min.x, posY+min.y);
    
    }
    */
   

    
    public void deplacer() {
    	int posX = getCoord().x;
        int posY = getCoord().y;
    	//calculer les 4 distance possible
       int dis1= distance(h.getCoord(),new Point(posX+1,posY));
       int dis2 = distance(h.getCoord(),new Point(posX,posY+1));
       int dis3=distance(h.getCoord(),new Point(posX-1,posY));
       int dis4 = distance(h.getCoord(),new Point(posX,posY-1));

       m=minDis(dis1,dis2,dis3,dis4);

  Jeu mod = Jeu.getInstance();
        //Random r = new Random();
       
        

        switch (m) {
            //Haut
            case 0 :
                posX++;
               if( plateau.estLibre(posX++,posY)) 
                dir = Direction.DROITE;
               m=minDis(9999,dis2,dis3,dis4);
                break;
                //Bas
            case 1: 
            	posY++;
            	 if( plateau.estLibre(posX,posY++)) 
                dir = Direction.BAS;
            	 m=minDis(dis1,9999,dis3,dis4);
                break;
                //Gauche
            case 2:
                posX--;
                if( plateau.estLibre(posX--,posY))
                dir = Direction.GAUCHE;
                m=minDis(dis1,dis2,9999,dis4);
                break;
            case 3 : case 5:
                posY--;
                if( plateau.estLibre(posX,posY--))
                dir = Direction.HAUT;
                m=minDis(dis1,dis2,dis3,9999);
                break;
		
    }
        if (mod.collisionEntites(this,new Point(posX,posY)) != null && mod.collisionEntites(this,new Point(posX,posY)).getType().equals("h")){
            mod.appliquerDegats(this.getDegat());
        }
	
	 if( mod.collisionEntites(this,new Point(getCoord().x,getCoord().y)) == null)
            if (posX >= 0 && posY>= 0 && posX < mod.getPlateau().getLargeur() && posY < mod.getPlateau().getHauteur())
                coord.move(posX,posY);

}
}




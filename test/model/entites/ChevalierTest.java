package model.entites;

import model.Jeu;
import model.factory.CaseFactory;
import model.plateau.ECase;
import model.plateau.Plateau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.awt.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


public class ChevalierTest {

    private Random r;
    Plateau p;
    @BeforeEach
	void setUp() throws Exception {
    	p = new Plateau();
	}

    @Test
    void deplacerSimple() {
        for (int i = 0; i < 100000; i++) {
            
            Point coord = new Point(6,6);
            Chevalier c = new Chevalier(new Point(coord),p);
            c.deplacer();
            assertEquals(0, c.getCoord().distance(coord));
        }
    }
   

    @Test
    void deplacerCollisionMur() {
        for (int i = 0; i < 100000; i++) {
            
            p.setCase(CaseFactory.creerCase(ECase.MUR), new Point(2, 1));
            p.setCase(CaseFactory.creerCase(ECase.MUR), new Point(1, 2));
            Point coord = new Point(1, 1);
            Chevalier c = new Chevalier(new Point(coord), p);

            c.deplacer();
            assertEquals(0, c.getCoord().distance(coord));
        }
    }
    
   /*
    * tester l'IA du dépalcement du chevalier 
    */
    
    @Test
    void seRapprocherDeHero() {
     
        	Jeu mod = Jeu.getInstance();
          //  mod.getPlateau().addMonstre(c);
            Point coord1 = new Point(5,4);
            Point coord2 = new Point(7,4);
            Chevalier c = new Chevalier(new Point(coord1),p);
            
            
           
            mod.getPlateau().addMonstre(c);
            Hero h = mod.getHero();
            h.setCoord(coord2);
            mod.getMonstres().get(0).setCoord(coord1);
            p.setCase(CaseFactory.creerCase(ECase.MUR), new Point(4, 4));
            p.setCase(CaseFactory.creerCase(ECase.MUR), new Point(5, 5));
            p.setCase(CaseFactory.creerCase(ECase.MUR), new Point(5, 3));
          
            c.deplacer();
            
            
           System.out.println("x= "+c.getCoord().x+" y = "+c.getCoord().y);
            assertEquals(2, c.getCoord().distance(coord2));
           
        }
        
        
        
    
    
    @Test
    void seRapprocherDeHero1() {
        for (int i = 0; i < 10; i++) {
        	Jeu mod = Jeu.getInstance();
          //  mod.getPlateau().addMonstre(c);
            Point coord1 = new Point(5,4);
            Point coord2 = new Point(7,4);
            Chevalier c = new Chevalier(new Point(coord1),p);
            
            
           
            mod.getPlateau().addMonstre(c);
            Hero h = mod.getHero();
            h.setCoord(coord2);
            mod.getMonstres().get(0).setCoord(coord1);
            
          
            c.deplacer();
          
            assertEquals(2, c.getCoord().distance(coord2));
           
        }
        
        
        
    }
    
}
package model.entites;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import model.Jeu;
import model.factory.CaseFactory;
import model.plateau.ECase;
import model.plateau.Plateau;

class FantomeTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConstructeur() {
		Point coord = new Point();
		Plateau p = new Plateau();
		Fantome f = new Fantome(coord, p);
		
		assertEquals(1,f.vie);
		assertEquals(1,f.degat);
		assertEquals(Direction.BAS,f.dir);
		
	}


	 
	
	@Test
	void testDeplacement() {
		Point coord = new Point(4,4);
		Plateau p = new Plateau();
		Fantome f = new Fantome(coord, p);
		Jeu mod = Jeu.getInstance();
		Hero h = mod.getHero();
		p.addMonstre(f);
		h.setCoord(new Point(5,4));
		assertEquals(1,f.getCoord().distance(new Point(5,4)));
		
	}
	
	@Test
	void testDeplacementAvecMur() {
		Point coord = new Point(4,4);
		Plateau p = new Plateau();
		Fantome f = new Fantome(coord, p);
		Jeu mod = Jeu.getInstance();
		Hero h = mod.getHero();
		p.addMonstre(f);
		h.setCoord(new Point(6,4));
		p.setCase(CaseFactory.creerCase(ECase.MUR), new Point(5, 4));
		f.deplacer();
		assertEquals(2,f.getCoord().distance(new Point(6,4)));
		
	}
	
	
}

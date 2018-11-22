package model.plateau;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.plateau.objet.ObjetEscalier;

class ObjetEscalierTest {
	
	private Point coord;



	@BeforeEach
	void setUp() throws Exception {
		coord = new Point(5,5);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	
	@Test
	void testContructeurSameCoordonnee() {
		//setCoord(new Point(getX(),gety()));
		
		ObjetEscalier es = new ObjetEscalier(coord,10);
		//ImitationObjet io = new ImitationObjet();
		
		assertSame(coord, es.getCoord());
	}
	
	
	
	
	
	
	@Test
	void testContructeurSamePlateau() {
	
		ObjetEscalier es = new ObjetEscalier(coord,10);
		
		
		assertEquals(10, es.getPlateau());
	}
	
	
	
	
	
	
	@Test
	void testContructeurSameIndex() {
		//setCoord(new Point(getX(),gety()));
		
		ObjetEscalier es = new ObjetEscalier(coord,10);
		//ImitationObjet io = new ImitationObjet();
		
		assertEquals(10, es.getInfo());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	void testTypeEscalier() {
		
		
		ObjetEscalier es = new ObjetEscalier(coord,10);
		
		assertEquals(ECase.ESCALIER, es.getType());
	}
		
	
	
	@Test
	void testContructeurVideObjet() {
			
			
		Case c = new Case();
			
		assertNull(c.getObjet());
	
	}
	
	@Test
	void testAppliqerEffet() {
		ImitationObjet io = new ImitationObjet();
		
		Case c = new Case(io);
		c.appliquerEffet();
		
		assertTrue(io.isT());
	}
	
	
	
	
	

	public Point getCoord() {
		return coord;
	}

	public void setCoord(Point coord) {
		this.coord = coord;
	}

	

	
}

package model.plateau;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.plateau.objet.Objet;
import model.plateau.objet.ObjetEscalier;

class ImitationObjet extends Objet{
  private boolean t = false;
	@Override
	public void appliquerEffet() {
		setT(true) ;
		// TODO Auto-generated method stub
		
	}
	
	
	
	public ECase getType () {
		return ECase.SOL;
	}



	public boolean isT() {
		return t;
	}



	public void setT(boolean t) {
		this.t = t;
	}
	
}






class CaseTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
// test du COnstructeur 
	@Test
	void testContructeurSameObjet() {
		ImitationObjet io = new ImitationObjet();
		
		Case c = new Case(io);
		
		assertSame(io, c.getObjet());
	}
	
	@Test
	void testContructeurMemeType() {
		ImitationObjet io = new ImitationObjet();
		
		Case c = new Case(io);
		
		assertEquals(io.getType(), c.getType());
	}
	
	@Test
	void testContructeurVide() {
		
		
		Case c = new Case();
		
		assertEquals(ECase.SOL, c.getType());
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
	

}

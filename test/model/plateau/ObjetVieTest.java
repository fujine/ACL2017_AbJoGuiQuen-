package model.plateau;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Jeu;
import model.entites.Hero;
import model.plateau.objet.ObjetVie;

class ObjetVieTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	 
		public ECase getType () {
			return ECase.SOL;
		}


		
	// test du COnstructeur 
		@Test
		void testContructeurType() {
			ObjetVie ov = new ObjetVie();
		
			assertEquals(ECase.VIE, ov.getType());
		}
		
		@Test
		void testContructeurInfo() {
			ImitationObjet io = new ImitationObjet();
			
			ObjetVie ov = new ObjetVie();
			
			assertEquals(2, ov.getInfo());
		}
		
		
		
		@Test
		void testAppliqerEffet() {
			Jeu j = Jeu.getInstance();
			Hero h = j.getHero();
			h.setVie(6);
			ObjetVie ov = new ObjetVie();
			
			ov.setInfo(5);
			ov.appliquerEffet();
			
			assertEquals(1,ov.getInfo());
		}
		

		@Test
		void testAppliqerEffetHero() {
			Jeu j = Jeu.getInstance();
			Hero h = j.getHero();
			h.setVie(2);
			ObjetVie ov = new ObjetVie();
			
			ov.setInfo(3);
			ov.appliquerEffet();
			
			assertEquals(5,h.getVie());
		}
}

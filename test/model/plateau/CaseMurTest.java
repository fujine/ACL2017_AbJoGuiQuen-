package model.plateau;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.plateau.objet.Objet;

class CaseMurTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void testCaseType() {
			
			
			CaseMur cm = new CaseMur();
			
			assertEquals(ECase.MUR, cm.getType());
		}
			
		@Test
		void testContructeurVideObjet() {
				
				
			CaseMur cm = new CaseMur();
				
			assertNull(cm.getObjet());
		
		}
		
		
	}



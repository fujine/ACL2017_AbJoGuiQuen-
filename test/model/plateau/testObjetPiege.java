package model.plateau;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Jeu;
import model.entites.Direction;
import model.entites.Hero;
import model.factory.CaseFactory;
import model.plateau.objet.ObjetPiege;

class testObjetPiege {

	
	@Test
	void testConstructeur() {
		ObjetPiege op = new ObjetPiege();
		assertEquals(ECase.PIEGE,op.getType());
		assertEquals(1,op.getInfo());
		
	}
	
	
	@Test
	void testAppliquerEffet() {
		ObjetPiege op = new ObjetPiege();
		Plateau p = new Plateau();
		Jeu mod = Jeu.getInstance();
		Hero h = mod.getHero();
		p.setCase(CaseFactory.creerCase(ECase.PIEGE), new Point(5, 3));
		h.setVie(3);
		h.setCoord(new Point(5,3));
		System.out.print(h.getCoord());
		h.deplacer(new Point(5,3), Direction.DROITE);
		op.appliquerEffet();
		System.out.println("apres"+h.getCoord());
		
		assertEquals(1,h.getVie());
		assertEquals(0,op.getInfo());
		
		
	}
	

}

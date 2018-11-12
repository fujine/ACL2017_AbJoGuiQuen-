package model.entites;

import model.Jeu;
import model.factory.CaseFactory;
import model.plateau.ECase;
import model.plateau.Plateau;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.powermock.api.easymock.PowerMock;


import java.awt.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


public class ChevalierTest {

    private Random r;



    @Test
    void deplacerSimple() {
        for (int i = 0; i < 100000; i++) {
            Plateau p = new Plateau();
            Point coord = new Point(6,6);
            Chevalier c = new Chevalier(new Point(coord),p);

            c.deplacer();
            assertEquals(1, c.getCoord().distance(coord));
        }
    }

    @Test
    void deplacerCollisionMur() {
        for (int i = 0; i < 100000; i++) {
            Plateau p = new Plateau();
            p.setCase(CaseFactory.creerCase(ECase.MUR), new Point(2, 1));
            p.setCase(CaseFactory.creerCase(ECase.MUR), new Point(1, 2));
            Point coord = new Point(1, 1);
            Chevalier c = new Chevalier(new Point(coord), p);

            c.deplacer();
            assertEquals(0, c.getCoord().distance(coord));
        }
    }
}
package model.entites;

import model.plateau.Plateau;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @Test
    void deplacer() {
        Plateau p = new Plateau();
        Hero h = new Hero(new Point(5,5), p);

        h.deplacer(new Point(5,6));

        assertEquals(5,h.getCoord().x);
        assertEquals(6, h.getCoord().y);
    }

    @Test
    void deplacerCollisionMur() {
        Plateau p = new Plateau();
        Hero h = new Hero(new Point(1,1), p);

        h.deplacer(new Point(1,0));

        assertEquals(1,h.getCoord().x);
        assertEquals(1, h.getCoord().y);
    }
}
package model;

import model.entites.Monstre;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JeuTest {

    @Test
    void retirerMonstre() {
        Jeu mod = Jeu.getInstance();
        ArrayList<Monstre>liste = mod.getMonstres();
        assertEquals(2, liste.size());
        Monstre m = liste.get(0);
        mod.addCimetiere(m);
        mod.retirerMonstre();
        assertTrue(!mod.getMonstres().contains(m));
    }

    @Test
    void collisionEntitesHeroMonstre() {
        Jeu mod = Jeu.getInstance();
        mod.getHero().setCoord(new Point(5,5));
        //assertTrue(mod.collisionEntites(mod.getHero(),new Point(6,5)) instanceof Monstre);
    }

    @Test
    void collisionEntitesHeroCaseVide() {
        Jeu mod = Jeu.getInstance();
        mod.getHero().setCoord(new Point(5,5));
        mod.getMonstres().get(0).setCoord(new Point(7,5));
        //assertTrue(mod.collisionEntites(mod.getHero(),new Point(6,5)) == null);

    }

    @Test
    void collisionEntitesMonstreMonstre() {
        Jeu mod = Jeu.getInstance();
        mod.getMonstres().get(1).setCoord(new Point(5,5));
        mod.getMonstres().get(0).setCoord(new Point(6,5));
        //assertTrue(mod.collisionEntites(mod.getMonstres().get(1),new Point(6,5)) instanceof Monstre);
    }
}
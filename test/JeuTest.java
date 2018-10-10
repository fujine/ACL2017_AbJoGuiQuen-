package ACL2018_AbJoGuiQuen.test;

import ACL2018_AbJoGuiQuen.model.Jeu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JeuTest {
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void evolve() {
        Jeu j = new Jeu();

        // test déplacement sans collision
        j.evolve("b");
        assertEquals(2,j.getHero().getPosY(), "Erreur deplacement bas");
        j.evolve("h");
        assertEquals(1,j.getHero().getPosY(), "Erreur deplacement haut");
        j.evolve("d");
        assertEquals(2,j.getHero().getPosX(), "Erreur deplacement droit");
        j.evolve("g");
        assertEquals(1,j.getHero().getPosX(), "Erreur deplacement gauche");

        // test déplacement avec collision
        j.evolve("g");
        assertEquals(1,j.getHero().getPosX(), "Erreur deplacement gauche : collision");
        j.evolve("h");
        assertEquals(1,j.getHero().getPosY(), "Erreur deplacement haut : collision");
    }


}
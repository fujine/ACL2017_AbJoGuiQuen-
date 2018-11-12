
import model.Jeu;
import engine.Cmd;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class JeuTest {
    private Jeu j = Jeu.getInstance();

    @BeforeEach
    void setUp() {
        j.getHero().setCoord(new Point(1,1));
    }

    @AfterEach
    void tearDown() {
    }

    // TEST deplacement
    @Test
    void evolve_bas() {
        // test déplacement sans collision
        j.evolve(Cmd.DOWN);
        assertEquals(2,j.getHero().getCoord().x, "Erreur deplacement bas");
    }

    @Test
    void evolve_haut() {
        j.evolve(Cmd.UP);
        assertEquals(1,j.getHero().getCoord().y, "Erreur deplacement haut");
    }

    @Test
    void evolve_droite() {
        j.evolve(Cmd.RIGHT);
        assertEquals(2,j.getHero().getCoord().x, "Erreur deplacement droit");
    }

    @Test
    void evolve_gauche() {
        j.evolve(Cmd.LEFT);
        assertEquals(1,j.getHero().getCoord().x, "Erreur deplacement gauche");
    }

    // TEST  collisions
    @Test
    void evolve_gauche_colision() {
        j.evolve(Cmd.LEFT);
        assertEquals(1,j.getHero().getCoord().x, "Erreur deplacement gauche : collision");
    }

    @Test
    void evolve_haut_colision() {
        j.evolve(Cmd.UP);
        assertEquals(1,j.getHero().getCoord().y, "Erreur deplacement haut : collision");
    }

    // TEST  collisions
    @Test
    void evolve_droite_colision() {
        for (int i = j.getHero().getCoord().x; i < j.getPlateau().getLargeur()-1 ; i++){
            j.evolve(Cmd.RIGHT);
        }
        j.evolve(Cmd.RIGHT);
       assertEquals(8,j.getHero().getCoord().x, "Erreur deplacement droite : collision");
    }

    @Test
    void evolve_bas_colision() {
        for (int i = j.getHero().getCoord().y; i < j.getPlateau().getLargeur()-1 ; i++){
            j.evolve(Cmd.DOWN);
        }
        j.evolve(Cmd.DOWN);
        assertEquals(8,j.getHero().getCoord().y, "Erreur deplacement bas : collision");
    }

    // TEST debut
    @Test
    void evolve_debut() {
        assertEquals(false,j.isFinished(), "Erreur debut du jeu");
    }

    // TEST fin
    @Test
    void evolve_fin() {
        j.evolve(Cmd.END);
        assertEquals(true,j.isFinished(), "Erreur fin du jeu");
    }


}
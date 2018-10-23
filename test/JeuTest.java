
import model.Jeu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JeuTest {
    private Jeu j = Jeu.getInstance();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    // TEST deplacement
    @Test
    void evolve_bas() {
        // test déplacement sans collision
        j.evolve("b");
        assertEquals(2,j.getHero().getPosY(), "Erreur deplacement bas");
    }

    @Test
    void evolve_haut() {
        j.evolve("h");
        assertEquals(1,j.getHero().getPosY(), "Erreur deplacement haut");
    }

    @Test
    void evolve_droite() {
        j.evolve("d");
        assertEquals(2,j.getHero().getPosX(), "Erreur deplacement droit");
    }

    @Test
    void evolve_gauche() {
        j.evolve("g");
        assertEquals(1,j.getHero().getPosX(), "Erreur deplacement gauche");
    }

    // TEST  collisions
    @Test
    void evolve_gauche_colision() {
        j.evolve("g");
        assertEquals(1,j.getHero().getPosX(), "Erreur deplacement gauche : collision");
    }

    @Test
    void evolve_haut_colision() {
        j.evolve("h");
        assertEquals(1,j.getHero().getPosY(), "Erreur deplacement haut : collision");
    }

    // TEST  collisions
    @Test
    void evolve_droite_colision() {
        for (int i = j.getHero().getPosX(); i < j.getPlateau().LARGEUR-1 ; i++){
            j.evolve("d");
        }
        j.evolve("d");
       assertEquals(8,j.getHero().getPosX(), "Erreur deplacement droite : collision");
    }

    @Test
    void evolve_bas_colision() {
        for (int i = j.getHero().getPosY(); i < j.getPlateau().LARGEUR-1 ; i++){
            j.evolve("b");
        }
        j.evolve("d");
        assertEquals(8,j.getHero().getPosY(), "Erreur deplacement bas : collision");
    }

    // TEST debut
    @Test
    void evolve_debut() {
        assertEquals(false,j.isFinished(), "Erreur debut du jeu");
    }

    // TEST fin
    @Test
    void evolve_fin() {
        j.evolve("f");
        assertEquals(true,j.isFinished(), "Erreur fin du jeu");
    }


}
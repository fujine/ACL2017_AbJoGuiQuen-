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

    }
}
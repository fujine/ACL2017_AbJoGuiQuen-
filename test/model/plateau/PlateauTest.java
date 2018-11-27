package model.plateau;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @Test
    void estLibre() {
        Plateau p = new Plateau();
        assertTrue(p.estLibre(new Point(1,1)));
        assertTrue(!p.estLibre(new Point(0,0)));
    }
}
package model.plateau;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @Test
    void estLibre() {
        Plateau p = new Plateau();
        assertTrue(p.estLibre(1,1));
        assertTrue(!p.estLibre(0,0));
    }
}
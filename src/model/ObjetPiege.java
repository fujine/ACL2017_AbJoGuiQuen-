package model;

public class ObjetPiege implements Objet {

    @Override
    public void appliquerEffet() {
        Jeu.getInstance().appliquerDegats(2);
    }

}

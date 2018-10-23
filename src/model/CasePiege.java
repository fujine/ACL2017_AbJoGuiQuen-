package model;

public class CasePiege implements Case {


    @Override
    public boolean estTraversable() {
        return true;
    }

    @Override
    public void appliquerEffet() {
        Jeu.getInstance().appliquerDegats(2);
    }

}

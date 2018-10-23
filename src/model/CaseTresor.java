package model;

public class CaseTresor implements Case {

    @Override
    public boolean estTraversable() {
        return true;
    }

    @Override
    public void appliquerEffet() {
        Jeu.getInstance().setFini(true);
    }
}

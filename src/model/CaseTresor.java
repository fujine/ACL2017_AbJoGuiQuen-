package model;

public class CaseTresor implements ICase {

    @Override
    public boolean estTraversable() {
        return true;
    }

    @Override
    public void appliquerEffet() {
        Jeu.getInstance().setFini(true);
    }
}

package model.plateau;

public class CaseMur  implements ICase {

    @Override
    public boolean estTraversable() {
        return false;
    }

    @Override
    public void appliquerEffet() {
    }
}
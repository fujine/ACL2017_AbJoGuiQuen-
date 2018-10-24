package model.plateau;

public class CaseMur  implements ICase {

    @Override
    public ECase getType() {
        return ECase.MUR;
    }

    @Override
    public boolean estTraversable() {
        return false;
    }

    @Override
    public void appliquerEffet() {
    }
}

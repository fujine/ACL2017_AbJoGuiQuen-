package model.plateau;

public class CaseVide implements ICase {



    @Override
    public ECase getType() { return ECase.VIDE; }

    @Override
    public boolean estTraversable() {
        return false;
    }

    @Override
    public void appliquerEffet() {

    }
}

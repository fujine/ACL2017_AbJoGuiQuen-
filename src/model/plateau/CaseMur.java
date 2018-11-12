package model.plateau;

public class CaseMur  implements ICase {

    @Override
    public ECase getType() {
        return ECase.MUR;
    }

    /**
     *
     * @return false car un mur ne peut pas être traversé
     */
    @Override
    public boolean estTraversable() {
        return false;
    }

    /**
     * N'applique aucun effet.
     */
    @Override
    public void appliquerEffet() {
    }
}

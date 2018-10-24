package model.plateau;

public interface ICase {
    ECase getType();
    boolean estTraversable();
    void appliquerEffet();
}

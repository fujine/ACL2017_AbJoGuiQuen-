package model.factory;

        import model.plateau.Case;
        import model.plateau.CaseMur;
        import model.plateau.ICase;
        import model.plateau.ECase;

public class CaseFactory {
    public static ICase creerCase(ECase type) {
        switch (type) {
            case MUR:
                return new CaseMur();
            case VIDE:
                return new Case();
            case PIEGE:
                return new Case(ObjetFactory.creerObjet(type));
            case TRESOR:
                return new Case(ObjetFactory.creerObjet(type));
            default:
                return null;
        }
    }
}

package model.factory;

        import model.plateau.Case;
        import model.plateau.CaseMur;
        import model.plateau.ICase;
        import model.plateau.objet.ObjetPiege;

public class CaseFactory {
    public static final int MUR = 0;
    public static final int CASEVIDE = 1;
    public static final int CASEPIEGE = 2;
    public static final int CASETRESOR = 3;
    public static ICase creerCase(int type) {
        switch (type) {
            case MUR:
                return new CaseMur();
            case CASEVIDE:
                return new Case();
            case CASEPIEGE:
                return new Case(ObjetFactory.creerObjet(ObjetFactory.PIEGE));
            case CASETRESOR:
                return new Case(ObjetFactory.creerObjet(ObjetFactory.TRESOR));
            default:
                return null;
        }
    }
}

package model.factory;

        import model.plateau.Case;
        import model.plateau.CaseMur;
        import model.plateau.ICase;
        import model.plateau.ECase;
        import model.plateau.objet.Objet;

        import java.util.ArrayList;

public class CaseFactory {
    /**
     * Créer un case en fonction d'un type donné
     * @param type de case
     * @return la case correspondant au type
     */
    public static ICase creerCase(ECase type) {
        switch (type) {
            case MUR:
                return new CaseMur();
            case SOL:
                return new Case();
            case PIEGE:
                return new Case(ObjetFactory.creerObjet(type));
            case TRESOR:
                return new Case(ObjetFactory.creerObjet(type));
            case VIE:
                return new Case(ObjetFactory.creerObjet(type));
            default:
                return new Case();
        }
    }

    /**
     * Créer un case en fonction d'un type donné et de paramètre pour la création d'objet
     * @param type de case
     * @param arguments parametre pour la création d'objet
     * @return la case avec l'objet correspondant
     */
    public static ICase creerCase(ECase type, Object ... arguments) {
        switch (type) {
            case TELEPORTEUR:
                return new Case(ObjetFactory.creerObjet(type,arguments));
            case ESCALIER:
                return new Case(ObjetFactory.creerObjet(type, arguments));
            default:
                return new Case();
        }
    }
}

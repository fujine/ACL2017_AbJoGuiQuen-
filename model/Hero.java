package ACL2018_AbJoGuiQuen.model;

public class Hero {
    private int posX = 1;
    private int posY = 1;

    private Plateau plateau = new Plateau();

    /**
     * Déplace le héro vers le haut
     */
    public void deplacementHaut() {
        //Nouvelle ordonnee
        int newY = posY-1;
        if (plateau.estLibre(posX,newY))
            posY = newY;
    }

    /**
     * Déplace le héro vers le bas
     */
    public void deplacementBas() {
        //Nouvelle ordonnee
        int newY = posY+1;
        if (plateau.estLibre(posX,newY))
            posY = newY;
    }

    /**
     * Déplace le héro vers la gauche
     */
    public void deplacementGauche() {
        //Nouvelle ordonnee
        int newX = posX-1;
        if (plateau.estLibre(newX,posY))
            posX = newX;
    }

    /**
     * Déplace le héro vers la droite
     */
    public void deplacementDroit() {
        //Nouvelle ordonnee
        int newX = posX+1;
        if (plateau.estLibre(newX,posY))
            posX = newX;
    }

    /**
     * @return Position en x du héro
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posX Nouvelle abscisse du héro
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * @return Position en y du héro
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY Nouvelle ordonnée du héro
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * @return Retourne le plateau sur le quelle se trouve le héro
     */
    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * @param plateau Nouveau plateau de jeu
     */
    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }
}

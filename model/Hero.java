package ACL2018_AbJoGuiQuen.model;

public class Hero {
    private int posX = 1;
    private int posY = 1;

    private Plateau plateau = new Plateau();

    public Hero() {
    }

    public void deplacementHaut() {
        //Nouvelle ordonnee
        int newY = posY-1;
        if (plateau.estLibre(posX,newY))
            posY = newY;
    }

    public void deplacementBas() {
        //Nouvelle ordonnee
        int newY = posY+1;
        if (plateau.estLibre(posX,newY))
            posY = newY;
    }

    public void deplacementGauche() {
        //Nouvelle ordonnee
        int newX = posX-1;
        if (plateau.estLibre(newX,posY))
            posX = newX;
    }

    public void deplacementDroit() {
        //Nouvelle ordonnee
        int newX = posX+1;
        if (plateau.estLibre(newX,posY))
            posX = newX;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }
}

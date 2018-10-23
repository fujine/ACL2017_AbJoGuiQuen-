package model;

public class Hero {
    private int posX = 1;
    private int posY = 1;

    private Jeu jeu;

    public Hero(Jeu jeu) {
        this.jeu = jeu;
    }

    public void deplacer(int x, int y) {
        posX = x;
        posY = y;
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
}

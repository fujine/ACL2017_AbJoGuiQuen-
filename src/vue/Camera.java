package vue;

import model.Jeu;
import model.entites.Hero;

import java.awt.*;

public class Camera {
    private int largeurEcran;
    private int portee;
    private int[] limX = new int[2];
    private int[] limY = new int[2];

    public Camera() {
        largeurEcran = 11;
        portee = (largeurEcran-1)/2;
    }

    public void setLimites(Hero hero) {
        if(hero.getCoord().x - portee * Jeu.ECHELLE < 0) {
            limX[0] = 0;
            limX[1] = largeurEcran*Jeu.ECHELLE;
        } else if (hero.getCoord().x + (portee+1) *Jeu.ECHELLE > hero.getPlateau().getLargeur()){
            limX[0] = hero.getPlateau().getLargeur()-1;
            limX[1] = hero.getPlateau().getLargeur()-1 - largeurEcran*Jeu.ECHELLE;
        } else {
            limX[0] = hero.getCoord().x - largeurEcran/2 * Jeu.ECHELLE;
            limX[1] = limX[0] + largeurEcran*Jeu.ECHELLE;
        }
        if(hero.getCoord().y - portee * Jeu.ECHELLE < 0) {
            limY[0] = 0;
            limY[1] = largeurEcran*Jeu.ECHELLE;
        } else if (hero.getCoord().y + (portee+1) * Jeu.ECHELLE > hero.getPlateau().getLargeur()){
            limY[0] = hero.getPlateau().getLargeur()-1;
            limY[1] = hero.getPlateau().getLargeur()-1 - largeurEcran*Jeu.ECHELLE;
        } else {
            limY[0] = hero.getCoord().y - largeurEcran/2 * Jeu.ECHELLE;
            limY[1] = limY[0] + largeurEcran*Jeu.ECHELLE;
        }
    }

    public int getLimXInf() {
        return limX[0];
    }

    public int getLimXSup() {
        return limX[1];
    }

    public int getLimYInf() {
        return limY[0];
    }

    public int getLimYSup() {
        return limY[1];
    }

    public boolean dansCadre(Point coord) {
        if (coord.x + Jeu.ECHELLE >= getLimXInf() && coord.x+ Jeu.ECHELLE <= getLimXSup() && coord.y + Jeu.ECHELLE>= getLimYInf() && coord.y+ Jeu.ECHELLE <= getLimYSup())
            return true;
        return false;
    }
}

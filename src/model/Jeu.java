package model;

import engine.Cmd;
import engine.Game;
import model.plateau.Plateau;

import java.awt.*;

public class Jeu implements Game {

    private static Jeu instance;
    private boolean fini = false;
    private Hero hero = new Hero();
    private Plateau plateau;

    private Jeu() {
        plateau = LectureFichier.lireFichier("src\\ACL2018_AbJoGuiQuen\\src\\plateau1.txt");
    };

    public void modifierPlateau() {
        plateau.modifierCase();
    }

    public static Jeu getInstance() {
        if(instance == null)
            instance = new Jeu();
        return instance;
    }

    /**
     * @param cmd Input de l'utilisateur
     */
    public void evolve(Cmd cmd){
        int x = hero.getPosX();
        int y = hero.getPosY();
        switch (cmd){
            case UP :
                y--;
                break;
            case DOWN :
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
            case END:
                this.fini = true;
                break;
        }
        if(cmd != Cmd.IDLE && plateau.estLibre(x,y)) {
            hero.deplacer(x, y);
            plateau.appliquerEffetCase(x,y);
        }

    }

    public boolean isFini() {
        return fini;
    }

    public void setFini(boolean fini) {
        this.fini = fini;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    @Override

    /**
     * @return true si partie terminée, false sinon
     */
    public boolean isFinished(){
        return this.fini;
    }

    /**
     * @return Le hero lié au jeu
     */
    public Hero getHero(){
        return this.hero;
    }

    public void estMort() {
        fini = true;
    }

    public void appliquerDegats(int nbDegats) {
        hero.subirDegat(nbDegats);
    }

    public boolean verifLibre(Point coord) {
        return plateau.estLibre(coord.x,coord.y);
    }
}

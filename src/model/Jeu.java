package model;

import engine.Cmd;
import engine.Game;
import model.entites.Chevalier;
import model.entites.Entites;
import model.entites.Hero;
import model.entites.Monstre;
import model.plateau.Plateau;

import java.awt.*;
import java.util.ArrayList;

public class Jeu implements Game {

    private static Jeu instance;
    private boolean fini = false;
    private Hero hero;
    private ArrayList<Monstre> monstres;
    private ArrayList<Monstre> cimetiere;
    private Plateau plateau;
    private int compteur;

    private Jeu() {
        plateau = new Plateau();
        hero = new Hero(new Point(1,1),plateau);
        monstres = new ArrayList<>();
        cimetiere = new ArrayList<>();
        monstres.add(new Chevalier(new Point(4,4),plateau));
        monstres.add(new Chevalier(new Point(2,4),plateau));
        compteur = 0;
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
        int x = hero.getCoord().x;
        int y = hero.getCoord().y;
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
        if(cmd != Cmd.IDLE) {
            hero.deplacer(new Point(x,y));
        }
        retirerMonstre();
        compteur++;
        if(compteur == 3) {
            deplacerMonstre();
            compteur = 0;
        }

    }

    public void addCimetiere(Monstre m) {
        cimetiere.add(m);
    }

    public void retirerMonstre() {
        for(Monstre m : cimetiere){
            monstres.remove(m);
        }
    }

    public boolean collisionEntites(Entites e, Point coord) {
        if(!e.equals(hero) && coord.equals(hero.getCoord()))
            return true;
        for(Monstre m : monstres)
            if(!e.equals(m) && coord.equals(m.getCoord()))
                return true;
        return false;
    }

    public ArrayList<Monstre> getMonstres() {
        return monstres;
    }

    public void setMonstres(ArrayList<Monstre> monstres) {
        this.monstres = monstres;
    }

    public void deplacerMonstre() {
        for( Monstre m : monstres) {
            m.deplacer();
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

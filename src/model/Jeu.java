package model;

import com.sun.jndi.toolkit.url.Uri;
import engine.Cmd;
import engine.Game;
import model.entites.*;
import model.plateau.Plateau;

import java.awt.*;
import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class Jeu implements Game {

	/**
     * Instance de Jeu pour Singleton
     */
    private static Jeu instance;

    /**
     * boolean pour la jeu si terminé
     */
    private boolean fini = false;

    /**
     * Hero qui interagit avec le joueur
     */
    private Hero hero;

    private ArrayList<Plateau> donjon;

    private int plateauCourant;

    /**
     * Liste des monstres morts
     */
    private ArrayList<Monstre> cimetiere;

    /**
     * Plateau du jeu sur le quelle le joueur se deplace
     */
    private Plateau plateau;

    /**
     * Timer pour r�duire le temps des autres actions
     */
    private int compteur;

    public static int ECHELLE = 80;
    public static int TAILLE = 50;

    /**
     * Constructeur du jeu par defaut qui instancie un plateau un héro et des monstre par defaut.
     */
    private Jeu() {
        donjon = new ArrayList<>();

        InputStream url = null;

        try {
            String[] ch = {"plateau0.csv","plateau1.csv","plateau2.csv",};
            for (String path : ch) {
                url = getClass().getResourceAsStream("/Ressources/plateau/"+path);
                plateau = LectureFichier.lireFichier(url);
                donjon.add(plateau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        donjon.add(new Plateau());
        plateauCourant = 0;

        hero = new Hero(new Point(80,80),getPlateau());
        cimetiere = new ArrayList<>();
        compteur = 0;
    };

    /**
     * Methode pour ajouter par defaut des cases specifique. principalement pour test
     */
    public void modifierCasePlateau() {
        plateau.modifierCase();
    }

    public void changerPlateau(int index) {
        plateauCourant = index;
        hero.setPlateau(donjon.get(plateauCourant));
    }

    /**
     * Recuperation d'une instance unique de jeu : Singleton
     * @return instance de Jeu
     */
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
        int vitesse = hero.getVitesse();
        Direction dir = null;
        switch (cmd){
            case UP :
                y-=vitesse;
                dir = Direction.HAUT;
                break;
            case DOWN :
                y+=vitesse;
                dir = Direction.BAS;
                break;
            case LEFT:
                dir = Direction.GAUCHE;
                x-=vitesse;
                break;
            case RIGHT:
                dir = Direction.DROITE;
                x+=vitesse;
                break;
            case END:
                this.fini = true;
                break;
            case ATTAQUE:
                hero.attaquer();
        }
        // si une touche de direction enfonc� on d�place le Hero
        if(dir != null) {
            hero.deplacer(new Point(x,y),dir);
        }



        //Retire les monstre mort de la liste des monstre
        retirerMonstre();

        //Utilisation du compteur pour ralentir par 3 la vitesse de deplacement des monstre par apport au hero
        compteur++;
        if(compteur == 3) {
            deplacerMonstre();
            compteur = 0;
        }

    }

    /**
     * Ajout d'un monstre dans le cimetiere au moment de sa mort
     * @param m Monstre tu�
     */
    public void addCimetiere(Monstre m) {
        cimetiere.add(m);
    }

    /**
     * Retire les monstre pr�sent dans le cimetiere de la liste des monstres pr�sent dans le jeu
     */
    public void retirerMonstre() {
        for(Monstre m : cimetiere){
            donjon.get(plateauCourant).getMonstres().remove(m);
        }
    }

    /**
     * Verification d'une collision entre 2 entite
     * @param e Entite en mouvement
     * @param coord future coordonnees dans l'entite
     * @return une entite si collision, null sinon
     */
    public Entites collisionEntites(Entites e, Rectangle coord) {

        //V�rifie collision avec le hero

        //Vérifie collision avec le hero

        if(!e.equals(hero) && coord.intersects(hero.getSurfaceCollision()))
            return hero;

        //V�rifie Collision avec les monstres
        for(Monstre m : donjon.get(plateauCourant).getMonstres())
            if(!e.equals(m) && coord.intersects(m.getSurfaceCollision()))
                return m;
        return null;
    }


    public ArrayList<Monstre> getMonstres() {
        return donjon.get(plateauCourant).getMonstres();
    }


    /**
     * Effectue le deplacement de tout les monstre pr�sent dans le jeu
     */
    public void deplacerMonstre() {
        for( Monstre m : donjon.get(plateauCourant).getMonstres()) {
            m.deplacer();
        }
    }

    public boolean isFini() {
        return fini;
    }

    public void setFini(boolean fini) {
        this.fini = fini;
    }

    public int getIndex() {
        return plateauCourant;
    }

    public Plateau getPlateau() {
        return donjon.get(plateauCourant);
    }


    /**
     * @return true si partie termin�e, false sinon
     */
    @Override
    public boolean isFinished(){
        return this.fini;
    }

    /**
     * @return Le hero li� au jeu
     */
    public Hero getHero(){
        return this.hero;
    }

    /**
     * Quand le hero meurt le Jeu est termin�
     */
    public boolean estMort() {
        if (hero.getVie() <=0)
            return true;
        return false;
    }

    /**
     * Fait subir des d�gats aux h�ro
     * @param nbDegats
     */
    public void appliquerDegats(int nbDegats) {
        hero.subirDegat(nbDegats);
    }

    /**
     * V�rifie que la case au coordonn�e est libre
     * @param coord Coordonn�es de la case
     * @return true si traversable false sinon
     */
    public boolean verifLibre(Point coord) {
        return plateau.estLibre(coord);
    }
}

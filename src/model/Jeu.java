package model;

import engine.Cmd;
import engine.Game;
import model.entites.*;
import model.plateau.Plateau;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
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
     * Timer pour réduire le temps des autres actions
     */
    private int compteur;

    public static int ECHELLE = 80;
    public static int TAILLE = 50;

    /**
     * Constructeur du jeu par défaut qui instancie un plateau un héro et des monstre par défaut.
     */
    private Jeu() {
        donjon = new ArrayList<>();

        URI uri = null;

        try {
            File file = new File(getClass().getResource("/Ressources/plateau").toURI());
            System.out.println(file.list()[0]);
            for (String path : file.list()) {
                uri = getClass().getResource("/Ressources/plateau/"+path).toURI();
                plateau = LectureFichier.lireFichier(uri);
                donjon.add(plateau);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        plateau = LectureFichier.lireFichier(uri);
        donjon.add(new Plateau());
        plateauCourant = 0;

        hero = new Hero(new Point(320,80),getPlateau());
        cimetiere = new ArrayList<>();
        donjon.get(plateauCourant).addMonstre(new Chevalier(new Point(80,160),getPlateau()));
        compteur = 0;
    };

    /**
     * Méthode pour ajouter par défaut des cases spécifique. principalement pour test
     */
    public void modifierCasePlateau() {
        plateau.modifierCase();
    }

    public void changerPlateau(int index) {
        plateauCourant = index;
        hero.setPlateau(donjon.get(plateauCourant));
    }

    /**
     * Récupération d'une instance unique de jeu : Singleton
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
        // si une touche de direction enfoncé on déplace le Hero
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
     * @param m Monstre tué
     */
    public void addCimetiere(Monstre m) {
        cimetiere.add(m);
    }

    /**
     * Retire les monstre présent dans le cimetiere de la liste des monstres présent dans le jeu
     */
    public void retirerMonstre() {
        for(Monstre m : cimetiere){
            donjon.get(plateauCourant).getMonstres().remove(m);
        }
    }

    /**
     * Vérification d'une collision entre 2 entité
     * @param e Entité en mouvement
     * @param coord future coordonnées dans l'entité
     * @return une entité si collision, null sinon
     */
    public Entites collisionEntites(Entites e, Rectangle coord) {

        //Vérifie collision avec le hero

        //VÃ©rifie collision avec le hero

        if(!e.equals(hero) && coord.intersects(hero.getSurfaceCollision()))
            return hero;

        //Vérifie Collision avec les monstres
        for(Monstre m : donjon.get(plateauCourant).getMonstres())
            if(!e.equals(m) && coord.intersects(m.getSurfaceCollision()))
                return m;
        return null;
    }


    public ArrayList<Monstre> getMonstres() {
        return donjon.get(plateauCourant).getMonstres();
    }


    /**
     * Effectue le deplacement de tout les monstre présent dans le jeu
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
     * @return true si partie terminée, false sinon
     */
    @Override
    public boolean isFinished(){
        return this.fini;
    }

    /**
     * @return Le hero lié au jeu
     */
    public Hero getHero(){
        return this.hero;
    }

    /**
     * Quand le hero meurt le Jeu est terminé
     */
    public boolean estMort() {
        if (hero.getVie() <=0)
            return true;
        return false;
    }

    /**
     * Fait subir des dégats aux héro
     * @param nbDegats
     */
    public void appliquerDegats(int nbDegats) {
        hero.subirDegat(nbDegats);
    }

    /**
     * Vérifie que la case au coordonnée est libre
     * @param coord Coordonnées de la case
     * @return true si traversable false sinon
     */
    public boolean verifLibre(Point coord) {
        return plateau.estLibre(coord);
    }
}

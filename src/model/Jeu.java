package model;

import engine.Cmd;
import engine.Game;
import model.entites.*;
import model.plateau.Plateau;

import java.awt.*;
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

    /**
     * Constructeur du jeu par défaut qui instancie un plateau un héro et des monstre par défaut.
     */
    private Jeu() {
        URI uri = null;
        try {
            uri = getClass().getResource("plateau1.txt").toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        plateau = LectureFichier.lireFichier(uri);
        donjon = new ArrayList<>();
        donjon.add(plateau);
        donjon.add(new Plateau());
        plateauCourant = 0;

        hero = new Hero(new Point(1,1),plateau);
        cimetiere = new ArrayList<>();
        donjon.get(plateauCourant).addMonstre(new Chevalier(new Point(4,4),plateau));
        donjon.get(plateauCourant).addMonstre(new Chevalier(new Point(13,13),plateau));
        compteur = 0;
    };

    /**
     * Méthode pour ajouter par défaut des cases spécifique. principalement pour test
     */
    public void modifierCasePlateau() {
        plateau.modifierCase();
    }

    public void changerPlateaur(int index) {
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
        Direction dir = null;
        switch (cmd){
            case UP :
                y--;
                dir = Direction.HAUT;
                break;
            case DOWN :
                y++;
                dir = Direction.BAS;
                break;
            case LEFT:
                dir = Direction.GAUCHE;
                x--;
                break;
            case RIGHT:
                dir = Direction.DROITE;
                x++;
                break;
            case END:
                this.fini = true;
                break;
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
    public Entites collisionEntites(Entites e, Point coord) {
        //Vérifie collision avec le hero
        if(!e.equals(hero) && coord.equals(hero.getCoord()))
            return hero;

        //Vérifie Collision avec les monstres
        for(Monstre m : donjon.get(plateauCourant).getMonstres())
            if(!e.equals(m) && coord.equals(m.getCoord()))
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
    public void estMort() {
        fini = true;
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
        return plateau.estLibre(coord.x,coord.y);
    }
}

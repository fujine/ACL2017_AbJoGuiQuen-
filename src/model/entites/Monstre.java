package model.entites;

import model.Jeu;
import model.plateau.Plateau;
import java.util.Random;

import java.awt.*;

public abstract class Monstre extends Entites {
    /**
     * nombre de déplacement à faire lorsque le hero n'est pas proche
     */
    protected int deplacementRestant;

    /**
     * distance où le monstre peut repérer le hero
     */
    protected int distanceObservation;

    protected long timer;
    

	/**
     * Constructeur a partir d'une position et d'un plateau
     *
     * @param coord   Coordonnee du Monstre sur le plateau
     * @param plateau Plateau au qu'elle appartient le Monstre
     */
    public Monstre(Point coord, Plateau plateau) {
        super(coord, plateau);
    }

    /**
     * Calcul et verifie le deplacement du Monstre avant de la deplacer
     */
    public void deplacer(){
        Jeu mod = Jeu.getInstance();
        Point posM = this.getCoord();
        Point posH = new Point(mod.getHero().getCoord());
        double dist = posM.distance(posH);

        if (dist > this.distanceObservation*Jeu.ECHELLE){
            deplacementAlea(mod);
        }else{
            suivreHero(dist,mod,posM,posH);
        }
    }

    /**
     * Permet à la methode deplacer de suivre le hero
     * 
     */
    private void suivreHero(double dist,Jeu mod, Point posM, Point posH){
        
        Direction[] tab = {Direction.HAUT, Direction.BAS,Direction.GAUCHE,Direction.DROITE};

        for (int i = 0; i < 4 ; i++){
            Direction choix = tab[i];
            int x = coord.x;
            int y = coord.y;

            switch (choix) {
                case HAUT:
                    y-=vitesse;
                    break;
                case BAS:
                    y+=vitesse;
                    break;
                case GAUCHE:
                    x-=vitesse;
                    break;
                case DROITE:
                    x+=vitesse;
                    break;
            }

            //Test d'éloignement
            if(dist> posH.distance(new Point(x, y))) {
                //Test Collision mur
                Point coordBG = new Point(x,y+Jeu.TAILLE-1);
                Point coordBD = new Point(x + Jeu.TAILLE-1, y + Jeu.TAILLE-1);
                Point coordHG = new Point(x,y+Jeu.TAILLE + 1 - Jeu.ECHELLE/4);
                Point coordHD = new Point(x + Jeu.TAILLE-1,y+Jeu.TAILLE-1- Jeu.ECHELLE/4);
                Rectangle colli = new Rectangle(coordHG,new Dimension(Jeu.TAILLE,Jeu.ECHELLE/4));
                Entites e = mod.collisionEntites(this,colli);
                if(e == null) {
                    if (x >= 0 && y >= 0 && x < mod.getPlateau().getLargeur() && y < mod.getPlateau().getHauteur()) {
                        coord.move(x, y);
                        dir = choix;
                        i = 4;
                    }
                }  else if (e instanceof Hero) {
                    if (timer == 0) {
                        e.subirDegat(this.degat);
                        timer = System.currentTimeMillis();
                    } else {
                        if(System.currentTimeMillis() - timer > 1000) {
                            timer = 0;
                        }
                    }
                }
            }

        }
    
    }

    /**
     * Effectue un deplacement aléatoire
     * 
     */
    protected void deplacementAlea(Jeu mod){

        int posX = getCoord().x;
        int posY = getCoord().y;

        //on deplace le monstre si il a encore déplacements à faire
        if (deplacementRestant > 0){
            if (dir == Direction.HAUT){
                posY-=vitesse;
            }
            if (dir == Direction.BAS){
                posY+=vitesse;
            }
            if (dir == Direction.GAUCHE){
                posX-=vitesse;
            }
            if (dir == Direction.DROITE){
                posX+=vitesse;
            }
            if (canMove(posX, posY))
                coord.move(posX, posY);
            // on décrement le nombre de déplacement restant même si le monstre n'a pas bougé
            // sinon il resterait bloqué contre un mur 
            deplacementRestant--;
        }else{
            resetDeplacementAlea();
        }
    }

    /**
     * Teste si le monstre peut bougé à une position 
     * @param posX
     * @param posY
     * @return true si le montre peut aller à cette position false sinon
     */
    protected abstract boolean canMove(int posX, int posY);

    /**
     * Methode qui génère une direction et un nombre de déplacement à faire aléatoirement
     */
    protected void resetDeplacementAlea(){
        Random r = new Random();
        deplacementRestant = r.nextInt(10)+2;
        r = new Random();
        int d = r.nextInt(4);

        if(d <=0){
            dir = Direction.HAUT;
        }
        else if(d<= 1){
            
            dir = Direction.BAS;
        }
        else if (d<=2){
            dir = Direction.GAUCHE;
        }
        else {
            dir = Direction.DROITE;
        }
    }

    @Override
    public void subirDegat(int degat) {
        super.subirDegat(degat);
        estMort();
    }

    /**
     * Verifie si le Monstre est vivant et applique un effet sinon
     */
    public void estMort() {
        if (vie <= 0)
            Jeu.getInstance().addCimetiere(this);
    }

    public String getType() {
        return "m";
    }

    public int getDegat() {
        return this.degat;
    }
}

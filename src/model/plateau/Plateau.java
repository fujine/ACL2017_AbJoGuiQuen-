package model.plateau;

import model.Jeu;
import model.entites.Chevalier;
import model.entites.Monstre;
import model.factory.CaseFactory;

import java.awt.*;
import java.util.ArrayList;

public class Plateau {
    private ICase plateau[][];
    private ArrayList<Monstre> monstres;
    private int hauteur;
    private int largeur;


    /**
     * Constructeur par défaut avec mur sur les bordures
     */
    public Plateau() {
        largeur = 15*Jeu.ECHELLE;
        hauteur = 15*Jeu.ECHELLE;
        monstres = new ArrayList<>();
        plateau = new ICase[hauteur][largeur];

        //Génère un tableau avec un mur sur les 4 bordures
        for(int i = 0; i < hauteur; i++) {
            for(int j = 0; j < largeur; j++) {
                if(i == 0 || i == hauteur - 1)
                    plateau[i][j] = CaseFactory.creerCase(ECase.MUR);
                else {
                    if(j == 0 || j == largeur-1)
                        plateau[i][j] = CaseFactory.creerCase(ECase.MUR);
                    else
                        plateau[i][j] = CaseFactory.creerCase(ECase.SOL);
                }
            }
        }

        modifierCase();
    }

    public void addMonstre(Monstre m) {
        if(!horsPlateau(m.getCoord()))
            monstres.add(m);
    }

    public ArrayList<Monstre> getMonstres() {
        return monstres;
    }

    public void setMonstres(ArrayList<Monstre> monstres) {
        this.monstres = monstres;
    }

    public Plateau(ICase[][] p){
        this.plateau = p;
        this.hauteur = this.plateau.length*Jeu.ECHELLE;
        this.largeur = this.plateau[0].length*Jeu.ECHELLE;
        monstres = new ArrayList<>();

    }

    /**
     * Vérifie que la case est vide
     * @return true si case vide, false sinon
     */
    public boolean estLibre(Point coord) {
        int coordX = coord.x/Jeu.ECHELLE;
        int coordY = coord.y/Jeu.ECHELLE;
        return plateau[coordX][coordY].estTraversable();
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setCase(ICase c, Point coord) {
        plateau[coord.x][coord.y] = c;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    /**
     * Ajoute des cases spécifiques au plateau
     */
    public void modifierCase() {
        plateau[3][1] = CaseFactory.creerCase(ECase.PIEGE);
        plateau[6][5] = CaseFactory.creerCase(ECase.MUR);
        plateau[5][5] = CaseFactory.creerCase(ECase.MUR);
        plateau[4][8] = CaseFactory.creerCase(ECase.VIE);
        plateau[8][8] = CaseFactory.creerCase(ECase.TELEPORTEUR,new Point(160,80));
        plateau[2][1] = CaseFactory.creerCase(ECase.TELEPORTEUR,new Point(640,80));
        plateau[9][9] = CaseFactory.creerCase(ECase.ESCALIER, new Point(80,80),0);
        //addMonstre(new Chevalier(new Point(5,6),this));
        //addMonstre(new Chevalier(new Point(8,7),this));
    }

    public ICase getCase(Point coord) {
        Point coordPlateau = new Point(coord.x/Jeu.ECHELLE, coord.y/Jeu.ECHELLE);
        return plateau[coordPlateau.x][coordPlateau.y];
    }

    public boolean horsPlateau(Point coord) {
        Point coordPlateau = new Point(coord.x/Jeu.ECHELLE, coord.y/Jeu.ECHELLE);
        if(coordPlateau.x < 0 || coordPlateau.x >= plateau.length || coordPlateau.y < 0 || coordPlateau.y >= plateau[0].length)
            return true;
        return false;
    }

    public void appliquerEffetCase(Point coord) {
        Point coordPlateau = new Point(coord.x/Jeu.ECHELLE, coord.y/Jeu.ECHELLE);
        plateau[coordPlateau.x][coordPlateau.y].appliquerEffet();
    }

    public ECase getType(Point coord){
        Point coordPlateau = new Point(coord.x/Jeu.ECHELLE, coord.y/Jeu.ECHELLE);
        if(coordPlateau.x >= plateau.length || coordPlateau.y >= plateau[0].length)
            return ECase.MUR;
        return plateau[coordPlateau.x][coordPlateau.y].getType();
    }

    /**
     * Affiche dans la console la visualisation graphique du plateau
     */
    public void affichageText() {
        for(int i = 0; i < hauteur; i++) {
            for(int j = 0; j < largeur; j++) {
                if(plateau[i][j].estTraversable())
                    System.out.print("X");
                else
                    System.out.print("O");
            }
            System.out.println();
        }
    }
}

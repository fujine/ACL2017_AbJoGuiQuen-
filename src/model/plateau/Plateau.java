package model.plateau;

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
        largeur = 15;
        hauteur = 15;
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
        this.hauteur = this.plateau[0].length;
        this.largeur = this.plateau[0].length;
        monstres = new ArrayList<>();

    }

    /**
     * Vérifie que la case est vide
     * @param posx abscisse de la case
     * @param posy ordonnée de la case
     * @return true si case vide, false sinon
     */
    public boolean estLibre(int posx, int posy) {
        return plateau[posx][posy].estTraversable();
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
        plateau[8][8] = CaseFactory.creerCase(ECase.TELEPORTEUR,new Point(2,1));
        plateau[2][1] = CaseFactory.creerCase(ECase.TELEPORTEUR,new Point(8,1));
        plateau[9][9] = CaseFactory.creerCase(ECase.ESCALIER, new Point(1,1),0);
    }

    public boolean horsPlateau(Point p) {
        if(p.x < 0 || p.x >= plateau.length || p.y < 0 || p.y >= plateau[0].length)
            return true;
        return false;
    }

    public void appliquerEffetCase(Point coord) {
        plateau[coord.x][coord.y].appliquerEffet();
    }

    public ECase getType(Point coord){
        return plateau[coord.x][coord.y].getType();
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

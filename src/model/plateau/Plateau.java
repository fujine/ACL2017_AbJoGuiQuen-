package model.plateau;

import model.factory.CaseFactory;
import model.plateau.objet.ObjetPiege;
import model.plateau.objet.ObjetTresor;

import java.awt.*;

public class Plateau {
    private ICase plateau[][];
    public static int HAUTEUR = 50;
    public static int LARGEUR = 50;

    /**
     * Constructeur par défaut avec mur sur les bordures
     */
    public Plateau() {
        plateau = new ICase[HAUTEUR][LARGEUR];

        //Génère un tableau avec un mur sur les 4 bordures
        for(int i = 0; i < HAUTEUR; i++) {
            for(int j = 0; j < LARGEUR; j++) {
                if(i == 0 || i == HAUTEUR - 1)
                    plateau[i][j] = CaseFactory.creerCase(ECase.MUR);
                else {
                    if(j == 0 || j == LARGEUR-1)
                        plateau[i][j] = CaseFactory.creerCase(ECase.MUR);
                    else
                        plateau[i][j] = CaseFactory.creerCase(ECase.VIDE);
                }
            }
        }
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

    public void modifierCase() {
        plateau[3][1] = CaseFactory.creerCase(ECase.PIEGE);
        plateau[6][5] = CaseFactory.creerCase(ECase.MUR);
        plateau[5][5] = CaseFactory.creerCase(ECase.MUR);
        plateau[8][8] = CaseFactory.creerCase(ECase.TELEPORTEUR,new Point(1,1));
        plateau[1][1] = CaseFactory.creerCase(ECase.TELEPORTEUR,new Point(8,1));
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
        for(int i = 0; i < HAUTEUR; i++) {
            for(int j = 0; j < LARGEUR; j++) {
                if(plateau[i][j].estTraversable())
                    System.out.print("X");
                else
                    System.out.print("O");
            }
            System.out.println();
        }
    }
}

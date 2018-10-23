package model;

public class Plateau {
    private ICase plateau[][];
    public static int HAUTEUR = 10;
    public static int LARGEUR = 10;

    /**
     * Constructeur par défaut avec mur sur les bordures
     */
    public Plateau() {
        plateau = new ICase[HAUTEUR][LARGEUR];

        //Génère un tableau avec un mur sur les 4 bordures
        for(int i = 0; i < HAUTEUR; i++) {
            for(int j = 0; j < LARGEUR; j++) {
                if(i == 0 || i == HAUTEUR - 1)
                    plateau[i][j] = new CaseMur();
                else {
                    if(j == 0 || j == LARGEUR-1)
                        plateau[i][j] = new CaseMur();
                    else
                        plateau[i][j] = new CaseVide();
                }
            }
        }
        plateau[1][3] = new CaseTresor();
        plateau[3][1] = new CasePiege();
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

    public void appliquerEffetCase(int posx, int posy) {
        plateau[posx][posy].appliquerEffet();
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

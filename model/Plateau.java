package ACL2018_AbJoGuiQuen.model;

public class Plateau {
    private boolean plateau[][];
    public static int HAUTEUR = 10;
    public static int LARGEUR = 10;

    public Plateau() {
        plateau = new boolean[HAUTEUR][LARGEUR];
        for(int i = 0; i < HAUTEUR; i++) {
            for(int j = 0; j < LARGEUR; j++) {
                if(i == 0 || i == HAUTEUR - 1)
                    plateau[i][j] = false;
                else {
                    if(j == 0 || j == LARGEUR-1)
                        plateau[i][j] = false;
                    else
                        plateau[i][j] = true;
                }
            }
        }
    }

    public boolean estLibre(int posx, int posy) {
        return plateau[posx][posy];
    }

    public void affichageText() {
        for(int i = 0; i < HAUTEUR; i++) {
            for(int j = 0; j < LARGEUR; j++) {
                if(plateau[i][j])
                    System.out.print("X");
                else
                    System.out.print("O");
            }
            System.out.println();
        }
    }
}

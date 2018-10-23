import model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Permet de récuper l'entrée de l'utilisateur
        Scanner sc = new Scanner(System.in);

        //initialisation du jeu
        Jeu j = Jeu.getInstance();

        //Boucle de jeu
        while (!j.isFinished()) {
            System.out.println("Choisir une action: (h,b,g,d,f)");
            String cmd = sc.next();
            j.evolve(cmd);
            System.out.println();
            System.out.println("Hero(" + j.getHero().getPosX() + "," + j.getHero().getPosY() + ")");
        }

        System.out.println("Fin du jeu!");

        //Libération du Scanner
        sc.close();
    }
}

package ACL2018_AbJoGuiQuen;

import ACL2018_AbJoGuiQuen.model.Jeu;
import ACL2018_AbJoGuiQuen.model.Plateau;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Jeu j = new Jeu();
        while (!j.isFinished()) {
            System.out.println("Choisir une action: (h,b,g,d,f)");
            String cmd = sc.next();
            j.evolve(cmd);
            System.out.println();
            System.out.println("Hero(" + j.getHero().getPosX() + "," + j.getHero().getPosY() + ")");
        }
        System.out.println("Fin du jeu!");

        sc.close();
    }
}

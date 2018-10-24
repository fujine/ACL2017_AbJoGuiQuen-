import controller.Controller;
import model.*;
import engine.GameEngineGraphical;
import vue.Painter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        //Permet de récuper l'entrée de l'utilisateur
        Scanner sc = new Scanner(System.in);

        //initialisation du jeu
        Jeu j = Jeu.getInstance();
        Painter painter = new Painter(j);
        Controller controller = new Controller();

        GameEngineGraphical engine = new GameEngineGraphical(j,painter,controller);
        engine.run();

        System.out.println("Partie finie!");

        //Libération du Scanner
        sc.close();
    }
}

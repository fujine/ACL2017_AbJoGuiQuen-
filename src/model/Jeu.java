package model;

public class Jeu {

    private static Jeu instance;
    private boolean fini = false;
    private Hero hero = new Hero(this);
    private Plateau plateau = new Plateau();

    private Jeu() {};

    public static Jeu getInstance() {
        if(instance == null)
            instance = new Jeu();
        return instance;
    }

    /**
     * @param cmd Input de l'utilisateur
     */
    public void evolve(String cmd){
        int x = hero.getPosX();
        int y = hero.getPosY();
        switch (cmd){
            case "h" :
                y--;
                break;
            case "b" :
                y++;
                break;
            case "g" :
                x--;
                break;
            case "d" :
                x++;
                break;
            case "f" :
                this.fini = true;
                break;
        }
        if(plateau.estLibre(x,y))
            hero.deplacer(x,y);

    }

    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * @return true si partie terminée, false sinon
     */
    public boolean isFinished(){
        return this.fini;
    }

    /**
     * @return Le hero lié au jeu
     */
    public Hero getHero(){
        return this.hero;
    }
}

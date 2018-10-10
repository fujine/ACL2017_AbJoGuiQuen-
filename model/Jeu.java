package ACL2018_AbJoGuiQuen.model;

public class Jeu {

    private boolean fini;
    private Hero hero;

    public Jeu(){
        this.hero = new Hero();
        this.fini = true;
    }

    public void evolve(String cmd){
        switch (cmd){
            case "h" :
                this.hero.deplacementHaut();
                break;
            case "b" :
                this.hero.deplacementBas();
                break;
            case "g" :
                this.hero.deplacementGauche();
                break;
            case "d" :
                this.hero.deplacementDroit();
                break;
            case "f" :
                this.fini = true;
                break;
        }

    }

    public void isFinished(){
        return this.fini;
    }

    public Hero getHero(){
        return this.hero;
    }


}

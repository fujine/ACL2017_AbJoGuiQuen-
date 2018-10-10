package ACL2018_AbJoGuiQuen.model;

public class Jeu {

    private boolean fini = false;
    private Hero hero = new Hero();

    /**
     * @param cmd Input de l'utilisateur
     */
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

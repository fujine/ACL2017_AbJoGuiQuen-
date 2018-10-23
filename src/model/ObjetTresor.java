package model;

public class ObjetTresor implements Objet {

    @Override
    public void appliquerEffet() {
        Jeu.getInstance().setFini(true);
    }
}

package model.plateau.objet;

import model.Jeu;
import model.plateau.ECase;

public class ObjetPiege extends Objet {

    private long timer =0;

    /**
     * Constructore par défaut qui défini le type de la case
     */
    public ObjetPiege() {
        info =1;
        type = ECase.PIEGE;
    }

    /**
     * Retire la vie au héro quand il marche dessus
     */
    @Override
    public void appliquerEffet() {
        if (timer == 0) {
            timer = System.currentTimeMillis();
            Jeu.getInstance().appliquerDegats(2);
            info = 0;
        }else{
            if (System.currentTimeMillis() - timer > 1000){
                timer = 0;
            }
        }
    }

}

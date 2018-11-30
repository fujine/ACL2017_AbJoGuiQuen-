package model.effet;

import model.entites.Entites;

public class EffetRalenti implements  Effet {

    private long timer;
    private static long dureeEffet = 10;
    private int vitesseDep;
    private boolean finEffet = false;

    @Override
    public void appliquer(Entites e) {
        if (timer == 0){
            vitesseDep = e.getVitesse();
            e.setVitesse(e.getVitesse() / 2);
            timer = System.currentTimeMillis();
        }else if (System.currentTimeMillis() - timer > dureeEffet){
            e.setVitesse(vitesseDep);
            finEffet = true;
        }
    }

    @Override
    public boolean finEffet() {
        return finEffet;
    }


}

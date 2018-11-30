package model.effet;

import model.entites.Entites;

public class EffetPoison implements Effet{

    private static int nbTick = 3;
    private int cptTick;
    private long timer;

    public EffetPoison(){
        timer = System.currentTimeMillis();
        cptTick = 0;
    }

    @Override
    public void appliquer(Entites e) {
        if (timer == 0) {
            timer = System.currentTimeMillis();
            e.subirDegat(1);
            cptTick++;
        }else{
            if (System.currentTimeMillis() - timer > 1000){
                timer = 0;
            }
        }
    }

    @Override
    public boolean finEffet() {
        return nbTick == cptTick;
    }


}

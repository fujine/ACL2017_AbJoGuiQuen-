package model.effet;

import model.entites.Entites;

public interface Effet {


    public void appliquer(Entites e);
    public boolean finEffet();

}

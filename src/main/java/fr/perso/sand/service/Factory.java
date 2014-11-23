package fr.perso.sand.service;

import fr.perso.sand.model.Ensemble;
import fr.perso.sand.model.Grain;
import fr.perso.sand.model.Terrain;
import fr.perso.sand.model.TypeGrain;

/**
 * Created by sbuisson on 22/11/2014.
 */
public class Factory {
    public Grain initGrain(TypeGrain type) {
        throw  new RuntimeException();
    }

    public Ensemble initBulle() {
        throw  new RuntimeException();
    }

    public Terrain initTerrain(int largeur, int hauteur) {
        throw  new RuntimeException();
    }

    public Terrain initTerrain(String strTerrain) {
        throw  new RuntimeException();
    }
}

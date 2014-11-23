package fr.perso.sand.action;

import fr.perso.sand.model.Grain;
import fr.perso.sand.model.Terrain;

/**
 * Created by sbuisson on 22/11/2014.
 */
public class ActionAccelere extends Action<Grain,Terrain> {
    @Override
    public boolean execute(Grain appelant, Terrain terrain) {
        throw new RuntimeException("pas im");
    }
}

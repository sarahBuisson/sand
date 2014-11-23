package fr.perso.sand.action;

import fr.perso.sand.action.Action;
import fr.perso.sand.model.Ensemble;
import fr.perso.sand.model.Grain;
import fr.perso.sand.model.Terrain;

/**
 * Created by sbuisson on 22/11/2014.
 */
public class ContracteBulle extends Action<Ensemble, Terrain> {
    @Override
    public boolean execute(Ensemble appelant, Terrain terrain) {
        throw new RuntimeException("pas impl");
    }

    public void lisseGrain(Grain grain) {
        throw new RuntimeException();
    }

    public void rapprocheGrain(Grain grain) {
        throw new RuntimeException();


    }

    public void quitteGrain(Grain grain) {

        throw new RuntimeException();
    }
}

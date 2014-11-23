package fr.perso.sand.model;

import java.awt.*;

/**
 * Created by sbuisson on 16/11/2014.
 */
public abstract class TypeGrain extends TypeObjet {
    public final VecteurAcceleration acceleration;

    public TypeGrain(VecteurAcceleration acceleration) {
        this.acceleration = acceleration;
    }

    public abstract Color initColor();

    public abstract Color getColor(Grain grain);
}

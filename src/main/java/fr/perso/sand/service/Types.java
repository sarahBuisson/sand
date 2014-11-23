package fr.perso.sand.service;

import fr.perso.sand.action.ActionAccelere;
import fr.perso.sand.action.ActionBouge;
import fr.perso.sand.action.ActionCreationBulle;
import fr.perso.sand.action.ContracteBulle;
import fr.perso.sand.model.*;

import java.awt.*;

/**
 * Created by sbuisson on 22/11/2014.
 */
public class Types {

    public static final TypeGrain air = new TypeGrain(new VecteurAcceleration(0,-2)){
        @Override
        public Color initColor() {
            return Color.cyan;
        }

        @Override
        public Color getColor(Grain grain) {
            if(grain.ensemble==null)
                return grain.color;
            //  return grain.ensemble.color;

            for(Position p:grain.getPosition().getVoisinsImediats())
                if(p.getContenu()==null||p.getContenu().ensemble!=grain.ensemble)
                    return grain.ensemble.color;
            return Color.white;
        }
    };
    {
        air.actions.put(InstantEnum.avant, new ActionAccelere());
        air.actions.put(InstantEnum.pendant, new ActionBouge());
        air.actions.put(InstantEnum.apres, new ActionCreationBulle());
    }
    public static final TypeGrain sable = new TypeGrain(new VecteurAcceleration(0,1)){
        @Override
        public Color initColor() {

            return new Color(Color.HSBtoRGB(0.3f,0.5f+0.5f*(float)Math.random(),0.5f+0.5f*(float)Math.random()));
        }

        @Override
        public Color getColor(Grain grain) {
            return grain.color;
        }

    };
    public static final TypeGrain sable1 = new TypeGrain(new VecteurAcceleration(0,3)){
        @Override
        public Color initColor() {
            return new Color(Color.HSBtoRGB(0.1f,0.5f+0.5f*(float)Math.random(),0.5f+0.5f*(float)Math.random()));

        }
        @Override
        public Color getColor(Grain grain) {
            return grain.color;
        }
    };    public static final TypeGrain sable2 = new TypeGrain(new VecteurAcceleration(0,2)){
        @Override
        public Color initColor() {
            return new Color(Color.HSBtoRGB(0.7f,0.5f+0.5f*(float)Math.random(),0.5f+0.5f*(float)Math.random()));

        }
        @Override
        public Color getColor(Grain grain) {
            return grain.color;
        }
    };
    {
        sable.actions.put(InstantEnum.avant, new ActionAccelere());
        sable.actions.put(InstantEnum.pendant, new ActionBouge());
        sable1.actions=sable.actions;
        sable2.actions=sable.actions;
    }

    public static TypeObjet bulle=new TypeObjet();{
        bulle.actions.put(InstantEnum.apres, new ContracteBulle());
    }
}

package fr.perso.sand.model;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Ensemble extends ObjetTerrain {
	  HashSet<Grain> contenu = new HashSet<Grain>();
    VecteurInvariant centre;
    Collection<VecteurInvariant> centres;
    boolean isActual=false;
    public   final Color color= Color.getHSBColor((float)Math.random()*50,(float)Math.random()*50,(float)Math.random()*50);

	public void  addElement(Grain grain) {
        if(grain.ensemble !=this)
        synchronized (contenu) {

            if (grain.ensemble != null)
                grain.ensemble.removeElement(grain);
            contenu.add(grain);
            grain.ensemble = this;
        }
	}

	public void removeElement(Grain grain) {
		contenu.remove(grain);
		grain.ensemble = null;
	}

    synchronized
	public void calcul() {
        if(isActual)
            return;

		int x = 0;
		int y = 0;
        synchronized (contenu) {
            for (Grain grain : contenu) {
                x += grain.getPosition().x;
                y += grain.getPosition().y;
            }
            if (contenu.isEmpty()) {
                centre = null;
                centres = null;
            } else {
                Grain firstGrain = contenu.iterator().next();
                int fgy = firstGrain.type.acceleration.getRealY()*(int)(Math.log(contenu.size()));
                int fgx = firstGrain.type.acceleration.getRealX()*(int)(Math.log(contenu.size()));
                centre = new VecteurInvariant(x / contenu.size(), y / contenu.size());
                centres = Arrays.asList(centre, centre.add(fgy, fgx), centre.remove(fgy, fgx));
            }
        }
	}

    synchronized public VecteurInvariant getCentre() {
		return centre;
	}

	public float getRayonCarre() {
		return contenu.size() /(3.14f);
	}
	public double getRayon() {
		return Math.sqrt(contenu.size() / 3.14f);
	}

	public Collection<Grain> getContenu() {
		return contenu;
	}
    public void fusion(Ensemble autre){
        if(autre!=this)
        synchronized (contenu) {
            synchronized (this) {
                contenu.addAll(autre.contenu);
                for (Grain grain : autre.contenu)
                    grain.ensemble = this;
                autre.contenu.clear();
            }
        }
        calcul();
    }

    public Collection<VecteurInvariant> getCentres() {
        return centres;
    }

    public void addAll(Collection<Grain> contenu) {
        for (Grain grain : contenu) {
            grain.ensemble = this;
        }
        contenu.addAll(contenu);
    }

    public void unactualise() {
        isActual=false;
    }
}

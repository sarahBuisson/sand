package fr.perso.sand.model;

 public class VecteurInvariant extends Vecteur {

	private final int dec;
	{
		if (Math.random() > 0.5)
			dec = -1;
		else
			dec = 1;
	}

	 public VecteurInvariant() {
		setX(0);
		y = 0;

	}

	 public VecteurInvariant(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}





	@Override public synchronized VecteurInvariant add(int x, int y) {
        VecteurInvariant retour=this.clone();
		retour.x += x;
		retour.y += y;
        return retour;
	}

	@Override public synchronized VecteurInvariant remove(int x, int y) {
        VecteurInvariant retour=this.clone();
        retour.x -= x;
        retour.y -= y;
        return retour;
	}

	@Override public Vecteur  add(Vecteur vect) {
        VecteurInvariant retour=this.clone();
		synchronized (vect) {
			retour.x += vect.x;
			retour.y += vect.y;
		}
        return retour;
	}

	@Override public Vecteur remove(Vecteur vect) {
        VecteurInvariant retour=this.clone();
        synchronized (vect) {
            retour.x -= vect.x;
            retour.y -= vect.y;
        }
return retour;
	}

	@Override public synchronized Vecteur multiple(double multi) {
        VecteurInvariant retour=this.clone();

            retour.x *= multi;
            retour.y *= multi;

        return retour;

	}

	@Override
    public synchronized Vecteur inverse() {
        VecteurInvariant retour=this.clone();

        retour.x *= -1;
        retour.y *= -1;

        return retour;
	}

	@Override public boolean isNegligeable() {
		return Math.abs(x) + Math.abs(y) < PRECISION;
	}

	@Override public int getRealX() {
		return (x / PRECISION);
	}

	@Override public int getRealY() {
		return (y / PRECISION);
	}


	@Override public VecteurInvariant clone() {
		return new VecteurInvariant(x, this.y);
	}


}

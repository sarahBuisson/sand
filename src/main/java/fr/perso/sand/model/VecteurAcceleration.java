package fr.perso.sand.model;

public class VecteurAcceleration extends Vecteur {
	public static final int PRECISION = 10;

	private final int dec;
	{
		if (Math.random() > 0.5)
			dec = -1;
		else
			dec = 1;
	}

	public VecteurAcceleration() {
		x = 0;
		y = 0;

	}

	public VecteurAcceleration(int x, int y) {
		this();
		this.x = x*PRECISION;
		this.y = y*PRECISION;
	}
/*
	public synchronized VecteurUnitaire getFragment() {
		double signX = Math.signum(x);
		double signY = Math.signum(y);
		int absX = Math.abs(x);
		int absY = Math.abs(y);
		if (absX + absY < PRECISION)
			return null;
		int xFrag = 0;
		int yFrag = 0;

		if (absX >= absY * 2) {
			xFrag = (int) (PRECISION * signX);
		} else if (absY >= 2 * absX) {
			yFrag = (int) (PRECISION * signY);
		} else {
			xFrag = (int) (PRECISION * signX);

			yFrag = (int) (PRECISION * signY);
		}
		VecteurUnitaire fragment = VecteurUnitaire.get(xFrag, yFrag);

		return fragment;
	}*/

	public synchronized VecteurUnitaire getFragmentAlternatif(VecteurUnitaire alreadyGiven) {
		double signX = Math.signum(x);
		double signY = Math.signum(y);
		int absX = (int) Math.abs(x);
		int absY = (int) Math.abs(y);
		if (absX + absY < PRECISION)
			return null;
		int xFrag = alreadyGiven.x;
		int yFrag = alreadyGiven.y;

		// do {
		if (true || dec > 0) {
			xFrag +=  dec;
		} else {
			yFrag +=  dec;
		}
		return VecteurUnitaire.get(xFrag, yFrag);

	}

	/*
	 * renvoie un fragment du vecteur et reduit le vecteur d'autant"
	 */
	public synchronized VecteurAcceleration extractFragment() {
		double signX = Math.signum(x);
		double signY = Math.signum(y);
		int absX = (int) Math.abs(x);
		int absY = (int) Math.abs(y);
		if (absX + absY < PRECISION)
			return null;
		int xFrag = 0;
		int yFrag = 0;
		/*
		 * if(absY<PRECISION && absY<PRECISION)
		 *
		 * else if(absY>=PRECISION ){ yFrag=PRECISION*signY; } else
		 * if(absX>=PRECISION ){ xFrag=PRECISION*signY; }
		 */
		if (absX > absY) {
			xFrag = (int) (PRECISION * signX);
		} else {
			yFrag = (int) (PRECISION * signY);
		}

		VecteurAcceleration fragment = new VecteurAcceleration(xFrag, yFrag);
		this.x -= xFrag;
		this.y -= yFrag;
		return fragment;
	}



	public boolean isNegligeable() {
		return x*x+y*y < PRECISION*PRECISION;
	}

	public int getRealX() {
		return (x / PRECISION);
	}

	public int getRealY() {
		return (y / PRECISION);
	}



	public int getDec() {
		return dec;
	}

}

package fr.perso.sand.model;

public class Vecteur {
	public static final int PRECISION = 10;
	protected int x;
    protected int y;


	public Vecteur() {
		x = 0;
		y = 0;

	}

	public Vecteur(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}


	public synchronized Vecteur add(int x, int y) {
		this.x += x;
		this.y += y;
        return this;
	}

	public synchronized Vecteur remove(int x, int y) {
		this.x -= x;
		this.y -= y;
        return this;
	}

	public synchronized Vecteur add(Vecteur vect) {
		synchronized (vect) {
			x += vect.x;
			y += vect.y;
		}
        return this;

	}

	public synchronized Vecteur remove(Vecteur vect) {
		synchronized (vect) {
			x -= vect.x;
			y -= vect.y;
		}
        return this;

	}

	public synchronized Vecteur multiple(double multi) {
		x *= multi;
		y *= multi;
        return this;

	}

	public synchronized Vecteur inverse() {
		x *= -1;
		y *= -1;
        return this;
	}


    public boolean isNegligeable() {
		return Math.abs(x) + Math.abs(y) < PRECISION;
	}

	public int getRealX() {
		return (x / PRECISION);
	}

	public int getRealY() {
		return (y / PRECISION);
	}



	public Vecteur clone() {
		return new Vecteur(x, this.y);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Vecteur) {
			Vecteur vect = (Vecteur) o;
			return vect.x == x && vect.y == y;
		} else
			return false;
	}

	@Override
	public int hashCode() {
		return x + y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public int produit(Vecteur vecteur) {
		return (vecteur.x * x + vecteur.y * y) / 100;
	}



	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int lengthSqrt() {
		return x *x + y *y;
	}
    public Vecteur normal(){
    return new Vecteur(y,-x);
}

    public void setY(int y) {
        this.y = y;
    }

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
            xFrag = (int) (signX);
        } else if (absY >= 2 * absX) {
            yFrag = (int) (signY);
        } else {
            xFrag = (int) (signX);

            yFrag = (int) (signY);
        }
        return VecteurUnitaire.get(xFrag, yFrag);


    }
}

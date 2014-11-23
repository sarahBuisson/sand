package fr.perso.sand.model;

import java.awt.*;

public class Grain extends ObjetTerrain<TypeGrain> {

	public final VecteurAcceleration vitesseRestante=new VecteurAcceleration();
	public Color color;
	private Position position;
	public Ensemble ensemble;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setColor(Color hsbColor) {
		this.color=hsbColor;
		
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}
}

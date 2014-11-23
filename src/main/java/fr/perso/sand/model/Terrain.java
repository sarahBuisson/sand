package fr.perso.sand.model;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;

import java.util.*;

public class Terrain {
    private int largeur;
    private int hauteur;
    Table<Integer, Integer, Position> positions;
    Collection<ObjetTerrain> contenu = new LinkedList<ObjetTerrain>();
    Collection<ObjetTerrain> contenuToAdd = new LinkedList<ObjetTerrain>();
    Collection<ObjetTerrain> contenuToRemove = new LinkedList<ObjetTerrain>();

    Collection<Grain> listenedGrain = new ArrayList<Grain>();
    public List<Position> mergePosition = new ArrayList<Position>();

    public Terrain(int largeur, int hauteur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        initPositionsArray();
        fillPositionArrayWithPosition();

    }

    private void fillPositionArrayWithPosition() {
        for (int i = 0; i < largeur; i++)
            for (int j = 0; j < hauteur; j++) {
                Map<VecteurUnitaire, Position> voisinsPos = new HashMap<VecteurUnitaire, Position>();
                for (VecteurUnitaire vect : VecteurUnitaire.vecteurs)
                    if (positions.contains(i + vect.getRealX(),
                            j + vect.getRealY()))
                        voisinsPos.put(
                                vect,
                                positions.get(i + vect.getRealX(),
                                        j + vect.getRealY()));
                positions.get(i, j).setVoisins(voisinsPos);
            }
    }

    private void initPositionsArray() {
        Collection<Integer> indexLargeur = new ArrayList<Integer>();
        Collection<Integer> indexHauteur = new ArrayList<Integer>();
        for (int i = 0; i < largeur; i++)
            indexLargeur.add(i);
        for (int j = 0; j < hauteur; j++)
            indexHauteur.add(j);
        positions = ArrayTable.create((indexLargeur), (indexHauteur));
        for (int i = 0; i < largeur; i++)
            for (int j = 0; j < hauteur; j++) {
                positions.put(i, j, new Position(i, j, this));
            }
    }

    public void addElement(ObjetTerrain grain) {
        contenuToAdd.add(grain);
    }

    public void removeElement(ObjetTerrain grain) {
        contenuToRemove.add(grain);
    }

    public Collection<ObjetTerrain> getContenu() {
        return contenu;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }


    public Position getAt(int x, int y) {
        // TODO Auto-generated method stub
        return positions.get(x, y);
    }

    public void addElement(ObjetTerrain obj, int x, int y) {

        contenu.add(obj);
        if (obj instanceof Grain) {
            Grain grain = (Grain) obj;
            grain.setPosition(getAt(x, y));
            getAt(x, y).setContenu(grain);
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        for (int j = 0; j < hauteur; j++) {
            for (int i = 0; i < largeur; i++) {
                if (getAt(i, j).isEmpty())
                    buffer.append(" ");
                else
                    buffer.append("x");
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    public Collection<Grain> getListenedGrain() {
        return listenedGrain;
    }

    public void toogleGrain(Grain grain) {
        if (listenedGrain.contains(grain))
            listenedGrain.remove(grain);
        else
            listenedGrain.add(grain);
    }

    public boolean isListenedGrain(Grain grain) {
        return listenedGrain.contains(grain);
    }

    public void renverse() {
        for (int j = 0; j < hauteur / 2; j++) {
            for (int i = 0; i < largeur; i++) {
                int newj = hauteur - j - 1;
                Position p1 = this.getAt(i, j);
                Position p2 = this.getAt(i, newj);
                Grain g1 = p1.getContenu();
                Grain g2 = p2.getContenu();
                if (g2 != null) {
                    p1.setContenu(g2);
                    g2.ensemble = null;
                }
                if (g1 != null) {
                    p2.setContenu(g1);
                    g1.ensemble = null;
                }

            }
        }

    }

    public void addAndRemoveElementsInPile() {
        this.contenuToAdd.removeAll(contenuToRemove);
        this.contenu.removeAll(contenuToRemove);
        this.contenu.addAll(contenuToAdd);
        contenuToAdd.clear();
        contenuToRemove.clear();
    }

    public void fusion(Ensemble ensemble1,Ensemble  ensemble2){
        if(ensemble1.getContenu().size()>ensemble2.getContenu().size()){
            ensemble1.fusion(ensemble2);
            this.removeElement(ensemble2);
        }else{
            ensemble2.fusion(ensemble1);
            this.removeElement(ensemble2);
        }
    }
}

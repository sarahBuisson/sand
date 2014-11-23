package fr.perso.sand.model;

import java.util.*;

public class Position {
    private Grain contenu;
    private Collection<Position> voisins;
    private Collection<Position> voisinsImmediats;
    private Map<VecteurUnitaire, Position> voisinsPos;
    public int x;
    public int y;
    Terrain terrain;

    public Position(int i, int j, Terrain terrain2) {
        this.x = i;
        this.y = j;
        this.terrain = terrain2;
    }

    public void setContenu(Grain grain) {
    if(grain.getPosition()!=null)
        grain.getPosition().clear();
        this.contenu = grain;
        grain.setPosition(this);
    }

   

    public void setVoisins(Map<VecteurUnitaire, Position> voisinsPos2) {
        this.voisinsPos = voisinsPos2;//new MapVecteurUnitaire<Position>(voisinsPos2);
        this.voisins = this.voisinsPos.values();
        this.voisinsImmediats= new ArrayList(Arrays.asList(voisinsPos2.get(VecteurUnitaire.get(0, 1)), voisinsPos2.get(VecteurUnitaire.get(1, 0)), voisinsPos2.get(VecteurUnitaire.get(0, -1)), voisinsPos2.get(VecteurUnitaire.get(-1, 0))));
        while(voisinsImmediats.remove(null));
    }

    public Collection<Position> getVoisins() {
        return voisins;
    }
    
    public Collection<Position> getVoisinsImediats() {
        return voisinsImmediats;
    }


    public Position getVoisinAt(Vecteur v) {
        return voisinsPos.get(v);
    }

    public boolean isEmpty() {
        return contenu == null;
    }
    public boolean isFull() {
        return contenu != null;
    }

    public void clear() {
        this.contenu = null;
    }

    public Grain getContenu() {
        return contenu;
    }

    public boolean hasFreeVoisin() {
        for (Position voisin : voisins)
            if (voisin.isEmpty())
                return true;
        return false;
    }

    public Iterable<Position> forFreeVoisin(){
       return new Iterable() {
            @Override
            public Iterator iterator() {
                return new Iterator<Position>(){
                    Iterator<Position> sousIterator=voisins.iterator();
                    Position potentielNext=sousIterator.next();
                    {
                        while(potentielNext.isFull()&&potentielNext!=null){
                            potentielNext=sousIterator.next();
                        }
                    }
                    @Override
                    public boolean hasNext() {
                        return potentielNext!=null;
                    }

                    @Override
                    public Position next() {
                        Position retour=potentielNext;
                        while(potentielNext.isFull()){
                            potentielNext=sousIterator.next();
                        }
                        return retour;
                    }

                    @Override
                    public void remove() {
                        throw new RuntimeException("not implemented");
                    }
                };
            }
        };


    }

    public Iterable<Grain> forGrainVoisin(){
        return new Iterable() {
            @Override
            public Iterator iterator() {
                return new Iterator<Grain>() {
                    Iterator<Position> sousIterator = voisins.iterator();
                    Position potentielNext = sousIterator.next();

                    {
                        while (potentielNext.isEmpty()) {
                            potentielNext = sousIterator.next();
                        }
                    }

                    @Override
                    public boolean hasNext() {
                        return potentielNext != null;
                    }

                    @Override
                    public Grain next() {
                        Position retour = potentielNext;
                        while (potentielNext.isFull()) {
                            potentielNext = sousIterator.next();
                        }
                        return retour.getContenu();
                    }

                    @Override
                    public void remove() {
                        throw new RuntimeException("not implemented");
                    }
                };
            };
    };}

}

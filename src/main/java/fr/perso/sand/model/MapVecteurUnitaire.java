package fr.perso.sand.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by sbuisson on 18/11/2014.
 */
public class MapVecteurUnitaire<T> implements Map<VecteurUnitaire,T> {

    int size;
    T[] datas;
    Set<VecteurUnitaire> keySet;
    Set<Entry<VecteurUnitaire, T>> entrySet;
    Collection<T> values;
    public MapVecteurUnitaire(Map<VecteurUnitaire,T> initialData){
        size=initialData.size();
        datas= (T[])new Object[VecteurUnitaire.vecteurs.length];
        values= new ArrayList();
        keySet=new VecteurUnitaireSet();
        entrySet=new VecteurUnitaireEntrySet<T>();
        for(Entry<VecteurUnitaire,T>entry:initialData.entrySet()) {

            keySet.add(entry.getKey());
            entrySet.add(new EntryMVU(entry.getKey(),entry.getValue()));
            values.add(entry.getValue());
        }

        for(Entry<VecteurUnitaire,T>entry:initialData.entrySet())
            datas[entry.getKey().index]=entry.getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean containsKey(Object key) {
        return datas[((VecteurUnitaire)key).index]!=null;
    }

    @Override
    public boolean containsValue(Object value) {
       throw new RuntimeException("pas implémenté");
    }

    @Override
    public T get(Object key) {
        return datas[((VecteurUnitaire)key).index];
    }

    @Override
    public T put(VecteurUnitaire key, T value) {
        throw new RuntimeException("pas implémenté");
    }

    @Override
    public T remove(Object key) {
        throw new RuntimeException("pas implémenté");
    }

    @Override
    public void putAll(Map<? extends VecteurUnitaire, ? extends T> m) {
        throw new RuntimeException("pas implémenté");
    }

    @Override
    public void clear() {
        throw new RuntimeException("pas implémenté");
    }

    @Override
    public Set<VecteurUnitaire> keySet() {
        return keySet;
    }

    @Override
    public Collection<T> values() {


        return values;
    }

    @Override
    public Set<Entry<VecteurUnitaire, T>> entrySet() {
        return entrySet;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    class EntryMVU implements Entry<VecteurUnitaire,T>{
        public EntryMVU(VecteurUnitaire key,T value){
            this.key=key;
            this.value=value;
        }
        VecteurUnitaire key;
        T value;
        @Override
        public VecteurUnitaire getKey() {
            return key;
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public T setValue(T value) {
            return this.value=value;
        }
    }

    private class VecteurUnitaireSet extends ArrayList<VecteurUnitaire> implements Set<VecteurUnitaire> {

    }

    private class VecteurUnitaireEntrySet<T>  extends ArrayList<Entry<VecteurUnitaire, T>> implements Set<Entry<VecteurUnitaire, T>> {
    }

}

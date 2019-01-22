package Domain.ADT;

import java.util.*;

import Exceptions.*;

public class Heap<K,V> implements IDictionary<K,V> {

    private HashMap<K,V> dictionary;

    public Heap(){
        dictionary = new HashMap<K,V>();
    }

    @Override
    public HashMap<K,V> getDictionary(){
        return dictionary;
    }

    @Override
    public V getValueForKey(K key) throws VariableNotFound {
        if(dictionary.get(key) != null)
            return dictionary.get(key);
        throw new VariableNotFound();
    }

    @Override
    public boolean checkExistence(K key){
        if (dictionary.get(key) != null)
            return true;
        return false;
    }

    @Override
    public void add(K key, V value){
        dictionary.put(key,value);
    }

    @Override
    public void update(K key, V value){
        dictionary.put(key, value);
    }

    @Override
    public void delete(K key) {
        dictionary.remove(key);
    }

    @Override
    public String toString(){
        String str = "";

        for(HashMap.Entry<K,V> element: dictionary.entrySet())
            str += "\t" +"Address: " + element.getKey().toString() + " Value: " + element.getValue().toString() + "\n";

        return str;
    }

    @Override
    public void setContent(Set<Map.Entry<K, V>> set) {
        this.dictionary.clear();
        for(Map.Entry<K, V> e: set)
            add(e.getKey(), e.getValue());
    }

    @Override
    public Collection<V> values() {
        return dictionary.values();
    }

    @Override
    public Iterable<Map.Entry<K,V>> getAll(){
        return dictionary.entrySet();
    }

    @Override
    public IDictionary<K, V> clone() {
        return null;
    }
}

package Domain.ADT;

import Exceptions.VariableNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<K,V> implements IDictionary<K,V> {

    private HashMap<K,V> dictionary;

    public MyDictionary(){
        dictionary = new HashMap<K,V>();
    }

    public MyDictionary(HashMap<K,V> d){
        dictionary = d;
    }

    @Override
    public V getValueForKey(K key) {
        if(dictionary.get(key) != null)
            return dictionary.get(key);
        throw new VariableNotFoundException();
    }

    @Override
    public boolean checkExistence(K key){
        return dictionary.get(key) != null;
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
            str += "\t" + element.getKey().toString() + " = " + element.getValue().toString() + "\n";

        return str;
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return dictionary.entrySet();
    }

    @Override
    public Collection<V> values() {
        return dictionary.values();
    }

    @Override
    public void setContent(Set<Map.Entry<K, V>> set) {
        this.dictionary.clear();
        for(Map.Entry<K, V> e: set)
            add(e.getKey(), e.getValue());
    }

    @Override
    public IDictionary<K,V> clone(){
        HashMap<K,V> d = (HashMap<K,V>) this.dictionary.clone();
        MyDictionary<K,V> clone = new MyDictionary(d);
        return clone;
    }

    @Override
    public HashMap<K,V> getDictionary(){
        return dictionary;
    }
}

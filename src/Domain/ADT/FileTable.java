package Domain.ADT;

import Exceptions.VariableNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileTable<K,V> implements IDictionary<K,V> {

    private HashMap<K,V> fileTable;

    public FileTable(){
        fileTable = new HashMap<K,V>();
    }

    @Override
    public void add(K key, V value) {
        fileTable.putIfAbsent(key, value);
    }

    @Override
    public V getValueForKey(K key) {
        if(fileTable.get(key) != null)
            return fileTable.get(key);
        throw new VariableNotFoundException();
    }

    @Override
    public boolean checkExistence(K key){
        return fileTable.get(key) != null;
    }

    @Override
    public void update(K key, V value){
        fileTable.put(key, value);
    }

    @Override
    public String toString(){
        String str = "";

        for(HashMap.Entry<K,V> element: fileTable.entrySet())
            str += "\t" + element.getKey().toString() + " = " + element.getValue().toString() + "\n";

        return str;
    }

    @Override
    public Iterable<Map.Entry<K,V>> getAll(){
        return fileTable.entrySet();
    }

    @Override
    public void setContent(Set<Map.Entry<K, V>> set) {
        this.fileTable.clear();
        for(Map.Entry<K, V> e: set)
            add(e.getKey(), e.getValue());
    }

    @Override
    public Collection<V> values() {
        return fileTable.values();
    }

    @Override
    public HashMap<K, V> getProcTable() {
        return fileTable;
    }

    @Override
    public void delete(K key) {
        fileTable.remove(key);
    }

    @Override
    public IDictionary<K, V> clone() {
        return null;
    }
}

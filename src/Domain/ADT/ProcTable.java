package Domain.ADT;

import Exceptions.VariableNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProcTable<K, V> implements IProc<K, V> {

    private HashMap<K, V> procTable;

    public ProcTable() {
        procTable = new HashMap<K, V>();
    }

    public ProcTable(HashMap<K, V> d) {
        procTable = d;
    }

    @Override
    public V getValueForKey(K key) {
        if (procTable.get(key) != null)
            return procTable.get(key);
        throw new VariableNotFoundException();
    }

    @Override
    public boolean checkExistence(K key) {
        return procTable.get(key) != null;
    }

    @Override
    public void add(K key, V value) {
        procTable.put(key, value);
    }

    @Override
    public void update(K key, V value) {
        procTable.put(key, value);
    }

    @Override
    public void delete(K key) {
        procTable.remove(key);
    }

    @Override
    public String toString() {
        String str = "";

        for (HashMap.Entry<K, V> element : procTable.entrySet())
            str += "\t" + element.getKey().toString() + " = " + element.getValue().toString() + "\n";

        return str;
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return procTable.entrySet();
    }

    @Override
    public Collection<V> values() {
        return procTable.values();
    }

    @Override
    public void setContent(Set<Map.Entry<K, V>> set) {
        this.procTable.clear();
        for (Map.Entry<K, V> e : set)
            add(e.getKey(), e.getValue());
    }

    @Override
    public IProc<K, V> clone() {
        return null;
    }

    @Override
    public HashMap<K, V> getProcTable() {
        return procTable;
    }
}

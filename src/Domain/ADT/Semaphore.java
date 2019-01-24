package Domain.ADT;

import Exceptions.VariableNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Semaphore<K, V> implements IDictionary<K, V> {

    private static int newFreeLocation = 50000;
    private HashMap<K, V> semaphoreTable;

    public Semaphore() {
        semaphoreTable = new HashMap<>();
    }

    public static int getNewFreeLocation() {
        return newFreeLocation;
    }

    @Override
    public void add(K key, V value) {
        newFreeLocation++;
        semaphoreTable.put(key, value);
    }

    @Override
    public void update(K key, V value) {
        newFreeLocation++;
        semaphoreTable.put(key, value);
    }

    @Override
    public void delete(K key) {
        semaphoreTable.remove(key);
    }

    @Override
    public V getValueForKey(K key) {
        if (checkExistence(key)) {
            return semaphoreTable.get(key);
        }
        throw new VariableNotFoundException();
    }

    @Override
    public boolean checkExistence(K key) {
        return semaphoreTable.get(key) != null;
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return semaphoreTable.entrySet();
    }

    @Override
    public Collection<V> values() {
        return semaphoreTable.values();
    }

    @Override
    public void setContent(Set<Map.Entry<K, V>> set) {
        semaphoreTable.clone();
        for (Map.Entry<K, V> e : set)
            add(e.getKey(), e.getValue());
    }

    @Override
    public HashMap<K, V> getProcTable() {
        return semaphoreTable;
    }

    @Override
    public IDictionary<K, V> clone() {
        return null;
    }

    @Override
    public String toString() {
        String str = "";

        for (HashMap.Entry<K, V> element : semaphoreTable.entrySet())
            str += "\t" + "Location: " + element.getKey().toString() + " Value: " + element.getValue().toString() + "\n";

        return str;
    }
}

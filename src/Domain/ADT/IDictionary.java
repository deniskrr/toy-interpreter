package Domain.ADT;

import Exceptions.VariableNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface IDictionary<K, V> {

    /**
     * Adds a pair (key, value) to the dictionary.
     *
     * @param key   - the key of the element
     * @param value - the value of the element
     */
    void add(K key, V value);

    /**
     * Removes a key from the dictionary.
     *
     * @param key - the key to eb removed
     */
    void delete(K key);

    /**
     * Updates the value of a given key with another value.
     *
     * @param key   - the given key
     * @param value - new value for key
     */
    void update(K key, V value);


    /**
     * Gets the value for a given key.
     *
     * @param key - the givem key
     * @return the value corresponding to the given key
     * @throws VariableNotFoundException if the key was not found
     */
    V getValueForKey(K key) throws VariableNotFoundException;

    /**
     * Checks if a given key is in the dictionary.
     *
     * @param key - the key to be searched for
     * @return <code>true</code> - if the key was found
     * <code>false</code> - otherwise
     */
    boolean checkExistence(K key);


    /**
     * Gets all the elements from the dictionary.
     *
     * @return a string with all the elements
     */
    String toString();


    /**
     * Gets all the elements of the dictionary.
     *
     * @return the entries of the map
     */
    Iterable<Map.Entry<K, V>> getAll();

    /**
     * Gets a Collection with all the values from the dictionary.
     *
     * @return the values of the map
     */
    Collection<V> values();


    /**
     * Sets the dictionary content to a given set of entries.
     *
     * @param set: the given set of values
     */
    void setContent(Set<Map.Entry<K, V>> set);

    /**
     * Gets the dictionary.
     *
     * @return the dictionary
     */
    HashMap<K, V> getProcTable();

    /**
     * Clones an instance.
     *
     * @return the cloned instance
     */
    IDictionary<K, V> clone();

}

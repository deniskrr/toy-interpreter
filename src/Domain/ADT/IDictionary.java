package Domain.ADT;

import Exceptions.*;

import java.util.*;

public interface IDictionary<K,V> {

    /**
     * Add a pair (key, value) to the dictionary;
     * @param key - the key of the element
     * @param value - the value of the element
     */
    void add(K key, V value);


    /**
     * Update the value from a given key with another value
     * @param key - the given key
     * @param value - new value for key
     */
    void update(K key, V value);


    /**
     * Get the value for a given key
     * @param key - the givem key
     * @return the value corresponding to the given key
     * @throws VariableNotFound if the key was not found
     */
    V getValueForKey(K key) throws VariableNotFound;

    /**
     * Check if a given key is in the dictionary
     * @param key - the key to be searched for
     * @return true - if the key was found
     *         false - otherwise
     */
    boolean checkExistence(K key);


    /**
     * String method to get all the elements from the dictionary.
     * @return a string with all the elements
     */
    String toString();


    /**
     * Get all the elements of the dictionary
     * @return the elements of the map
     */
    Iterable<Map.Entry<K,V>> getAll();

    /**
     * Get a Collection with all the elements of type V from the HashMap
     * @return Collecion<V>
     */
    Collection<V> values();


    /**
     * Set the dictionary content from a given set of entries
     * @param set: the given set of values which will be set for the HashMap
     */
    void setContent(Set<Map.Entry<K, V>> set);

    /**
     * Get the dictionary
     * @return HashMap
     */
    HashMap<K,V> getDictionary();

    IDictionary<K, V> clone();

    void delete(K key);
}

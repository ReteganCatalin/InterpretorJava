package Model.Dict;

import Exceptions.MyExceptions;

import java.util.HashMap;

public interface MyIDictionary<K, V> {
    public V lookup(K key) throws MyExceptions;
    public void update(K key, V value);
    public boolean isDefined(K key);
    public void setValues(HashMap<K,V> new_values);
    public HashMap<K,V>  getValues();
}
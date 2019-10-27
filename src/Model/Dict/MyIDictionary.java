package Model.Dict;

import Exceptions.MyExceptions;

public interface MyIDictionary<K, V> {
    public V lookup(K key) throws MyExceptions;
    public void update(K key, V value);
    public boolean isDefined(K key);
}
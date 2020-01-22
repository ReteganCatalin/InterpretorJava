package Model.Dict;

import Exceptions.MyExceptions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreTable<V> implements MyISemaphore<V> {
    Map<Integer,V> dictionary;
    private AtomicInteger freeLocation;
    public synchronized V lookup(int key) throws MyExceptions
    {
        try {
            return dictionary.get(key);
        }
        catch(Exception empty)
        {
            throw new MyExceptions("No key found");
        }
    }
    @Override
    public synchronized void update(int key, V value)
    {
        dictionary.put(key, value);
    }
    public synchronized  boolean isDefined(int key)
    {
        return dictionary.containsKey(key);
    }

    public SemaphoreTable() {
        this.dictionary = new ConcurrentHashMap<Integer,V>();
        this.freeLocation=new AtomicInteger(0);
    }

    public synchronized String toString() {
        StringBuilder content = new StringBuilder();
        Iterator<HashMap.Entry<Integer, V>> it = dictionary.entrySet().iterator();

        while (it.hasNext()) {
            HashMap.Entry<Integer, V> entry = it.next();
            content.append("(")
                    .append(entry.getKey())
                    .append("->")
                    .append(entry.getValue())
                    .append(")")
                    .append("\n");
        }
        return content.toString();
    }

    public synchronized void setValues(Map<Integer, V> dictionary) {
        this.dictionary = dictionary;
    }

    public synchronized Map<Integer, V> getValues() {
        return dictionary;
    }

    public synchronized int allocate(V value) {
        int newLocation = freeLocation.incrementAndGet();
        dictionary.put(newLocation, value);
        return newLocation;
    }
    public synchronized void deallocate(int address)
    {
        dictionary.remove(address);
    }
}
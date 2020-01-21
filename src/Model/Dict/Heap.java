package Model.Dict;

import Exceptions.MyExceptions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Heap<V> implements MyIHeap<V> {
    Map<Integer,V> dictionary;
    private AtomicInteger freeLocation;
    public V lookup(int key) throws MyExceptions
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
    public void update(int key, V value)
    {
        dictionary.put(key, value);
    }
    public boolean isDefined(int key)
    {
        return dictionary.containsKey(key);
    }

    public Heap() {
        this.dictionary = new ConcurrentHashMap<Integer,V>();
        this.freeLocation=new AtomicInteger(0);
    }

    public String toString() {
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

    public void setValues(Map<Integer, V> dictionary) {
        this.dictionary = dictionary;
    }

    public Map<Integer, V> getValues() {
        return dictionary;
    }

    public int allocate(V value) {
        int newLocation = freeLocation.incrementAndGet();
        dictionary.put(newLocation, value);
        return newLocation;
    }
    public void deallocate(int address)
    {
        dictionary.remove(address);
    }
}

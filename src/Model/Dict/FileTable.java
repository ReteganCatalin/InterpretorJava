package Model.Dict;

import Exceptions.MyExceptions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileTable<K,V> implements  MyIDictionary<K,V> {
    Map<K,V> dictionary;
    public V lookup(K key) throws MyExceptions
    {
        try {
            return dictionary.get(key);
        }
        catch(Exception empty)
        {
            throw new MyExceptions("No key found");
        }
    }
    public void update(K key, V value)
    {
        if(this.isDefined(key)==false) {
            dictionary.put(key, value);
        }
        else
        {
            dictionary.remove(key);
        }
    }
    public boolean isDefined(K key)
    {
        return dictionary.containsKey(key);
    }

    public FileTable() {
        this.dictionary = new ConcurrentHashMap<K,V>();
    }

    public String toString() {
        StringBuilder content = new StringBuilder();
        Iterator<HashMap.Entry<K, V>> it = dictionary.entrySet().iterator();

        while (it.hasNext()) {
            HashMap.Entry<K, V> entry = it.next();
            content.append(entry.getKey())
                    .append("\n");
        }
        return content.toString();
    }
    public void setValues(Map<K,V> new_values)
    {
        this.dictionary=new_values;
    }
    public Map<K, V> getValues() {
        return dictionary;
    }
}

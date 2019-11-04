package Model.Dict;

import Exceptions.MyExceptions;

import java.util.HashMap;
import java.util.Iterator;

public class FileTable<K,V> implements  MyIDictionary<K,V> {
    HashMap<K,V> dictionary;
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
        this.dictionary = new HashMap<K,V>();
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
}

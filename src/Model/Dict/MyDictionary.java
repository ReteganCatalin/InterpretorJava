package Model.Dict;

import Exceptions.MyExceptions;

import java.util.HashMap;
import java.util.Iterator;

public class MyDictionary<K,V> implements  MyIDictionary<K,V> {
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
        dictionary.put(key,value);
    }
    public boolean isDefined(K key)
    {
        return dictionary.containsKey(key);
    }

    public MyDictionary() {
        this.dictionary = new HashMap<K,V>();
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        Iterator<HashMap.Entry<K, V>> it = dictionary.entrySet().iterator();

        while (it.hasNext()) {
            HashMap.Entry<K, V> entry = it.next();
            content.append("(")
                    .append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append(")")
                    .append("\n");
        }
        return content.toString();
    }

    @Override
    public void setValues(HashMap<K,V> new_values) {
        dictionary=new_values;
    }
    public HashMap<K,V>  getValues()
    {
        return dictionary;
    }

}
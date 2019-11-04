package Model.List;

import Exceptions.MyExceptions;

import java.util.ArrayList;

public class MyList<T> implements  MyIList<T> {
    ArrayList<T> vector;

    public MyList() {
        vector=new ArrayList<T>();
    }

    public void add(T item)
    {
        vector.add(item);
    }
    public void remove(T item) throws MyExceptions
    {
        try
        {
            vector.remove(item);
        }
        catch(Exception except)
        {
            throw new MyExceptions("Couldn't find something for removal");
        }
    }

    public T get(int index) throws MyExceptions
    {
        try {
            return vector.get(index);
        }
        catch(Exception except)
        {
            throw new MyExceptions("Couldn't find anything at that position");
        }
    }

    public int size()
    {
        return vector.size();
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        for (T item: vector) {
            content.append(item).append("\n");
        }
        return content.toString();
    }
}

package Model.Dict;


import Exceptions.MyExceptions;

import java.util.Map;

public interface MyIHeap <V> {

    public void update(int address, V value);
    public boolean isDefined(int key);
    public void setValues(Map<Integer,V> new_values);
    public Map<Integer,V> getValues();
    V lookup(int address ) throws MyExceptions;
    public int allocate(V value);
    public void deallocate(int address);
}

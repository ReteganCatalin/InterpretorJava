package Model.List;


import Exceptions.MyExceptions;

import java.util.List;

public interface MyIList<T> {
    void add(T item);
    void remove(T item) throws MyExceptions;
    T get(int index) throws MyExceptions;
    List<T> getValues();
    int size();
    String toString();
}
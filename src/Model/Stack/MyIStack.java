package Model.Stack;

import Exceptions.MyExceptions;

public interface MyIStack<T> {
    T pop() throws MyExceptions;
    void push(T v);
    public boolean isEmpty();

}

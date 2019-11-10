package Model.Stack;

import Exceptions.MyExceptions;

import java.util.Stack;

public interface MyIStack<T> {
    T pop() throws MyExceptions;
    void push(T v);
    public boolean isEmpty();
    public Stack<T> getStack();

}

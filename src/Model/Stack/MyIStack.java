package Model.Stack;

import Exceptions.MyExceptions;
import Model.Stmt.IStmt;

public interface MyIStack<T> {
    T pop() throws MyExceptions;
    void push(T v);
    public boolean isEmpty();

}

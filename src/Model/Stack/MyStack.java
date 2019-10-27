package Model.Stack;

import Exceptions.MyExceptions;

import java.util.EmptyStackException;
import java.util.Stack;

public class MyStack<T> implements  MyIStack<T>{
    Stack<T> stack;

    public T pop() throws MyExceptions
    {
        try {
            return stack.pop();
        }
        catch(EmptyStackException empty)
        {
            throw new MyExceptions("Stack is empty");
        }
    }

    public void push(T statement)
    {
        stack.push(statement);
    }

    public boolean isEmpty()
    {
        return  stack.isEmpty();
    }

    public MyStack() {
        this.stack = new Stack<T>();
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();

        for (T item: stack) {
            content.insert(0,"|").insert(0,item);
    }
        return content.toString();
    }
}

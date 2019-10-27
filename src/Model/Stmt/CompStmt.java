package Model.Stmt;

import Model.ProgramState;
import Model.Stack.MyIStack;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt second;
    public ProgramState execute(ProgramState state)
    {
        MyIStack<IStmt> stack=state.getStack();
        stack.push(second);
        stack.push(first);
        return state;
    }
    public String toString()
    {
        return "{"+first.toString() + ";" + second.toString()+"}";
    }

    public CompStmt(IStmt f,IStmt s) {
        first=f;
        second=s;
    }
}

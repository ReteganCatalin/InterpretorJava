package Model.Stmt;

import Model.ProgramState;
import Model.Stack.MyIStack;

public class CompoundStatement implements IStatement {
    IStatement first;
    IStatement second;
    public ProgramState execute(ProgramState state)
    {
        MyIStack<IStatement> stack=state.getStack();
        stack.push(second);
        stack.push(first);
        return state;
    }
    public String toString()
    {
        return "{"+first.toString() + ";" + second.toString()+"}";
    }

    public CompoundStatement(IStatement f, IStatement s) {
        first=f;
        second=s;
    }
}

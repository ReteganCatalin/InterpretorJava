package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Type.Type;

public class CompoundStatement implements IStatement {
    IStatement first;
    IStatement second;
    public ProgramState execute(ProgramState state)
    {
        MyIStack<IStatement> stack=state.getStack();
        stack.push(second);
        stack.push(first);
        return null;
    }
    public String toString()
    {
        return "{"+first.toString() + ";" + second.toString()+"}";
    }
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnvironment) throws MyExceptions
    {

        return second.typeCheck(first.typeCheck(typeEnvironment));
    }

    public CompoundStatement(IStatement f, IStatement s) {
        first=f;
        second=s;
    }

    public IStatement deepCopy()
    {
        return new CompoundStatement(first.deepCopy(),second.deepCopy());
    }
}

package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.ProgramState;
import Model.Type.Type;

public class NopStatement implements IStatement {
    public NopStatement() {
    }
    public ProgramState execute(ProgramState state){
        for(int i=0;i<5;i++)
        {
            i++;
        }
        return null;
    }
    public IStatement deepCopy()
    {
        return new NopStatement();
    }
    @Override
    public String toString() {
        return "";
    }
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyExceptions
    {
        return typeEnv;
    }
}

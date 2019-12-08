package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.ProgramState;
import Model.Type.Type;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyExceptions;
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyExceptions;
    IStatement deepCopy();
}

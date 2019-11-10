package Model.Stmt;
import Exceptions.MyExceptions;
import Model.ProgramState;

import java.io.IOException;

public interface IStatement {
    public ProgramState execute(ProgramState state) throws MyExceptions, IOException;
}

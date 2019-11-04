package Model.Stmt;
import Exceptions.MyExceptions;
import Model.ProgramState;

import java.io.IOException;

public interface IStmt {
    public ProgramState execute(ProgramState state) throws MyExceptions, IOException;
}

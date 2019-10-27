package Model.Stmt;
import Exceptions.MyExceptions;
import Model.ProgramState;

public interface IStmt {
    public ProgramState execute(ProgramState state)throws MyExceptions;
}

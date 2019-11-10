package Model.Stmt;
import Exceptions.MyExceptions;
import Model.ProgramState;

public interface IStatement {
    public ProgramState execute(ProgramState state) throws MyExceptions;
}

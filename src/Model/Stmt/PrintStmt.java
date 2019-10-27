package Model.Stmt;
import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Exp;
import Model.List.MyIList;
import Model.ProgramState;
import Model.Stmt.IStmt;
import Model.Value.Value;

public class PrintStmt implements IStmt
{
    Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString()
    {
        return "print(" +exp.toString()+")";
    }

    public ProgramState execute(ProgramState state) throws MyExceptions
    {
        MyIList<Value> out=state.getOutput();
        MyIDictionary<String,Value>  symTable=state.getSymTable();
        out.add(exp.eval(symTable));
        return state;
    }
}

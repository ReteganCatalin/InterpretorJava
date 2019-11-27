package Model.Stmt;
import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Expression;
import Model.List.MyIList;
import Model.ProgramState;
import Model.Value.Value;

public class PrintStatement implements IStatement
{
    Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString()
    {
        return "print(" + expression.toString()+")";
    }

    public ProgramState execute(ProgramState state) throws MyExceptions
    {
        MyIList<Value> out=state.getOutput();
        MyIDictionary<String,Value>  symTable=state.getSymbolsTable();
        out.add(expression.eval(symTable,state.getHeapTable() ));
        return null;
    }

    public IStatement deepCopy()
    {
        return new PrintStatement(expression.deepCopy());
    }
}

package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Expression;
import Model.List.MyIList;
import Model.ProgramState;
import Model.Type.Type;
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
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyExceptions
    {
        Type type = expression.typeCheck(typeEnv);
        return typeEnv;
    }

    public IStatement deepCopy()
    {
        return new PrintStatement(expression.deepCopy());
    }
}

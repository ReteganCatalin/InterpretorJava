package Model.Exp;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Type.Type;
import Model.Value.Value;

public class VariableExpression implements Expression
{
    String id;
    public Value eval(MyIDictionary<String, Value> symbolTable, MyIDictionary<Integer, Value> Heap) throws MyExceptions
    {
        return symbolTable.lookup(id);
    }
    @Override
    public Type typeCheck(MyIDictionary<String,Type> typeEnv) throws MyExceptions
    {
        return typeEnv.lookup(id);
    }

    public VariableExpression(String id) {
        this.id = id;
    }

    public Expression deepCopy()
    {
        return new VariableExpression(new String(id));
    }

    @Override
    public String toString() {
        return id;
    }
}

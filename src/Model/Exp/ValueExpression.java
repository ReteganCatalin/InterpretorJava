package Model.Exp;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Type.Type;
import Model.Value.Value;

public class ValueExpression implements Expression {
    Value e;
    public Value eval(MyIDictionary<String, Value> symbolTable, MyIDictionary<Integer, Value> Heap)
    {
        return e;
    }

    public ValueExpression(Value e) {
        this.e = e;
    }
    @Override
    public Type typeCheck(MyIDictionary<String,Type> typeEnv) throws MyExceptions
    {
        return e.getType();
    }

    @Override
    public String toString()
    {
        return e.toString();
    }

    public Expression deepCopy()
    {
        return new ValueExpression(e.deepCopy());
    }
}

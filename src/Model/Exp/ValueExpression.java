package Model.Exp;

import Model.Dict.MyIDictionary;
import Model.Value.Value;

public class ValueExpression implements Expression {
    Value e;
    public Value eval(MyIDictionary<String,Value> tbl)
    {
        return e;
    }

    public ValueExpression(Value e) {
        this.e = e;
    }

    @Override
    public String toString()
    {
        return e.toString();
    }
}

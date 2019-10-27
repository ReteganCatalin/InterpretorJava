package Model.Exp;

import Model.Dict.MyIDictionary;
import Model.Value.Value;

public class ValueExp implements Exp {
    Value e;
    public Value eval(MyIDictionary<String,Value> tbl)
    {
        return e;
    }

    public ValueExp(Value e) {
        this.e = e;
    }

    @Override
    public String toString()
    {
        return e.toString();
    }
}

package Model.Exp;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Value.Value;

public class VariableExpression implements Expression
{
    String id;
    public Value eval(MyIDictionary<String,Value> tbl) throws MyExceptions
    {
        return tbl.lookup(id);
    }

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}

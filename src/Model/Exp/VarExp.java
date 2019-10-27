package Model.Exp;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Value.Value;

public class VarExp implements Exp
{
    String id;
    public Value eval(MyIDictionary<String,Value> tbl) throws MyExceptions
    {
        return tbl.lookup(id);
    }

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}

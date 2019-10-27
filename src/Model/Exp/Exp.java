package Model.Exp;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Value.Value;

public interface Exp {
    Value eval(MyIDictionary<String, Value> tbl) throws MyExceptions;
}

package Model.Exp;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Value.Value;

public interface Expression {
    Value eval(MyIDictionary<String, Value> symbolTable, MyIDictionary<Integer, Value> Heap) throws MyExceptions;
    Expression deepCopy();
}

package Model.Exp;

import Exceptions.MyExceptions;
import Model.Dict.Heap;
import Model.Dict.MyIDictionary;
import Model.Dict.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;


public interface Expression {
    Value eval(MyIDictionary<String, Value> symbolTable, MyIHeap< Value> Heap) throws MyExceptions;
    Type typeCheck(MyIDictionary<String,Type> typeEnvironment) throws MyExceptions;
    Expression deepCopy();
}

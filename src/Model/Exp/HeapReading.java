package Model.Exp;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Value.ReferenceValue;
import Model.Value.Value;

public class HeapReading implements Expression {
    Expression expression;

    public HeapReading(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symbolTable, MyIDictionary<Integer, Value> Heap) throws MyExceptions {

        if(expression.eval(symbolTable,Heap) instanceof ReferenceValue)
        {
            int address=((ReferenceValue) expression.eval(symbolTable,Heap)).getAddress();
            Value heap_value= Heap.lookup(address);
            return heap_value;
        }
        throw new MyExceptions("Not a reference Value");
    }

    public Expression deepCopy()
    {
        return new HeapReading( expression.deepCopy() );
    }


    public String toString() {
        return "HeapReading (" + expression.toString() + ')';
    }
}

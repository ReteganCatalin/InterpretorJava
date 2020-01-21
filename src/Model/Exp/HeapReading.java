package Model.Exp;

import Exceptions.MyExceptions;
import Model.Dict.Heap;
import Model.Dict.MyIDictionary;
import Model.Dict.MyIHeap;
import Model.Type.ReferenceType;
import Model.Type.Type;
import Model.Value.ReferenceValue;
import Model.Value.Value;

public class HeapReading implements Expression {
    Expression expression;

    public HeapReading(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symbolTable, MyIHeap <Value>Heap) throws MyExceptions {

        if(expression.eval(symbolTable,Heap) instanceof ReferenceValue)
        {
            int address=((ReferenceValue) expression.eval(symbolTable,Heap)).getAddress();
            Value heap_value= Heap.lookup(address);
            return heap_value;
        }
        throw new MyExceptions("Not a reference Value");
    }

    @Override
    public Type typeCheck(MyIDictionary<String,Type> typeEnv) throws MyExceptions {
        Type type = expression.typeCheck(typeEnv);
        if (type instanceof ReferenceType)
        {
            ReferenceType referenced = (ReferenceType) type;
            return referenced.getReferencedType();
        }
        else throw new MyExceptions("the rH argument is not a Reference Type");
    }
        public Expression deepCopy()
    {
        return new HeapReading( expression.deepCopy() );
    }


    public String toString() {
        return "HeapReading (" + expression.toString() + ')';
    }
}

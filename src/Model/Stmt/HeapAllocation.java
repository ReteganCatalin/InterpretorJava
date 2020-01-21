package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Dict.MyIHeap;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Type.*;
import Model.Value.*;

public class HeapAllocation implements IStatement {
    String variable_name;
    Expression expression;

    public HeapAllocation(String variable_name, Expression expression) {
        this.variable_name = variable_name;
        this.expression = expression;
    }
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyExceptions
    {
        Type typevar = typeEnv.lookup(variable_name);
        Type typexp = expression.typeCheck(typeEnv);
        if(typevar instanceof ReferenceType) {
            if (typevar.equals(new ReferenceType(typexp)))
                return typeEnv;
            else
                throw new MyExceptions("HeapAllocation: right hand side and left hand side have different types ");
        }
        else
            throw new MyExceptions("HeapAllocation: variable type not a reference type");
    }

    @Override
    public synchronized ProgramState execute(ProgramState state) throws MyExceptions{
        MyIDictionary<String, Value> symbolTabel = state.getSymbolsTable();
        MyIHeap<Value> Heap= state.getHeapTable();
        if (symbolTabel.isDefined(variable_name)) {
            if (symbolTabel.lookup(variable_name).getType() instanceof  ReferenceType) {
                Value expression_value =  expression.eval(symbolTabel,Heap );
                ReferenceType referenced=(ReferenceType)symbolTabel.lookup(variable_name).getType();
                if(referenced.getReferencedType().equals(expression_value.getType())) {
                    Value new_reference;
                    if (referenced.getReferencedType() instanceof IntType)
                        new_reference=new IntValue(((IntValue) expression_value).getValue()) ;
                    else if(referenced.getReferencedType() instanceof BoolType)
                        new_reference=new BoolValue(((BoolValue) expression_value).getValue()) ;
                    else if( referenced.getReferencedType() instanceof StringType)
                        new_reference=new StringValue(((StringValue) expression_value).getValue()) ;
                    else
                        new_reference=new ReferenceValue(((ReferenceValue) expression_value).getAddress(),((ReferenceValue) expression_value).getLocationType());
                    int address=Heap.allocate(new_reference);
                    ReferenceValue ref_value= (ReferenceValue)symbolTabel.lookup(variable_name);
                    ref_value.setAddress(address);
                }
                else
                    {
                    throw new MyExceptions("Not the same type");
                    }
                }
        }
        return null;
    }
    public IStatement deepCopy()
    {
        return new HeapAllocation(new String(variable_name),expression.deepCopy());
    }
    @Override
    public String toString() {
        return "new (" + variable_name + ","
                + expression.toString() +
                ')';
    }
}

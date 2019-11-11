package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.ReferenceType;
import Model.Type.StringType;
import Model.Value.*;

public class HeapAllocation implements IStatement {
    String variable_name;
    Expression expression;

    public HeapAllocation(String variable_name, Expression expression) {
        this.variable_name = variable_name;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExceptions{
        MyIDictionary<String, Value> symbolTabel = state.getSymbolsTable();
        MyIDictionary<Integer, Value> Heap = state.getHeapTable();
        if (symbolTabel.isDefined(variable_name)) {

            if (symbolTabel.lookup(variable_name).getType() instanceof  ReferenceType) {
                Value expression_value =  expression.eval(symbolTabel,Heap );
                ReferenceType referenced=(ReferenceType)symbolTabel.lookup(variable_name).getType();
                if(referenced.getReferencedType().equals(expression_value.getType())) {
                    Boolean found=false;
                    int index=1;
                    while(!found) {
                        if(Heap.isDefined(index)) {
                            index++;
                        }
                        else {
                            found=true;
                        }
                    }
                    Value new_reference;
                    if (referenced.getReferencedType() instanceof IntType)
                        new_reference=new IntValue(((IntValue) expression_value).getValue()) ;
                    else if(referenced.getReferencedType() instanceof BoolType)
                        new_reference=new BoolValue(((BoolValue) expression_value).getValue()) ;
                    else if( referenced.getReferencedType() instanceof StringType)
                        new_reference=new StringValue(((StringValue) expression_value).getValue()) ;
                    else
                        new_reference=new ReferenceValue(((ReferenceValue) expression_value).getAddress(),((ReferenceValue) expression_value).getLocationType());
                    Heap.update(index,new_reference);
                    ReferenceValue ref_value= (ReferenceValue)symbolTabel.lookup(variable_name);
                    ref_value.setAddress(index);
                }
                else
                    {
                    throw new MyExceptions("Not the same type");
                    }
                }
        }
        return state;
    }

    @Override
    public String toString() {
        return "new (" + variable_name + ","
                + expression.toString() +
                ')';
    }
}

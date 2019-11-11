package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Type.ReferenceType;
import Model.Value.ReferenceValue;
import Model.Value.Value;

public class HeapWrite implements IStatement {
    Expression expression;
    String variable_name;

    public HeapWrite(String variable_name,Expression expression) {
        this.expression = expression;
        this.variable_name = variable_name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyExceptions {
        MyIDictionary<String, Value> symbolTabel = state.getSymbolsTable();
        MyIDictionary<Integer,Value> Heap = state.getHeapTable();
        if(symbolTabel.isDefined(variable_name)) {
            if(symbolTabel.lookup(variable_name).getType() instanceof ReferenceType) {
                ReferenceValue referenced_value=(ReferenceValue)symbolTabel.lookup(variable_name);
                int address=referenced_value.getAddress();
                if (Heap.isDefined(address)) {
                    if(expression.eval(symbolTabel, Heap).getType().equals(Heap.lookup(address).getType())) {
                        Heap.update(address,expression.eval(symbolTabel, Heap));
                        return state;
                    }
                    else {
                        throw new MyExceptions("Not the same Type");
                    }

                }
                else
                {
                    throw new MyExceptions("The address is not in use");
                }
            }
            throw new MyExceptions("Not a reference Value");
        }
        else
            throw new MyExceptions("The variable is not defined");

    }

    public String toString() {
        return "HeapWrite (" +variable_name+","+expression.toString() + ')';
    }
}


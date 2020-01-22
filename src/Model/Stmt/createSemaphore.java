package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Dict.MyIHeap;
import Model.Dict.MyISemaphore;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import javafx.util.Pair;

import java.util.Vector;

public class createSemaphore implements IStatement {
    String id;
    Expression expression;
    public String toString()
    {
        return "CreateSemaphore("+id+","+ expression.toString()+")";
    }

    public createSemaphore(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    public synchronized ProgramState execute(ProgramState state) throws MyExceptions {
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();
        MyIHeap<Value> heap= state.getHeapTable();
        MyISemaphore<Pair<Integer, Vector<Integer>>> semaphoreTable=state.getSemaphoreTabel();
        Value val = expression.eval(symTbl,heap);
        if (val.getType().equals(new IntType())) {
            Type typId;
            int value=((IntValue)val).getValue();
            typId = (symTbl.lookup(id)).getType();
            Vector<Integer> v=new Vector<>();
            Pair pair= new Pair<>(value,v);
            int address=semaphoreTable.allocate(pair);
            if (typId.equals(new IntType())) {
                symTbl.update(id, new IntValue(address));
            } else {
                throw new MyExceptions("declared type of variable" + id + " is not int");
            }

        }
        else{
            throw new MyExceptions("the used variable" + id + " was not declared before");
        }
        return null;
    }
    @Override
    public MyIDictionary<String,Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyExceptions
    {
        Type VariableType = typeEnv.lookup(id);
        Type ExpressionType = expression.typeCheck(typeEnv);
        if (VariableType.equals(ExpressionType) && VariableType.equals(new IntType()))
            return typeEnv;
        else
            throw new MyExceptions("Create Semaphore: right hand side and left hand side have different types or are not ints");
    }
    public IStatement deepCopy()
    {
        return new createSemaphore(new String(id),expression.deepCopy());
    }


}

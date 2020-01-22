package Model.Stmt;


import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Dict.MyIHeap;
import Model.Dict.MyISemaphore;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import javafx.util.Pair;

import java.util.Vector;

public class Acquire implements IStatement {
    String id;
    public String toString()
    {
        return "Acquire("+id+")";
    }

    public Acquire(String id) {
        this.id = id;
    }

    public synchronized ProgramState execute(ProgramState state) throws MyExceptions {
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();
        MyIStack<IStatement> stack=state.getStack();
        MyIHeap<Value> heap= state.getHeapTable();
        MyISemaphore<Pair<Integer, Vector<Integer>>> semaphoreTable=state.getSemaphoreTabel();
        int threadID=state.getID();
        Value ValueId = (symTbl.lookup(id));

        if (ValueId.getType().equals(new IntType())) {
            IntValue i=(IntValue) ValueId;
            Pair<Integer,Vector<Integer>> pair=semaphoreTable.lookup(i.getValue());
            int length = pair.getValue().size();
            if(pair.getKey()>length) {
                if(pair.getValue().contains(new Integer(threadID))) {
                }
                else
                {
                    pair.getValue().add(new Integer(threadID));
                }
            }
            else
            {
                stack.push(this);
            }
        } else
            {
                throw new MyExceptions("declared type of variable" + id + " is not int");
            }
        return null;
    }
    @Override
    public MyIDictionary<String,Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyExceptions
    {
        Type VariableType = typeEnv.lookup(id);
        if(VariableType.equals(new IntType()))
            return typeEnv;
        else
            throw new MyExceptions("Acquire error: "+id+" not an int type");

    }
    public IStatement deepCopy()
    {
        return new Acquire(new String(id));
    }


}
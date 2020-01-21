package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyDictionary;
import Model.Dict.MyIDictionary;
import Model.Dict.MyIHeap;
import Model.List.MyIList;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.Value;

import java.io.BufferedReader;
import java.util.Map;

public class ForkStatement implements IStatement {
    IStatement forked_statement;
    public ForkStatement(IStatement fork)
    {
        this.forked_statement=fork;
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(forked_statement.deepCopy());
    }

    public ProgramState execute(ProgramState state)
    {
        MyIDictionary<String, Value> symbolsTable = state.getSymbolsTable();
        MyIHeap<Value> heap = state.getHeapTable();
        MyIList<Value> output = state.getOutput();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyDictionary<String, Value> newSymbolsTable = new MyDictionary<String, Value>();
        for (Map.Entry<String, Value> entry: symbolsTable.getValues().entrySet()) {
            newSymbolsTable.update(new String(entry.getKey()), entry.getValue().deepCopy());
        }
        ProgramState new_state=new ProgramState(forked_statement);
        new_state.setSymbolsTable(newSymbolsTable);
        new_state.setFileTable(fileTable);
        new_state.setHeapTable(heap);
        new_state.setOut(output);
        return new_state;
    }
    @Override
    public  MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyExceptions {
        MyDictionary<String, Type> newEnv=new MyDictionary<>();
        for (Map.Entry<String, Type> entry: typeEnv.getValues().entrySet()) {
            newEnv.update(entry.getKey(), entry.getValue().deepCopy());
        }
        forked_statement.typeCheck(newEnv);
        return typeEnv;
    }


    @Override
    public String toString()
    {
        return "fork(" + forked_statement + ")";
    }
}

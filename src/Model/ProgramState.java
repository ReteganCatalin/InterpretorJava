package Model;

import Model.Dict.FileTable;
import Model.Dict.Heap;
import Model.Dict.MyDictionary;
import Model.Dict.MyIDictionary;
import Model.List.MyIList;
import Model.List.MyList;
import Model.Stack.MyIStack;
import Model.Stack.MyStack;
import Model.Stmt.IStatement;
import Model.Value.Value;

import java.io.BufferedReader;

public class ProgramState {
    MyIStack<IStatement> statements;
    MyIDictionary<String, Value> symbolsTable;
    MyIList<Value> out;
    MyIDictionary<String, BufferedReader> fileTable;
    MyIDictionary<Integer, Value> HeapTable;
    IStatement originalProgram;

    public MyIStack<IStatement> getStack()
    {
        return statements;
    }
    public MyIList<Value> getOutput() { return out; }
    public MyIDictionary<String,Value> getSymbolsTable() {return symbolsTable;}

    public ProgramState(IStatement statement) {
        this.statements = new MyStack<IStatement>();
        this.symbolsTable = new MyDictionary<String, Value>();
        this.out = new MyList<Value>();
        this.fileTable=new FileTable<String,BufferedReader>();
        this.HeapTable=new Heap<Integer, Value>();
        originalProgram=statement;
        statements.push(statement);


    }

    public MyIDictionary<Integer, Value> getHeapTable() {
        return HeapTable;
    }

    public void setHeapTable(MyIDictionary<Integer, Value> heapTable) {
        HeapTable = heapTable;
    }

    public void setStatements(MyIStack<IStatement> statements) {
        this.statements = statements;
    }

    public void setSymbolsTable(MyIDictionary<String, Value> symbolsTable) {
        this.symbolsTable = symbolsTable;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public MyIDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIDictionary<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    @Override
    public String toString() {
        return "ProgramState:" +
                "\n" +
                "ExeStack:\n" + statements +"\n"+
                "SymbolsTable:\n" + symbolsTable + "\n" +
                "Out:\n" + out +"\n"+
                "FileTable:\n" + fileTable +"\n"+
                "Heap:\n" + HeapTable +"\n";
    }

    public ProgramState GetCurrentState()
    {
        return this;
    }
}

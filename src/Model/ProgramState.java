package Model;

import Exceptions.MyExceptions;
import Model.Dict.*;
import Model.List.MyIList;
import Model.List.MyList;
import Model.Stack.MyIStack;
import Model.Stack.MyStack;
import Model.Stmt.IStatement;
import Model.Value.Value;

import java.io.BufferedReader;

public class ProgramState {
    private MyIStack<IStatement> statements;
    private MyIDictionary<String, Value> symbolsTable;
    private MyIList<Value> out;
    private MyIDictionary<String, BufferedReader> fileTable;
    private MyIHeap<Value>  HeapTable;
    private IStatement originalProgram;
    private int identificator;
    private static int lastID;
    public MyIStack<IStatement> getStack()
    {
        return statements;
    }
    public MyIList<Value> getOutput() { return out; }
    public MyIDictionary<String,Value> getSymbolsTable() {return symbolsTable;}
    public Boolean isNotCompleted()
    {
        if(statements.isEmpty())
            return false;
        return true;
    }

    public int getID()
    {
        return identificator;
    }


    public ProgramState(IStatement statement) {
        this.statements = new MyStack<IStatement>();
        this.symbolsTable = new MyDictionary<String, Value>();
        this.out = new MyList<Value>();
        this.fileTable=new FileTable<String,BufferedReader>();
        this.HeapTable=new Heap<Value>();
        originalProgram=statement;
        statements.push(statement);
        identificator=getnewID();

    }


    public MyIHeap <Value> getHeapTable() {
        return HeapTable;
    }

    public void setHeapTable(MyIHeap <Value> heapTable) {
        HeapTable = heapTable;
    }

    public IStatement getStatement() {
        return originalProgram;
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

    public ProgramState oneStep() throws MyExceptions {
        if(statements.isEmpty())
        {
            throw new MyExceptions("Stack is empty no more executions");
        }
        IStatement currentStatement = statements.pop();
        return currentStatement.execute(this);

    }
    public static synchronized int getnewID()
    {
        lastID++;
        return lastID;
    }
    @Override
    public String toString() {
        return "ProgramState with id: " + identificator +
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

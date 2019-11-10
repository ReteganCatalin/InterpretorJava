package Model;

import Model.Dict.FileTable;
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
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIDictionary<String, BufferedReader> fileTable;
    IStatement originalProgram;

    public MyIStack<IStatement> getStack()
    {
        return statements;
    }
    public MyIList<Value> getOutput() { return out; }
    public MyIDictionary<String,Value> getSymTable() {return symTable;}

    public ProgramState(IStatement statement) {
        this.statements = new MyStack<IStatement>();
        this.symTable = new MyDictionary<String, Value>();
        this.out = new MyList<Value>();
        this.fileTable=new FileTable<String,BufferedReader>();
        originalProgram=statement;
        statements.push(statement);


    }

    public void setStatements(MyIStack<IStatement> statements) {
        this.statements = statements;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
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
                "SymTable:\n" + symTable + "\n" +
                "Out:\n" + out +"\n"+
                "FileTable:\n" + fileTable +"\n";
    }

    public ProgramState GetCurrentState()
    {
        return this;
    }
}

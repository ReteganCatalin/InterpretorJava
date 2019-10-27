package Model;
import Model.Dict.MyIDictionary;
import Model.List.MyIList;
import Model.Stack.MyIStack;
import Model.Stack.MyStack;
import Model.Stmt.IStmt;
import Model.Value.Value;

public class ProgramState {
    MyIStack<IStmt> statements;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;

    public MyIStack<IStmt> getStack()
    {
        return statements;
    }
    public MyIList<Value> getOutput() { return out; }
    public MyIDictionary<String,Value> getSymTable() {return symTable;}

    public ProgramState(MyIStack<IStmt> statements, MyIDictionary<String, Value> symTable, MyIList<Value> out) {
        this.statements = statements;
        this.symTable = symTable;
        this.out = out;
    }

    public void setStatements(MyIStack<IStmt> statements) {
        this.statements = statements;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return "ProgramState:" +
                "\n" +
                "ExeStack:" + statements +"\n"+
                "SymTable:" + symTable + "\n" +
                "Out:" + out +"\n";
    }

    public ProgramState GetCurrentState()
    {
        return this;
    }
}

package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.Value;

public class WhileStatement implements IStatement {
    Expression expression;
    IStatement execute_this;

    public WhileStatement(Expression e,IStatement execute_this ) {
        expression = e;
        this.execute_this=execute_this;

    }

    public String toString() {
        return "while(" + expression.toString()  + ") do:"+execute_this.toString();
    }

    public ProgramState execute(ProgramState state) throws MyExceptions {
        MyIStack<IStatement> stack = state.getStack();
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();
        MyIDictionary<Integer, Value> heap = state.getHeapTable();
        Value Cond = expression.eval(symTbl, heap);
        if (!Cond.getType().equals(new BoolType())) {
            throw new MyExceptions("No a boolean condition");
        } else {
            BoolValue BoolCond = (BoolValue) Cond;
            if (BoolCond.getValue()) {
                stack.push(this);
                stack.push(execute_this);
            }
        }
        return state;
    }


}

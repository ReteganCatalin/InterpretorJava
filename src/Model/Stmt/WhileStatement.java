package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyDictionary;
import Model.Dict.MyIDictionary;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

import java.util.Map;

public class WhileStatement implements IStatement {
    Expression expression;
    IStatement execute_this;

    public WhileStatement(Expression e,IStatement execute_this ) {
        expression = e;
        this.execute_this=execute_this;

    }
    @Override
    public  MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyExceptions {
        Type ExpressionType = expression.typeCheck(typeEnv);
        if (ExpressionType.equals(new BoolType())) {
            MyDictionary<String, Type> newEnv=new MyDictionary<>();
            for (Map.Entry<String, Type> entry: typeEnv.getValues().entrySet()) {
                newEnv.update(entry.getKey(), entry.getValue().deepCopy());
            }
            execute_this.typeCheck(newEnv);
            return typeEnv;
        }
        else
            throw new MyExceptions("The condition of IF has not the type bool");
    }

     @Override
     public IStatement deepCopy()
     {
         return new WhileStatement(expression.deepCopy(),execute_this.deepCopy());
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
        return null;
    }


}

package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class IfStatement implements IStatement {
    Expression expression;
    IStatement thenS;
    IStatement elseS;
    public IfStatement(Expression e, IStatement t, IStatement el)
    {
        expression =e;
        thenS=t;
        elseS=el;
    }

    @Override
    public  MyIDictionary<String,Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyExceptions {
        Type ExpressionType = expression.typeCheck(typeEnv);
        if (ExpressionType.equals(new BoolType())) {
            thenS.typeCheck(typeEnv);
            elseS.typeCheck(typeEnv);
            return typeEnv;
        }
        else
            throw new MyExceptions("The condition of IF does not have the bool type");
    }
    @Override
    public String toString()
    {
        return "IF("+ expression.toString()+") THEN(" +thenS.toString()+")ELSE("+elseS.toString()+")";
    }
    public ProgramState execute(ProgramState state) throws MyExceptions
    {
        MyIStack<IStatement> stack=state.getStack();
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();
        MyIDictionary<Integer, Value> heap = state.getHeapTable();
        Value Cond= expression.eval(symTbl,heap );
        if(!Cond.getType().equals(new BoolType()))
        {
            throw new MyExceptions("No a boolean condition");
        }
        else
        {
            BoolValue BoolCond = (BoolValue) Cond;
            if(BoolCond.getValue())
            {
                stack.push(thenS);
            }
            else
            {
                stack.push(elseS);
            }
        }
        return null;
    }
    public IStatement deepCopy()
    {
        return new IfStatement(expression.deepCopy(),thenS.deepCopy(),elseS.deepCopy());
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void setThenS(IStatement thenS) {
        this.thenS = thenS;
    }

    public void setElseS(IStatement elseS) {
        this.elseS = elseS;
    }
}

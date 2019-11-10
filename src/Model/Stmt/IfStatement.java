package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Type.BoolType;
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
    public String toString()
    {
        return "IF("+ expression.toString()+") THEN(" +thenS.toString()+")ELSE("+elseS.toString()+")";
    }
    public ProgramState execute(ProgramState state) throws MyExceptions
    {
        MyIStack<IStatement> stack=state.getStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        Value Cond= expression.eval(symTbl);
        if(Cond.getType().equals(new BoolType())==false)
        {
            throw new MyExceptions("No a boolean condition");
        }
        else
        {
            BoolValue BoolCond = (BoolValue) Cond;
            if(BoolCond.getValue()==true)
            {
                stack.push(thenS);
            }
            else
            {
                stack.push(elseS);
            }
        }
        return state;
    }

    public IfStatement(IfStatement other) {
        this.expression = other.expression;
        this.thenS = other.thenS;
        this.elseS = other.elseS;
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

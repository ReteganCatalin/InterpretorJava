package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Exp;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Value.BoolValue;
import Model.Value.Value;

public class IfStmt implements IStmt{
    Exp exp;
    IStmt thenS;
    IStmt elseS;
    public IfStmt(Exp e, IStmt t, IStmt el)
    {
        exp=e;
        thenS=t;
        elseS=el;
    }
    public String toString()
    {
        return "IF("+ exp.toString()+") THEN(" +thenS.toString()+")ELSE("+elseS.toString()+")";
    }
    public ProgramState execute(ProgramState state) throws MyExceptions
    {
        MyIStack<IStmt> stack=state.getStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        Value Cond=exp.eval(symTbl);
        if(Cond.getType() instanceof BoolValue)
        {
            throw new MyExceptions("No a boolean condition");
        }
        else
        {
            BoolValue BoolCond = (BoolValue) Cond;
            if(BoolCond.getVal()==true)
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
}

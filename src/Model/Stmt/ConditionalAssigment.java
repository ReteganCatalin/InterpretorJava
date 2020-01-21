package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyDictionary;
import Model.Dict.MyIDictionary;
import Model.Dict.MyIHeap;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

import java.util.Map;

public class ConditionalAssigment implements IStatement {
    Expression expression;
    String id;
    Expression thenE;
    Expression elseE;
    public ConditionalAssigment(Expression e,String v, Expression t, Expression el)
    {
        expression =e;
        thenE=t;
        elseE=el;
        id=v;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyExceptions {
        //Type ExpressionType = expression.typeCheck(typeEnv);

        //if (ExpressionType.equals(new BoolType())) {
          //  MyDictionary<String, Type> newEnv=new MyDictionary<>();
            //for (Map.Entry<String, Type> entry: typeEnv.getValues().entrySet()) {
              //  newEnv.update(entry.getKey(), entry.getValue().deepCopy());
            //}
            //thenS.typeCheck(newEnv);
            //elseS.typeCheck(newEnv);
            return typeEnv;
        //}
        //else
           // throw new MyExceptions("The condition of IF does not have the bool type");
    }
    @Override
    public String toString()
    {
        return "IF("+ expression.toString()+") THEN("+id+"="+thenE.toString()+")ELSE("+id+"="+elseE.toString()+")";
    }
    public ProgramState execute(ProgramState state) throws MyExceptions
    {
        MyIStack<IStatement> stack=state.getStack();
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();
        MyIHeap<Value> heap = state.getHeapTable();
        Value Cond= expression.eval(symTbl,heap );
        if(!Cond.getType().equals(new BoolType()))
        {
            throw new MyExceptions("Not a boolean condition");
        }
        else {
            BoolValue BoolCond = (BoolValue) Cond;
            if (BoolCond.getValue()) {
                if (thenE.eval(symTbl, heap).getType().equals((new IntType()))) {
                    Value val = thenE.eval(symTbl, heap);
                    if (symTbl.isDefined(id)) {
                        Type typId;
                        typId = (symTbl.lookup(id)).getType();
                        if (val.getType().equals(typId)) {
                            symTbl.update(id, val);
                        } else {
                            throw new MyExceptions("declared type of variable" + id + " and type of  the assigned expression do not match");
                        }

                    } else {
                        throw new MyExceptions("the used variable" + id + " was not declared before");
                    }
                }
            } else {
                if (elseE.eval(symTbl, heap).getType().equals((new IntType()))) {
                    Value val = elseE.eval(symTbl, heap);
                    if (symTbl.isDefined(id)) {
                        Type typId;
                        typId = (symTbl.lookup(id)).getType();
                        if (val.getType().equals(typId)) {
                            symTbl.update(id, val);
                        } else {
                            throw new MyExceptions("declared type of variable" + id + " and type of  the assigned expression do not match");
                        }

                    } else {
                        throw new MyExceptions("the used variable" + id + " was not declared before");
                    }
                }
            }
        }
        return null;
    }
    public IStatement deepCopy()
    {
        return new ConditionalAssigment(expression.deepCopy(),id,thenE.deepCopy(),elseE.deepCopy());
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

}


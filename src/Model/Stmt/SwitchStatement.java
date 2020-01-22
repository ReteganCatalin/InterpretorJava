package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyDictionary;
import Model.Dict.MyIDictionary;
import Model.Dict.MyIHeap;
import Model.Exp.ArithmeticExpression;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

import java.util.Map;

public class SwitchStatement implements IStatement {
    Expression expression1;
    Expression expression;
    Expression expression2;
    IStatement  case1;
    IStatement case2;
    IStatement Defaultcase;
    public SwitchStatement(Expression e,Expression e1,Expression ex2, IStatement c1, IStatement c2,IStatement dc)
    {
        expression=e;
        expression1 =e1;
        expression2=ex2;
        case1=c1;
        case2=c2;
        Defaultcase=dc;
    }

    @Override
    public  MyIDictionary<String,Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyExceptions {
        Type Expression1Type = expression1.typeCheck(typeEnv);
        Type Expression2Type = expression1.typeCheck(typeEnv);
        Type ExpressionType=expression.typeCheck(typeEnv);
        if (Expression2Type.equals(ExpressionType) && Expression1Type.equals(ExpressionType)) {
            MyDictionary<String, Type> newEnv=new MyDictionary<>();
            for (Map.Entry<String, Type> entry: typeEnv.getValues().entrySet()) {
                newEnv.update(entry.getKey(), entry.getValue().deepCopy());
            }
            case1.typeCheck(newEnv);
            case2.typeCheck(newEnv);
            Defaultcase.typeCheck(newEnv);
            return typeEnv;
        }
        else
            throw new MyExceptions("One or both conditions do not have the expression type");
    }
    @Override
    public String toString()
    {
        return "Switch("+ expression.toString()+") (case " +expression.toString()+"=="+expression1.toString()+": "+case1.toString()
                +") (case "+expression.toString()+"=="+expression2.toString()+":"+case2.toString()+")"
                +")default"+Defaultcase+")";
    }
    public ProgramState execute(ProgramState state) throws MyExceptions
    {
        MyIStack<IStatement> stack=state.getStack();
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();
        MyIHeap<Value> heap = state.getHeapTable();

        IStatement ifer=new IfStatement(new ArithmeticExpression(7,expression,expression1),case1,new IfStatement(new ArithmeticExpression(7,expression,expression2),case2,Defaultcase));
        stack.push(ifer);
        return null;
    }
    public IStatement deepCopy()
    {
        return new SwitchStatement(expression.deepCopy(),expression1.deepCopy(),expression2.deepCopy(), case1.deepCopy(), case2.deepCopy(),Defaultcase.deepCopy());
    }



}
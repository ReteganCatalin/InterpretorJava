package Model.Exp;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Dict.MyIHeap;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class LogicExpression implements Expression {
    Expression first_expression;
    Expression second_expression;
    int operand; //1-plus, 2-minus, 3-star, 4-divide

    public LogicExpression(int op, Expression e1, Expression e2) {
        this.first_expression = e1;
        this.second_expression = e2;
        this.operand = op;
    }
    //
    @Override
    public Type typeCheck(MyIDictionary<String,Type> typeEnv) throws MyExceptions {
        Type first_type, second_type;
        first_type = first_expression.typeCheck(typeEnv);
        second_type = second_expression.typeCheck(typeEnv);
        if (first_type.equals(new BoolType())) {
            if (second_type.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new MyExceptions("second operand is not an boolean");
        } else
            throw new MyExceptions("first operand is not a boolean");
    }

    public Value eval(MyIDictionary<String, Value> symbolTable, MyIHeap<Value> Heap) throws MyExceptions {
        Value v1, v2;
        v1 = first_expression.eval(symbolTable,Heap );
        if (v1.getType().equals(new BoolType())) {
            v2 = second_expression.eval(symbolTable,Heap );
            if (v2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                boolean b1, b2;
                b1 = i1.getValue();
                b2 = i2.getValue();
                if (operand == 1) return new BoolValue(b1 && b2);
                else if (operand == 2) return new BoolValue(b1 || b2);

            } else
                throw new MyExceptions("second operand is not a boolean");
        } else
            throw new MyExceptions("first operand is not a boolean");
        return new BoolValue(true);
    }

    @Override
    public String toString() {
        if (operand == 1) return first_expression.toString() + "&&" + second_expression.toString();
        else if (operand == 2) return first_expression.toString() + "||" + second_expression.toString();
        return "";
    }

    public Expression deepCopy()
    {
        return new LogicExpression(new Integer(operand), first_expression.deepCopy(), second_expression.deepCopy());
    }
}
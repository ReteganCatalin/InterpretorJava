package Model.Exp;


import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class ArithmeticExpression implements Expression {
    Expression first_expression;
    Expression second_expression;
    int operand; //1-plus, 2-minus, 3-star, 4-divide,

    public ArithmeticExpression(int operand, Expression e1, Expression second_expression) {
        this.first_expression = e1;
        this.second_expression = second_expression;
        this.operand = operand;
    }
    //
    // override


    public Value eval(MyIDictionary<String, Value> symbolTable, MyIDictionary<Integer, Value> Heap) throws MyExceptions {
        Value v1, v2;
        v1 = first_expression.eval(symbolTable,Heap);
        if (v1.getType().equals(new IntType())) {
            v2 = second_expression.eval(symbolTable,Heap );
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (operand == 1) return new IntValue(n1 + n2);
                else if (operand == 2) return new IntValue(n1 - n2);
                else if (operand == 3) return new IntValue(n1 * n2);
                else if (operand == 4) {
                    if (n2 == 0) {
                        throw new MyExceptions("division by zero");
                    } else return new IntValue(n1 / n2);
                }
                else if (operand == 5) return new BoolValue(n1 < n2);
                else if (operand == 6) return new BoolValue(n1 <= n2);
                else if (operand == 7) return new BoolValue(n1 == n2);
                else if (operand == 8) return new BoolValue(n1 != n2);
                else if (operand == 9) return new BoolValue(n1 > n2);
                else if (operand == 10) return new BoolValue(n1 >= n2);
            } else
                throw new MyExceptions("second operand is not an integer");
        } else
            throw new MyExceptions("first operand is not an integer");
        return new IntValue(0);
    }

    @Override
    public String toString() {
        if (operand == 1) return first_expression.toString() + "+" + second_expression.toString();
        else if (operand == 2) return first_expression.toString() + "-" + second_expression.toString();
        else if (operand == 3) return first_expression.toString() + "*" + second_expression.toString();
        else if (operand == 5) return first_expression.toString() + "<" + second_expression.toString();
        else if (operand == 6) return first_expression.toString() + "<=" + second_expression.toString();
        else if (operand == 7) return first_expression.toString() + "==" + second_expression.toString();
        else if (operand == 8) return first_expression.toString() + "!=" + second_expression.toString();
        else if (operand == 9) return first_expression.toString() + ">" + second_expression.toString();
        else if (operand == 10) return first_expression.toString() + ">=" + second_expression.toString();
        else if(operand ==4) return first_expression.toString() + "/" + second_expression.toString();
        else return "INVALID!";
    }

    public Expression deepCopy()
    {
        return new ArithmeticExpression( new Integer(operand),first_expression.deepCopy(), second_expression.deepCopy());
    }
}



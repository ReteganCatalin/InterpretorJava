package Model.Exp;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.Value;

public class LogicExpression implements Expression {
    Expression e1;
    Expression e2;
    int op; //1-plus, 2-minus, 3-star, 4-divide

    public LogicExpression(int op, Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    //
    // override


    public Value eval(MyIDictionary<String, Value> tbl) throws MyExceptions {
        Value v1, v2;
        v1 = e1.eval(tbl);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl);
            if (v2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                boolean b1, b2;
                b1 = i1.getValue();
                b2 = i2.getValue();
                if (op == 1) return new BoolValue(b1 && b2);
                else if (op == 2) return new BoolValue(b1 || b2);

            } else
                throw new MyExceptions("second operand is not a boolean");
        } else
            throw new MyExceptions("first operand is not a boolean");
        return new BoolValue(true);
    }

    @Override
    public String toString() {
        if (op == 1) return e1.toString() + "&&" + e2.toString();
        else if (op == 2) return e1.toString() + "||" + e2.toString();
        return "";
    }
}
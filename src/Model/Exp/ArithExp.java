package Model.Exp;


import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Type.IntType;
import Model.Value.IntValue;
import Model.Value.Value;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(int op, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    //
    // override


    public Value eval(MyIDictionary<String, Value> tbl) throws MyExceptions {
        Value v1, v2;
        v1 = e1.eval(tbl);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == 1) return new IntValue(n1 + n2);
                if (op == 2) return new IntValue(n1 - n2);
                if (op == 3) return new IntValue(n1 * n2);
                if (op == 4)
                    if (n2 == 0) {throw new MyExceptions("division by zero");}
                    else return new IntValue(n1 / n2);
            } else
                throw new MyExceptions("second operand is not an integer");
        } else
            throw new MyExceptions("first operand is not an integer");
        return new IntValue(0);
    }

    @Override
    public String toString() {
        if (op == 1) return e1.toString() + "+" + e2.toString();
        else if (op == 2) return e1.toString() + "-" + e2.toString();
        else if (op == 3) return e1.toString() + "*" + e2.toString();
        else return e1.toString() + "/" + e2.toString();
    }
}



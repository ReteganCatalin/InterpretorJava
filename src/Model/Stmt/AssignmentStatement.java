package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.Value;

public class AssignmentStatement implements IStatement {
    String id;
    Expression expression;
    public String toString()
    {
        return id+"="+ expression.toString();
    }

    public AssignmentStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    public ProgramState execute(ProgramState state) throws MyExceptions {
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();
        MyIDictionary<Integer, Value> heap = state.getHeapTable();
        Value val = expression.eval(symTbl,heap );
        if (symTbl.isDefined(id)) {
            Type typId;
            try {
                typId = (symTbl.lookup(id)).getType();
                if (val.getType().equals(typId)) {
                    symTbl.update(id, val);
                } else {
                    throw new MyExceptions("declared type of variable" + id + " and type of  the assigned expression do not match");
                }
            } catch (MyExceptions except) {

            }
        }
        else{
            throw new MyExceptions("the used variable" + id + " was not declared before");
    }
    return state;
    }

}

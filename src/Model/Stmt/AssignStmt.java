package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Exp;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Type.Type;
import Model.Value.Value;

public class AssignStmt implements  IStmt{
    String id;
    Exp exp;
    public String toString()
    {
        return id+"="+ exp.toString();
    }

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    public ProgramState execute(ProgramState state) throws MyExceptions {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        Value val = exp.eval(symTbl);
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

package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class VarDeclStmt implements IStmt
{
    String name;
    Type typ;

    public VarDeclStmt(String namer,Type t)
    {
        typ=t;
        name=namer;
    }

    @Override
    public String toString() {
        return typ + " " + name +";";

    }

    public ProgramState execute(ProgramState state) throws MyExceptions
    {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(symTbl.isDefined(name)==true)
        {
            throw new MyExceptions("It is already defined");
        }
        else
        {
            if(typ instanceof BoolType)
            {
                BoolValue bool=new BoolValue(false);
                symTbl.update(name,bool);
            }
            else
            {
                IntValue inter=new IntValue(0);
                symTbl.update(name,inter);
            }

        }
        return state;
    }
}

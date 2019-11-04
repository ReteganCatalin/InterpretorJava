package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
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
            if(typ.equals(new BoolType())==true)
            {
                BoolValue bool=(BoolValue) typ.defaultValue();
                symTbl.update(name,bool);
            }
            else if(typ.equals(new IntType())==true) {
                IntValue inter = (IntValue) typ.defaultValue();
                symTbl.update(name, inter);
            }
            else
            {
                StringValue string = (StringValue) typ.defaultValue();
                symTbl.update(name, string);
            }

        }
        return state;
    }
}

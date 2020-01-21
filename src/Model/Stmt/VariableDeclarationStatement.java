package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyDictionary;
import Model.Dict.MyIDictionary;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.*;

import java.util.Map;

public class VariableDeclarationStatement implements IStatement
{
    private String name;
    private Type typ;

    public VariableDeclarationStatement(String namer, Type t)
    {
        typ=t;
        name=namer;
    }

    @Override
    public String toString() {
        return typ + " " + name +";";

    }

    @Override
    public MyIDictionary<String,Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyExceptions
    {

        typeEnv.update(name,typ);
        return typeEnv;
    }

    public IStatement deepCopy()
    {
        return new VariableDeclarationStatement(new String(name),typ.deepCopy());
    }

    public ProgramState execute(ProgramState state) throws MyExceptions
    {
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();
        if(symTbl.isDefined(name))
        {
            throw new MyExceptions("It is already defined");
        }
        else
        {
            if(typ.equals(new BoolType()))
            {
                BoolValue bool=(BoolValue) typ.defaultValue();
                symTbl.update(name,bool);
            }
            else if(typ.equals(new IntType())) {
                IntValue inter = (IntValue) typ.defaultValue();
                symTbl.update(name, inter);
            }
            else if(typ.equals(new StringType()))
            {
                StringValue string = (StringValue) typ.defaultValue();
                symTbl.update(name, string);
            }
            else
            {
                ReferenceValue reference= (ReferenceValue) typ.defaultValue();
                symTbl.update(name,reference);
            }

        }
        return null;
    }
}

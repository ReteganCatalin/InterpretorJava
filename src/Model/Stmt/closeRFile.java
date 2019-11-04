package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Exp;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt {
    Exp exp;
    public closeRFile(Exp express) {
        exp=express;
    }
    public ProgramState execute(ProgramState state) throws MyExceptions
    {
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if (exp.eval(symTbl).getType().equals(new StringType()))
        {
            StringValue file_name=(StringValue)exp.eval(symTbl);

            if(fileTable.isDefined(file_name.getVal())==false){
                throw new MyExceptions("No key found!");
            }
            else
            {
                BufferedReader file_buffer=fileTable.lookup(file_name.getVal());
                fileTable.update(file_name.getVal(),file_buffer);
                try{
                    file_buffer.close();
                }
                catch(IOException except)
                {
                    throw new MyExceptions(except.getMessage());
                }
            }
        }

        return state;
    }

    @Override
    public String toString() {
        return "close file: "+exp.toString();
    }
}

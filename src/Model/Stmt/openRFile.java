package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Exp;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class openRFile implements IStmt {
    Exp exp;
    public openRFile(Exp express) {
        exp=express;
    }
    public ProgramState execute(ProgramState state) throws MyExceptions{
        MyIDictionary<String,BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if (exp.eval(symTbl).getType().equals(new StringType()))
        {
            StringValue file_name=(StringValue)exp.eval(symTbl);

            if(fileTable.isDefined(file_name.getVal())==true){
                throw new MyExceptions("Key found!");
            }
            else
            {
                    try {
                        BufferedReader buffreader = new BufferedReader(new FileReader(file_name.getVal()));
                        fileTable.update(file_name.getVal(),buffreader);
                    }
                    catch(IOException exce) {
                        throw new MyExceptions(exce.getMessage());
                    }
            }
        }

        return state;
    }

    @Override
    public String toString() {
        return "open file: "+exp.toString();
    }
}

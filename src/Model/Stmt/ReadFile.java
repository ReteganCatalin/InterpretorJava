package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Exp;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt {
    Exp exp;
    String val;
    public ReadFile(Exp express, String val) {
        exp=express;
        this.val=val;
    }
    public ProgramState execute(ProgramState state) throws MyExceptions {
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        symTbl.lookup(val.toString());
        if (exp.eval(symTbl).getType().equals(new StringType()))
        {
            StringValue file_name=(StringValue)exp.eval(symTbl);

            if(fileTable.isDefined(file_name.getVal())==false){
                throw new MyExceptions("No file found!");
            }
            else
            {
                BufferedReader file_buffer=fileTable.lookup(file_name.getVal());
                try {
                    String read_line = file_buffer.readLine();
                    if (read_line == null) {
                        symTbl.update(val.toString(), new IntValue(0));
                    } else {
                        symTbl.update(val.toString(), new IntValue(Integer.parseInt(read_line)));
                    }
                }
                catch(IOException exception) {
                    throw new MyExceptions(exception.getMessage());
            }
            }
        }

        return state;
    }

    @Override
    public String toString() {
        return "read from file: "+exp.toString();
    }
}

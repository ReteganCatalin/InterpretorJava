package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    Expression expression;
    String val;
    public ReadFile(Expression express, String val) {
        expression =express;
        this.val=val;
    }

    public IStatement deepCopy()
    {
        return new ReadFile(expression.deepCopy(),new String(val));
    }
    public ProgramState execute(ProgramState state) throws MyExceptions {
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();
        MyIDictionary<Integer, Value> heap = state.getHeapTable();
        symTbl.lookup(val.toString());
        if (expression.eval(symTbl,heap ).getType().equals(new StringType()))
        {
            StringValue file_name=(StringValue) expression.eval(symTbl,heap );

            if(fileTable.isDefined(file_name.getValue())==false){
                throw new MyExceptions("No file found!");
            }
            else
            {
                BufferedReader file_buffer=fileTable.lookup(file_name.getValue());
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

        return null;
    }

    @Override
    public String toString() {
        return "read from file: "+ expression.toString();
    }
}

package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeReaderFile implements IStatement {
    Expression expression;
    public closeReaderFile(Expression express) {
        expression =express;
    }
    public ProgramState execute(ProgramState state) throws MyExceptions
    {
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();
        MyIDictionary<Integer, Value> heap = state.getHeapTable();
        if (expression.eval(symTbl,heap ).getType().equals(new StringType()))
        {
            StringValue file_name=(StringValue) expression.eval(symTbl,heap );

            if(fileTable.isDefined(file_name.getValue())==false){
                throw new MyExceptions("No key found!");
            }
            else
            {
                BufferedReader file_buffer=fileTable.lookup(file_name.getValue());
                fileTable.update(file_name.getValue(),file_buffer);
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
        return "close file: "+ expression.toString();
    }
}

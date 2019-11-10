package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class openReaderFile implements IStatement {
    Expression expression;
    public openReaderFile(Expression express) {
        expression =express;
    }
    public ProgramState execute(ProgramState state) throws MyExceptions{
        MyIDictionary<String,BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if (expression.eval(symTbl).getType().equals(new StringType()))
        {
            StringValue file_name=(StringValue) expression.eval(symTbl);

            if(fileTable.isDefined(file_name.getValue())==true){
                throw new MyExceptions("Key found!");
            }
            else
            {
                    try {
                        BufferedReader buffreader = new BufferedReader(new FileReader(file_name.getValue()));
                        fileTable.update(file_name.getValue(),buffreader);
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
        return "open file: "+ expression.toString();
    }
}

package Model.Stmt;

import Exceptions.MyExceptions;
import Model.Dict.MyIDictionary;
import Model.Dict.MyIHeap;
import Model.Exp.Expression;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Type.Type;
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
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();
        MyIHeap<Value> heap = state.getHeapTable();

        if (expression.eval(symTbl,heap ).getType().equals(new StringType()))
        {
            StringValue file_name=(StringValue) expression.eval(symTbl,heap );

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

        return null;
    }
    @Override
    public  MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyExceptions {
        Type ExpressionType = expression.typeCheck(typeEnv);
        if (ExpressionType.equals(new StringType())) {
            return typeEnv;
        }
        else
            throw new MyExceptions("The Reading expression type is not a string");
    }
    public IStatement deepCopy()
    {
        return new openReaderFile(expression.deepCopy());
    }
    @Override
    public String toString() {
        return "open file: "+ expression.toString();
    }
}

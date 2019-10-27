package View;

import Controller.ProgramController;
import Exceptions.MyExceptions;
import Model.Dict.MyDictionary;
import Model.Dict.MyIDictionary;
import Model.Exp.ArithExp;
import Model.Exp.ValueExp;
import Model.Exp.VarExp;
import Model.List.MyIList;
import Model.List.MyList;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Stack.MyStack;
import Model.Stmt.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;
import Repository.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Main
{

    public static void main(String[] args)
    {
        MyIStack<IStmt> statements = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTable= new MyDictionary<String,Value>();
        MyIList<Value> out=new MyList<Value>();

        statements.push(ChooseProgram(1));
        ProgramState state=new ProgramState(statements,symTable,out);
        Repository repo=new ProgramRepo(state);
        ProgramController ctrl=new ProgramController(repo);
        try
        {
            ArrayList<String> To_print=ctrl.allStep();
            for(String item: To_print)
            {
                System.out.println(item);
            }

        }
        catch(MyExceptions exceptions)
        {

        }

    }

    public static IStmt ChooseProgram(int input_program)
    {
        if(input_program==1) {
            IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
            return ex1;
        }
        else if (input_program==2)
        {
            IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                    new CompStmt(new VarDeclStmt("b",new IntType()),new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2))
                            ,new ArithExp(3,new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),  new CompStmt(new AssignStmt("b",new ArithExp(1,new VarExp("a"),
                            new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
            return ex2;
        }
        else
        {
            IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                    new CompStmt(new VarDeclStmt("v", new IntType()),new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                            new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                    new PrintStmt(new VarExp("v"))))));
            return ex3;
        }
    }
}

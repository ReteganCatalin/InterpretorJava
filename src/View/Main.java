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
        System.out.println("Choose your program 1,2,3:");
        Scanner input= new Scanner(System.in);
        int input_program=0;

        try {
            input_program = input.nextInt();
            if (input_program <= 0 && input_program > 3) {
                throw new Exception("esd");
            }
        } catch (Exception e) {
                System.out.println("Not a valid input");
                return;
        }

        statements.push(ChooseProgram(input_program));
        ProgramState state=new ProgramState(statements,symTable,out);
        Repository repo=new ProgramRepo(state);
        ProgramController ctrl=new ProgramController(repo);
        while(true)
        {
            System.out.println("1-One step verification");
            System.out.println("2-All step verification");
            System.out.println("3-Exit");
            try {
                input_program = input.nextInt();
                if (input_program <= 0 && input_program > 3) {
                    throw new Exception("lolo");
                }
            } catch (Exception e) {
                System.out.println("Not a valid input");
                return;
            }
            if(input_program==2) {
                try {
                    ArrayList<String> To_print = ctrl.allStep();
                    for (String item : To_print) {
                        System.out.println(item);
                    }

                } catch (MyExceptions exceptions) {
                    System.out.println(exceptions.getMessage());

                }
            }
            else if(input_program==3)
            {
                return;
            }
            else if(input_program==1) {
                try {
                    String print_step = ctrl.WrapperOneStep();
                    System.out.println(print_step);
                }
                catch(MyExceptions exception)
                {
                    System.out.println(exception.getMessage());
                }
            }

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
                    new CompStmt(new VarDeclStmt("b",new IntType()),new CompStmt(new AssignStmt("a", new ArithExp(1,new ValueExp(new IntValue(2))
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

package View;

import Exceptions.MyExceptions;
import Model.Dict.MyDictionary;
import Model.Dict.MyIDictionary;
import Model.Exp.ArithmeticExpression;
import Model.Exp.HeapReading;
import Model.Exp.ValueExpression;
import Model.Exp.VariableExpression;
import Model.ProgramState;
import Model.Stmt.*;
import Model.Type.*;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import ProgramController.ProgramController;
import Repository.ProgramRepo;
import Repository.Repository;

class Interpreter {
    public static void main(String[] args) {
        try {

            IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                    new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
            MyIDictionary<String, Type> typeEnv1 = new MyDictionary<>();
            ex1.typeCheck(typeEnv1);
            ProgramState prg1 = new ProgramState(ex1);
            Repository repo1 = new ProgramRepo(prg1, "log1.txt");
            ProgramController ctr1 = new ProgramController(repo1);
            IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                    new CompoundStatement(new VariableDeclarationStatement("b", new IntType()), new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression(1, new ValueExpression(new IntValue(2))
                            , new ArithmeticExpression(3, new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))), new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression(1, new VariableExpression("a"),
                            new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
            MyIDictionary<String, Type> typeEnv2 = new MyDictionary<>();
            ex2.typeCheck(typeEnv2);
            ProgramState prg2 = new ProgramState(ex2);
            Repository repo2 = new ProgramRepo(prg2, "log2.txt");
            ProgramController ctr2 = new ProgramController(repo2);
            IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                    new CompoundStatement(new VariableDeclarationStatement("v", new IntType()), new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                            new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                    new PrintStatement(new VariableExpression("v"))))));
            MyIDictionary<String, Type> typeEnv3 = new MyDictionary<>();
            ex3.typeCheck(typeEnv3);
            ProgramState prg3 = new ProgramState(ex3);
            Repository repo3 = new ProgramRepo(prg3, "log3.txt");
            ProgramController ctr3 = new ProgramController(repo3);
            IStatement ex4 = new CompoundStatement(
                    new VariableDeclarationStatement("varf", new StringType()), new CompoundStatement(
                    new AssignmentStatement("varf", new ValueExpression(new StringValue("test.txt"))), new CompoundStatement(
                    new openReaderFile(new VariableExpression("varf")), new CompoundStatement(
                    new VariableDeclarationStatement("varc", new IntType()), new CompoundStatement(
                    new ReadFile(new VariableExpression("varf"), "varc"), new CompoundStatement(
                    new PrintStatement(new VariableExpression("varc")), new CompoundStatement(
                    new ReadFile(new VariableExpression("varf"), "varc"), new CompoundStatement(new PrintStatement(new VariableExpression("varc")), new closeReaderFile(new VariableExpression("varf"))))))))));
            MyIDictionary<String, Type> typeEnv4 = new MyDictionary<>();
            ex4.typeCheck(typeEnv4);
            ProgramState prg4 = new ProgramState(ex4);
            Repository repo4 = new ProgramRepo(prg4, "log4.txt");
            ProgramController ctr4 = new ProgramController(repo4);
            IStatement ex5 = new CompoundStatement(
                    new VariableDeclarationStatement("v",new IntType()),
                    new CompoundStatement(
                            new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
                            new WhileStatement(
                                    new ArithmeticExpression(9,new VariableExpression("v"),new ValueExpression(new IntValue(0))),
                                    new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                            new AssignmentStatement( "v",new ArithmeticExpression(2,new VariableExpression("v"),new ValueExpression(new IntValue(1))))
                                    )
                            )));
            MyIDictionary<String, Type> typeEnv5 = new MyDictionary<>();
            ex5.typeCheck(typeEnv5);
            ProgramState prg5 = new ProgramState(ex5 );
            Repository repo5= new ProgramRepo(prg5, "log5.txt");
            ProgramController ctr5 = new ProgramController(repo5);
            IStatement ex6 = new CompoundStatement(
                    new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                    new CompoundStatement(
                            new HeapAllocation("v",new ValueExpression(new IntValue(20))),
                            new CompoundStatement(
                                    new PrintStatement(new HeapReading(new VariableExpression("v"))), new CompoundStatement(
                                            new VariableDeclarationStatement("a",new ReferenceType(new ReferenceType(new  IntType()))), new CompoundStatement(
                                                new HeapAllocation("a",new VariableExpression("v")),new CompoundStatement(
                                                        new HeapAllocation("v",new ValueExpression(new IntValue(30))),
                                                        new PrintStatement(new ArithmeticExpression(1 ,new HeapReading(new HeapReading( new VariableExpression("a"))),new ValueExpression(new IntValue(5))))))))));
            MyIDictionary<String, Type> typeEnv6 = new MyDictionary<>();
            ex6.typeCheck(typeEnv6);
            ProgramState prg6 = new ProgramState(ex6);
            Repository repo6= new ProgramRepo(prg6, "log6.txt");
            ProgramController ctr6 = new ProgramController(repo6);
            IStatement ex7 = new CompoundStatement(
                    new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                    new CompoundStatement(
                            new HeapAllocation("v",new ValueExpression(new IntValue(20))),
                            new CompoundStatement(
                                    new PrintStatement(new HeapReading(new VariableExpression("v"))), new CompoundStatement(
                                    new VariableDeclarationStatement("a",new ReferenceType(new ReferenceType(new  IntType()))), new CompoundStatement(
                                    new HeapAllocation("a",new VariableExpression("v")),new CompoundStatement(
                                    new HeapWrite("v",new ValueExpression(new IntValue(30))),
                                    new PrintStatement(new ArithmeticExpression(1 ,new HeapReading(new HeapReading( new VariableExpression("a"))),new ValueExpression(new IntValue(5))))))))));
            MyIDictionary<String, Type> typeEnv7 = new MyDictionary<>();
            ex7.typeCheck(typeEnv7);
            ProgramState prg7 = new ProgramState(ex7);
            Repository repo7= new ProgramRepo(prg7, "log7.txt");
            ProgramController ctr7 = new ProgramController(repo7);
            IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                    new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                            new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                    new CompoundStatement(new HeapAllocation("a", new ValueExpression(new IntValue(22))),
                                            new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWrite("a", new ValueExpression(new IntValue(30))),
                                                    new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                            new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                    new PrintStatement(new HeapReading(new VariableExpression("a"))))))),
                                                    new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(37))) ,new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                            new PrintStatement(new HeapReading(new VariableExpression("a"))))))))));
            MyIDictionary<String, Type> typeEnv8 = new MyDictionary<>();
            ex8.typeCheck(typeEnv8);
            ProgramState prg8 = new ProgramState(ex8);
            Repository repo8= new ProgramRepo(prg8, "log8.txt");
            ProgramController ctr8 = new ProgramController(repo8);
            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));
            menu.addCommand(new RunCommand("1", ex1.toString(), ctr1));
            menu.addCommand(new RunCommand("2", ex2.toString(), ctr2));
            menu.addCommand(new RunCommand("3", ex3.toString(), ctr3));
            menu.addCommand(new RunCommand("4", ex4.toString(), ctr4));
            menu.addCommand(new RunCommand("5", ex5.toString(), ctr5));
            menu.addCommand(new RunCommand("6", ex6.toString(), ctr6));
            menu.addCommand(new RunCommand("7", ex7.toString(), ctr7));
            menu.addCommand(new RunCommand("8", ex8.toString(), ctr8));
            menu.show();
        }
        catch(MyExceptions exception)
        {
            System.out.println(exception);
        }
    }
}
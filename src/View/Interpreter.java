package View;

import Exceptions.MyExceptions;
import Model.Exp.ArithmeticExpression;
import Model.Exp.ValueExpression;
import Model.Exp.VariableExpression;
import Model.ProgramState;
import Model.Stmt.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.StringType;
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
                    new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
            ProgramState prg1 = new ProgramState(ex1);
            Repository repo1 = new ProgramRepo(prg1, "log1.txt");
            ProgramController ctr1 = new ProgramController(repo1);
            IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                    new CompoundStatement(new VariableDeclarationStatement("b", new IntType()), new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(1, new ValueExpression(new IntValue(2))
                            , new ArithmeticExpression(3, new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))), new CompoundStatement(new AssignStatement("b", new ArithmeticExpression(1, new VariableExpression("a"),
                            new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
            ;
            ProgramState prg2 = new ProgramState(ex2);
            Repository repo2 = new ProgramRepo(prg2, "log2.txt");
            ProgramController ctr2 = new ProgramController(repo2);
            IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                    new CompoundStatement(new VariableDeclarationStatement("v", new IntType()), new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                            new CompoundStatement(new IfStatement(new VariableExpression("v"), new AssignStatement("v", new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                    new PrintStatement(new VariableExpression("v"))))));
            ;
            ProgramState prg3 = new ProgramState(ex3);
            Repository repo3 = new ProgramRepo(prg3, "log3.txt");
            ProgramController ctr3 = new ProgramController(repo3);
            IStatement ex4 = new CompoundStatement(
                    new VariableDeclarationStatement("varf", new StringType()), new CompoundStatement(
                    new AssignStatement("varf", new ValueExpression(new StringValue("test.txt"))), new CompoundStatement(
                    new openReaderFile(new VariableExpression("varf")), new CompoundStatement(
                    new VariableDeclarationStatement("varc", new IntType()), new CompoundStatement(
                    new ReadFile(new VariableExpression("varf"), "varc"), new CompoundStatement(
                    new PrintStatement(new VariableExpression("varc")), new CompoundStatement(
                    new ReadFile(new VariableExpression("varf"), "varc"), new CompoundStatement(new PrintStatement(new VariableExpression("varc")), new closeReaderFile(new VariableExpression("varf"))))))))));
            ProgramState prg4 = new ProgramState(ex4);
            Repository repo4 = new ProgramRepo(prg4, "log4.txt");
            ProgramController ctr4 = new ProgramController(repo4);
            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));
            menu.addCommand(new RunCommand("1", ex1.toString(), ctr1));
            menu.addCommand(new RunCommand("2", ex2.toString(), ctr2));
            menu.addCommand(new RunCommand("3", ex3.toString(), ctr3));
            menu.addCommand(new RunCommand("4", ex4.toString(), ctr4));
            menu.show();
        }
        catch(MyExceptions exception)
        {

        }
    }
}
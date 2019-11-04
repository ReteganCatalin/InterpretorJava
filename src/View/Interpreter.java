package View;

import Exceptions.MyExceptions;
import Model.Exp.ArithExp;
import Model.Exp.ValueExp;
import Model.Exp.VarExp;
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
            IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
            ProgramState prg1 = new ProgramState(ex1);
            Repository repo1 = new ProgramRepo(prg1, "log1.txt");
            ProgramController ctr1 = new ProgramController(repo1);
            IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new IntValue(2))
                            , new ArithExp(3, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"),
                            new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
            ;
            ProgramState prg2 = new ProgramState(ex2);
            Repository repo2 = new ProgramRepo(prg2, "log2.txt");
            ProgramController ctr2 = new ProgramController(repo2);
            IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                    new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                            new CompStmt(new IfStmt(new VarExp("v"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                    new PrintStmt(new VarExp("v"))))));
            ;
            ProgramState prg3 = new ProgramState(ex3);
            Repository repo3 = new ProgramRepo(prg3, "log3.txt");
            ProgramController ctr3 = new ProgramController(repo3);
            IStmt ex4 = new CompStmt(
                    new VarDeclStmt("varf", new StringType()), new CompStmt(
                    new AssignStmt("varf", new ValueExp(new StringValue("test.txt"))), new CompStmt(
                    new openRFile(new VarExp("varf")), new CompStmt(
                    new VarDeclStmt("varc", new IntType()), new CompStmt(
                    new ReadFile(new VarExp("varf"), "varc"), new CompStmt(
                    new PrintStmt(new VarExp("varc")), new CompStmt(
                    new ReadFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))))))));
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
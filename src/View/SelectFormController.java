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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SelectFormController implements Initializable {
    private List<IStatement> programStatements;
    private RunFormController mainWindowController;

    @FXML
    private ListView<String> programListView;

    @FXML
    private Button executeProgram;

    void setMainWindowController(RunFormController mainWindowController){
        this.mainWindowController = mainWindowController;
    }

    private void buildProgramStatements()
    {
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
        IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()), new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression(1, new ValueExpression(new IntValue(2))
                        , new ArithmeticExpression(3, new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))), new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression(1, new VariableExpression("a"),
                        new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        // example with error that the typechecker will catch
        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()), new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                        new CompoundStatement(new IfStatement(new VariableExpression("v"), new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                new PrintStatement(new VariableExpression("v"))))));
        IStatement ex4 = new CompoundStatement(
                new VariableDeclarationStatement("varf", new StringType()), new CompoundStatement(
                new AssignmentStatement("varf", new ValueExpression(new StringValue("test.txt"))), new CompoundStatement(
                new openReaderFile(new VariableExpression("varf")), new CompoundStatement(
                new VariableDeclarationStatement("varc", new IntType()), new CompoundStatement(
                new ReadFile(new VariableExpression("varf"), "varc"), new CompoundStatement(
                new PrintStatement(new VariableExpression("varc")), new CompoundStatement(
                new ReadFile(new VariableExpression("varf"), "varc"), new CompoundStatement(new PrintStatement(new VariableExpression("varc")), new closeReaderFile(new VariableExpression("varf"))))))))));
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
        programStatements = new ArrayList<>(Arrays.asList(ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8));}

    private List<String> getStringRepresentations(){
        return programStatements.stream().map(Object::toString).collect(Collectors.toList());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        buildProgramStatements();
        List<String> strings=getStringRepresentations();
        ObservableList<String>  str= FXCollections.observableList(strings);
        programListView.setItems(str);

        executeProgram.setOnAction(actionEvent -> {
            int index = programListView.getSelectionModel().getSelectedIndex();

            if(index < 0)
                return;

            ProgramState initialProgramState = new ProgramState(programStatements.get(index));

            Repository repository;
            try {
                index+=1;
                MyIDictionary<String, Type> typeEnv = new MyDictionary<>();
                initialProgramState.getStatement().typeCheck(typeEnv);
                repository = new ProgramRepo(initialProgramState, "log" + index + ".txt");
                ProgramController ctrl = new ProgramController(repository);

                mainWindowController.setController(ctrl);
            }
            catch(MyExceptions exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage(), ButtonType.OK);
                alert.showAndWait();
                return;
            }

        });
    }
}
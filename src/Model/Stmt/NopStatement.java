package Model.Stmt;

import Model.ProgramState;

public class NopStatement implements IStatement {
    public NopStatement() {
    }
    public ProgramState execute(ProgramState state){
        for(int i=0;i<5;i++)
        {
            i++;
        }
        return state;
    }

    @Override
    public String toString() {
        return "";
    }
}

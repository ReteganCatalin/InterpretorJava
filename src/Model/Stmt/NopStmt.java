package Model.Stmt;

import Model.ProgramState;

public class NopStmt implements IStmt {
    public NopStmt() {
    }
    public ProgramState execute(ProgramState state){
        for(int i=0;i<5;i++)
        {
            i++;
        }
        return state;
    }
}

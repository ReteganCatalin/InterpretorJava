package Repository;


import Model.ProgramState;

import java.util.ArrayList;

public class  ProgramRepo implements  Repository{
    ArrayList<ProgramState> repo=new ArrayList<ProgramState>();
    @Override
    public ProgramState getCurrentProgram() {
        return repo.get(0);
    }

    public ProgramRepo(ProgramState state1) {
        this.repo.add(state1);
    }
}

package Repository;

import Exceptions.MyExceptions;
import Model.ProgramState;

import java.util.ArrayList;

public interface Repository {
    ArrayList<ProgramState> getProgramList();
    void setProgramStates(ArrayList<ProgramState> new_states);
    void logProgramStateExecution(ProgramState state) throws MyExceptions;
}

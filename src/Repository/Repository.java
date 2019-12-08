package Repository;

import Exceptions.MyExceptions;
import Model.ProgramState;

import java.util.List;

public interface Repository {
    List<ProgramState> getProgramList();
    void setProgramStates(List<ProgramState> new_states);
    void logProgramStateExecution(ProgramState state) throws MyExceptions;
    ProgramState getProgramStatewithID(int currentID);
}

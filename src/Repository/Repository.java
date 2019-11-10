package Repository;

import Exceptions.MyExceptions;
import Model.ProgramState;

public interface Repository {
    ProgramState getCurrentProgram();
    void logProgramStateExecution() throws MyExceptions;
}

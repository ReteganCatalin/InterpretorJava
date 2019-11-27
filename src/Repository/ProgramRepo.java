package Repository;


import Exceptions.MyExceptions;
import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class  ProgramRepo implements  Repository{
    List<ProgramState> states =new ArrayList<ProgramState>();
    String logFilePath="log.txt";
    @Override
    public List<ProgramState> getProgramList() {
        return states;
    }

    public void setProgramStates(List<ProgramState> new_states)
    {
        states=new_states;
    }

    public void logProgramStateExecution(ProgramState state) throws MyExceptions
    {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.write(state.toString());
            logFile.close();
        }
        catch(IOException e)
        {
            throw new MyExceptions("IO exception");
        }
    }

    public ProgramRepo(ProgramState state1,String logFilePath) throws MyExceptions{
        this.states.add(state1);
        this.logFilePath=logFilePath;
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath)));
        }
        catch(IOException e)
        {
            throw new MyExceptions("IO exception");
        }

    }
}

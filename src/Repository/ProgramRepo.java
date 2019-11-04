package Repository;


import Exceptions.MyExceptions;
import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class  ProgramRepo implements  Repository{
    ArrayList<ProgramState> repo=new ArrayList<ProgramState>();
    String logFilePath="log.txt";
    @Override
    public ProgramState getCurrentProgram() {
        return repo.get(0);
    }

    public void logPrgStateExec() throws MyExceptions
    {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.write(repo.get(0).toString());
            logFile.close();
        }
        catch(IOException e)
        {
            throw new MyExceptions("IO exception");
        }
    }

    public ProgramRepo(ProgramState state1,String logFilePath) throws MyExceptions{
        this.repo.add(state1);
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

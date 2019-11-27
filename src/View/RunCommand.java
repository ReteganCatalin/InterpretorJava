package View;

import Exceptions.MyExceptions;
import ProgramController.ProgramController;

public class RunCommand extends Command {
    private ProgramController ctrl;
    public RunCommand(String key, String desc,ProgramController ctr)
    {
        super(key, desc);

        this.ctrl=ctr;
    }
    @Override
    public void execute()
    {
        try {

            ctrl.allStep();

        } catch (MyExceptions exceptions) {
            System.out.println(exceptions.getMessage());

        }
    } //here you must treat the exceptions that can not be solved in the controller    }}
    }
package View;

import ProgramController.ProgramController;
import Exceptions.MyExceptions;

import java.util.ArrayList;

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

            ArrayList<String> To_print = ctrl.allStep();
            {
                for (String item : To_print) {
                    System.out.println(item);
                }
            }

        } catch (MyExceptions exceptions) {
            System.out.println(exceptions.getMessage());

        }
    } //here you must treat the exceptions that can not be solved in the controller    }}
    }
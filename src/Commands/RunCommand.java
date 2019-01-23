package Commands;

import Controller.Controller;
import Exceptions.*;

public class RunCommand extends Command {

    private Controller ctrl;

    /**
     * Constructor
     *
     * @param key  - key of the command
     * @param desc - description of the command
     * @param ctrl - controller associated with the command
     */
    public RunCommand(String key, String desc, Controller ctrl) {
        super(key, desc);
        this.ctrl = ctrl;
    }

    /**
     * Execute a ProgramState from start to end
     */

    @Override
    public void execute() {
        try {
            ctrl.allStepEvaluation();
        } catch (DivisionByZeroException |
                InvalidOperatorException |
                VariableNotFoundException |
                HeapWritingException |
                HeapVariableNotFoundException |
                EmptyStackException e) {
            System.err.println(e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("The stack is empty!\n");
        }
    }

    public Controller getController() {
        return ctrl;
    }

}

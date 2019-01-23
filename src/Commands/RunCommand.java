package Commands;

import Controller.Controller;

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
        ctrl.allStepEvaluation();
    }

    public Controller getController() {
        return ctrl;
    }

}

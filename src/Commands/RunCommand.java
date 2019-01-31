package Commands;

import Controller.Controller;

/**
 * A Command subclass used for running a program contained in a {@link Controller}.
 */
public class RunCommand extends Command {

    private Controller ctrl;

    public RunCommand(String key, String desc, Controller ctrl) {
        super(key, desc);
        this.ctrl = ctrl;
    }

    @Override
    public void execute() {
        ctrl.allStepEvaluation();
    }

    public Controller getController() {
        return ctrl;
    }

}

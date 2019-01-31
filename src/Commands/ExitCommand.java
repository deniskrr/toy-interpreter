package Commands;


/**
 * A Command subclass used for exitting the program.
 */
public class ExitCommand extends Command {

    public ExitCommand(String key, String desc){
        super(key, desc);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}

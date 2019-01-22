package Commands;

public class ExitCommand extends Command {

    /**
     * Constructor
     * @param key - String
     * @param desc - String
     */
    public ExitCommand(String key, String desc){
        super(key, desc);
    }


    /**
     * Exit the program
     */

    @Override
    public void execute() {
        System.exit(0);
    }
}

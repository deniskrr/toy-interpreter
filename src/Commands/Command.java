package Commands;

public abstract class Command {

    private String key, description;

    /**
     * Construct a command
     * @param key - the key of the command
     * @param description - description of the command
     */
    public Command(String key, String description) {
        this.key = key;
        this.description = description;
    }


    /**
     * Execute the command
     */
    public abstract void execute();


    /**
     * Key getter
     * @return key of the command
     */
    public String getKey(){
        return key;
    }


    /**
     * Description getter
     * @return description of the command
     */
    public String getDescription(){
        return description;
    }


}

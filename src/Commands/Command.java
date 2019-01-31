package Commands;

public abstract class Command {

    private String key, description;

    /**
     * Constructs a command
     * @param key - the key of the command
     * @param description - description of the command
     */
    public Command(String key, String description) {
        this.key = key;
        this.description = description;
    }


    /**
     * Executes the command
     */
    public abstract void execute();


    /**
     * Gets the key
     * @return the key of the command
     */
    public String getKey(){
        return key;
    }


    /**
     * Gets the description
     * @return the description of the command
     */
    public String getDescription(){
        return description;
    }


}

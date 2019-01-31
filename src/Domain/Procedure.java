package Domain;


/**
 * A wrapper for a Procedure
 */
public class Procedure {
    private String name;
    private ProcedureEntry entry;


    public Procedure(String name, ProcedureEntry entry) {
        this.name = name;
        this.entry = entry;
    }

    public String getName() {
        return name;
    }

    public ProcedureEntry getEntry() {
        return entry;
    }


}

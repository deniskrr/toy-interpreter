package Domain;

import Domain.Statements.ProcedureEntry;

public class Procedure {
    String name;
    ProcedureEntry entry;

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

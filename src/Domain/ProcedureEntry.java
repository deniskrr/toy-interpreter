package Domain;

import Domain.Statements.IStatement;

import java.util.List;

/**
 * A wrapper for a {@link Procedure}'s parameters and body
 */
public class ProcedureEntry {

    private List<String> parameters;
    private IStatement body;

    public ProcedureEntry(List<String> parameters, IStatement body) {
        this.parameters = parameters;
        this.body = body;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public IStatement getBody() {
        return body;
    }

    @Override
    public String toString() {
        String str = "(";
        for (String param : parameters) {
            str += param + ", ";
        }
        str += ")" + body;
        return str;
    }
}

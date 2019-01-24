package Domain.Statements;

import java.util.List;

public class ProcedureEntry {

    private List<String> parameters;
    private IStmt body;

    public ProcedureEntry(List<String> parameters, IStmt body) {
        this.parameters = parameters;
        this.body = body;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public IStmt getBody() {
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

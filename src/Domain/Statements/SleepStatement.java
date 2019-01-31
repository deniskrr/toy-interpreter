package Domain.Statements;

import Domain.ProgramState;

public class SleepStatement implements IStatement {
    private int number;

    public SleepStatement(int number) {
        this.number = number;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        if (number != 0) {
            number--;
            state.getExecutionStack().push(this);
        }
        return null;
    }

    @Override
    public String toString() {
        return "sleep(" + number + ")";
    }
}

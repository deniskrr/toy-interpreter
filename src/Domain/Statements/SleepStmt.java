package Domain.Statements;

import Domain.PrgState;

public class SleepStmt implements IStmt {
    private int number;

    public SleepStmt(int number) {
        this.number = number;
    }

    @Override
    public PrgState execute(PrgState state) {
        if (number != 0) {
            number--;
            state.getExeStack().push(this);
        }
        return null;
    }

    @Override
    public String toString() {
        return "sleep(" + number + ")";
    }
}

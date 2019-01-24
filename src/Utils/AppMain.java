package Utils;

import Commands.Command;
import Commands.RunCommand;
import Controller.Controller;
import Domain.Expression.ArithExp;
import Domain.Expression.ConstExp;
import Domain.Expression.HeapReadingExp;
import Domain.Expression.VarExp;
import Domain.PrgState;
import Domain.Procedure;
import Domain.Statements.*;
import Domain.Statements.FileStatements.CloseFile;
import Domain.Statements.FileStatements.OpenFile;
import Domain.Statements.FileStatements.ReadFile;
import Domain.Statements.HeapStatements.HeapAllocationStmt;
import Domain.Statements.HeapStatements.HeapWritingStmt;
import Domain.Statements.SemaphoreStatements.AcquireStmt;
import Domain.Statements.SemaphoreStatements.NewSemaphoreStmt;
import Domain.Statements.SemaphoreStatements.ReleaseStmt;
import GUI.GUIInterpreter;
import Repository.TextRepository;
import View.TextInterpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AppMain {

    private static int logger = 1;

    /**
     * Choice == 1: GUI
     * == 2: CLI
     */
    public static int choice = 0;

    private static Command createCommand(IStmt mainStatement) {
        TextRepository repo = new TextRepository("Log" + logger + ".txt");
        Controller ctrl = new Controller(repo);
        PrgState prgState = new PrgState(mainStatement);
        repo.addProgram(prgState);

        return new RunCommand(String.valueOf(logger++), mainStatement.toString(), ctrl);
    }

    private static Command createCommand(IStmt mainStatement, List<Procedure> procedures) {
        TextRepository repo = new TextRepository("Log" + logger + ".txt");
        Controller ctrl = new Controller(repo);
        PrgState prgState = new PrgState(mainStatement);
        for (Procedure procedure : procedures) {
            prgState.getProcTable().add(procedure.getName(), procedure.getEntry());
        }
        repo.addProgram(prgState);

        return new RunCommand(String.valueOf(logger++), mainStatement.toString(), ctrl);
    }

    public static List<Command> getCommands() {
        List<Command> commandList = new ArrayList<>();

        IStmt ex1 = new CompStmt(
                new AssignStmt("v", new ConstExp(2)), new PrintStmt(new VarExp("v")));
        commandList.add(createCommand(ex1));

        IStmt ex2 = new CompStmt(
                new AssignStmt("a", new ArithExp('+', new ConstExp(2), new ArithExp('*', new ConstExp(3), new ConstExp(5)))),
                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ConstExp(1))), new PrintStmt(new VarExp("b"))));
        commandList.add(createCommand(ex2));

        IStmt ex3 = new CompStmt(
                new AssignStmt("a", new ArithExp('-', new ConstExp(2), new ConstExp(2))),
                new CompStmt(
                        new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
        commandList.add(createCommand(ex3));

        IStmt ex4 = new CompStmt(
                new OpenFile("var_f", "test.in"),
                new CompStmt(
                        new ReadFile(new VarExp("var_f"), "var_c"), new CompStmt(new PrintStmt(new VarExp("var_c")),
                        new CompStmt(
                                new IfStmt(
                                        new VarExp("var_c"),
                                        new ReadFile(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                                new PrintStmt(new ConstExp(0)))))); //new CloseFile(new VarExp("var_f"))))));
        commandList.add(createCommand(ex4));

        IStmt ex5 = new CompStmt(new OpenFile("var_f", "test.in"),
                new CompStmt(new ReadFile(new ArithExp('+', new VarExp("var_f"), new ConstExp(2)), "var_c"),
                        new CompStmt(new PrintStmt(new VarExp("var_c")),
                                new CompStmt(new IfStmt(new VarExp("var_c"),
                                        new CompStmt(new ReadFile(new VarExp("var_f"), "var_c"),
                                                new PrintStmt(new VarExp("var_c"))), new PrintStmt(new ConstExp(0))),
                                        new CloseFile(new VarExp("var_f"))))));
        commandList.add(createCommand(ex5));

        IStmt ex6 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new HeapAllocationStmt("v", new ConstExp(20)),
                new CompStmt(new HeapAllocationStmt("a", new ConstExp(22)), new PrintStmt(new VarExp("v")))));
        commandList.add(createCommand(ex6));

        IStmt ex8 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(
                new HeapAllocationStmt("v", new ConstExp(20)), new CompStmt(new HeapAllocationStmt("a", new ConstExp(22)),
                new CompStmt(new HeapWritingStmt("a", new ConstExp(30)), new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new HeapReadingExp("a")))))));
        commandList.add(createCommand(ex8));

        IStmt ex9 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new HeapAllocationStmt("v", new ConstExp(20)),
                new CompStmt(new HeapAllocationStmt("a", new ConstExp(22)), new CompStmt(new HeapWritingStmt("a", new ConstExp(30)),
                        new CompStmt(new PrintStmt(new VarExp("a")), new CompStmt(new PrintStmt(new HeapReadingExp("a")), new AssignStmt("a", new ConstExp(0))))))));
        commandList.add(createCommand(ex9));

        IStmt ex10 = new CompStmt(new AssignStmt("v", new ConstExp(6)),
                new CompStmt(new WhileStmt(
                        new ArithExp('-', new VarExp("v"), new ConstExp(4)),
                        new CompStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v",
                                        new ArithExp('-', new VarExp("v"), new ConstExp(1))))), new PrintStmt(new VarExp("v"))));
        commandList.add(createCommand(ex10));

        IStmt ex11 = new CompStmt(new CompStmt(new CompStmt(new AssignStmt("v", new ConstExp(10)), new HeapAllocationStmt("v", new ConstExp(20))),
                new CompStmt(new HeapAllocationStmt("a", new ConstExp(22)), new HeapWritingStmt("a", new ConstExp(30)))), new CompStmt(new CompStmt(new PrintStmt(new VarExp("a")),
                new PrintStmt(new HeapReadingExp("a"))),
                new CompStmt(new AssignStmt("a", new ConstExp(0)), new PrintStmt(new HeapReadingExp("a")))));
        commandList.add(createCommand(ex11));

        IStmt ex12 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new HeapAllocationStmt("a", new ConstExp(22)),
                        new CompStmt(new ForkStmt(new CompStmt(new HeapWritingStmt("a", new ConstExp(30)),
                                new CompStmt(new AssignStmt("v", new ConstExp(32)),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new HeapReadingExp("a")))))),
                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp("a"))))));
        commandList.add(createCommand(ex12));

        IStmt ex13 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new HeapAllocationStmt("a", new ConstExp(22)),
                        new CompStmt(new ForkStmt(new CompStmt(new HeapWritingStmt("a", new ConstExp(30)),
                                new CompStmt(new AssignStmt("v", new ConstExp(32)),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new CompStmt(new ForkStmt(new PrintStmt(new VarExp("v"))),
                                                        new PrintStmt(new HeapReadingExp("a"))))))),
                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp("a"))))));
        commandList.add(createCommand(ex13));

        IStmt ex14 = new CompStmt(new AssignStmt("v", new ConstExp(20)),
                new CompStmt(
                        new ForStmt("v",
                                new ConstExp(0), new ConstExp(3),
                                new ArithExp('+', new VarExp("v"), new ConstExp(1)),
                                new ForkStmt(new CompStmt(
                                        new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ConstExp(1)))))),
                        new PrintStmt(new ArithExp('*', new VarExp("v"), new ConstExp(10)))));
        commandList.add(createCommand(ex14));

        IStmt ex15 = new NewSemaphoreStmt("v", new ConstExp(10));
        commandList.add(createCommand(ex15));

        IStmt ex16 = new AssignStmt("v", new ArithExp('/', new ConstExp(5), new ConstExp(0)));
        commandList.add(createCommand(ex16));

        IStmt ex17 = new CompStmt(new NewSemaphoreStmt("cnt", new ConstExp(1)), new AcquireStmt("cnt"));
        commandList.add(createCommand(ex17));

        IStmt firstFork = new ForkStmt(new CompStmt(new AcquireStmt("cnt"),
                new CompStmt(new HeapWritingStmt("v", new ArithExp('*', new HeapReadingExp("v"), new ConstExp(10))),
                        new CompStmt(new PrintStmt(new HeapReadingExp("v")), new ReleaseStmt("cnt")))));

        IStmt secondFork = new ForkStmt(new CompStmt(new AcquireStmt("cnt"),
                new CompStmt(new HeapWritingStmt("v", new ArithExp('*', new HeapReadingExp("v"), new ConstExp(10))),
                        new CompStmt(new HeapWritingStmt("v", new ArithExp('*', new HeapReadingExp("v"), new ConstExp(2))),
                                new CompStmt(new PrintStmt(new HeapReadingExp("v")), new ReleaseStmt("cnt"))))));

        IStmt ex18 = new CompStmt(new HeapAllocationStmt("v", new ConstExp(1)),
                new CompStmt(new NewSemaphoreStmt("cnt", new HeapReadingExp("v")),
                        new CompStmt(firstFork,
                                new CompStmt(secondFork,
                                        new CompStmt(new AcquireStmt("cnt"),
                                                new CompStmt(new PrintStmt(new ArithExp('-', new HeapReadingExp("v"), new ConstExp(1))),
                                                        new ReleaseStmt("cnt")))))));
        commandList.add(createCommand(ex18));

        IStmt testb = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new ForkStmt(
                        new CompStmt(
                                new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ConstExp(1))),
                                new CompStmt(new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ConstExp(1))),
                                        new PrintStmt(new VarExp("v"))))),
                        new CompStmt(new SleepStmt(10), new PrintStmt(new ArithExp('*', new VarExp("v"), new ConstExp(10))))));
        commandList.add(createCommand(testb));

        IStmt sum = new CompStmt(new AssignStmt("v", new ArithExp('+', new VarExp("a"), new VarExp("b"))),
                new PrintStmt(new VarExp("v")));
//        Procedure sum = new Procedure("sum", new ProcedureEntry(Arrays.asList("a", "b"),))

        return commandList;
    }

    public static void main(String[] args) {
        System.out.println("\t\t 1 - GUI");
        System.out.println("\t\t 2 - Text");

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if (choice == 1) {
            GUIInterpreter.main(args);
        } else if (choice == 2) {
            TextInterpreter.main(args);
        }
    }
}

package Utils;

import Commands.Command;
import Commands.RunCommand;
import Controller.Controller;
import Domain.Expression.*;
import Domain.Expression.HeapReadingExpression;
import Domain.Expression.VariableExpression;
import Domain.ProgramState;
import Domain.ProcedureEntry;
import Domain.Statements.PrintStatement;
import Domain.Procedure;
import Domain.Statements.*;
import Domain.Statements.FileStatements.CloseFile;
import Domain.Statements.FileStatements.OpenFile;
import Domain.Statements.FileStatements.ReadFile;
import Domain.Statements.HeapStatements.HeapAllocationStatement;
import Domain.Statements.HeapStatements.HeapWritingStatement;
import Domain.Statements.ProcedureStatements.CallStatement;
import Domain.Statements.SemaphoreStatements.AcquireStatement;
import Domain.Statements.SemaphoreStatements.NewSemaphoreStatement;
import Domain.Statements.SemaphoreStatements.ReleaseStatement;
import GUI.GUIInterpreter;
import Repository.TextRepository;
import View.TextInterpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class AppMain {

    private static int logger = 1;
    private static List<Procedure> procedures = new ArrayList<>();

    /**
     * Choice == 1: GUI
     * == 2: CLI
     */

    private static Command createCommand(IStatement mainStatement) {
        TextRepository repo = new TextRepository("Log" + logger + ".txt");
        Controller ctrl = new Controller(repo);
        ProgramState programState = new ProgramState(mainStatement);
        for (Procedure procedure : procedures) {
            programState.getProcedureTable().add(procedure.getName(), procedure.getEntry());
        }
        repo.addProgram(programState);

        return new RunCommand(String.valueOf(logger++), mainStatement.toString(), ctrl);
    }


    public static List<Command> getCommands() {
        List<Command> commandList = new ArrayList<>();

        IStatement sum = new CompoundStatement(new AssignStatement("v", new ArithmeticExpression('+', new VariableExpression("a"), new VariableExpression("b"))),
                new PrintStatement(new VariableExpression("v")));
        Procedure sumProcedure = new Procedure("sum", new ProcedureEntry(Arrays.asList("a", "b"), sum));
        IStatement product = new CompoundStatement(new AssignStatement("v", new ArithmeticExpression('*', new VariableExpression("a"), new VariableExpression("b"))),
                new PrintStatement(new VariableExpression("v")));
        Procedure productProcedure = new Procedure("product", new ProcedureEntry(Arrays.asList("a", "b"), product));
        procedures.add(sumProcedure);
        procedures.add(productProcedure);


        IStatement ex1 = new CompoundStatement(
                new AssignStatement("v", new ConstantExpression(2)), new PrintStatement(new VariableExpression("v")));
        commandList.add(createCommand(ex1));

        IStatement ex2 = new CompoundStatement(
                new AssignStatement("a", new ArithmeticExpression('+', new ConstantExpression(2), new ArithmeticExpression('*', new ConstantExpression(3), new ConstantExpression(5)))),
                new CompoundStatement(new AssignStatement("b", new ArithmeticExpression('+', new VariableExpression("a"), new ConstantExpression(1))), new PrintStatement(new VariableExpression("b"))));
        commandList.add(createCommand(ex2));

        IStatement ex3 = new CompoundStatement(
                new AssignStatement("a", new ArithmeticExpression('-', new ConstantExpression(2), new ConstantExpression(2))),
                new CompoundStatement(
                        new IfStatement(new VariableExpression("a"), new AssignStatement("v", new ConstantExpression(2)), new AssignStatement("v", new ConstantExpression(3))), new PrintStatement(new VariableExpression("v"))));
        commandList.add(createCommand(ex3));

        IStatement ex4 = new CompoundStatement(
                new OpenFile("var_f", "test.in"),
                new CompoundStatement(
                        new ReadFile(new VariableExpression("var_f"), "var_c"), new CompoundStatement(new PrintStatement(new VariableExpression("var_c")),
                        new CompoundStatement(
                                new IfStatement(
                                        new VariableExpression("var_c"),
                                        new ReadFile(new VariableExpression("var_f"), "var_c"), new PrintStatement(new VariableExpression("var_c"))),
                                new PrintStatement(new ConstantExpression(0)))))); //new CloseFile(new VariableExpression("var_f"))))));
        commandList.add(createCommand(ex4));

        IStatement ex5 = new CompoundStatement(new OpenFile("var_f", "test.in"),
                new CompoundStatement(new ReadFile(new ArithmeticExpression('+', new VariableExpression("var_f"), new ConstantExpression(2)), "var_c"),
                        new CompoundStatement(new PrintStatement(new VariableExpression("var_c")),
                                new CompoundStatement(new IfStatement(new VariableExpression("var_c"),
                                        new CompoundStatement(new ReadFile(new VariableExpression("var_f"), "var_c"),
                                                new PrintStatement(new VariableExpression("var_c"))), new PrintStatement(new ConstantExpression(0))),
                                        new CloseFile(new VariableExpression("var_f"))))));
        commandList.add(createCommand(ex5));

        IStatement ex6 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)), new CompoundStatement(new HeapAllocationStatement("v", new ConstantExpression(20)),
                new CompoundStatement(new HeapAllocationStatement("a", new ConstantExpression(22)), new PrintStatement(new VariableExpression("v")))));
        commandList.add(createCommand(ex6));

        IStatement ex8 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)), new CompoundStatement(
                new HeapAllocationStatement("v", new ConstantExpression(20)), new CompoundStatement(new HeapAllocationStatement("a", new ConstantExpression(22)),
                new CompoundStatement(new HeapWritingStatement("a", new ConstantExpression(30)), new CompoundStatement(new PrintStatement(new VariableExpression("a")), new PrintStatement(new HeapReadingExpression("a")))))));
        commandList.add(createCommand(ex8));

        IStatement ex9 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)), new CompoundStatement(new HeapAllocationStatement("v", new ConstantExpression(20)),
                new CompoundStatement(new HeapAllocationStatement("a", new ConstantExpression(22)), new CompoundStatement(new HeapWritingStatement("a", new ConstantExpression(30)),
                        new CompoundStatement(new PrintStatement(new VariableExpression("a")), new CompoundStatement(new PrintStatement(new HeapReadingExpression("a")), new AssignStatement("a", new ConstantExpression(0))))))));
        commandList.add(createCommand(ex9));

        IStatement ex10 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(6)),
                new CompoundStatement(new WhileStatement(
                        new ArithmeticExpression('-', new VariableExpression("v"), new ConstantExpression(4)),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                new AssignStatement("v",
                                        new ArithmeticExpression('-', new VariableExpression("v"), new ConstantExpression(1))))), new PrintStatement(new VariableExpression("v"))));
        commandList.add(createCommand(ex10));

        IStatement ex11 = new CompoundStatement(new CompoundStatement(new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)), new HeapAllocationStatement("v", new ConstantExpression(20))),
                new CompoundStatement(new HeapAllocationStatement("a", new ConstantExpression(22)), new HeapWritingStatement("a", new ConstantExpression(30)))), new CompoundStatement(new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                new PrintStatement(new HeapReadingExpression("a"))),
                new CompoundStatement(new AssignStatement("a", new ConstantExpression(0)), new PrintStatement(new HeapReadingExpression("a")))));
        commandList.add(createCommand(ex11));

        IStatement ex12 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new HeapAllocationStatement("a", new ConstantExpression(22)),
                        new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWritingStatement("a", new ConstantExpression(30)),
                                new CompoundStatement(new AssignStatement("v", new ConstantExpression(32)),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new HeapReadingExpression("a")))))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReadingExpression("a"))))));
        commandList.add(createCommand(ex12));

        IStatement ex13 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new HeapAllocationStatement("a", new ConstantExpression(22)),
                        new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWritingStatement("a", new ConstantExpression(30)),
                                new CompoundStatement(new AssignStatement("v", new ConstantExpression(32)),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new CompoundStatement(new ForkStatement(new PrintStatement(new VariableExpression("v"))),
                                                        new PrintStatement(new HeapReadingExpression("a"))))))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReadingExpression("a"))))));
        commandList.add(createCommand(ex13));

        IStatement ex14 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(20)),
                new CompoundStatement(
                        new ForStatement("v",
                                new ConstantExpression(0), new ConstantExpression(3),
                                new ArithmeticExpression('+', new VariableExpression("v"), new ConstantExpression(1)),
                                new ForkStatement(new CompoundStatement(
                                        new PrintStatement(new VariableExpression("v")),
                                        new AssignStatement("v", new ArithmeticExpression('+', new VariableExpression("v"), new ConstantExpression(1)))))),
                        new PrintStatement(new ArithmeticExpression('*', new VariableExpression("v"), new ConstantExpression(10)))));
        commandList.add(createCommand(ex14));

        IStatement ex15 = new NewSemaphoreStatement("v", new ConstantExpression(10));
        commandList.add(createCommand(ex15));

        IStatement ex16 = new AssignStatement("v", new ArithmeticExpression('/', new ConstantExpression(5), new ConstantExpression(0)));
        commandList.add(createCommand(ex16));

        IStatement ex17 = new CompoundStatement(new NewSemaphoreStatement("cnt", new ConstantExpression(1)), new AcquireStatement("cnt"));
        commandList.add(createCommand(ex17));

        IStatement firstFork = new ForkStatement(new CompoundStatement(new AcquireStatement("cnt"),
                new CompoundStatement(new HeapWritingStatement("v", new ArithmeticExpression('*', new HeapReadingExpression("v"), new ConstantExpression(10))),
                        new CompoundStatement(new PrintStatement(new HeapReadingExpression("v")), new ReleaseStatement("cnt")))));

        IStatement secondFork = new ForkStatement(new CompoundStatement(new AcquireStatement("cnt"),
                new CompoundStatement(new HeapWritingStatement("v", new ArithmeticExpression('*', new HeapReadingExpression("v"), new ConstantExpression(10))),
                        new CompoundStatement(new HeapWritingStatement("v", new ArithmeticExpression('*', new HeapReadingExpression("v"), new ConstantExpression(2))),
                                new CompoundStatement(new PrintStatement(new HeapReadingExpression("v")), new ReleaseStatement("cnt"))))));

        IStatement ex18 = new CompoundStatement(new HeapAllocationStatement("v", new ConstantExpression(1)),
                new CompoundStatement(new NewSemaphoreStatement("cnt", new HeapReadingExpression("v")),
                        new CompoundStatement(firstFork,
                                new CompoundStatement(secondFork,
                                        new CompoundStatement(new AcquireStatement("cnt"),
                                                new CompoundStatement(new PrintStatement(new ArithmeticExpression('-', new HeapReadingExpression("v"), new ConstantExpression(1))),
                                                        new ReleaseStatement("cnt")))))));
        commandList.add(createCommand(ex18));

        IStatement testb = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new ForkStatement(
                        new CompoundStatement(
                                new AssignStatement("v", new ArithmeticExpression('-', new VariableExpression("v"), new ConstantExpression(1))),
                                new CompoundStatement(new AssignStatement("v", new ArithmeticExpression('-', new VariableExpression("v"), new ConstantExpression(1))),
                                        new PrintStatement(new VariableExpression("v"))))),
                        new CompoundStatement(new SleepStatement(10), new PrintStatement(new ArithmeticExpression('*', new VariableExpression("v"), new ConstantExpression(10))))));
        commandList.add(createCommand(testb));


        IStatement testa = new CompoundStatement(new AssignStatement("v", new ConstantExpression(2)),
                new CompoundStatement(new AssignStatement("w", new ConstantExpression(5)),
                        new CompoundStatement(new CallStatement("sum", Arrays.asList(new ArithmeticExpression('*', new VariableExpression("v"), new ConstantExpression(10)), new VariableExpression("w"))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new CompoundStatement(
                                                new ForkStatement(new CallStatement("product", Arrays.asList(new VariableExpression("v"), new VariableExpression("w")))),
                                                new ForkStatement(new CallStatement("sum", Arrays.asList(new VariableExpression("v"), new VariableExpression("w"))))
                                        )))));
        commandList.add(createCommand(testa));
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

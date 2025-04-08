package testingframework;

import testingframework.printers.ConsolePrinter;
import testingframework.printers.FilePrinter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        new ExtendedTestAutomationRunner(
                List.of(ForExample.class),
               new HashSet<>(Set.of(new ConsolePrinter(), new FilePrinter("test_results.md")))
                ).run();
    }
}

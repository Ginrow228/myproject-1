package testingframework;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        new TestAutomationRunner(List.of(ForExample.class)).run();
    }
}

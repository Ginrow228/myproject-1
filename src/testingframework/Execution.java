package testingframework;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class representing execution of scenarios of a particular testing class (aka class with testing scenario).
 */
public record Execution(
        Class<?> testingClass,
        List<ExecutionItem<?>> executions,
        LocalDateTime startedAt,
        LocalDateTime executedAt
) {
}

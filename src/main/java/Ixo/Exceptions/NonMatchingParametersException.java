package Ixo.Exceptions;

/**
 * Exception for task creation to prevent adding a task with
 * incorrect number of parameters as provided by the user.
 */

public class NonMatchingParametersException extends RuntimeException {

    public String errorMessage;

    public NonMatchingParametersException(boolean more) {
        errorMessage = more ? "You provided too many details for this task." : "You did not provide sufficient details for this task.";
    }
}

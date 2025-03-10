public class NonMatchingParametersException extends RuntimeException {

    String errorMessage;

    public NonMatchingParametersException(boolean more) {
        errorMessage = more ? "You provided too many details for this task." : "You did not provide sufficient details for this task.";
    }
}

public class DeleteTaskException extends RuntimeException {

    String errorMessage;

    public DeleteTaskException() {
    }

    public DeleteTaskException(int deleteIndex) {
        errorMessage = (deleteIndex <= 0) ? "You cannot enter a number less than or equal to zero" : "That number exceeds the number of tasks";
    }
}

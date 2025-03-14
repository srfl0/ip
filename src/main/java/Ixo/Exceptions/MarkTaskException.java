package Ixo.Exceptions;

/**
 * Exception exclusively for mark task feature
 * Provides reactive response based on whether a redundant action
 * is done (i.e. attempting mark an already marked task.
 */

public class MarkTaskException extends RuntimeException {

    public String errorMessage;

    public MarkTaskException(String marked) {
        this.errorMessage = (marked.equals("mark")) ? "This task had already been marked" : "This task was not marked";
    }

    public MarkTaskException(int markIndex) {
        this.errorMessage = (markIndex <= 0) ? "You cannot enter a number less than or equal to zero" : "That number exceeds the number of tasks";
    }
}

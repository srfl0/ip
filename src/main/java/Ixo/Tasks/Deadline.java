package Ixo.Tasks;

/**
 * Subclass of Task that contains the 'by' attribute,
 * for date which the task should be completed by, and
 * the relevant identity 'D'
 */

public final class Deadline extends Task {
    private final String by;

    public Deadline(String description, String dueDate) {
        super("D", description);
        this.by = dueDate.split("by")[1].trim();
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    public String getBy() {
        return by;
    }
}
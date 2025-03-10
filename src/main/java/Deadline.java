public final class Deadline extends Task {
    private final String by;

    public Deadline(String description, String dueDate) {
        super("D", description);
        this.by = dueDate.contains("by") ? dueDate.split("by")[1].trim() : dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    public String getBy() {
        return by;
    }
}
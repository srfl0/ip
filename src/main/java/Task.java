public sealed class Task permits Event, Deadline, ToDo {
    protected String description;
    protected boolean isDone;
    protected String identity;

    // ✅ Accept identity in the constructor
    public Task(String identity, String description) {
        this.identity = identity;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + identity + "] [" + getStatusIcon() + "] " + description;
    }
}
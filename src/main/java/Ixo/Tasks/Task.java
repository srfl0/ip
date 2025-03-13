package Ixo.Tasks;

public sealed class Task permits Event, Deadline, ToDo {
    protected String description;
    protected boolean isDone;
    protected String identity;

    public Task(String identity, String description) {
        this.identity = identity;
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getIdentity() {
        return identity;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + identity + "] [" + getStatusIcon() + "] " + description;
    }
}
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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

    public boolean isCorrectParameters(String content, String toCheck) {
        String[] lengthCheck = content.split(toCheck);
        return lengthCheck.length == 2; //
    }

    @Override
    public String toString() {
        return "[" + identity + "] [" + getStatusIcon() + "] " + description;
    }
}
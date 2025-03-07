public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        identity = 'D';
        this.by = by.split("by")[1].trim();
    }

    public String toString(){
        return "[" + identity + "]" + super.toString() + " (by:" + by + ")";
    }
}


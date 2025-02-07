public class Deadline extends Task {

    protected String by;

    public Deadline(String[] content){
        super(content[0]);
        identity = 'D';
        this.by = content[1];
    }

    public String toString(){
        return "[" + identity + "]" + super.toString() + " (by:" + by + ")";
    }
}


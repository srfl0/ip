public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String description, String duration) {
        super(description);
        this.from = duration.split("/to")[0];
        this.to = duration.split("/to")[1];
        identity = 'E';
    }

    public String toString(){
        return "[" + identity + "]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }

}

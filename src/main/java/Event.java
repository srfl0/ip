public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from.split("from")[1].trim();
        this.to = to.split("to")[1].trim();
        identity = 'E';
    }

    public String toString(){
        return "[" + identity + "]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }

}

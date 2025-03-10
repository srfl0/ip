public final class Event extends Task {

    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super("E", description); // ✅ Pass identity to Task

        // ✅ Parse "from" and "to" inside Task or here in Event
        this.from = from.contains("from") ? from.split("from")[1].trim() : from;
        this.to = to.contains("to") ? to.split("to")[1].trim() : to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
package Ixo.Tasks;

import Ixo.Exceptions.NonMatchingParametersException;

public final class Event extends Task {

    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super("E", description);

        this.from = from.contains("from") ? from.split("from")[1].trim() : from;
        if (this.from.equals(from)) {throw new NonMatchingParametersException(false);}

        this.to = to.contains("to") ? to.split("to")[1].trim() : to;
        if (this.to.equals(to)) {throw new NonMatchingParametersException(false);}
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
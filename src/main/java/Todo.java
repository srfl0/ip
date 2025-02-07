public class Todo extends Task {

    public Todo(String content) {
        super(content);
        identity = 'T';
    }

    public String toString() {
        return "[" + identity + "]" + super.toString();
    }

}
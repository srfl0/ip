public class ToDo extends Task {

    public ToDo(String content) {
        super(content);
        identity = 'T';
    }

    public String toString() {
        return "[" + identity + "]" + super.toString();
    }

}
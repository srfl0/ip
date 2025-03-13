package Ixo.Tasks;

public final class ToDo extends Task {
    public ToDo(String description) {
        super("T", description); // ✅ Identity handled in superclass
    }
}
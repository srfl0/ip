package Ixo.Ui;

public class ShowMenu implements FlatString{

    public ShowMenu(String menuType) {
        switch (menuType) {
        case "apps":
            System.out.println("1. Task List");
            System.out.println("2. Exit (bye)");
            break;

        case "list":
            System.out.println("Sure! These are the commands you can run, and the associated commands to enter");
            System.out.println(SEPARATOR + "\n");
            System.out.println("1. View list of Tasks (list)");
            System.out.println("2. Add To-Do (todo)");
            System.out.println("3. Add Deadline (deadline)");
            System.out.println("4. Add Event (event)");
            System.out.println("5. Delete Tasks (task)");
            System.out.println("6. Mark task (mark)");
            System.out.println("7. Unmark task (unmark)");
            System.out.println("8. Exit (bye)");
            break;
        }
    }
}

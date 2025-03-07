import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;


public class Ixo {

    public final static String SEPARATOR = "____________________________________________________";
    public final static int TASK_CONTENT_COUNT = 2;
    public final static int EXPECTED_DEADLINE_TASK_CONTENT_COUNT = 2;
    public final static int EXPECTED_EVENT_TASK_CONTENT_COUNT = 3;


    public static void menu(String menuType) {
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
            System.out.println("5. Delete Task (task)");
            System.out.println("6. Mark task (mark)");
            System.out.println("7. Unmark task (unmark)");
            System.out.println("8. Exit (bye)");
            break;
        }

    }

    public static void taskList() {

        Scanner lineScan;
        ArrayList<Task> taskStore = new ArrayList<>();
        taskStore.add(null); //dummy task to pad index to 1-based
        String cmd;
        String[] inputLine;
        String[] content;

        Ixo.menu("list");

        while (true) {
            System.out.println(SEPARATOR);
            lineScan = new Scanner(System.in);
            System.out.print("");
            inputLine = lineScan.nextLine().split(" ", TASK_CONTENT_COUNT);
            cmd = inputLine[0].toLowerCase();

            switch (cmd) {
            case "list":
            case "1":
                try {
                    if (taskStore.size() == 1) { //taskStore contains only the dummy task
                        throw new IndexOutOfBoundsException();
                    }
                    System.out.println("Here's your list of tasks: ");
                    int listIndex = 1;
                    for (Task item : taskStore.subList(1, taskStore.size())) { // excludes the dummy task while showing list
                        System.out.println(listIndex++ + ": " + item);
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println("Your task list is empty");
                }
                break;

            case "todo":
            case "2":
                try {
                    ToDo newToDo = new ToDo(inputLine[1].trim());
                    if (newToDo.description.isEmpty()){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    taskStore.add(newToDo);
                    addTaskText(taskStore, "todo");
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You cannot enter an empty to-do.");
                }
                break;

            case "deadline":
            case "3":
                try {
                    content = inputLine[1].split("/", EXPECTED_DEADLINE_TASK_CONTENT_COUNT);

                    String desc = content[0].trim();
                    String by = content[1].trim();

                    if (content.length != EXPECTED_DEADLINE_TASK_CONTENT_COUNT || by.isEmpty()) {
                        throw new NonMatchingParametersException(content.length > EXPECTED_DEADLINE_TASK_CONTENT_COUNT);
                    }

                    Deadline newDeadline = new Deadline(desc,by);
                    taskStore.add(newDeadline);
                    addTaskText(taskStore, "deadline");
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You cannot enter an empty task");
                }
                catch (NonMatchingParametersException e) {
                    System.out.println(e.errorMessage);
                }
                break;

            case "event":
            case "4":
                try {
                    content = inputLine[1].split("/", EXPECTED_EVENT_TASK_CONTENT_COUNT);
                    String desc = content[0].trim();
                    String from = content[1].trim();
                    String to = content[2].trim();

                    if (content.length != EXPECTED_EVENT_TASK_CONTENT_COUNT || from.isEmpty() || to.isEmpty()) {
                        throw new NonMatchingParametersException(content.length > EXPECTED_EVENT_TASK_CONTENT_COUNT);
                    }

                    Event newEvent = new Event(desc, from, to);
                    taskStore.add(newEvent);
                    addTaskText(taskStore, "event");
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You cannot enter an empty task");
                }
                catch (NonMatchingParametersException e) {
                    System.out.println(e.errorMessage);
                }
                break;

            case "delete":
            case "5":
                try{
                    int deleteIndex = Integer.parseInt(inputLine[1]);
                    if (deleteIndex < 0 || deleteIndex > taskStore.size()) {
                        throw new DeleteTaskException();
                    }
                    System.out.println("Task deleted: " + taskStore.get(deleteIndex));
                    System.out.println(SEPARATOR);
                    taskStore.remove(deleteIndex);

                }
                catch (NumberFormatException e) {
                    System.out.println("That is not a valid number");
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("There are no tasks to delete");
                }
                catch (DeleteTaskException e) {
                    System.out.println(e.errorMessage);
                }
                break;

            case "mark":
            case "6":
            case "unmark":
            case "7":
                cmd = cmd.equals("6") ? "mark" : cmd.equals("7") ? "unmark" : cmd;
                try {
                    int markIndex = Integer.parseInt(inputLine[1]);
                    if (markIndex >= taskStore.size() || markIndex <= 0) {
                        throw new MarkTaskException(markIndex);
                    }
                    Task taskToMark = taskStore.get(markIndex);

                    if (cmd.equals("mark") == taskToMark.isDone) { //if newStatus does not match up with the correct type of command, throw exception
                        throw new MarkTaskException(cmd);
                    }

                    taskToMark.isDone = !taskToMark.isDone;

                    if (taskToMark.isDone) {
                        System.out.println("Well done! task " + taskToMark.description + " has been marked as done.");
                    }
                    else {
                        System.out.println("The task " + taskToMark.description + " has been unmarked.");
                    }

                }
                catch (NumberFormatException e) {
                    System.out.println("That is not a valid number");
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("There are no tasks to " + cmd);
                }
                catch (MarkTaskException e) {
                    System.out.println(e.errorMessage);
                }

                break;

            case "bye":
            case "8":
                return;

            case "test":
                System.out.println(Arrays.toString(inputLine));
                break;

            default:
                System.out.println("Unknown command. Type 'bye' to exit");
                break;
            }
        }
    }

    private static void addTaskText(ArrayList<Task> taskStore, String taskType) {
        System.out.println("Okay, I have added this " + taskType + ":");
        System.out.println("\t" + taskStore.getLast());
        int storeSize = taskStore.size() - 1; //correctly reflect number of tasks in the list after adding dummy task
        System.out.println("Now you have " + storeSize + " tasks in the list."); //
    }


    public static void command() {
        String cmd;
        Scanner cmdScan = new Scanner(System.in);
        cmd = cmdScan.nextLine();
        do {
            switch (cmd.toLowerCase()) {
            case "list":
            case "1":
                taskList();
                break;

            case "bye":
            case "2":
                return;

            default:
                System.out.println("Invalid command. Type 'bye' by itself to make me stop.");
                cmd = cmdScan.nextLine();
                continue;
            }
            cmdScan = new Scanner(System.in);
            System.out.println("Any other tasks you wish me to do? Type 'no' to exit");
            Ixo.menu("apps");
            cmd = cmdScan.nextLine();
        } while (!cmd.equalsIgnoreCase("no"));
    }

    public static void main(String[] args) {

        System.out.println(SEPARATOR);
        System.out.println("Hello! I'm Ixo!");
        System.out.println("What can I do for you?");
        Ixo.menu("apps");
        System.out.println(SEPARATOR);

        Ixo.command();

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);

    }
}
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ixo {

    public final static String SEPARATOR = "____________________________________________________";
    public final static int TASK_CONTENT_COUNT = 2;
    public final static int EXPECTED_DEADLINE_TASK_CONTENT_COUNT = 2;
    public final static int EXPECTED_EVENT_TASK_CONTENT_COUNT = 3;
    public final static String filePath = "data/tasks.txt";
    public static Scanner inpScan = new Scanner(System.in);

    public static void showMenu(String menuType) {
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

    public static void taskListInit(File f, ArrayList<Task> tasks) {
        try {
            Scanner fileScan = new Scanner(f);
            String lineReader;
            String[] stringProcessor;
            while (fileScan.hasNextLine()) {
                lineReader = fileScan.nextLine();
                System.out.println(lineReader);
                stringProcessor = lineReader.split(" \\| ");
                try {
                    switch (stringProcessor[0]) {
                    case "T":
                        tasks.add(new ToDo(stringProcessor[2]));
                        break;
                    case "D":
                        tasks.add(new Deadline(stringProcessor[2], stringProcessor[3]));
                        break;
                    case "E":
                        tasks.add(new Event(stringProcessor[2], stringProcessor[3], stringProcessor[4]));
                        break;
                    default:
                        tasks.add(null);
                        break;
                    }
                    tasks.getLast().isDone = (stringProcessor[1].equals("X"));
                }
                catch (NullPointerException _) {
                }
            }
            fileScan.close();
        }
        catch (IOException _){
        }
    }

    public static void taskList() {

        Scanner lineScan;
        File f = new File(filePath);
        File parentDir = f.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); // Creates all missing parent directories
        }
        try {
            f.createNewFile();
        }
        catch (IOException _){

        }
        ArrayList<Task> taskStore = new ArrayList<>();
        taskStore.add(null); // dummy task to pad index
        taskListInit(f,taskStore);

        String cmd;
        String[] inputLine;
        String[] content;

        Ixo.showMenu("list");

        while (true) {
            System.out.println(SEPARATOR);
            lineScan = inpScan;
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

                    if (cmd.equals("mark") == taskToMark.isDone) { //if
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
                String taskList = getWriteString(taskStore);

                try {
                    FileWriter fw = new FileWriter(f);
                    fw.write(taskList);
                    fw.close();
                }
                catch (IOException e) {
                    System.out.println("Something went wrong");
                }
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

    private static String getWriteString(ArrayList<Task> taskStore) {
        StringBuilder taskList = new StringBuilder();
        for (Task task : taskStore) {
            try {
                switch (task) {
                case ToDo t:
                    taskList.append(t.identity)
                            .append(" | ")
                            .append(t.getStatusIcon())
                            .append(" | ")
                            .append(t.description);
                    break;

                case Deadline d:
                    taskList.append(d.identity)
                            .append(" | ")
                            .append(d.getStatusIcon())
                            .append(" | ")
                            .append(d.description)
                            .append(" | ")
                            .append(d.getBy());
                    break;

                case Event e:
                    taskList.append(e.identity)
                            .append(" | ")
                            .append(e.getStatusIcon())
                            .append(" | ")
                            .append(e.description)
                            .append(" | ")
                            .append(e.getFrom())
                            .append(" | ")
                            .append(e.getTo());
                    break;

                default:
                    break;
                }
            }
            catch (NullPointerException e) {
                System.out.println("Starting save...");
                continue;
            }
            taskList.append("\n");
        }
        return taskList.toString();
    }

    private static void addTaskText(ArrayList<Task> taskStore, String taskType) {
        System.out.println("Okay, I have added this " + taskType + ":");
        System.out.println("\t" + taskStore.getLast());
        int storeSize = taskStore.size() - 1; //correctly reflect number of tasks in the list after adding dummy task
        System.out.println("Now you have " + storeSize + " task" + ((storeSize == 1) ? " " : "s ") + "in the list."); //
    }

    public static void parseCommand() {
        Scanner cmdScan = new Scanner(System.in);

        while (true) {

            Ixo.showMenu("apps");

            String cmd = cmdScan.nextLine().toLowerCase();

            switch (cmd) {
            case "list":
            case "1":
                taskList();
                System.out.println("Any other tasks you wish me to do? Type 'no' to exit");
                break;

            case "bye":
            case "2":
            case "no":
                return; // Exit the method immediately

            default:
                System.out.println("Invalid parseCommand. Type 'bye' by itself to make me stop.");
                break;
            }
        }
    }

    public static void main(String[] args) {

        System.out.println(SEPARATOR);
        System.out.println("Hello! I'm Ixo!");
        System.out.println("What can I do for you?");
        System.out.println(SEPARATOR);

        Ixo.parseCommand();

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);

    }
}
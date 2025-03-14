package Ixo.Parser;

import Ixo.Exceptions.*;
import Ixo.Tasks.Task;
import Ixo.Tasks.Deadline;
import Ixo.Tasks.Event;
import Ixo.Tasks.ToDo;
import Ixo.Ui.FlatString;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The Ui class handles user interactions and manages the task list.
 * It provides methods to parse user commands, display menus, and update tasks.
 */

public class Ui implements FlatString {

    protected ArrayList<Task> taskStore;
    private final String filePath;

    public Ui(ArrayList<Task> tasks, String filePath){
        this.taskStore = tasks;
        this.filePath = filePath;
    }

    /**
     * Main method for handling the task list program.
     * It continuously takes user input and processes commands.
     */

    public void taskList() {

        Scanner lineScan;
        File f = new File(filePath);
        String cmd;
        String[] inputLine;
        String[] content;

        showMenu("list");

        while (true) {
            System.out.println(FlatString.SEPARATOR);
            lineScan = inpScan;
            System.out.print("");
            inputLine = lineScan.nextLine().split(" ", FlatString.TASK_CONTENT_COUNT);
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
                    if (newToDo.getDescription().isEmpty()) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    taskStore.add(newToDo);
                    addTaskText(taskStore, "todo");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You cannot enter an empty to-do.");
                }
                break;

            case "deadline":
            case "3":
                try {
                    content = inputLine[1].split("/", FlatString.EXPECTED_DEADLINE_TASK_CONTENT_COUNT);

                    String desc = content[0].trim();
                    String by = content[1].trim();

                    if (content.length != FlatString.EXPECTED_DEADLINE_TASK_CONTENT_COUNT || by.isEmpty()) {
                        throw new NonMatchingParametersException(content.length > FlatString.EXPECTED_DEADLINE_TASK_CONTENT_COUNT);
                    }

                    Deadline newDeadline = new Deadline(desc, by);
                    taskStore.add(newDeadline);
                    addTaskText(taskStore, "deadline");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("There are missing fields for a deadline.");
                } catch (NonMatchingParametersException e) {
                    System.out.println(e.errorMessage);
                }
                break;

            case "event":
            case "4":
                try {
                    content = inputLine[1].split("/", FlatString.EXPECTED_EVENT_TASK_CONTENT_COUNT);
                    String desc = content[0].trim();
                    String from = content[1].trim();
                    String to = content[2].trim();

                    if (content.length != FlatString.EXPECTED_EVENT_TASK_CONTENT_COUNT || from.isEmpty() || to.isEmpty()) {
                        throw new NonMatchingParametersException(content.length > FlatString.EXPECTED_EVENT_TASK_CONTENT_COUNT);
                    }

                    Event newEvent = new Event(desc, from, to);
                    taskStore.add(newEvent);
                    addTaskText(taskStore, "event");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("There are missing fields for an event");
                } catch (NonMatchingParametersException e) {
                    System.out.println(e.errorMessage);
                }
                break;

            case "delete":
            case "5":
                try {
                    int deleteIndex = Integer.parseInt(inputLine[1]);
                    if (deleteIndex < 0 || deleteIndex > taskStore.size()) {
                        throw new DeleteTaskException();
                    }
                    System.out.println("Task deleted: " + taskStore.get(deleteIndex));
                    taskStore.remove(deleteIndex);

                } catch (NumberFormatException e) {
                    System.out.println("That is not a valid number");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("There are no tasks to delete");
                } catch (DeleteTaskException e) {
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

                    if (cmd.equals("mark") == taskToMark.isDone()) { //if
                        throw new MarkTaskException(cmd);
                    }

                    taskToMark.setDone(!taskToMark.isDone());

                    if (taskToMark.isDone()) {
                        System.out.println("Well done! task " + taskToMark.getDescription() + " has been marked as done.");
                    } else {
                        System.out.println("The task " + taskToMark.getDescription() + " has been unmarked.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("That is not a valid number");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("There are no tasks to " + cmd);
                } catch (MarkTaskException e) {
                    System.out.println(e.errorMessage);
                }

                break;

            case "find":
            case "8":
                try {
                    if (taskStore.isEmpty()) {
                        throw new ArrayIndexOutOfBoundsException();
                    }

                    String find = inputLine[1];

                    ArrayList<Task> foundList = new ArrayList<>();
                    ArrayList<java.lang.Integer> index = new ArrayList<>();

                    int counter = 0;

                    for (Task task : taskStore) {
                        if (task != null && task.getDescription().contains(find)) {
                            index.add(counter);
                            foundList.add(task);
                        }
                        counter++;
                    }

                    if (foundList.isEmpty()) {
                        throw new FindException();
                    }

                    System.out.println("These are the tasks matching your search key");

                    for(int i = 0; i < foundList.size(); i++) {
                        System.out.println(index.get(i) + ": " + foundList.get(i).toString());
                    }

                }catch(FindException e) {
                    System.out.println("There are no tasks matching your search key");
                }catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println("There are no tasks to find");
                }

                break;


            case "bye":
            case "9":
                String taskList = getWriteString(taskStore);

                try {
                    FileWriter fw = new FileWriter(f);
                    fw.write(taskList);
                    fw.close();
                } catch (IOException e) {
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

    /**
     * Parses user commands and directs to the appropriate program.
     */

    public void parseCommand() {

        Scanner cmdScan = new Scanner(System.in);

        while (true) {

            showMenu("apps");

            System.out.println(FlatString.SEPARATOR);

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

    /**
     * Displays a message confirming that a task has been successfully added to the task list.
     *
     * @param taskStore The complete task list.
     * @param taskType  The type of task being added (e.g., "todo", "deadline", "event").
     */

    public static void addTaskText(ArrayList<Task> taskStore, String taskType) {
        System.out.println("Okay, I have added this " + taskType + ":");
        System.out.println("\t" + taskStore.get(taskStore.size()-1));
        int storeSize = taskStore.size() - 1; //correctly reflect number of tasks in the list after adding dummy task
        System.out.println("Now you have " + storeSize + " task" + ((storeSize == 1) ? " " : "s ") + "in the list."); //
    }

    /**
     * Extracts the contents of the task list and formats them into a structured string for file storage.
     *
     * @param taskStore The complete task list.
     * @return A formatted string representation of the task list, where each task is stored as a line in the format:
     *         "[Task Type] | [Completion Status] | [Description] | [Additional Details]".
     */

    public static String getWriteString(ArrayList<Task> taskStore) {
        StringBuilder taskList = new StringBuilder();
        for (Task task : taskStore) {
            try {
                switch(task.getIdentity()){
                case "T":
                    taskList.append(task.getIdentity())
                            .append(" | ")
                            .append(task.getStatusIcon())
                            .append(" | ")
                            .append(task.getDescription());
                    break;

                case "D":
                    Deadline d = (Deadline) task;
                    taskList.append(d.getIdentity())
                            .append(" | ")
                            .append(d.getStatusIcon())
                            .append(" | ")
                            .append(d.getDescription())
                            .append(" | ")
                            .append(d.getBy());
                    break;

                case "E":
                    Event e = (Event) task;
                    taskList.append(e.getIdentity())
                            .append(" | ")
                            .append(e.getStatusIcon())
                            .append(" | ")
                            .append(e.getDescription())
                            .append(" | ")
                            .append(e.getFrom())
                            .append(" | ")
                            .append(e.getTo());
                    break;

                default:
                    break;
                }
            } catch (NullPointerException e) {
                System.out.println("Starting save...");
                continue;
            }
            taskList.append("\n");
        }
        return taskList.toString();
    }

    /**
     * Starter method to the chatbot, provides user with a
     * field to input to select programs available to them
     */

    public void begin() {
        System.out.println(FlatString.SEPARATOR);
        System.out.println("Hello! I'm Ixo!");
        System.out.println("ooooooooooooooooo");
        for (int rowsTop = 1; rowsTop < 4; rowsTop++){
            for (int spacesFront = 0; spacesFront < rowsTop; spacesFront++) {
                System.out.print("  ");
            }
            System.out.print("o");

            for (int spacesMid = 0; spacesMid < (15 - (4*rowsTop)); spacesMid++) {
                System.out.print(" ");
            }

            System.out.println("o");

        }

        System.out.println("        o        ");

        for (int rowsBottom = 3; rowsBottom > 0; rowsBottom--){
            for (int spacesFront = 0; spacesFront < rowsBottom; spacesFront++) {
                System.out.print("  ");
            }
            System.out.print("o");

            for (int spacesMid = 0; spacesMid < (15 - 4*(rowsBottom)) ; spacesMid++) {
                System.out.print(" ");
            }

            System.out.print("o");

            for (int spacesBack = 0; spacesBack < rowsBottom; spacesBack++) {
                System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println("ooooooooooooooooo");


        System.out.println("What can I do for you?");

        parseCommand();

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(FlatString.SEPARATOR);
    }

    /**
     * Displays the available menu options to the user.
     *
     * @param menuType The type of menu to display ("apps" or "list").
     */

    public static void showMenu(String menuType) {

        System.out.println(FlatString.SEPARATOR);

        switch (menuType) {
        case "apps":
            System.out.println("1. Task List");
            System.out.println("2. Exit (bye)");
            break;

        case "list":
            System.out.println("Sure! These are the commands you can run, and the associated commands to enter");
            System.out.println("1. View list of Tasks (list)");
            System.out.println("2. Add To-Do (todo)");
            System.out.println("3. Add Deadline (deadline)");
            System.out.println("4. Add Event (event)");
            System.out.println("5. Delete Tasks (task)");
            System.out.println("6. Mark task (mark)");
            System.out.println("7. Unmark task (unmark)");
            System.out.println("8. Find task (find)");
            System.out.println("9. Exit (bye)");
            break;
        }
    }

}

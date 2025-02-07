import java.util.Scanner;


public class Ixo {

    public final static String SEPARATOR = "____________________________________________________";

    public static void menu() {
        System.out.println("1. Task List");
    }

    public static void taskList() {

        Scanner lineScan;
        Task[] taskStore = new Task[100]; //limit list to store only 100 items
        int storeIndex = 0;
        int taskToMark;
        String cmd;
        String inputLine;
        String[] content;


        System.out.println("I'll now take a note on what you typed. Type 'bye' by itself to make me stop.");

        while (true) {
            System.out.println(SEPARATOR);
            lineScan = new Scanner(System.in);
            System.out.print("");
            inputLine = lineScan.nextLine();
            cmd = inputLine.split(" ")[0];

            switch (cmd) {
            case "bye":
                return;
            case "list":
                System.out.println("Here's your list of tasks: ");
                int i = 1;
                for (Task item : taskStore) {
                    if (item == null) {
                        break;
                    }
                    System.out.println(i + ": " + item);
                    i++;
                }
                break;

            case "mark":
            case "unmark":
                taskToMark = Integer.parseInt(inputLine.split(" ")[1]) - 1; //-1 to match up user view to actual index access
                if (taskToMark >= storeIndex || taskToMark < 0) {
                    System.out.println("that task does not exist");
                    System.out.println(SEPARATOR);
                }
                boolean isMark = cmd.equals("mark");
                if (isMark) {
                    System.out.println("Well done! task " + taskStore[taskToMark] + " has been marked.");
                } else {
                    System.out.println("The task " + taskStore[taskToMark] + " has been unmarked.");
                }
                taskStore[taskToMark].isDone = isMark;
                break;

            case "todo":
                taskStore[storeIndex] = new Todo(inputLine.split("todo ")[1]);
                storeIndex++;
                System.out.println("Todo added, you have " + storeIndex + " task(s) on the list");
                break;

            case "deadline":
                content = inputLine.split("deadline ")[1].split("/by");
                taskStore[storeIndex] = new Deadline(content);
                storeIndex++;
                System.out.println("Deadline added, you have " + storeIndex + " task(s) on the list");
                break;

            case "event":
                content = inputLine.split("event ")[1].split("/from");
                String description = content[0];
                String duration = content[1];
                taskStore[storeIndex] = new Event(description, duration);
                storeIndex++;
                System.out.println("Event added, you have " + storeIndex + " task(s) on the list");
                break;

            default:
                lineScan.nextLine();
                System.out.println("Unknown command. Type 'bye' to exit");
                break;
            }
        }
    }


    public static void command() {
        String cmd;
        Scanner cmdScan = new Scanner(System.in);
        cmd = cmdScan.nextLine();
        do {
            switch (cmd.toLowerCase()) {
            case "task list":
            case "1":
                taskList();
                break;

            default:
                System.out.println("Invalid command. Type 'bye' by itself to make me stop.");
                cmd = cmdScan.nextLine();
                continue;
            }
            cmdScan = new Scanner(System.in);
            System.out.println("Any other tasks you wish me to do? Type 'no' to exit");
            Ixo.menu();
            cmd = cmdScan.nextLine();
        } while (!cmd.equalsIgnoreCase("no"));
    }

    public static void main(String[] args) {

        System.out.println(SEPARATOR);
        System.out.println("Hello! I'm Ixo!");
        System.out.println("What can I do for you?");
        Ixo.menu();
        System.out.println(SEPARATOR);

        Ixo.command();

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);

    }
}
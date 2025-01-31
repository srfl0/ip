import java.util.Scanner;

public class Ixo {

    public static String separator = "____________________________________________________";

    public static void echo() {
        System.out.println("I'll now repeat after you! Type 'bye' by itself to make me stop.");

        String[] line;
        Scanner echoScan;

        String space; //variable to determine if spacing is needed for purposes of echoing

        while (true) {
            echoScan = new Scanner(System.in);
            System.out.print("You said: ");
            line = echoScan.nextLine().split(" ");

            if (line[0].equals("bye")) {
                System.out.println("Okay, goodbye!");
                return;
            }

            System.out.println();

            System.out.print("I say: ");
            for (String word : line) {
                System.out.print(word);
                space = (word.equals(line[line.length - 1])) ? "" : " ";
                System.out.print(space);
            }
            System.out.println(separator);
        }

    }

    public static void add() {
        System.out.println("I'll now take a note on what you typed. Type 'bye' by itself to make me stop.");

        String[] line;
        Scanner addScan;

        String[] store = new String[50]; //limit list to store only 50 items
        int storeIndex = 0;
        String newline;

        String space; //variable to determine if spacing is needed for purposes of echoing

        while (true) {
            addScan = new Scanner(System.in);
            System.out.print("You said: ");
            newline = addScan.nextLine();
            line = newline.split(" ");

            switch (line[0]) {
            case "bye":
                return;

            case "list":
                int i = 1;
                for (String item : store) {
                    if (item == null) {
                        break;
                    }
                    System.out.println(i + ". " + item);
                    i++;
                }
                continue;
            }

            System.out.println();

            System.out.print("added: ");
            for (String word : line) {
                System.out.print(word);
                space = (word.equals(line[line.length - 1])) ? "" : " ";
                System.out.print(space);
            }
            store[storeIndex] = newline;
            storeIndex++;
            System.out.println();
            System.out.println(separator);
        }

    }

    public static void tasklist() {
        System.out.println("I'll now take a note on what you typed. Type 'bye' by itself to make me stop.");

        String[] line;
        Scanner addScan;

        Task[] store = new Task[50]; //limit list to store only 50 items
        int storeIndex = 0;
        String newline;

        String space; //variable to determine if spacing is needed for purposes of echoing

        while (true) {
            addScan = new Scanner(System.in);
            System.out.print("You said: ");
            newline = addScan.nextLine();
            Task newTask = new Task(newline);
            line = newline.split(" ");

            switch (line[0]) {
            case "bye":
                return;

            case "list":
                System.out.println("Here's your list of tasks: ");
                int i = 1;
                for (Task item : store) {
                    if (item == null) {
                        break;
                    }
                    System.out.println(i + ". [" + item.getStatusIcon() + "] " + item.description);
                    i++;
                }
                continue;

            case "mark":
                System.out.println("Well done! I've marked this task as done");
                Task itemToMark = store[Integer.parseInt(line[1])-1];
                itemToMark.isDone = true;
                System.out.println("[" + itemToMark.getStatusIcon() + "] " + itemToMark.description);
                System.out.println(separator);
                continue;

            case "unmark":
                System.out.println("Okay, I'll mark this task as not done");
                Task itemToUnmark = store[Integer.parseInt(line[1])-1];
                itemToUnmark.isDone = false;
                System.out.println("[" + itemToUnmark.getStatusIcon() + "] " + itemToUnmark.description);
                System.out.println(separator);
                continue;
            }

            System.out.println();

            System.out.print("added: ");
            for (String word : line) {
                System.out.print(word);
                space = (word.equals(line[line.length - 1])) ? "" : " ";
                System.out.print(space);
            }
            store[storeIndex] = newTask;
            storeIndex++;
            System.out.println();
            System.out.println(separator);
        }

    }

    public static void command() {
        String cmd;
        Scanner cmdScan = new Scanner(System.in);
        cmd = cmdScan.nextLine();
        do {
            switch (cmd.toLowerCase()) {
            case "echo":
            case "1":
                echo();
                break;

            case "add":
            case "2":
                add();
                break;

            case "tasking":
            case "3":
                tasklist();
                break;

            default:
                System.out.println("Invalid command. Type 'bye' by itself to make me stop.");
                continue;
            }
            cmdScan = new Scanner(System.in);
            System.out.println("Any other tasks you wish me to do? Type 'no' to exit");
            cmdScan.nextLine();
        } while (!cmd.equalsIgnoreCase("no"));
    }

    public static void main(String[] args) {

        System.out.println(separator);
        System.out.println("Hello! I'm Ixo!");
        System.out.println("What can I do for you?");
        System.out.println("1. Echo");
        System.out.println("2. Add");
        System.out.println("3. Tasking");
        System.out.println(separator);

        Ixo.command();

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);

    }
}
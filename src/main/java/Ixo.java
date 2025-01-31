import java.util.Scanner;

public class Ixo {

    public static String separator = "____________________________________________________________";

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
                    if (item == null){break;}
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

    public static void command() {
        String cmd;
        Scanner cmdScan = new Scanner(System.in);
        cmd = cmdScan.nextLine();
        switch (cmd.toLowerCase()) {
        case "echo":
            echo();
            break;

        case "add":
            add();
            break;

            
        }
    }

    public static void main(String[] args) {

        System.out.println(separator);
        System.out.println("Hello! I'm Ixo!");
        System.out.println("What can I do for you?");
        System.out.println("1. Echo");
        System.out.println("2. Add");
        System.out.println(separator);

        Ixo.command();

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);

    }
}
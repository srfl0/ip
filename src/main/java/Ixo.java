import java.util.Scanner;

public class Ixo {

    public static String sepa = "____________________________________________________________";

    public static void echo(){
        System.out.println("I'll now repeat after you! Type 'bye' by itself to make me stop.");

        String[] line;
        Scanner echoScan;
        String space;

        while (true) {
            echoScan = new Scanner(System.in);
            System.out.print("You said: ");
            line = echoScan.nextLine().split(" ");

            if (line[0].equals("bye")) {
                System.out.println("Okay, goodbye!");
                return;
            }

            System.out.print("I say: ");
            for (String word : line) {
                System.out.print(word);
                space = (word.equals(line[line.length - 1])) ? "" : " ";
                System.out.print(space);
            }
            System.out.println();
        }

    }

    public static void command(){
        String cmd;
        Scanner cmdScan = new Scanner(System.in);
        cmd = cmdScan.nextLine();
        if (cmd.equalsIgnoreCase("echo")){
            echo();
        }
    }

    public static void main(String[] args){

        System.out.println(sepa);
        System.out.println("Hello! I'm Ixo!");
        System.out.println("What can I do for you?");
        System.out.println("1. Echo");
        System.out.println(sepa);

        Ixo.command();

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(sepa);

    }
}
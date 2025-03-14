package Ixo.Storage;

import Ixo.Tasks.Deadline;
import Ixo.Tasks.Event;
import Ixo.Tasks.Task;
import Ixo.Tasks.ToDo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading of tasks from a text file and initializes the task list.
 */

public record Storage(String filePath) {

    /**
     * Loads tasks from the specified file and returns an ArrayList of Task objects.
     *
     * @return An ArrayList of Task objects if the file exists; otherwise, returns null.
     */


    public ArrayList<Task> load() {
        File f = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (f.exists()) {
            return loadProcess(f, tasks);
        }
        return null;

    }


    /**
     * Reads and processes a file to extract tasks and populate an ArrayList.
     *
     * @param file  The file containing stored task data.
     * @param tasks The list to store extracted tasks.
     * @return The populated ArrayList of Task objects.
     */

    public static ArrayList<Task> loadProcess(File file, ArrayList<Task> tasks) {
        try {
            Scanner fileScan = new Scanner(file);
            String lineReader;
            String[] stringProcessor;
            while (fileScan.hasNextLine()) {
                lineReader = fileScan.nextLine();
                stringProcessor = lineReader.split(" \\| ");
                try {
                    switch (stringProcessor[0]) {
                    case "T":
                        tasks.add(new ToDo(stringProcessor[2]));
                        break;
                    case "D":
                        tasks.add(new Deadline(stringProcessor[2], "by"+stringProcessor[3]));
                        break;
                    case "E":
                        tasks.add(new Event(stringProcessor[2], "from"+stringProcessor[3], "to"+stringProcessor[4]));
                        break;
                    default:
                        tasks.add(null);
                        break;
                    }
                    tasks.getLast().setDone((stringProcessor[1].equals("X")));
                } catch (NullPointerException _) {
                }
            }
            fileScan.close();
        } catch (IOException _) {
        }
        return tasks;
    }

}

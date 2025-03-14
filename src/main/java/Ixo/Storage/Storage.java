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
        File parentDir = f.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); // Creates all missing parent directories
        }
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.getMessage();
        }
        ArrayList<Task> tasks = new ArrayList<>();
        return loadProcess(f, tasks);
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
                stringProcessor = lineReader.split(" \\| ", 3);
                try {
                    switch (stringProcessor[0]) {
                    case "T":
                        tasks.add(new ToDo(stringProcessor[2]));
                        break;
                    case "D":
                        String[] deadlineContents = stringProcessor[2].split("/by", 2);
                        tasks.add(new Deadline(deadlineContents[0], "by"+ deadlineContents[1]));
                        break;
                    case "E":
                        String[] eventFrom = stringProcessor[2].split("/from", 2);
                        String[] eventTo = stringProcessor[2].split("/to", 2);
                        tasks.add(new Event(eventFrom[0], "from"+eventFrom[1], "to"+eventTo[1]));
                        break;
                    default:
                        tasks.add(null);
                        break;
                    }
                    tasks.get(tasks.size()-1).setDone((stringProcessor[1].equals("X"))); //-1 to account for last number in taskList
                } catch (ArrayIndexOutOfBoundsException e) {
                    fileScan.close();
                    return tasks;
                }
            }
            fileScan.close();
        } catch (IOException e) {
            e.getMessage();
        }
        return tasks;
    }

}

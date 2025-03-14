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
 * Handles initialization of array list from
 * text file
 * @param filePath file path of text file
 */

public record Storage(String filePath) {

    public ArrayList<Task> load() {
        File f = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (f.exists()) {
            return loadProcess(f, tasks);
        }
        return null;

    }

    public static ArrayList<Task> loadProcess(File f, ArrayList<Task> tasks) {
        try {
            Scanner fileScan = new Scanner(f);
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

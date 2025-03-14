package Ixo.Tasks;

import java.util.ArrayList;

/**
 * Handles creation of ArrayList for use in task list program
 */

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        tasks.add(0,null); // dummy task to pad index
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

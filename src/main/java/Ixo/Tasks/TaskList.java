package Ixo.Tasks;

import java.util.ArrayList;

/**
 * Handles creation of ArrayList for use in task list program
 */

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks){
        if (tasks == null){
            throw new NullPointerException();
        }
        tasks.addFirst(null); //dummy task addition
        this.tasks = tasks;

    }

    public TaskList(){
        tasks.add(null); // dummy task to pad index
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

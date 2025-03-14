package Ixo.Top;

import Ixo.Parser.Ui;
import Ixo.Storage.Storage;
import Ixo.Tasks.TaskList;

/**
 * Top-level for Ixo chatbot program
 */

public class Ixo implements FlatString{

    Storage storage;
    TaskList taskList;
    Ui ui;

    public Ixo(String filePath){
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
        ui = new Ui(taskList.getTasks(),filePath);
    }

    public void run(){
        ui.begin();
    }

    public static void main(String[] args) {
        new Ixo("data/tasks.txt").run();
    }

}
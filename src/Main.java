import com.classes.Task;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<Task>();

        taskList.add(new Task("Study", "Study Java every day"));
        taskList.add(new Task("Workout", "Go to the gym for 1 hour"));
        taskList.add(new Task("Read", "Read 20 pages of a book"));
        taskList.add(new Task("Code", "Work on personal project"));
        taskList.add(new Task("Meditate", "Meditate for 10 minutes"));
        taskList.add(new Task("Clean", "Tidy up the workspace"));
        taskList.add(new Task("Emails", "Check and respond to emails"));
        taskList.add(new Task("Groceries", "Buy ingredients for dinner"));
        taskList.add(new Task("Learn SQL", "Practice SQL queries"));
        taskList.add(new Task("Rest", "Sleep at least 7 hours"));

        System.out.println("Quantidade de tarefas a fazer: " + getNotCompletedTaskQuant(taskList));
        System.out.println("Quantidade de tarefas feitas: " + getCompletedTaskQuant(taskList));

        for  (Task task : taskList){
            task.getInfo();
        }

//        System.out.println("TASKS TO-DO");
//        System.out.println("Quantidade de tarefas a fazer: " + getNotCompletedTaskQuant(taskList));
//        System.out.println("Quantidade de tarefas feitas: " + getCompletedTaskQuant(taskList));
//
//        for (Task task : taskList){
//            if (!task.getStatus().equals("Feito")){
//                System.out.println("Task: " + task.getName());
//            }
//        }
    }

    public int getLength(ArrayList<Task> taskList){
        int length = 0;

        for (Task task : taskList){
            length++;
        }

        return length;
    }

    public static int getCompletedTaskQuant(ArrayList<Task> taskList){
        int completedLength = 0;
        for (Task task : taskList){
            if (task.getStatus().equals("Feito")){
                completedLength++;
            }
        }
        return completedLength;
    }

    public static int getNotCompletedTaskQuant(ArrayList<Task> taskList){
        int notCompletedLength = 0;
        for (Task task : taskList){
            if (!task.getStatus().equals("Feito")){
                notCompletedLength++;
            }
        }
        return  notCompletedLength;
    }
}